package com.ddareungi.controller;

import com.ddareungi.config.auth.SessionUser;
import com.ddareungi.dto.review.ReviewResponseDto;
import com.ddareungi.dto.station.StationResponseDto;
import com.ddareungi.service.ReviewService;
import com.ddareungi.service.StationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;
    private final ReviewService reviewService;
    private final HttpSession session;

    @GetMapping
    public String stations(@RequestParam String address, @PageableDefault Pageable pageable, Model model) {
        Page<StationResponseDto> stations = stationService.search(address, pageable);

        int cur = pageable.getPageNumber();
        int start = Math.max(1, cur - 5);
        int last = Math.min(stations.getTotalPages(), cur + 5);

        model.addAttribute("stations", stations);
        model.addAttribute("address", address);
        model.addAttribute("cur", cur);
        model.addAttribute("start", start);
        model.addAttribute("last", last);

        return "station/searchList";
    }

    @GetMapping("/{id}")
    public String station(@PathVariable Long id, Model model) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }

        StationResponseDto station = stationService.findById(id);
        List<ReviewResponseDto> reviews = reviewService.findAllByStationId(id);

        model.addAttribute("station", station);
        model.addAttribute("reviews", reviews);
        return "station/detail";
    }

}
