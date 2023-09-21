package com.ddareungi.controller;

import com.ddareungi.dto.station.StationResponseDto;
import com.ddareungi.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    @GetMapping
    public String stations(@RequestParam String address, @PageableDefault Pageable pageable, Model model) {
        List<StationResponseDto> stations = stationService.search(address, pageable);
        model.addAttribute("stations", stations);
        return "station/searchList";
    }

    @GetMapping("/{id}")
    public String station(@PathVariable Long id, Model model) {
        StationResponseDto station = stationService.findById(id);
        model.addAttribute("station", station);
        return "station/detail";
    }

}
