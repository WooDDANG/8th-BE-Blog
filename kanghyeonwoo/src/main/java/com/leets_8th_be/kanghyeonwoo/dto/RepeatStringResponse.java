package com.leets_8th_be.kanghyeonwoo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RepeatStringResponse extends BaseResponse {

    @JsonProperty("string_one")
    private String stringOne;

    @JsonProperty("string_two")
    private String stringTwo;

    public RepeatStringResponse(String stringOne, String stringTwo) {
        super(true, "요청이 성공적으로 처리되었습니다.");
        this.stringOne = stringOne;
        this.stringTwo = stringTwo;
    }

    public static RepeatStringResponse of(String str1, String str2) {
        return new RepeatStringResponse(str1, str2);
    }
}
