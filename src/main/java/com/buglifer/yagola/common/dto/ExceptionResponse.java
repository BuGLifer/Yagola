package com.buglifer.yagola.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionResponse {

    private int code;
    private String result;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String reason;

    @Builder(
            builderClassName = "init"
            , builderMethodName = "initException"
    )
    private ExceptionResponse(HttpStatus httpStatus, Exception exception) {
        code = httpStatus.value();
        result = exception.getMessage();
    }
}
