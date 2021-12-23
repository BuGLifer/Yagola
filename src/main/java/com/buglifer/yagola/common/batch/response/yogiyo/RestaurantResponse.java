package com.buglifer.yagola.common.batch.response.yogiyo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class RestaurantResponse {
    private long id;
    private String name;
    private String[] categories;
    private String logo_url;
    private String begin;
    private String end;
}
