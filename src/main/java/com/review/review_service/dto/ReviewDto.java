package com.review.review_service.dto;

import com.review.review_service.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewDto {
    private Long id;
    private Long userId;
    private int score;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;

    // Review 엔티티에서 필요한 데이터만 추출
    public ReviewDto(Review review) {
        this.id = review.getId();
        this.userId = review.getUserId();
        this.score = review.getScore();
        this.content = review.getContent();
//        this.imageUrl = review.getImageUrl();
        this.createdAt = review.getCreatedAt();
    }
}
