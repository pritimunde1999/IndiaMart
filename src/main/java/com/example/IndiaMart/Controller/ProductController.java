package com.example.IndiaMart.Controller;

import com.example.IndiaMart.Dto.request.AddProductRequest;
import com.example.IndiaMart.Dto.response.AddProductResponse;
import com.example.IndiaMart.Enums.ProductCategory;
import com.example.IndiaMart.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public AddProductResponse addProduct(@RequestBody AddProductRequest addProductRequest) throws Exception {

           return productService.addProduct(addProductRequest);

    }

    @GetMapping("/get/{category}")
    public List<AddProductResponse> getProductOfCategory(@PathVariable("category") ProductCategory category)
    {
        return productService.getProductOfSameCategory(category);
    }

    //get products by seller email
    @GetMapping("/getBySellerEmail")
    public List<AddProductResponse> getBySellerEmail(@RequestParam("email") String email) throws Exception {
        return productService.getBySellerEmail(email);
    }


    //delete product by seller id & product id
    @DeleteMapping("/deleteBySellerAndProductId")
    public String deleteBySellerAndProductId(@RequestParam("sellerId") int sellerId, @RequestParam("productId") int productId) throws Exception {
        return productService.deleteBySellerAndProductId(sellerId,productId);
    }


    //return top 5 cheapest product
    @GetMapping("/top5CheapestProducts")
    public List<AddProductResponse> top5CheapestProducts()
    {
          return productService.top5CheapestProducts();
    }

    //return top 5 costliest product
    @GetMapping("/top5CostliestProducts")
    public List<AddProductResponse> top5CostliestProducts()
    {
        return productService.top5CostliestProducts();
    }

    //return all availbale products
    @GetMapping("/allAvailableProducts")
    public List<AddProductResponse> allAvailableProducts()
    {
        return productService.allAvailableProducts();
    }


    //all out od stock product
    @GetMapping("/allOutOfStockProducts")
    public List<String> allOutOfStockProducts()
    {
        return productService.allOutOfStockProducts();
    }


    // return product quantity less than 10
    @GetMapping("/quantityLessThan10")
    public List<AddProductResponse> quantityLessThan10()
    {
         return productService.quantityLessThan10();
    }


    //cheapest product in particular cateogry
    @GetMapping("/cheapestProductInCategory")
    public AddProductResponse cheapestProductInCategory(@RequestParam("category") ProductCategory category)
    {
        return productService.cheapestProductInCategory(category);
    }


    // costliest in category
    @GetMapping("/costliestProductInCategory")
    public AddProductResponse costliestProductInCategory(@RequestParam("category") ProductCategory category)
    {
        return productService.costliestProductInCategory(category);
    }


    //get product of category whose price less than given price
    @GetMapping("/getProductsHavingPriceLessThanFromCat/{category}/{price}")
    public List<AddProductResponse> getProductsHavingPriceLessThanFromCat(@PathVariable("category") String category,
                                                                          @PathVariable("price") int price)
    {
           return productService.getProductsHavingPriceLessThanFromCat(category,price);
    }
}
