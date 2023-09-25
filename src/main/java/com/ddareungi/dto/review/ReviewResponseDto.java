package com.ddareungi.dto.review;

import com.ddareungi.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

    private String userName;
    private Long stationId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public ReviewResponseDto(Review review) {
        userName = review.getUser().getName();
        stationId = review.getStation().getId();
        content = review.getContent();
        createdAt = review.getCreatedAt();
        lastModifiedAt = review.getLastModifiedAt();
    }

}
