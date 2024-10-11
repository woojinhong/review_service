package com.review.review_service.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ReviewRequestDto {

    private Long userId;
    private int score;
    private String content;
    private MultipartFile image;

    public ReviewRequestDto(Long userId, int score, String content, MultipartFile image) {
        this.userId = userId;
        this.score = score;
        this.content = content;
        this.image = image;
    }
}
