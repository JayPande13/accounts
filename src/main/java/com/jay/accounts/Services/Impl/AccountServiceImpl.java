package com.jay.accounts.Services.Impl;

import com.jay.accounts.Constants.AccountsConstants;
import com.jay.accounts.Dto.AccountsDto;
import com.jay.accounts.Dto.CustomerDto;
import com.jay.accounts.Exception.CustomerAlreadyExistsException;
import com.jay.accounts.Exception.ResourceNotFoundException;
import com.jay.accounts.Repository.AccountsRepository;
import com.jay.accounts.Repository.CustomerRepository;
import com.jay.accounts.Services.IAccountsService;
import com.jay.accounts.entities.Accounts;
import com.jay.accounts.entities.Customer;
import com.jay.accounts.mapper.AccountsMapper;
import com.jay.accounts.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


    /**
     * create account
     *
     * @param customerDto customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customerRepository.findByMobileNumber(customer.getMobileNumber()).ifPresent((foundCustomer) -> {
            throw new CustomerAlreadyExistsException("Customer Already Present With This Mobile Number " + foundCustomer.getMobileNumber());
        });

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDto fetchAccountDetailsByMobileNumber(String mobileNumber) {
        Customer fetchedCustomer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts fetchAccount = accountsRepository.findByCustomerId(fetchedCustomer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "mobileNumber", mobileNumber));
        CustomerDto returningCustomerDto = CustomerMapper.mapToCustomerDto(fetchedCustomer, new CustomerDto());
        returningCustomerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(fetchAccount, new AccountsDto()));
        return returningCustomerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        Boolean isUpdate = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Account", "Account Number", accountsDto.getAccountNumber().toString()));

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer" ,"Customer ID" , customerId.toString()));

            CustomerMapper.mapToCustomer(customerDto,customer);
            customer = customerRepository.save(customer);
            isUpdate =true;

        }
        return isUpdate;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer fetchedCustomer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        accountsRepository.deleteByCustomerId(fetchedCustomer.getCustomerId());
        customerRepository.deleteById(fetchedCustomer.getCustomerId());

        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts returningAccount = new Accounts();
        returningAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(800000000);
        returningAccount.setAccountNumber(randomAccNumber);
        returningAccount.setAccountType(AccountsConstants.SAVINGS);
        returningAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return returningAccount;
    }
}
