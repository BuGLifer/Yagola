package com.buglifer.yagola.common.batch.response.yogiyo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TotalRestaurantResponse {
    private YogiyoPagination pagination;
    private RestaurantResponse[] restaurants;
}
