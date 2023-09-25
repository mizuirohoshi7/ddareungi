package com.ddareungi.controller;

import com.ddareungi.config.auth.SessionUser;
import com.ddareungi.dto.station.StationResponseDto;
import com.ddareungi.service.StationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final StationService stationService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            List<StationResponseDto> stations = stationService.findBookmarked(user.getId());
            model.addAttribute("user", user);
            model.addAttribute("stations", stations);
        }
        return "home";
    }

}
