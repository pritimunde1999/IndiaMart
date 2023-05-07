package com.example.IndiaMart.Dto.response;

import com.example.IndiaMart.Model.Items;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponse {

    String customerName;
    List<ItemResponse> itemsList;
    int totalCost;
    int numberOfItems;

}

