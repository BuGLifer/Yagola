package com.buglifer.yagola.common.enums.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ToString
@AllArgsConstructor
@Getter
public enum Category {
    CHINA("중식")
    , KOREA("한식")
    , JAPAN("일식돈까스")
    , ALONE("1인분주문")
    , FRANCHISE("프랜차이즈")
    , WESTERN("피자양식")
    , PIG("족발보쌈")
    , NIGHT("야식")
    , SCHOOL("분식")
    , CAFE("카페디저트")
    , MART("편의점")
    , CHICKEN("치킨")
    , ETC("기타");

    private String name;

}
