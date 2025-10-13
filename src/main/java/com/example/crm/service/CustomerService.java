package com.example.crm.service;

import com.example.crm.dto.CustomerRequest;
import com.example.crm.dto.CustomerResponse;
import java.util.List;

public interface CustomerService {
    CustomerResponse saveCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(Long id);
    List<CustomerResponse> getCustomerList();
    CustomerResponse updateCustomer(Long id, CustomerRequest request);
    void deactivateCustomer(Long id);
    List<CustomerResponse> searchCustomer(String firstName, String lastName);
}
