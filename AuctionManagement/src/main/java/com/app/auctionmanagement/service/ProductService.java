package com.app.auctionmanagement.service;

import com.app.auctionmanagement.model.Product;
import com.app.auctionmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public void addProduct(Product product) {

        if (null == product.getProductName() || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Invalid product name.");
        }
        productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).get();

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }


}
