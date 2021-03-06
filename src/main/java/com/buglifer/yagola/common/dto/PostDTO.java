package com.buglifer.yagola.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class PostDTO extends CommonDTO {

    private String comment;
    private Boolean view;

}
