package com.example.IndiaMart.Dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {

    int customerId;

    String cardUsed;

    int cvv;


}
