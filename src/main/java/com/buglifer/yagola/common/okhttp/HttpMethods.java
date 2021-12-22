package com.buglifer.yagola.common.okhttp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpMethods {
    GET("get")
    , POST("post")
    , PUT("put")
    , PATCH("patch")
    , DELETE("delete")
    , OPTIONS("options");

    private String name;
}