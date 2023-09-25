package com.ddareungi.dto.review;

import com.ddareungi.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

    private Long reviewId;
    private Long userId;
    private Long stationId;
    private String userName;
    private String content;
    private LocalDateTime createdAt;

    public ReviewResponseDto(Review review) {
        reviewId = review.getId();
        userId = review.getUser().getId();
        stationId = review.getStation().getId();
        userName = review.getUser().getName();
        content = review.getContent();
        createdAt = review.getCreatedAt();
    }

}
