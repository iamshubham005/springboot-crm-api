package com.example.crm.mapper;

import com.example.crm.dto.CustomerRequest;
import com.example.crm.dto.CustomerResponse;
import com.example.crm.entity.Customer;
import java.time.LocalDate;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest request) {
        if (request == null) return null;
        return Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .registrationDate(LocalDate.now())
                .isActive(true)
                .build();
    }

    public static CustomerResponse toResponse(Customer customer) {
        if (customer == null) return null;
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .dateOfBirth(customer.getDateOfBirth())
                .registrationDate(customer.getRegistrationDate())
                .isActive(customer.isActive())
                .build();
    }
}
