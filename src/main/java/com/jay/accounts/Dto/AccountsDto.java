package com.jay.accounts.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name="Accounts",
        description = "This holds information of account of a customer"
)
public class AccountsDto {

    @Schema(description = "Hold 10 Digit account number", example = "9483729482")
    @NotEmpty(message = "Account Number must not be null")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Hold Information regarding type of account", example = "Saving / Current")
    @NotEmpty(message = "Account Type must not be null")
    private String accountType;

    @Schema(description = "Hold Branch Address on which branch account is present", example = "212B Expo")
    @NotEmpty(message = "Branch Address must not be null")
    private String branchAddress;

}
