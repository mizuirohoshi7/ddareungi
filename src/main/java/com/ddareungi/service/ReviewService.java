package com.ddareungi.service;

import com.ddareungi.domain.Review;
import com.ddareungi.domain.Station;
import com.ddareungi.domain.User;
import com.ddareungi.dto.review.ReviewResponseDto;
import com.ddareungi.dto.review.ReviewSaveDto;
import com.ddareungi.repository.review.ReviewRepository;
import com.ddareungi.repository.station.StationRepository;
import com.ddareungi.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StationRepository stationRepository;

    public List<ReviewResponseDto> findAllByStationId(Long stationId) {
        List<Review> reviews = reviewRepository.findAllByStationId(stationId);
        List<ReviewResponseDto> responseDtos = new ArrayList<>();

        for (Review review : reviews) {
            responseDtos.add(new ReviewResponseDto(review));
        }

        return responseDtos;
    }

    public ReviewResponseDto save(Long userId, ReviewSaveDto saveDto) {
        User user = userRepository.findById(userId).orElseThrow();
        Station station = stationRepository.findById(saveDto.getStationId()).orElseThrow();

        Review review = Review.createReview(saveDto.getContent(), user, station);
        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDto(savedReview);
    }

    public ReviewResponseDto delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        reviewRepository.delete(review);
        return new ReviewResponseDto(review);
    }

}
