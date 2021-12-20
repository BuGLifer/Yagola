package com.buglifer.yagola.common.okhttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;

import java.io.IOException;

@Slf4j
public final class OKHttp {

    private static final OkHttpClient client = new OkHttpClient();

    public static Response okHttpRequest(String url, Headers headers, RequestBody requestBody, HttpMethods httpMethod) {
        Request request = createRequest(url, headers, requestBody, httpMethod);
        Response response = null;
        StringBuilder logBuilder = new StringBuilder();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            logBuilder.append("[OKHttp Request Fail]");
            if (request != null) logBuilder.append(request.toString()).append(" RequestBody = {").append(requestBodyToString(request)).append("} ");
            log.error(logBuilder.toString(), e);
            return null;
        }
        if ((response == null) || !response.isSuccessful()) {
            logBuilder.append("[OKHttp Response is not Succesed]");
            if (request != null) logBuilder.append(request.toString()).append(" RequestBody = {").append(requestBodyToString(request)).append("} ");
            if (response != null) {
                logBuilder.append("ResponseBody = {")
                        .append(response.toString())
                        .append("} ");
                try {
                    log.error(response.body().string());
                } catch (IOException e) {
                    log.error("Response Body Reading Fail", e);
                }
                response.body().close();
            }
            log.error(logBuilder.toString());
        }
        return response;
    }

    private static Request createRequest(String url, Headers headers, RequestBody requestBody, HttpMethods httpMethod) {
        Request.Builder builder = new Request.Builder().url(url);
        if (headers != null) builder.headers(headers);
        switch (httpMethod) {
            case POST :
                builder.post(requestBody);
                break;
            case PUT :
                builder.put(requestBody);
                break;
            case PATCH :
                builder.patch(requestBody);
                break;
            case OPTIONS :
                builder.method("OPTIONS", requestBody);
                break;
            case DELETE :
                builder.delete(requestBody);
                break;
            default:
                break;
        }
        return builder.build();
    }

    private static String requestBodyToString(Request request) {
        try {
            final Request copyRequest = request.newBuilder().build();
            if (copyRequest.body() == null) return "Request Body is Null";
            final Buffer buffer = new Buffer();
            copyRequest.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (IOException e) {
            return "Request Body Has Error";
        }
    }
    public static String getResponseBody(Request request) throws IOException {
        try {
            return getResponse(request).body().string();
        } catch (IOException e) {
            log.error("[OKHTTP Util] " + e.getMessage());
            throw e;
        }
    }

    private static Response getResponse(Request request) throws IOException {
        return client.newCall(request).execute();
    }
}
