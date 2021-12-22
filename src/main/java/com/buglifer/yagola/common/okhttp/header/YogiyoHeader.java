package com.buglifer.yagola.common.okhttp.header;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YogiyoHeader {
    APISECRET("x-apisecret")
    , APIKEY("x-apikey")
    , CONTENTTYPE("Content-Type");

    private String header;
}
