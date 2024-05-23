package com.jay.accounts.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {


    @NotEmpty(message = "Name must not be null")
    @Size(min = 5, max = 20,message = "Length of Customer Name should be between 5 to 30")
    private String name;

    @NotEmpty(message = "Email must not be null")
    @Email(message = "Email Address should be a valid value")
    private String email;

    @Pattern(regexp = "^$|[0-9]{10}",message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
