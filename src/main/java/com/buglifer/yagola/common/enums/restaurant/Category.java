package com.buglifer.yagola.common.enums.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum Category {
    CHINA("중식")
    , KOREA("한식")
    , JAPAN("일식")
    , ALONE("1인분")
    , FRANCHISE("프랜차이즈")
    , WESTERN("양식")
    , PIG("족발/보쌈")
    , NIGHT("야식")
    , SCHOOL("분식")
    , CAFE("카페/디저트")
    , MART("마트/편의점")
    , CHICKEN("치킨")
    , ETC("기타");
    private String name;
}
