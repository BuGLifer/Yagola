package com.buglifer.yagola.common.batch;

import com.buglifer.yagola.common.batch.response.yogiyo.TotalViewResponse;
import com.buglifer.yagola.common.okhttp.HttpMethods;
import com.buglifer.yagola.common.okhttp.OKHttp;
import com.buglifer.yagola.common.okhttp.header.YogiyoHeader;
import com.squareup.moshi.Moshi;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@Slf4j
@RequestMapping("batch")
@RestController
public class BatchTest {

    @GetMapping("test")
    public String test() throws IOException {
      return requestTotalView().toString();
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
}
