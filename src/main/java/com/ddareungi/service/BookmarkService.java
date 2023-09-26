package com.ddareungi.service;

import com.ddareungi.domain.Bookmark;
import com.ddareungi.domain.Station;
import com.ddareungi.domain.User;
import com.ddareungi.dto.bookmark.BookmarkResponseDto;
import com.ddareungi.dto.bookmark.BookmarkSaveDto;
import com.ddareungi.repository.bookmark.BookmarkRepository;
import com.ddareungi.repository.station.StationRepository;
import com.ddareungi.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final StationRepository stationRepository;

    public BookmarkResponseDto save(BookmarkSaveDto saveDto) {
        User user = userRepository.findById(saveDto.getUserId()).orElseThrow();
        Station station = stationRepository.findById(saveDto.getStationId()).orElseThrow();

        Bookmark bookmark = new Bookmark(user, station);
        Bookmark savedBookmark = bookmarkRepository.save(bookmark);

        return new BookmarkResponseDto(savedBookmark);
    }

    public BookmarkResponseDto delete(Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow();
        bookmarkRepository.delete(bookmark);
        return new BookmarkResponseDto(bookmark);
    }

    public BookmarkResponseDto findByUserIdAndStationId(Long userId, Long stationId) {
        Bookmark bookmark = bookmarkRepository.findByUserIdAndStationId(userId, stationId).orElse(null);
        if (bookmark == null) {
            return null;
        }
        return new BookmarkResponseDto(bookmark);
    }

}
