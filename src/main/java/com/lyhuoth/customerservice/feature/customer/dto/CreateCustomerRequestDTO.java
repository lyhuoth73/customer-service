package com.lyhuoth.customerservice.feature.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateCustomerRequestDTO {

    @NotBlank(message = "First name is required")
    private  String firstname;

    @NotBlank(message = "First name is required")
    private String lastName;

    @NotBlank(message = "First name is required")
    @Email
    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;
}
