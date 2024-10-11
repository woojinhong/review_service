package com.review.review_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userId", "product_id"})
        },
        indexes = {
        @Index(name = "idx_review_product_id_created_at_id", columnList = "product_id, created_at, id")
}
)
public class Review extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 아이디
    @Column(nullable = false)
    private Long userId;

    // 상품 아이디(외래 키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 리뷰 점수
    @Column(nullable = false)
    private int score;

    // 리뷰 내용
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // 리뷰용 이미지
//    private String imageUrl;


    public Review(Long userId, Product product, int score, String content) {
        this.userId = userId;
        this.product = product;
        this.score = score;
        this.content = content;
//        this.imageUrl = imageUrl;
    }
}
