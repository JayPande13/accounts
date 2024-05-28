package com.jay.accounts.controllers;

import com.jay.accounts.Constants.AccountsConstants;
import com.jay.accounts.Dto.CustomerDto;
import com.jay.accounts.Dto.ErrorResponseDto;
import com.jay.accounts.Dto.ResponseDto;
import com.jay.accounts.Services.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/accounts/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated // This Annotation tells spring boot to perform validation on all the inputs on all Rest APIs
@Tag(
        name = "Accounts API for Bank",
        description = "CRUD APIs fro accounts microservice"
)
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create Account API",
            description = "CRUD - C - API"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account Has been created"

    )
    @PostMapping("create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iAccountsService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));

    }

    @Operation(
            description = "API for fetching Account",
            summary = "on Hitting this API with mobile number and you will get Account Details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Account Fetched Successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Account not fetched",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )

    })

    @GetMapping("fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@Pattern(regexp = "^$|[0-9]{10}", message = "Mobile Number must be 10 digits") @RequestParam String mobileNumber) {

        CustomerDto customerDto = iAccountsService.fetchAccountDetailsByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);

    }

    @Operation(
            summary = "Update Account API",
            description = "CRUD - U - API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account Has been updated"

            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )}
    )

    @PostMapping("update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        Boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Update Account API",
            description = "CRUD - D - API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account Has been Deleted"

            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )}
    )
    @DeleteMapping("delete")
    public ResponseEntity<ResponseDto> deleteAccount(@Pattern(regexp = "^$|[0-9]{10}", message = "Account number must be 10 digits") @RequestParam String mobileNumber) {

        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }


}
