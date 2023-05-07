package com.example.IndiaMart.Repository;

import com.example.IndiaMart.Dto.response.AddProductResponse;
import com.example.IndiaMart.Enums.ProductCategory;
import com.example.IndiaMart.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory(ProductCategory category);


    @Query(value = "select * from product p where p.price < :price and p.category=:category",nativeQuery = true)
    public List<Product> getProductsHavingPriceLessThanFromCat(String category, int price);


}
