package com.example.IndiaMart.transformer;

import com.example.IndiaMart.Dto.request.CustomerRequest;
import com.example.IndiaMart.Dto.response.CustomerResponse;
import com.example.IndiaMart.Model.Customer;

public class CustomerTransformer {

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest)
    {
       return Customer.builder().name(customerRequest.getName())
                .age(customerRequest.getAge())
                .address(customerRequest.getAddress())
                .email(customerRequest.getEmail())
                .mobile(customerRequest.getMobile()).build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer)
    {
        return CustomerResponse.builder().name(customer.getName()).mobile(customer.getMobile()).build();
    }
}
