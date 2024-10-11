package com.review.review_service.service;

import com.review.review_service.dto.ReviewRequestDto;
import com.review.review_service.dto.ReviewResponseDto;
import com.review.review_service.entity.Product;
import com.review.review_service.entity.Review;
import com.review.review_service.repository.ProductRepository;
import com.review.review_service.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;


    @Transactional
    public ResponseEntity<?> addReview(ReviewRequestDto reviewRequestDto, Long productId){
        Product product = productRepository.findById(productId).orElseThrow(()
                -> new EntityNotFoundException("존재하지 않는 상품"));


        Review review = new Review(reviewRequestDto.getUserId(),
                                   product,
                                   reviewRequestDto.getScore(),
                                   reviewRequestDto.getContent());

        reviewRepository.save(review);

        updateProductReviewStats(product, reviewRequestDto.getScore());

        return ResponseEntity.ok("리뷰가 성공적으로 등록되었습니다.");
    }



    public ResponseEntity<ReviewResponseDto> getReviews(Long productId, Long cursorId, int size) {
        List<Review> reviews;

        // cursor가 없는 경우 최신 리뷰 조회
        if (cursorId == null) {
            reviews = reviewRepository.findReviewsByProductId(productId, size);
        } else {
            // cursor가 있는 경우 해당 cursor 이후의 리뷰 조회
            reviews = reviewRepository.findReviewsByProductIdAndCursorId(productId, cursorId, size);
        }

        // 마지막 리뷰의 id 값을 cursor로 설정
        Long newCursorId = null;
        if (!reviews.isEmpty()) {
            // 마지막 리뷰의 id를 cursor로 설정
            newCursorId = reviews.get(reviews.size() - 1).getId();
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 상품"));


        // 총 리뷰 개수 product 에서 가져오기
        Long totalCount = product.getReviewCount();

        // 평균 score 점수 product 에서 가져오기
        float score = product.getScore();


        // 응답 DTO로 변환 (newCursorId를 cursor로 포함)
        ReviewResponseDto responseDto = new ReviewResponseDto(totalCount, score, newCursorId, reviews);

        // ResponseEntity로 반환
        return ResponseEntity.ok(responseDto);
    }

    private void updateProductReviewStats(Product product, int score) {
        // 총 리뷰 값 1 증가
        Long updatedReviewCnt = product.getReviewCount()+1;

        // 새로운 평균 점수 계산
        float updatedAvgScore = (product.getScore() * product.getReviewCount() + score) / updatedReviewCnt;

        product.setReviewCount(updatedReviewCnt);

        product.setScore(updatedAvgScore);
    }

}
