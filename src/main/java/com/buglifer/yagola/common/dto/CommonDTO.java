package com.buglifer.yagola.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CommonDTO {

    private long seq;
    private Date createdTime;

}
