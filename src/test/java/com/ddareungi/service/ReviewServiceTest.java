package com.ddareungi.service;

import com.ddareungi.domain.Review;
import com.ddareungi.domain.Station;
import com.ddareungi.domain.User;
import com.ddareungi.dto.review.ReviewResponseDto;
import com.ddareungi.dto.review.ReviewSaveDto;
import com.ddareungi.repository.review.ReviewRepository;
import com.ddareungi.repository.station.StationRepository;
import com.ddareungi.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @InjectMocks
    ReviewService reviewService;

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    StationRepository stationRepository;

    static User user;
    static Station station;
    static Review review;

    final static Long userId = 3L;
    final static Long stationId = 5L;

    @BeforeAll
    static void beforeAll() {
        user = User.createUser("테스트 이름", "테스트 이메일", "테스트 프로필");
        station = Station.builder()
                .address("테스트 주소")
                .stationLat(0.0)
                .stationLong(0.0)
                .holdNumber("테스트 개수")
                .reviews(new ArrayList<>())
                .build();
        review = Review.createReview("테스트 내용", user, station);
    }

    @Test
    void stationId로_review_조회_성공() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(review);
        given(reviewRepository.findAllByStationId(stationId)).willReturn(reviews);

        List<ReviewResponseDto> responseDtos = reviewService.findAllByStationId(stationId);

        for (ReviewResponseDto responseDto : responseDtos) {
            assertThat(responseDto.getUserName()).isNotNull();
            assertThat(responseDto.getStationId()).isNull();
            assertThat(responseDto.getContent()).isNotNull();
            assertThat(responseDto.getCreatedAt()).isNotNull();
        }
    }

    @Test
    void review_저장_성공() {
        ReviewSaveDto saveDto = new ReviewSaveDto(stationId, "테스트 내용");
        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(stationRepository.findById(stationId)).willReturn(Optional.of(station));
        given(reviewRepository.save(any(Review.class))).willReturn(review);

        ReviewResponseDto responseDto = reviewService.save(userId, saveDto);

        assertThat(responseDto.getUserName()).isNotNull();
        assertThat(responseDto.getStationId()).isNull();
        assertThat(responseDto.getContent()).isNotNull();
        assertThat(responseDto.getCreatedAt()).isNotNull();
    }

    @Test
    void review_삭제_성공() {
        Long reviewId = 6L;
        given(reviewRepository.findById(any())).willReturn(Optional.of(review));

        ReviewResponseDto responseDto = reviewService.delete(reviewId);

        assertThat(responseDto.getUserName()).isNotNull();
        assertThat(responseDto.getStationId()).isNull();
        assertThat(responseDto.getContent()).isNotNull();
        assertThat(responseDto.getCreatedAt()).isNotNull();
    }

}