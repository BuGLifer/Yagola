package com.buglifer.yagola.common.batch;

import com.buglifer.yagola.common.batch.response.yogiyo.MenuTypeResponse;
import com.buglifer.yagola.common.batch.response.yogiyo.TotalRestaurantResponse;
import com.buglifer.yagola.common.batch.response.yogiyo.TotalViewResponse;
import com.buglifer.yagola.common.batch.service.BatchService;
import com.buglifer.yagola.common.okhttp.HttpMethods;
import com.buglifer.yagola.common.okhttp.OKHttp;
import com.buglifer.yagola.common.okhttp.header.YogiyoHeader;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
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
    public TotalRestaurantResponse getRestaurants() throws IOException {
      return batchService.getYogiyoTotalRestaurant();
    }

    @GetMapping("restaurants/list")
    public List<RestaurantDTO> getRestaurantDTOList() throws IOException {
        return batchService.getRestaurantDTOByYogiyoResponse();
    }

    @GetMapping("restaurants/compare")
    public void getRestaurantsCompare() {
        batchService.compareRestuaurantAPIWithDB();
    }

    @GetMapping("restaurants/pagination")
    public void getResturantsPagination() {
        batchService.logMaxPagination();
    }
}
