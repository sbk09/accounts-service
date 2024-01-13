package com.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {

    @NotEmpty(message = "Customer name should not be empty.")
    @Size(min = 5, max = 30, message = "Customer name should be between 5 to 30 characters.")
    private String customerName;

    @NotEmpty(message = "Email address is mandatory.")
    @Email
    private String email;

    @NotEmpty(message = "Mobile number should not be empty.")
    @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number should be 10 character long.")
    private String mobileNumber;

}
