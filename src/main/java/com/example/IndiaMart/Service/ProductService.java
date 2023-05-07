package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.AddProductRequest;
import com.example.IndiaMart.Dto.response.AddProductResponse;
import com.example.IndiaMart.Enums.ProductCategory;
import com.example.IndiaMart.Model.Items;
import com.example.IndiaMart.Model.Orders;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {

    public AddProductResponse addProduct(AddProductRequest addProductRequest) throws Exception;

    public List<AddProductResponse> getProductOfSameCategory(ProductCategory category);

    public List<AddProductResponse> getBySellerEmail(String email) throws Exception;

    public String deleteBySellerAndProductId(int sellerId, int productId) throws Exception;

    public List<AddProductResponse> top5CheapestProducts();

    public List<AddProductResponse> top5CostliestProducts();

    public List<AddProductResponse> allAvailableProducts();

    public List<String> allOutOfStockProducts();

    public List<AddProductResponse> quantityLessThan10();

    public AddProductResponse cheapestProductInCategory(ProductCategory category);

    public AddProductResponse costliestProductInCategory(ProductCategory category);

    public List<AddProductResponse> getProductsHavingPriceLessThanFromCat(String category, int price);

    void decreaseProductQuantity(Items item) throws Exception;
}
