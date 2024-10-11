package com.review.review_service.repository;

import com.review.review_service.entity.Product;
import com.review.review_service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
