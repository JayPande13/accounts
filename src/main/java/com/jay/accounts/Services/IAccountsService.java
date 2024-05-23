package com.jay.accounts.Services;

import com.jay.accounts.Dto.CustomerDto;

public interface IAccountsService {


    /**
     * create account
     *
     * @param customerDto customerDto
     */
    public void createAccount(CustomerDto customerDto);
    public CustomerDto fetchAccountDetailsByMobileNumber(String mobileNumber);
    public boolean updateAccount(CustomerDto customerDto);

    public boolean deleteAccount(String mobileNumber);
}
