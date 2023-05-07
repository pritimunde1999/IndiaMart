package com.example.IndiaMart.Service;

import com.example.IndiaMart.Dto.request.AddProductRequest;
import com.example.IndiaMart.Dto.response.AddProductResponse;
import com.example.IndiaMart.Enums.ProductCategory;
import com.example.IndiaMart.Enums.ProductStatus;
import com.example.IndiaMart.Model.Items;
import com.example.IndiaMart.Model.Orders;
import com.example.IndiaMart.Model.Product;
import com.example.IndiaMart.Model.Seller;
import com.example.IndiaMart.OrderByPrice;
import com.example.IndiaMart.Repository.ProductRepository;
import com.example.IndiaMart.Repository.SellerRepository;
import com.example.IndiaMart.transformer.ProductTransformer;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;


    public AddProductResponse addProduct(AddProductRequest addProductRequest) throws Exception {
        Seller seller;
        try{
             seller =  sellerRepository.findById(addProductRequest.getSellerId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Seller does not exist");
        }

        Product product = ProductTransformer.requestToProduct(addProductRequest);
        product.setSeller(seller);
        seller.getProductList().add(product);
        sellerRepository.save(seller);

        AddProductResponse addProductResponse = ProductTransformer.productToProductResponse(product);
        return addProductResponse;

    }

    //find products of perticular category
    public List<AddProductResponse> getProductOfSameCategory(ProductCategory category)
    {
         List<Product> products = productRepository.findByCategory(category);
         List<AddProductResponse> addProductResponseList = new ArrayList<>();

         for(Product product : products)
         {
             addProductResponseList.add(ProductTransformer.productToProductResponse(product));
         }

         return addProductResponseList;
    }

    @Override
    public List<AddProductResponse> getBySellerEmail(String email) throws Exception {
      Seller seller;
      try {
          seller =sellerRepository.findByEmail(email);
      }
      catch (Exception e)
      {
          throw new Exception("Seller does not exists");
      }

        List<Product> products = seller.getProductList();
        List<AddProductResponse> addProductResponseList = new ArrayList<>();

      for (Product product : products)
      {
          addProductResponseList.add(ProductTransformer.productToProductResponse(product));
      }

      return addProductResponseList;
    }

    @Override
    public String deleteBySellerAndProductId(int sellerId, int productId) throws Exception {
        Seller seller;
        try {
            seller = sellerRepository.findById(sellerId).get();
        }
        catch (Exception e)
        {
            throw new Exception("Seller does not exist");
        }

        productRepository.deleteById(productId);
        List<Product> products = seller.getProductList();
        for(Product product : products)
        {
            if(product.getId()==productId)
            {
                products.remove(product);
            }
        }
        seller.setProductList(products);
        sellerRepository.save(seller);
        return "Product deleted";
    }

    @Override
    public List<AddProductResponse> top5CheapestProducts() {

        List<Product> list = productRepository.findAll();
        Collections.sort(list,new OrderByPrice());
        List<AddProductResponse> list1 = new ArrayList<>();
        for(Product product : list)
        {
            if(list1.size()==5)
            {
                break;
            }
           list1.add(ProductTransformer.productToProductResponse(product));
        }
        return list1;
    }

    @Override
    public List<AddProductResponse> top5CostliestProducts() {
        List<Product> list = productRepository.findAll();
        Collections.sort(list,new OrderByPrice());

        List<AddProductResponse> list1 = new ArrayList<>();

        for(int i=list.size()-1; i>= list.size()-5; i--)
        {
            list1.add(ProductTransformer.productToProductResponse(list.get(i)));
        }
        return list1;
    }

    @Override
    public List<AddProductResponse> allAvailableProducts() {
        List<Product> list = productRepository.findAll();
        List<AddProductResponse> availableProducts = new ArrayList<>();
        for(Product product: list)
        {
            if(product.getProductStatus().equals(ProductStatus.AVAILABLE))
            {
                availableProducts.add(ProductTransformer.productToProductResponse(product));
            }
        }

        return availableProducts;
    }

    @Override
    public List<String> allOutOfStockProducts() {
        List<Product> list = productRepository.findAll();
        List<String> list1 = new ArrayList<>();

        for(Product product : list)
        {
            if(product.getProductStatus().equals(ProductStatus.OUT_OF_STOCK))
            {
                list1.add(product.getName());
            }
        }

        return list1;
    }

    @Override
    public List<AddProductResponse> quantityLessThan10() {
        List<Product> list = productRepository.findAll();
        List<AddProductResponse> addProductResponseList = new ArrayList<>();

        for(Product product: list)
        {
            if(product.getQuantity()<10)
            {
                addProductResponseList.add(ProductTransformer.productToProductResponse(product));
            }
        }

        return addProductResponseList;
    }

    @Override
    public AddProductResponse cheapestProductInCategory(ProductCategory category) {
        List<Product> products = productRepository.findByCategory(category);
        Collections.sort(products,new OrderByPrice());

        return ProductTransformer.productToProductResponse(products.get(0));

    }

    @Override
    public AddProductResponse costliestProductInCategory(ProductCategory category) {
        List<Product> products = productRepository.findByCategory(category);
        Collections.sort(products,new OrderByPrice());

        return ProductTransformer.productToProductResponse(products.get(products.size()-1));
    }

    @Override
    public List<AddProductResponse> getProductsHavingPriceLessThanFromCat(String category, int price) {

        List<Product> products =  productRepository.getProductsHavingPriceLessThanFromCat(category,price);

        List<AddProductResponse> addProductResponseList = new ArrayList<>();

        for(Product product: products)
        {
            addProductResponseList.add(ProductTransformer.productToProductResponse(product));

        }

        return addProductResponseList;
    }

    @Override
    public void decreaseProductQuantity(Items item) throws Exception {


            Product product = item.getProduct();
            int quantity = item.getRequiredQuantity();
            int currentQuantity = product.getQuantity();

            if(currentQuantity<quantity) {
                throw new Exception("out of stock");
            }
            product.setQuantity(currentQuantity-quantity);
            if(product.getQuantity()==0)
            {
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
        }
    }



