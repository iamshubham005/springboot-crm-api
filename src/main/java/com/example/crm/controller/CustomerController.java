package com.example.crm.controller;

import com.example.crm.dto.CustomerRequest;
import com.example.crm.dto.CustomerResponse;
import com.example.crm.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/saveCustomer")
    public ResponseEntity<CustomerResponse> saveCustomer(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.saveCustomer(request));
    }

    @GetMapping("/getCustomerList")
    public ResponseEntity<List<CustomerResponse>> getCustomerList() {
        return ResponseEntity.ok(customerService.getCustomerList());
    }

    @GetMapping("/getCustomerById")
    public ResponseEntity<CustomerResponse> getCustomerById(@RequestParam Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestParam Long id,
                                                           @Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }

    @DeleteMapping("/deactivateCustomer")
    public ResponseEntity<String> deactivateCustomer(@RequestParam Long id) {
        customerService.deactivateCustomer(id);
        return ResponseEntity.ok("Customer deactivated successfully");
    }


   @GetMapping("/searchCustomer")
    public ResponseEntity<List<CustomerResponse>> searchCustomer(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        try {
            return ResponseEntity.ok(customerService.searchCustomer(firstName, lastName));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    }
