package com.accounts.controller;

import com.accounts.dto.CustomerDetailsDto;
import com.accounts.dto.CustomerDto;
import com.accounts.dto.response.ResponseDto;
import com.accounts.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customerDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(@RequestParam Long customerID) {
        CustomerDetailsDto customerDetailsDto = customerService.getCustomerDetailsWithID(customerID);
        return new ResponseEntity<CustomerDetailsDto>(customerDetailsDto, HttpStatus.OK);
    }

    @PostMapping(value = "/createCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Validated
    public ResponseEntity<CustomerDetailsDto> createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/updateCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Validated
    public ResponseEntity<CustomerDetailsDto> updateCustomer(@RequestParam
                                                             @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number should be 10 character long.")
                                                             String mobileNumber,
                                                             @RequestBody @Valid CustomerDetailsDto customerDetailsDto) {
        return new ResponseEntity<>(customerService.updateCustomer(mobileNumber, customerDetailsDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @Validated
    public ResponseEntity<ResponseDto> deleteCustomerByMobileNumber(@RequestParam
                                                                    @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number should be 10 character long.")
                                                                    String mobileNumber) {
        ResponseDto responseDto = customerService.deleteCustomer(mobileNumber);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
