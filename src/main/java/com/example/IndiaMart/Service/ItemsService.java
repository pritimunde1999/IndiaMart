package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.ItemRequest;
import com.example.IndiaMart.Model.Items;

public interface ItemsService {

       public Items addItem(ItemRequest itemRequest) throws Exception;
       public Items removeItem(int customerId, int itemId) throws Exception;
}
