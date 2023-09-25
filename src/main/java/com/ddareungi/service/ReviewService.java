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

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StationRepository stationRepository;

    public ReviewResponseDto save(Long userId, ReviewSaveDto saveDto) {
        User user = userRepository.findById(userId).orElseThrow();
        Station station = stationRepository.findById(saveDto.getStationId()).orElseThrow();

        Review review = Review.createReview(saveDto.getContent(), user, station);
        Review savedReview = reviewRepository.save(review);

        System.out.println("review.getUser() = " + review.getUser());
        System.out.println("savedReview.getUser() = " + savedReview.getUser());

        return new ReviewResponseDto(savedReview);
    }

}
