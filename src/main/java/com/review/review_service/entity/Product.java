package com.review.review_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 상품의 총 리뷰 개수
    @Column(nullable = false)
    private Long reviewCount;

    // 상품의 평균 리뷰 점수
    @Column(nullable = false)
    private float score;

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
