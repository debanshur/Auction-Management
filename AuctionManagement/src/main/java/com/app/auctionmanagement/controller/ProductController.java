package com.app.auctionmanagement.controller;

import com.app.auctionmanagement.model.Product;
import com.app.auctionmanagement.payload.ProductRequest;
import com.app.auctionmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("@securityService.validateKey(#secret)")
    @PostMapping("/create")
    public void addProduct(@RequestHeader("secret") String secret,
                           @Valid @RequestBody ProductRequest request) {
        productService.createProduct(request);
    }

    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
}
