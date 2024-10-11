package com.review.review_service.dto;

import com.review.review_service.entity.Review;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReviewResponseDto {
    private Long totalCount;  // 총 리뷰 개수
    private float score;    // 평균 점수
    private Long cursor;     // 다음 요청을 위한 커서 값
    private List<ReviewDto> reviews;  // 리뷰 리스트

    public ReviewResponseDto(Long totalCount, float score, Long cursor, List<Review> reviews) {
        this.totalCount = totalCount;
        this.score = score;
        this.cursor = cursor;
        // 리뷰 리스트를 ReviewDto로 변환
        this.reviews = reviews.stream()
                .map(ReviewDto::new)
                .collect(Collectors.toList());
    }

}
