package com.ddareungi.controller;

import com.ddareungi.config.auth.SessionUser;
import com.ddareungi.dto.review.ReviewResponseDto;
import com.ddareungi.dto.review.ReviewSaveDto;
import com.ddareungi.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewApiController {

    private final ReviewService reviewService;
    private final HttpSession session;

    @PostMapping("/save")
    public ReviewResponseDto save(@RequestBody @Validated ReviewSaveDto saveDto) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        return reviewService.save(user.getId(), saveDto);
    }

    @DeleteMapping("/{reviewId}")
    public ReviewResponseDto delete(@PathVariable Long reviewId) {
        return reviewService.delete(reviewId);
    }

}
