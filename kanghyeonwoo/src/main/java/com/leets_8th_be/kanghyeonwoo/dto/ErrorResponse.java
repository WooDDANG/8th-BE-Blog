package com.leets_8th_be.kanghyeonwoo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse extends BaseResponse {

    @JsonProperty("error_code")
    private String errorCode;

    public ErrorResponse(String message, String errorCode) {
        super(false, message);
        this.errorCode = errorCode;
    }

    public static ErrorResponse of(String message, String errorCode) {
        return new ErrorResponse(message, errorCode);
    }
}
