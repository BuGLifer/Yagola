package com.buglifer.yagola.common.batch;

import com.buglifer.yagola.common.batch.response.yogiyo.MenuTypeResponse;
import com.buglifer.yagola.common.batch.response.yogiyo.TotalViewResponse;
import com.buglifer.yagola.common.batch.service.BatchService;
import com.buglifer.yagola.common.okhttp.HttpMethods;
import com.buglifer.yagola.common.okhttp.OKHttp;
import com.buglifer.yagola.common.okhttp.header.YogiyoHeader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Slf4j
@RequestMapping("batch")
@RequiredArgsConstructor
@RestController
public class BatchTest {

    private final BatchService batchService;

    @GetMapping("restaurants")
    public String getRestaurants() throws IOException {
      return requestMenuView(1018186);
    };

    private TotalViewResponse requestTotalView() throws IOException {
        String url = "https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=60&lat=37.563011615886&lng=126.835012463538&order=rank&page=0&search=";
        Response response = OKHttp.okHttpRequest(url
                , new Headers
                        .Builder()
                        .add(YogiyoHeader.APISECRET.getHeader(), "fe5183cc3dea12bd0ce299cf110a75a2")
                        .add(YogiyoHeader.APIKEY.getHeader(), "iphoneap")
                        .add(YogiyoHeader.CONTENTTYPE.getHeader(), "application/x-www-form-urlencoded")
                        .build()
                , null, HttpMethods.GET);
        if(!response.isSuccessful()) {
            return new TotalViewResponse();
        }
        TotalViewResponse result = new Moshi.Builder()
                .build()
                .adapter(TotalViewResponse.class)
                .fromJson(response.body().source());
        response.body().close();
        return result;
    };

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
