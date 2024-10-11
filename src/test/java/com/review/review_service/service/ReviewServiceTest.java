package com.review.review_service.service;


import com.review.review_service.entity.Product;
import com.review.review_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ReviewServiceTest {

    @Autowired
    private ProductRepository productRepository;


    // 테스트를 위한 데이터 초기화
    @BeforeEach
    public void init() {
        // 데이터 초기화 전에 기존 데이터를 지울 수 있음
        productRepository.deleteAll();

        // 상품 데이터 10개 추가
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Product product = new Product();
            product.setReviewCount(0L);
            product.setScore(0.0f);
            productRepository.save(product);
        });
    }

    @Test
    public void testProductCount() {
        // 저장된 상품 개수 확인 테스트
        long productCount = productRepository.count();
        assertEquals(10, productCount, "10개의 상품이 추가되어야 합니다.");
    }
}