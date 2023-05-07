package com.example.IndiaMart.Dto.request;

import com.example.IndiaMart.Enums.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequest {

    String cardNumber;

    String cardHolderName;

    int cvv;
    Date expiryDate;

    CardType cardType;

    int customerId;
}
