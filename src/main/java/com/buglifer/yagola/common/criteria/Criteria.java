package com.buglifer.yagola.common.criteria;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    private Integer page;
    private Integer pageSize;
    private String type;
    private String keyword;

    public Criteria(){
        this(1, 5, "","");
    }

    public Criteria(Integer page, Integer pageSize, String type, String keyword) {
        this.page = page;
        this.pageSize = pageSize;
        this.type = type;
        this.keyword = keyword;
    }
}
