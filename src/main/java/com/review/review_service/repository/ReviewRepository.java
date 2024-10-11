package com.review.review_service.repository;

import com.review.review_service.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 최신순으로 리뷰 조회 (cursorId보다 작은 리뷰)
    @Query(value = "SELECT * FROM review WHERE product_id = :productId AND id < :cursorId ORDER BY created_at DESC LIMIT :size", nativeQuery = true)
    List<Review> findReviewsByProductIdAndCursorId(
            Long productId,
            Long cursorId,
            int size

    );


    // 처음 조회할 때 cursorId 없이 최신 리뷰를 조회
    @Query(value = "SELECT * FROM review WHERE product_id = :productId ORDER BY created_at DESC LIMIT :size", nativeQuery = true)
    List<Review> findReviewsByProductId(
            Long productId,
            int size
    );

}
