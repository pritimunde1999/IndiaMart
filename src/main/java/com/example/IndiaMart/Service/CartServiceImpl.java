package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.CartRequest;
import com.example.IndiaMart.Dto.response.CartResponse;
import com.example.IndiaMart.Dto.response.ItemResponse;
import com.example.IndiaMart.Dto.response.OrderResponse;
import com.example.IndiaMart.Model.*;
import com.example.IndiaMart.Repository.CardRepository;
import com.example.IndiaMart.Repository.CartRepository;
import com.example.IndiaMart.Repository.CustomerRepository;
import com.example.IndiaMart.Repository.OrdersRepository;
import com.example.IndiaMart.transformer.ItemTransformer;
import com.example.IndiaMart.transformer.OrderTransformer;
import com.sun.mail.imap.protocol.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrdersRepository orderRepository;

    @Autowired
    ProductService productService;

//    @Autowired
//    private JavaMailSender emailSender;

    @Override
    public CartResponse saveCart(Items items, int customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        System.out.print(cart.getId());

        int newTotal = cart.getCartTotal() + items.getRequiredQuantity()*items.getProduct().getPrice();
        cart.setCartTotal(newTotal);
        cart.getItemsList().add(items);
        int noOfItems = cart.getNumberOfItems() + items.getRequiredQuantity();
        cart.setNumberOfItems(noOfItems);
//        customer.setCart(cart);
//        customerRepository.save(customer);

        cartRepository.save(cart);

        CartResponse cartResponse = CartResponse.builder().customerName(customer.getName()).totalCost(cart.getCartTotal())
                .numberOfItems(cart.getNumberOfItems()).build();

        List<ItemResponse> itemResponses = new ArrayList<>();

        for(Items item : cart.getItemsList())
        {
            ItemResponse itemResponse = ItemTransformer.itemToItemResponse(item);
            itemResponses.add(itemResponse);
        }

        cartResponse.setItemsList(itemResponses);
        return cartResponse;

    }

    @Override
    public OrderResponse checkOutCart(CartRequest cartRequest) throws Exception {
        Customer customer;
        try {
            customer =customerRepository.findById(cartRequest.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Invalid customer Id");
        }



        Card card = cardRepository.findByCardNumber(cartRequest.getCardUsed());

        //todays date
        Date date = new Date();

        if(card==null || card.getExpiryDate().compareTo(date)<0 || card.getCvv()!=cartRequest.getCvv() || (!card.getCustomer().equals(customer)))
        {
            throw new Exception("Invalid card");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0)
        {
            throw new Exception("Cart is Empty");
        }

        Orders savedOrder;
        try {
            Orders order = ordersService.placeOrder(customer, card);
            customer.getOrders().add(order);
            savedOrder = orderRepository.save(order);
            resetCart(cart);
            customerRepository.save(customer);
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage());
        }
        List<ItemResponse> itemResponses = new ArrayList<>();

        for(Items item : savedOrder.getItemsList())
        {
            ItemResponse itemResponse = ItemTransformer.itemToItemResponse(item);
            itemResponses.add(itemResponse);
        }



        OrderResponse orderResponse = OrderTransformer.orderToOrderResponse(savedOrder,itemResponses);

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("noreply.library.management.2023@gmail.com");
//        message.setTo(customer.getEmail());
//        message.setSubject("");
//       // message.setText(text);
//        emailSender.send(message);

        return orderResponse;
    }

    @Override
    public CartResponse removeFromCart(Items item, int customerId) throws Exception {

        return null;

    }

    public void resetCart(Cart cart)
    {
        cart.setCartTotal(0);
        cart.setNumberOfItems(0);
        for(Items item: cart.getItemsList()){
            item.setCart(null);
        }
        cart.getItemsList().clear();

    }
}
