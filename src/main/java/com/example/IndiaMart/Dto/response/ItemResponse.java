package com.example.IndiaMart.Dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponse {

    String productName;

    int priceOfItem;

    int totalPrice;

    int quantity;
}
