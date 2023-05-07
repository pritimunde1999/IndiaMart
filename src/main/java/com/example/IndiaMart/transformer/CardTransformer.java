package com.example.IndiaMart.transformer;

import com.example.IndiaMart.Dto.request.CardRequest;
import com.example.IndiaMart.Dto.response.CardResponse;
import com.example.IndiaMart.Model.Card;

public class CardTransformer {

    public static Card cardRequestToCard(CardRequest cardRequest)
    {
        return Card.builder().cardNumber(cardRequest.getCardNumber())
                .cardHolderName(cardRequest.getCardHolderName())
                .cvv(cardRequest.getCvv())
                .expiryDate(cardRequest.getExpiryDate())
                .cardType(cardRequest.getCardType())
                .build();
    }

    public static CardResponse cardToCardResponse(Card card)
    {
        return CardResponse.builder().cardNumber(card.getCardNumber()).customerId(card.getCustomer().getId()).build();
    }
}
