package com.example.IndiaMart.Repository;

import com.example.IndiaMart.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Seller findByEmail(String email);

    Seller findByMobile(String mobile);

    List<Seller> findAllByAge(int age);
}
