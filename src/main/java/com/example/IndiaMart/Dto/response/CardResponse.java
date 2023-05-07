package com.example.IndiaMart.Dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponse {

    String cardNumber;
    int customerId;

    String message;
}
