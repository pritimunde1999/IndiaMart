package com.example.IndiaMart.Controller;

import com.example.IndiaMart.Dto.request.SellerRequest;
import com.example.IndiaMart.Dto.response.GetSellerResponse;
import com.example.IndiaMart.Dto.response.SellerResponse;
import com.example.IndiaMart.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequest sellerRequest) {

            SellerResponse sellerResponse = sellerService.addSeller(sellerRequest);
            return new ResponseEntity<>(sellerResponse, HttpStatus.CREATED);

    }

    //get seller by email
    @GetMapping("/getByEmail")
    public GetSellerResponse getByEmail(@RequestParam("email") String email) throws Exception {
        return sellerService.getByEmail(email);
    }

    //get by id
    @GetMapping("/getById")
    public GetSellerResponse getById(@RequestParam("id") int id) throws Exception {
        return sellerService.getById(id);
    }

    //get all seller
    @GetMapping("/getAllSellers")
    public List<GetSellerResponse> getAllSellers()
    {
        return sellerService.getAllSellers();
    }


    //update seller mobile based on emailId
    @PutMapping("/updateByEmailId")
    public GetSellerResponse updateMobile(@RequestParam("email")String email,@RequestParam("mobile")String mobile) throws Exception {
        return sellerService.updateMobile(email,mobile);
    }


    //delete seller email
    @DeleteMapping("/deleteByEmail")
    public SellerResponse deleteSellerByEmail(@RequestParam("email")String email) throws Exception {
        return sellerService.deleteSellerByEmail(email);
    }


    //delete by id
    @DeleteMapping("/deleteById")
    public SellerResponse deleteSellerById(@RequestParam("id")int id) throws Exception {
        return sellerService.deleteSellerById(id);
    }


    //get all seller of particular age
    @GetMapping("/getAllSellerOfAge")
    public List<GetSellerResponse> getAllSellerOfAge(@RequestParam("age") int age)
    {
        return sellerService.getAllSellerOfAge(age);
    }
}
