package com.example.IndiaMart.Controller;

import com.example.IndiaMart.Dto.request.OrderRequest;
import com.example.IndiaMart.Dto.response.OrderResponse;
import com.example.IndiaMart.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
      @Autowired
      OrdersService ordersService;

      @PostMapping("/buyNow")
      public OrderResponse buyNow(@RequestBody OrderRequest orderRequest) throws Exception {
         return ordersService.placeOrder(orderRequest);
      }

      //get all orders of customer
      //get recent 5 orders
      //delete order from orderlist
      //select order with highest total value and also get customer name of that order

}
