package com.buglifer.yagola.common.batch.service;

import com.buglifer.yagola.common.batch.enums.yogiyo.YogiyoAPI;
import com.buglifer.yagola.common.batch.response.yogiyo.MenuTypeResponse;
import com.buglifer.yagola.common.batch.response.yogiyo.RestaurantResponse;
import com.buglifer.yagola.common.batch.response.yogiyo.TotalRestaurantResponse;
import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.common.okhttp.HttpMethods;
import com.buglifer.yagola.common.okhttp.OKHttp;
import com.buglifer.yagola.common.okhttp.header.YogiyoHeader;
import com.buglifer.yagola.menu.repository.MenuRepository;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import com.buglifer.yagola.restaurant.repository.RestaurantRepository;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Response;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BatchService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public List<RestaurantDTO> getRestaurants() {
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAll();
        return restaurantEntityList.stream().map(e -> RestaurantDTO
                .fromEntity()
                .entity(e)
                .build()).collect(Collectors.toList());
    }

    public TotalRestaurantResponse getYogiyoTotalRestaurant() {
        return requestTotalRestaurant(0);
    }

    private TotalRestaurantResponse requestTotalRestaurant(long page) {
        TotalRestaurantResponse result = null;
        String url = YogiyoAPI.TOTAL_RESTAURANT.getUrl() + "page=" + page;
        Response response = OKHttp.okHttpRequest(url
                , new Headers
                        .Builder()
                        .add(YogiyoHeader.APISECRET.getHeader(), "fe5183cc3dea12bd0ce299cf110a75a2")
                        .add(YogiyoHeader.APIKEY.getHeader(), "iphoneap")
                        .add(YogiyoHeader.CONTENTTYPE.getHeader(), "application/x-www-form-urlencoded")
                        .build()
                , null, HttpMethods.GET);
        try {
            result = new Moshi.Builder()
                    .build()
                    .adapter(TotalRestaurantResponse.class)
                    .fromJson(response.body().source());
        }
        catch (IOException e) {
            throw new JsonParseException(e);
        }

        response.body().close();
        return result;
    };

    public List<RestaurantDTO> getRestaurantDTOByYogiyoResponse(TotalRestaurantResponse totalRestaurantResponse) {
        RestaurantResponse[] restaurantResponses = totalRestaurantResponse.getRestaurants();

        List<RestaurantDTO> result = Arrays.stream(restaurantResponses)
                .map(
                        e -> RestaurantDTO.fromResponse()
                                .response(e)
                                .build()
                )
                .collect(Collectors.toList());

//        restaurantRepository.save(RestaurantEntity
//                .initRestaurant()
//                .dto(result.get(0))
//                .build()
//        );

        return result;
    }

    private String requestMenuView(long restaurantID) throws IOException {
        String url = "https://www.yogiyo.co.kr/api/v1/restaurants/" + restaurantID + "/menu/?add_photo_menu=android&add_one_dish_menu=true&additional_discount_per_menu=true&order_serving_type=delivery";
        Response response = OKHttp.okHttpRequest(url
                , new Headers
                        .Builder()
                        .add(YogiyoHeader.APISECRET.getHeader(), "fe5183cc3dea12bd0ce299cf110a75a2")
                        .add(YogiyoHeader.APIKEY.getHeader(), "iphoneap")
                        .add(YogiyoHeader.CONTENTTYPE.getHeader(), "application/x-www-form-urlencoded")
                        .build()
                , null, HttpMethods.GET);
        if(!response.isSuccessful()) log.info("실패!!!!!");
        Type type = Types.newParameterizedType(List.class, MenuTypeResponse.class);
        StringBuilder stringBuilder = new StringBuilder();
        List<MenuTypeResponse> result = (List<MenuTypeResponse>) new Moshi.Builder()
                .build()
                .adapter(type)
                .fromJson(response.body().source());
        response.body().close();
        result.forEach( e -> stringBuilder.append(e.toString()));
        return stringBuilder.toString();
    }

    public void compareRestuaurantAPIWithDB() {
        List<RestaurantDTO> apiList = getRestaurantDTOByYogiyoResponse(requestTotalRestaurant(0));
        List<RestaurantDTO> dbList = getRestaurants();
        apiList.forEach(e -> log.info("[API]" + e.getApiID() + ", " + e.getName()));
        dbList.forEach(e -> log.info("[DB]" + e.getApiID() + ", " + e.getName()));
        log.info("[MatchingTest] Start");
        apiList.stream()
                .filter(
                        e -> dbList.stream().map(a -> a.getApiID()).noneMatch(Predicate.isEqual(e.getApiID()))
                ).forEach(
                        e -> log.info("[Not Matched]" + e.getApiID() + ", " + e.getName()));
        log.info("[MatchingTest] End");
    }

    public void logMaxPagination() {
        TotalRestaurantResponse initResponse = requestTotalRestaurant(0);
        long totalPage = initResponse.getPagination().getTotal_pages();
        long currentPage = initResponse.getPagination().getCurrent_page();
        log.info("[MAX Pagination] " + initResponse.getPagination().getTotal_pages());
        log.info("[Current Page] " + initResponse.getPagination().getCurrent_page());
        if(totalPage == currentPage) return;
        while(totalPage >= currentPage) {
            log.info("[ " + currentPage + " ]");
            List<RestaurantDTO> restaurantDTOListinAPI = getRestaurantDTOByYogiyoResponse(requestTotalRestaurant(currentPage));
            if(restaurantDTOListinAPI.isEmpty()) break;
            log.info("[item count]" + restaurantDTOListinAPI.size());
            log.info(restaurantDTOListinAPI.get(0).getApiID() + ", " + restaurantDTOListinAPI.get(0).getName());
            currentPage++;
        }
    }

    public
}
