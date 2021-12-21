package com.buglifer.yagola.common.batch.response.yogiyo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MenuResponse {
    private long id;
    private String name;
    private String description;
    private int price;
    private String image;
    private String original_image;
}
