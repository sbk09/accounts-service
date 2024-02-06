package com.accounts.service.impl;

import com.accounts.dto.AccountsDto;
import com.accounts.dto.CustomerDetailsDto;
import com.accounts.dto.CustomerDto;
import com.accounts.dto.LoanDto;
import com.accounts.dto.response.ResponseDto;
import com.accounts.entity.Accounts;
import com.accounts.entity.Customer;
import com.accounts.exception.CustomerAlreadyExistsException;
import com.accounts.exception.CustomerNotPresentException;
import com.accounts.mapper.AccountsMapper;
import com.accounts.mapper.CustomerDetailsMapper;
import com.accounts.mapper.CustomerMapper;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
import com.accounts.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private CustomerDetailsMapper customerDetailsMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private AccountsMapper accountsMapper;

    /**
     * @return list of all customers
     */
    @Override
    public List<CustomerDto> getAllCustomers() {
        return null;
    }

    /**
     * @return customer details along with account information
     */
    @Override
    public CustomerDetailsDto getCustomerDetailsWithID(Long customerID) {
        Optional<Customer> customer = customerRepository.findById(customerID);
        Accounts account = accountsRepository.findAccountsByCustomerId(customerID);
        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();

        return customerDetailsMapper.getCustomerDetailsDto(customer.get(), account);
    }

    /**
     * @param customerDto
     * @return creates customer and returns customerDto
     */
    @Override
    @Transactional
    public CustomerDetailsDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.getCustomerFromDto(customerDto);
        if (Objects.nonNull(customerRepository.findCustomerByMobileNumber(customer.getMobileNumber())))
            throw new CustomerAlreadyExistsException("Customer is already present with given mobile number: " + customer.getMobileNumber());
        customer = customerRepository.save(customer);
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setCustomerID(customer.getCustomerId());
        accountsDto.setAccountType("Savings");
        accountsDto.setAccountNumber(Long.valueOf(generateAccountNumberPrefix() + customer.getCustomerId()));
        accountsDto.setBranchAddress("New York");
        Accounts accounts = accountsMapper.convertAccountsDtoToEntity(accountsDto);
        accounts.setCustomerId(customer.getCustomerId());
        accountsRepository.save(accounts);

        WebClient webClient = WebClient.builder().build();

        try {
            Customer finalCustomer = customer;
            String url = "http://loans-ms:9000/loan/request";
            url = url + "?mobileNumber=" + finalCustomer.getMobileNumber();
            Mono<LoanDto> loanDtoMono = webClient.post().uri(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(LoanDto.class).log();

            LoanDto loanDto = loanDtoMono.block();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return customerDetailsMapper.getCustomerDetailsDto(customer, accounts);
    }

    @Override
    @Transactional
    public CustomerDetailsDto updateCustomer(String mobileNumber, CustomerDetailsDto customerDetailsDto) {
        Customer customer = customerRepository.findCustomerByMobileNumber(mobileNumber);
        if (Objects.isNull(customer))
            throw new CustomerNotPresentException("Customer not present with given mobile number: " + customerDetailsDto.getMobileNumber());
        customer.setCustomerName(customerDetailsDto.getCustomerName());
        customer.setEmail(customerDetailsDto.getEmail());

        Accounts accounts = accountsRepository.findAccountsByCustomerId(customer.getCustomerId());
        accounts.setAccountType(customerDetailsDto.getAccountType());
        accounts.setBranchAddress(customerDetailsDto.getBranchAddress());

        customerRepository.save(customer);
        accountsRepository.save(accounts);

        return customerDetailsMapper.getCustomerDetailsDto(customer, accounts);
    }

    @Override
    @Transactional
    public ResponseDto deleteCustomer(String mobileNumber) {
        Customer customer = customerRepository.findCustomerByMobileNumber(mobileNumber);
        if (Objects.isNull(customer))
            throw new CustomerNotPresentException("Customer is not present with given mobile number: " + mobileNumber);

        customerRepository.deleteById(customer.getCustomerId());
        accountsRepository.deleteByCustomerId(customer.getCustomerId());

        return new ResponseDto(HttpStatus.OK.name(), "Deleted successfully.");
    }

    private String generateAccountNumberPrefix() {
        return "111222333";
    }
}
