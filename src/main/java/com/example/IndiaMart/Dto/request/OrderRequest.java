package com.example.IndiaMart.Dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    int customerId;

    int productId;

    int requiredQuantity;
    String cardUsed;
    int cvv;
}
