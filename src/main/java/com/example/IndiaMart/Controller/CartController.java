package com.example.IndiaMart.Controller;

import com.example.IndiaMart.Dto.request.CartRequest;
import com.example.IndiaMart.Dto.request.ItemRequest;
import com.example.IndiaMart.Dto.response.CartResponse;
import com.example.IndiaMart.Dto.response.OrderResponse;
import com.example.IndiaMart.Model.Items;
import com.example.IndiaMart.Service.CartService;
import com.example.IndiaMart.Service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemsService itemsService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequest itemRequest)
    {
        try {
            Items items = itemsService.addItem(itemRequest);
            CartResponse cartResponse = cartService.saveCart(items,itemRequest.getCustomerId());
            return new ResponseEntity<>(cartResponse,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/checkout")
    public OrderResponse checkOutCart(@RequestBody CartRequest cartRequest) throws Exception {
         return cartService.checkOutCart(cartRequest);
    }

    //remove from cart
    //view all items in cart

}
