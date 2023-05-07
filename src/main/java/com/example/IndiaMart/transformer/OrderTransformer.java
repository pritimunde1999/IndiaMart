package com.example.IndiaMart.transformer;

import com.example.IndiaMart.Dto.response.ItemResponse;
import com.example.IndiaMart.Dto.response.OrderResponse;
import com.example.IndiaMart.Model.Orders;

import java.util.List;

public class OrderTransformer {

    public static OrderResponse orderToOrderResponse(Orders savedOrder, List<ItemResponse> itemResponses)
    {
        OrderResponse orderResponse = OrderResponse.builder().orderDate(savedOrder.getOrderDate())
                .orderNo(savedOrder.getOrderNo())
                .totalValue(savedOrder.getTotalValue())
                .cardUsed(savedOrder.getCardUsed())
                .customerName(savedOrder.getCustomer().getName())
                .itemsList(itemResponses)
                .build();
        return orderResponse;
    }
}
