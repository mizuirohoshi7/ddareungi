package com.ddareungi.repository.review;

import com.ddareungi.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByStationId(Long stationId);

}
