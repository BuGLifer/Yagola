package com.buglifer.yagola.common.batch.response.yogiyo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MenuTypeResponse {
    private MenuResponse[] items;
    private String slug;
    private String name;
    private String description;
}
