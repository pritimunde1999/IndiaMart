package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.CustomerRequest;
import com.example.IndiaMart.Dto.response.CustomerResponse;
import com.example.IndiaMart.Dto.response.UpdateCustomerResponse;

import java.util.List;

public interface CustomerService {

    public CustomerResponse addCustomer(CustomerRequest customerRequest) throws Exception;

    public List<CustomerResponse> getAllCustomer();

    public CustomerResponse getCustomerByEmailOrMob(String mobile,String email) throws Exception;

    public List<CustomerResponse> getAllCustomersGreaterThan25();

    public List<CustomerResponse> getWhoUseVisaCard();

    public UpdateCustomerResponse updateInfo(String email, CustomerRequest customerRequest);

    public CustomerResponse deleteByMobOrEmail(String mobile,String email) throws Exception;
}
