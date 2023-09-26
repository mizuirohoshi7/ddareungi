package com.ddareungi.controller;

import com.ddareungi.dto.bookmark.BookmarkResponseDto;
import com.ddareungi.dto.bookmark.BookmarkSaveDto;
import com.ddareungi.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/bookmarks")
@RestController
public class BookmarkApiController {

    private final BookmarkService bookmarkService;

    @PostMapping("/save")
    public BookmarkResponseDto save(@RequestBody BookmarkSaveDto saveDto) {
        return bookmarkService.save(saveDto);
    }

    @DeleteMapping("/{bookmarkId}")
    public BookmarkResponseDto delete(@PathVariable Long bookmarkId) {
        return bookmarkService.delete(bookmarkId);
    }

}
