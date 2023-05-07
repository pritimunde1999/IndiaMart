package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.CartRequest;
import com.example.IndiaMart.Dto.response.CartResponse;
import com.example.IndiaMart.Dto.response.OrderResponse;
import com.example.IndiaMart.Model.Items;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CartService {

    public CartResponse saveCart(Items items, int customerId);

    public OrderResponse checkOutCart(CartRequest cartRequest) throws Exception;

    public CartResponse removeFromCart(Items item, int customerId) throws Exception;
}
