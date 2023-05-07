package com.example.IndiaMart.Controller;

import com.example.IndiaMart.Dto.request.CustomerRequest;
import com.example.IndiaMart.Dto.response.CustomerResponse;
import com.example.IndiaMart.Dto.response.UpdateCustomerResponse;
import com.example.IndiaMart.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) throws Exception {

        try {
            CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
            return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    //view all customer
    @GetMapping("/getAll")
    public List<CustomerResponse> getAllCustomer()
    {
        return customerService.getAllCustomer();
    }

    //get customer by email/mob
    @GetMapping("/get")
    public ResponseEntity getCustomerByEmailOrMob(@RequestParam("mobile") String mobile, @RequestParam("email") String email) throws Exception
    {
        try {

            CustomerResponse customerResponse = customerService.getCustomerByEmailOrMob(mobile,email);
            return new ResponseEntity<>(customerResponse,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    //get all customers whose age is > 25
    @GetMapping("/getAllCustomersGreaterThan25")
    public List<CustomerResponse> getAllCustomersGreaterThan25()
    {
        return customerService.getAllCustomersGreaterThan25();
    }


    //get all who use VISA card
    @GetMapping("/getWhoUseVisaCard")
    public List<CustomerResponse> getWhoUseVisaCard()
    {
        return customerService.getWhoUseVisaCard();
    }


    //update customer info by email
    @PutMapping("/updateInfo/{email}")
    public UpdateCustomerResponse updateInfo(@PathVariable("email") String email,@RequestBody CustomerRequest customerRequest){
        return customerService.updateInfo(email,customerRequest);
    }

    //delete customer by email/mob
    @DeleteMapping("/delete")
    public CustomerResponse deleteByMobOrEmail(@RequestParam("mobile") String mobile, @RequestParam("email") String email) throws Exception {
        return customerService.deleteByMobOrEmail(mobile,email);
    }

}
