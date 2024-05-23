package com.jay.accounts.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
// By default @data doesn't have @AllArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    private String statusCode; // 200,500,etc

    private String statusMessage;


}
