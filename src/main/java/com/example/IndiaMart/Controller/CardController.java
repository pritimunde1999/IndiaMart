package com.example.IndiaMart.Controller;

import com.example.IndiaMart.Dto.request.CardRequest;
import com.example.IndiaMart.Dto.response.CardResponse;
import com.example.IndiaMart.Enums.CardType;
import com.example.IndiaMart.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequest cardRequest) {
        try {
            CardResponse cardResponse = cardService.addCard(cardRequest);
            return new ResponseEntity<>(cardResponse, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //get all visa cards
    @GetMapping("/getVisaCards")
    public List<CardResponse> getAllVisaCards()
    {
        return cardService.getAllVisaCards();
    }


    // get all mastercard cards is greater than 1st jan 2025
    @GetMapping("/getAllMasterCardGreaterThan1stJan2025")
    public List<CardResponse> getAllMasterCardGreaterThan1stJan2025()
    {
        return cardService.getAllMasterCardGreaterThan1stJan2025();
    }


    // return cardtype which has max no of cards
    @GetMapping("/cardTypeHavingMaxNoOfCards")
    public CardType cardTypeHavingMaxNoOfCards()
    {
        return cardService.cardTypeHavingMaxNoOfCards();
    }
}
