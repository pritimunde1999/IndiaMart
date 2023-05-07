package com.example.IndiaMart.Dto.request;

import com.example.IndiaMart.Enums.ProductCategory;
import com.example.IndiaMart.Enums.ProductStatus;
import com.example.IndiaMart.Model.Seller;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddProductRequest {

    String productName;
    int price;
    int quantity;

    ProductCategory category;

    int sellerId;
}
