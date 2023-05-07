package com.example.IndiaMart.transformer;

import com.example.IndiaMart.Dto.request.SellerRequest;
import com.example.IndiaMart.Dto.response.GetSellerResponse;
import com.example.IndiaMart.Dto.response.SellerResponse;
import com.example.IndiaMart.Model.Seller;

public class SellerTransformer {

    public static Seller sellerReqToSeller(SellerRequest sellerRequest){
        Seller seller = Seller.builder().
                name(sellerRequest.getName()).
                email(sellerRequest.getEmail()).
                mobile(sellerRequest.getMobile()).
                age(sellerRequest.getAge()).
                build();

        return seller;
    }

    public static SellerResponse sellerToSellerResponse(Seller seller)
    {
        SellerResponse sellerResponse = SellerResponse.builder().name(seller.getName()).build();
        return sellerResponse;
    }

    public static GetSellerResponse sellerToGetSellerResponse(Seller seller)
    {
        GetSellerResponse getSellerResponse = GetSellerResponse.builder().sellerName(seller.getName()).email(seller.getEmail()).
                mobile(seller.getMobile()).build();

        return getSellerResponse;
    }
}
