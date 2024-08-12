package com.jay.accounts.Dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountControlInfoDto(String message, Map<String,String> contactDetails, List<String> support) {

}
