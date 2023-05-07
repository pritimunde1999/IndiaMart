package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.OrderRequest;
import com.example.IndiaMart.Dto.response.ItemResponse;
import com.example.IndiaMart.Dto.response.OrderResponse;
import com.example.IndiaMart.Enums.ProductStatus;
import com.example.IndiaMart.Model.*;
import com.example.IndiaMart.Repository.CardRepository;
import com.example.IndiaMart.Repository.CustomerRepository;
import com.example.IndiaMart.Repository.OrdersRepository;
import com.example.IndiaMart.Repository.ProductRepository;
import com.example.IndiaMart.transformer.ItemTransformer;
import com.example.IndiaMart.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersService{

    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrdersRepository ordersRepository;


    public Orders placeOrder(Customer customer, Card card) throws Exception {
        Cart cart = customer.getCart();

        Orders order = new Orders();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String cardNo = card.getCardNumber();
        String maskedNo = generateMaskedCard(cardNo);
        order.setCardUsed(maskedNo);
        order.setCustomer(customer);

        List<Items> orderedItems = new ArrayList<>();
        for(Items item : cart.getItemsList())
        {
            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            }
            catch (Exception e)
            {
                throw new Exception("Product Out of stock");
            }
        }
        order.setItemsList(orderedItems);
        for(Items item : orderedItems)
        {
            item.setOrders(order);
        }
        order.setTotalValue(cart.getCartTotal());

        return order;
    }

    public String generateMaskedCard(String cardNo)
    {     String maskedNo = "";
        for(int i=0; i<cardNo.length()-4; i++)
        {
            maskedNo+= 'X';
        }

        maskedNo+= cardNo.substring(cardNo.length()-4);

        return maskedNo;
    }

    public OrderResponse placeOrder(OrderRequest orderRequest) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(orderRequest.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Invalid Customer Id");
        }

        Product product;
        try {
            product = productRepository.findById(orderRequest.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Invalid Product Id");
        }

        Card card = cardRepository.findByCardNumber(orderRequest.getCardUsed());

        //todays date
        Date date = new Date();

        if(card==null || card.getExpiryDate().compareTo(date)<0 || card.getCvv()!=orderRequest.getCvv() || (!card.getCustomer().equals(customer)))
        {
            throw new Exception("Invalid card");
        }

        Items item = Items.builder().requiredQuantity(orderRequest.getRequiredQuantity())
                .product(product).build();

        try{
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }

        Orders order = new Orders();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String cardNo = card.getCardNumber();
        String maskedNo = generateMaskedCard(cardNo);
        order.setCardUsed(maskedNo);
        order.setCustomer(customer);
        order.setTotalValue(orderRequest.getRequiredQuantity() * product.getPrice());
        order.getItemsList().add(item);

        customer.getOrders().add(order);
        item.setOrders(order);
        product.getItems().add(item);
        Orders savedOrders =ordersRepository.save(order);

        List<ItemResponse> itemResponses = new ArrayList<>();

        for(Items item1 : savedOrders.getItemsList())
        {
            ItemResponse itemResponse = ItemTransformer.itemToItemResponse(item1);
            itemResponses.add(itemResponse);
        }


        OrderResponse orderResponse = OrderTransformer.orderToOrderResponse(savedOrders,itemResponses);
        return orderResponse;

    }
}
