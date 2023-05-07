package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.CardRequest;
import com.example.IndiaMart.Dto.response.CardResponse;
import com.example.IndiaMart.Enums.CardType;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CardService {

    public CardResponse addCard(CardRequest cardRequest) throws Exception;

    public List<CardResponse> getAllVisaCards();

    public List<CardResponse> getAllMasterCardGreaterThan1stJan2025();

    public CardType cardTypeHavingMaxNoOfCards();
}
