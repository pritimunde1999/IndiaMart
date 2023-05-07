package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.CardRequest;
import com.example.IndiaMart.Dto.response.CardResponse;
import com.example.IndiaMart.Enums.CardType;
import com.example.IndiaMart.Model.Card;
import com.example.IndiaMart.Model.Customer;
import com.example.IndiaMart.Repository.CardRepository;
import com.example.IndiaMart.Repository.CustomerRepository;
import com.example.IndiaMart.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class CardServiceImpl implements CardService{

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;



    @Override
    public CardResponse addCard(CardRequest cardRequest) throws Exception {

        //check if customer exist or not
        Customer customer;
        try {
            customer = customerRepository.findById(cardRequest.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Customer does not Exist");
        }



        //check card already present or not
        if(cardRepository.findByCardNumber(cardRequest.getCardNumber())!=null)
        {
            throw new Exception("Card is already Added");
        }

        //add customer to card
        Card card = CardTransformer.cardRequestToCard(cardRequest);
        card.setCustomer(customer);

        //add card to customer
        customer.getCards().add(card);

        customerRepository.save(customer);

        CardResponse cardResponse = CardTransformer.cardToCardResponse(card);
        cardResponse.setMessage("Card Added");

        return cardResponse;
    }


    @Override
    public List<CardResponse> getAllVisaCards() {
        List<Card> cards = cardRepository.findAll();
        List<CardResponse> cardResponses = new ArrayList<>();

        for(Card card : cards)
        {
            if(card.getCardType().equals(CardType.VISA))
            {
                cardResponses.add(CardTransformer.cardToCardResponse(card));
            }

        }
        return cardResponses;
    }

    @Override
    public List<CardResponse> getAllMasterCardGreaterThan1stJan2025() {
        List<CardResponse> cardResponses = new ArrayList<>();
        List<Card> cards = cardRepository.findAll();

        for(Card card : cards)
        {
            Date date = card.getExpiryDate();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate= formatter.format(date);
            if(card.getCardType().equals(CardType.MASTERCARD) && strDate.compareTo("01/01/2025")>0)
            {
                cardResponses.add(CardTransformer.cardToCardResponse(card));
            }

        }
        return cardResponses;
    }

    @Override
    public CardType cardTypeHavingMaxNoOfCards() {
        List<Card> cards = cardRepository.findAll();
        int rupay=0,mastercard=0,visa=0,maestro=0;

        for(Card card : cards)
        {
            if(card.getCardType().equals(CardType.VISA)) visa++;
            if(card.getCardType().equals(CardType.MASTERCARD)) mastercard++;
            if(card.getCardType().equals(CardType.RUPAY)) rupay++;
            if(card.getCardType().equals(CardType.MAESTRO)) maestro++;
        }

        if(rupay>maestro && rupay>mastercard && rupay>visa) return CardType.RUPAY;
        if(visa>maestro && visa>mastercard && visa>rupay) return CardType.VISA;
        if(mastercard>visa && mastercard>rupay && mastercard>maestro) return CardType.MASTERCARD;
        else return CardType.MAESTRO;
    }
}
