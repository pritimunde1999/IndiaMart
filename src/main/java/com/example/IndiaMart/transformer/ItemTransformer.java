package com.example.IndiaMart.transformer;

import com.example.IndiaMart.Dto.response.ItemResponse;
import com.example.IndiaMart.Model.Items;

public class ItemTransformer {

    public static ItemResponse itemToItemResponse(Items item)
    {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setPriceOfItem(item.getProduct().getPrice());
        itemResponse.setQuantity(item.getRequiredQuantity());
        itemResponse.setTotalPrice(item.getRequiredQuantity() * item.getProduct().getPrice());
        itemResponse.setProductName(item.getProduct().getName());
        return itemResponse;
    }
}
