package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.SellerRequest;
import com.example.IndiaMart.Dto.response.GetSellerResponse;
import com.example.IndiaMart.Dto.response.SellerResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SellerService {

    public SellerResponse addSeller(SellerRequest sellerRequest);

    public GetSellerResponse getByEmail(String email) throws Exception;

    public GetSellerResponse getById(int id) throws Exception;

    public List<GetSellerResponse> getAllSellers();

    public SellerResponse deleteSellerByEmail(String email) throws Exception;

    public SellerResponse deleteSellerById(int id) throws Exception;

    public List<GetSellerResponse> getAllSellerOfAge(int age);

    public GetSellerResponse updateMobile(String email,String mobile) throws Exception;
}
