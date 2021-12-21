package com.buglifer.yagola.common.batch.response.yogiyo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class YogiyoPagination {
    private long per_page;
    private long current_page;
    private long total_pages;
    private long total_objects;
}
