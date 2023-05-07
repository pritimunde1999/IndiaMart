package com.example.IndiaMart.Repository;

import com.example.IndiaMart.Model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Integer> {
}
