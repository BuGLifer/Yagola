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
import java.util.Arrays;
import java.util.List;
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

    private TotalRestaurantResponse requestTotalRestaurant(int page) {
        TotalRestaurantResponse result = null;
        Response response = OKHttp.okHttpRequest(YogiyoAPI.TOTAL_RESTAURANT.getUrl()
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

    public List<RestaurantDTO> getRestaurantDTOByYogiyoResponse() {
        TotalRestaurantResponse totalRestaurantResponse = requestTotalRestaurant(0);
        RestaurantResponse[] restaurantResponses = totalRestaurantResponse.getRestaurants();
        return Arrays.stream(restaurantResponses)
                .map(
                        e -> RestaurantDTO.fromResponse()
                                .response(e)
                                .build()
                )
                .collect(Collectors.toList());
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
}
