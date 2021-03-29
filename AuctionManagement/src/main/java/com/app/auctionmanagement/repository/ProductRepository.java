package com.app.auctionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.auctionmanagement.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductName(String productName);
}
