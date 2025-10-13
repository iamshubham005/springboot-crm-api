package com.example.crm.service.impl;

import com.example.crm.dto.CustomerRequest;
import com.example.crm.dto.CustomerResponse;
import com.example.crm.entity.Customer;
import com.example.crm.exception.ResourceNotFoundException;
import com.example.crm.mapper.CustomerMapper;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse saveCustomer(CustomerRequest request) {
        customerRepository.findByEmail(request.getEmail())
                .ifPresent(c -> { throw new IllegalArgumentException("Email already exists"); });
        Customer customer = CustomerMapper.toEntity(request);
        return CustomerMapper.toResponse(customerRepository.save(customer));
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        return CustomerMapper.toResponse(customer);
    }

    @Override
    public List<CustomerResponse> getCustomerList() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setDateOfBirth(request.getDateOfBirth());
        return CustomerMapper.toResponse(customerRepository.save(customer));
    }

    @Override
    public void deactivateCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setActive(false);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> searchCustomer(String firstName, String lastName) {
        return customerRepository
                .findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
                        firstName != null ? firstName : "",
                        lastName != null ? lastName : "")
                .stream()
                .map(CustomerMapper::toResponse)
                .collect(Collectors.toList());
    }
}
