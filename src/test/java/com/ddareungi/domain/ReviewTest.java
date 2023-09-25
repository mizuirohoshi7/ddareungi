package com.ddareungi.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    }

}