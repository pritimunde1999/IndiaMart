package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.CustomerRequest;
import com.example.IndiaMart.Dto.response.CustomerResponse;
import com.example.IndiaMart.Dto.response.UpdateCustomerResponse;
import com.example.IndiaMart.Enums.CardType;
import com.example.IndiaMart.Model.Card;
import com.example.IndiaMart.Model.Cart;
import com.example.IndiaMart.Model.Customer;
import com.example.IndiaMart.Repository.CustomerRepository;
import com.example.IndiaMart.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) throws Exception {

        if(customerRepository.findByMobile(customerRequest.getMobile())!=null)
        {
            throw new Exception("Customer is already Registered");
        }

        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);

        Cart cart = Cart.builder().numberOfItems(0).cartTotal(0).customer(customer).build();
        customer.setCart(cart);

        CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(customer);
        customerResponse.setMessage("Welcome to IndiaMart!!");

        customerRepository.save(customer);
        return customerResponse;
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> customerResponseList = new ArrayList<>();

        for(Customer customer : customers)
        {
            customerResponseList.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponseList;
    }

    @Override
    public CustomerResponse getCustomerByEmailOrMob(String mobile,String email) throws Exception {

        Customer customer;
        if(customerRepository.findByMobile(mobile)!=null)
        {
            customer = customerRepository.findByMobile(mobile);
        }
        else if(customerRepository.findByEmail(email)!=null)
        {
            customer = customerRepository.findByEmail(email);
        }
        else
        {
            throw new Exception("Please enter Mobile number or Email Id");
        }

        return CustomerTransformer.customerToCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomersGreaterThan25() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponse> customerResponseList = new ArrayList<>();

        for(Customer customer : customers)
        {
            if(customer.getAge()>25)
            {
                customerResponseList.add(CustomerTransformer.customerToCustomerResponse(customer));
            }
        }
        return customerResponseList;
    }

    @Override
    public List<CustomerResponse> getWhoUseVisaCard() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponse> customerResponseList = new ArrayList<>();

        for(Customer customer : customers)
        {
            List<Card> list = customer.getCards();
            for(Card card : list)
            {
                if(card.getCardType().equals(CardType.VISA))
                {
                    customerResponseList.add(CustomerTransformer.customerToCustomerResponse(customer));
                }
            }
        }
        return customerResponseList;
    }

    @Override
    public UpdateCustomerResponse updateInfo(String email,CustomerRequest customerRequest) {
        Customer customer = customerRepository.findByEmail(email);

        UpdateCustomerResponse updateCustomerResponse =new UpdateCustomerResponse();

        if(!customerRequest.getMobile().equals("string"))
        {
            customer.setMobile(customerRequest.getMobile());
            updateCustomerResponse.setMobile(customerRequest.getMobile());
        }
        if(!customerRequest.getName().equals("string"))
        {
            customer.setName(customerRequest.getName());
            updateCustomerResponse.setName(customerRequest.getName());
        }
        if(!customerRequest.getAddress().equals("string"))
        {
            customer.setAddress(customerRequest.getAddress());
            updateCustomerResponse.setAddress(customerRequest.getAddress());
        }
        if(!customerRequest.getEmail().equals("string"))
        {
            customer.setEmail(customerRequest.getEmail());
            updateCustomerResponse.setEmail(customerRequest.getEmail());
        }
        if(customerRequest.getAge()!=0)
        {
            customer.setAge(customerRequest.getAge());
            updateCustomerResponse.setAge(customerRequest.getAge());
        }

        customerRepository.save(customer);
        updateCustomerResponse.setMessage("Info Updated");

        return updateCustomerResponse;

    }

    @Override
    public CustomerResponse deleteByMobOrEmail(String mobile,String email) throws Exception {
        Customer customer;
        if(customerRepository.findByMobile(mobile)!=null)
        {
            customer = customerRepository.findByMobile(mobile);
        }
        else if(customerRepository.findByEmail(email)!=null)
        {
            customer = customerRepository.findByEmail(email);
        }
        else
        {
            throw new Exception("Please enter valid Mobile number or Email Id");
        }

        CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(customer);
        customerResponse.setMessage("Customer Deleted");
        customerRepository.deleteById(customer.getId());
        return customerResponse;
    }


}
