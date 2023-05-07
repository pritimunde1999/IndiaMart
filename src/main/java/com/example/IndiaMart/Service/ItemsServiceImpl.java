package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.ItemRequest;
import com.example.IndiaMart.Enums.ProductStatus;
import com.example.IndiaMart.Model.Customer;
import com.example.IndiaMart.Model.Items;
import com.example.IndiaMart.Model.Product;
import com.example.IndiaMart.Repository.CustomerRepository;
import com.example.IndiaMart.Repository.ItemsRepository;
import com.example.IndiaMart.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsServiceImpl implements ItemsService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemsRepository itemsRepository;

    @Override
    public Items addItem(ItemRequest itemRequest) throws Exception {

        Customer customer;
        try {
            customer = customerRepository.findById(itemRequest.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Invalid Customer Id");
        }

        Product product;
        try {
            product = productRepository.findById(itemRequest.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Invalid Product Id");
        }

        if(itemRequest.getRequiredQuantity()>product.getQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE)
        {
            throw new Exception("Product is not available");
        }

        Items items = Items.builder().requiredQuantity(itemRequest.getRequiredQuantity()).build();
        items.setCart(customer.getCart());
        items.setProduct(product);

        product.getItems().add(items);
        return itemsRepository.save(items);

    }

    @Override
    public Items removeItem(int customerId, int itemId) throws Exception {
        return null;
    }

}
