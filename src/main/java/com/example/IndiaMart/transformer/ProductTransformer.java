package com.example.IndiaMart.transformer;

import com.example.IndiaMart.Dto.request.AddProductRequest;
import com.example.IndiaMart.Dto.response.AddProductResponse;
import com.example.IndiaMart.Enums.ProductStatus;
import com.example.IndiaMart.Model.Product;

public class ProductTransformer {

    public static Product requestToProduct(AddProductRequest addProductRequest)
    {
        Product product = Product.builder().
                name(addProductRequest.getProductName())
                .quantity(addProductRequest.getQuantity())
                .price(addProductRequest.getPrice())
                .category(addProductRequest.getCategory())
                .productStatus(ProductStatus.AVAILABLE).
                build();

        return product;
    }

    public static AddProductResponse productToProductResponse(Product product)
    {
        AddProductResponse addProductResponse = AddProductResponse.builder().
                productName(product.getName())
                .sellerName(product.getSeller().getName())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        return addProductResponse;
    }
}
