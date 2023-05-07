package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.SellerRequest;
import com.example.IndiaMart.Dto.response.GetSellerResponse;
import com.example.IndiaMart.Dto.response.SellerResponse;
import com.example.IndiaMart.Model.Seller;
import com.example.IndiaMart.Repository.SellerRepository;
import com.example.IndiaMart.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    SellerRepository sellerRepository;


    @Override
    public SellerResponse addSeller(SellerRequest sellerRequest)  {
//        Seller seller = new Seller();
//        seller.setName(sellerRequest.getName());
//        seller.setAge(sellerRequest.getAge());
//        seller.setMobile(sellerRequest.getMobile());
//        seller.setEmail(sellerRequest.getEmail());

        //using builder
        Seller seller = SellerTransformer.sellerReqToSeller(sellerRequest);
        Seller savedSeller = sellerRepository.save(seller);

        SellerResponse sellerResponse = SellerTransformer.sellerToSellerResponse(savedSeller);
        sellerResponse.setMessage("Seller Added");
        return sellerResponse;

    }

    @Override
    public GetSellerResponse getByEmail(String email) throws Exception {
        Seller seller;
       try{
           seller = sellerRepository.findByEmail(email);
       }
       catch (Exception e)
       {
           throw new Exception("Seller doest not exists");
       }

       return SellerTransformer.sellerToGetSellerResponse(seller);
    }

    @Override
    public GetSellerResponse getById(int id) throws Exception {
        Seller seller;
        try{
            seller = sellerRepository.findById(id).get();
        }
        catch (Exception e)
        {
            throw new Exception("Seller doest not exists");
        }

        return SellerTransformer.sellerToGetSellerResponse(seller);
    }

    @Override
    public List<GetSellerResponse> getAllSellers() {

        List<Seller> sellers = sellerRepository.findAll();
        List<GetSellerResponse> getSellerResponses = new ArrayList<>();

        for (Seller seller : sellers)
        {
            getSellerResponses.add(SellerTransformer.sellerToGetSellerResponse(seller));
        }

        return getSellerResponses;
    }

    @Override
    public SellerResponse deleteSellerByEmail(String email) throws Exception {
        Seller seller;
        try{
            seller = sellerRepository.findByEmail(email);
        }
        catch (Exception e)
        {
            throw new Exception("Seller doest not exists");
        }

        sellerRepository.delete(seller);

        SellerResponse sellerResponse = SellerTransformer.sellerToSellerResponse(seller);
        sellerResponse.setMessage("Seller Deleted");
        return sellerResponse;
    }

    @Override
    public SellerResponse deleteSellerById(int id) throws Exception {
        Seller seller;
        try{
            seller = sellerRepository.findById(id).get();
        }
        catch (Exception e)
        {
            throw new Exception("Seller doest not exists");
        }

        sellerRepository.delete(seller);
        SellerResponse sellerResponse = SellerTransformer.sellerToSellerResponse(seller);
        sellerResponse.setMessage("Seller Deleted");
        return sellerResponse;
    }

    @Override
    public List<GetSellerResponse> getAllSellerOfAge(int age) {
        List<Seller> sellers = sellerRepository.findAllByAge(age);
        List<GetSellerResponse> getSellerResponses = new ArrayList<>();

        for(Seller seller : sellers)
        {
            getSellerResponses.add(SellerTransformer.sellerToGetSellerResponse(seller));
        }

        return getSellerResponses;
    }

    @Override
    public GetSellerResponse updateMobile(String email,String mobile) throws Exception {
        Seller seller;
        try{
            seller = sellerRepository.findByEmail(email);
        }
        catch (Exception e)
        {
            throw new Exception("Seller doest not exists");
        }

        seller.setMobile(mobile);
        sellerRepository.save(seller);
        return SellerTransformer.sellerToGetSellerResponse(seller);
    }
}
