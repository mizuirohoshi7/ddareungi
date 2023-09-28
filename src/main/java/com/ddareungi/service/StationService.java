package com.ddareungi.service;

import com.ddareungi.domain.Bookmark;
import com.ddareungi.domain.Station;
import com.ddareungi.dto.station.StationResponseDto;
import com.ddareungi.repository.bookmark.BookmarkRepository;
import com.ddareungi.repository.station.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StationService {

    private final StationRepository stationRepository;
    private final BookmarkRepository bookmarkRepository;

    public Page<StationResponseDto> search(String address, Pageable pageable) {
        return stationRepository.search(address, pageable)
                .map(StationResponseDto::new);
    }

    public StationResponseDto findById(Long id) {
        Station station = stationRepository.findById(id).orElseThrow();
        return new StationResponseDto(station);
    }

    public List<StationResponseDto> findBookmarked(Long userId) {
        List<Bookmark> bookmarks = bookmarkRepository.findAllByUserId(userId);

        List<StationResponseDto> responseDtos = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            responseDtos.add(new StationResponseDto(bookmark.getStation()));
        }

        return responseDtos;
    }

}
