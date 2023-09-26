package com.ddareungi.repository.bookmark;

import com.ddareungi.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findAllByUserId(Long userId);

    Optional<Bookmark> findByUserIdAndStationId(Long userId, Long stationId);

}