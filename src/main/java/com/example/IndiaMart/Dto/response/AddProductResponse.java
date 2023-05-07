package com.example.IndiaMart.Dto.response;

import com.example.IndiaMart.Enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AddProductResponse {

    String productName;
    String sellerName;
    ProductStatus productStatus;
    int price;
    int quantity;


}
