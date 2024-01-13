package com.accounts.service;

import com.accounts.dto.CustomerDetailsDto;
import com.accounts.dto.CustomerDto;
import com.accounts.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDetailsDto getCustomerDetailsWithID(Long customerID);

    CustomerDetailsDto createCustomer(CustomerDto customerDto);

    CustomerDetailsDto updateCustomer(String mobileNumber ,CustomerDetailsDto customerDetailsDto);

    ResponseDto deleteCustomer(String mobileNumber);
}
