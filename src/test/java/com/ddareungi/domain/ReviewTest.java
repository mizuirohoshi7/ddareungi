package com.ddareungi.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewTest {

    static Review review;

    @BeforeAll
    static void beforeAll() {
        String content = "테스트 내용";
        User user = new User();
        Station station = new Station();
        review = Review.createReview(content, user, station);
    }

    @Test
    void review_생성_성공() {
        assertThat(review.getId()).isNull();
        assertThat(review.getContent()).isNotNull();
        assertThat(review.getUser()).isNotNull();
        assertThat(review.getStation()).isNotNull();
        assertThat(review.getCreatedAt()).isNotNull();
        assertThat(review.getLastModifiedAt()).isNotNull();
    }

    @Test
    void review_수정_성공() {
        String updatedContent = "수정된 내용";
        LocalDateTime prevModifiedAt = review.getLastModifiedAt();

        review.update(updatedContent);

        assertThat(review.getContent()).isEqualTo("수정된 내용");
        assertThat(review.getLastModifiedAt()).isAfter(prevModifiedAt);
    }

}