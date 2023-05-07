package com.example.IndiaMart.Repository;

import com.example.IndiaMart.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByMobile(String mobile);

    Customer findByEmail(String email);
}
