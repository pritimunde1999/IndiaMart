package com.example.IndiaMart.Dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponse {

    String orderNo;
    int totalValue;
    Date orderDate;
    String cardUsed;
    String customerName;
    List<ItemResponse> itemsList;

}
