package com.jay.accounts.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account Number must not be null")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Account number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type must not be null")
    private String accountType;

    @NotEmpty(message = "Branch Address must not be null")
    private String branchAddress;

}
