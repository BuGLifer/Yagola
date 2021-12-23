package com.buglifer.yagola.common.batch.enums.yogiyo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YogiyoAPI {

    TOTAL_RESTAURANT("https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=100&lat=37.563011615886&lng=126.835012463538&order=rank&")
    , TOTAL_MENU_FRONT("https://www.yogiyo.co.kr/api/v1/restaurants/")
    , TOTAL_MENU_BACK("/menu/?add_photo_menu=android&add_one_dish_menu=true&order_serving_type=delivery");

    private String url;

}
