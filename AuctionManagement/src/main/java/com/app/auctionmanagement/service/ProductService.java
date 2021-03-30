package com.app.auctionmanagement.service;

import com.app.auctionmanagement.model.Product;
import com.app.auctionmanagement.payload.ProductRequest;
import com.app.auctionmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public void createProduct(ProductRequest productRequest) {

        if (Objects.isNull(productRequest.getProductName())
                || productRequest.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Invalid product name.");
        }

        if (productRepository.existsByProductName(productRequest.getProductName())) {
            throw new IllegalArgumentException("Product already present");
        }
        Product product = new Product(productRequest.getProductName(), productRequest.getDescription(), false);
        productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).get();

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }


}
