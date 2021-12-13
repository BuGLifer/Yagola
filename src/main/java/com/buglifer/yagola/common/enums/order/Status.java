package com.buglifer.yagola.common.enums.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public enum Status {

    ONLINE("참여")
    , OFFLINE("마감")
    , ORDER("주문")
    , ARRIVAL("도착");

    private String name;
}
