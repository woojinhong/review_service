package com.review.review_service.controller;

import com.review.review_service.dto.ReviewRequestDto;
import com.review.review_service.dto.ReviewResponseDto;
import com.review.review_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 등록 API
    @PostMapping("/{productId}/reviews")
    public ResponseEntity<?> addReview(@PathVariable Long productId,@RequestBody ReviewRequestDto reviewRequestDto) {
        return reviewService.addReview(reviewRequestDto,productId);
    }

    @GetMapping("/{productId}/reviews")
    public ResponseEntity<ReviewResponseDto> getReviews(
            @PathVariable Long productId,
            @RequestParam(required = false) Long cursor,  // cursor는 선택적 파라미터
            @RequestParam(defaultValue = "10") int size   // 기본 조회 사이즈는 10
    ){
        return reviewService.getReviews(productId, cursor, size);
    }
}
