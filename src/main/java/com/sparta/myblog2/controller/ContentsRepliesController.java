package com.sparta.myblog2.controller;

import com.sparta.myblog2.dto.ContentsRequestDto;
import com.sparta.myblog2.dto.RepliesRequestDto;
import com.sparta.myblog2.models.Contents;
import com.sparta.myblog2.models.Replies;
import com.sparta.myblog2.repository.ContentsRepository;
import com.sparta.myblog2.repository.RepliesRepository;
import com.sparta.myblog2.security.UserDetailsImpl;
import com.sparta.myblog2.service.ContentsService;
import com.sparta.myblog2.service.RepliesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentsRepliesController {

    private final RepliesRepository RepliesRepository;
    private final RepliesService RepliesService;

    // 게시글 특정의 댓글 전체 조회
    @GetMapping("/api/Replies/{id}")
    public List<Replies> getReplies(@PathVariable Long id) {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        //Long num = id;
        System.out.println(RepliesRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start,end));
        System.out.println(RepliesRepository.findAllByNumAndModifiedAtBetweenOrderByModifiedAtDesc(id,start,end));
        System.out.println(RepliesRepository.findByNumOrderByModifiedAtDesc(id));
        return RepliesRepository.findByNumOrderByModifiedAtDesc(id);
    }

    // 댓글 생성
    @PostMapping("/api/Replies")
    public Replies createReplies(@RequestBody RepliesRequestDto requestDto , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setUsername(userDetails.getUsername());
        Replies replies = new Replies(requestDto);

        return RepliesRepository.save(replies);
    }

    // 댓글 수정
    @PutMapping("/api/Replies/{id}")
    public Long updateReplies(@PathVariable Long id, @RequestBody RepliesRequestDto requestDto) {
        RepliesService.update(id, requestDto);
        return id;
    }

    // 댓글 삭제
    @DeleteMapping("/api/Replies/{id}")
    public Long deleteReplies(@PathVariable Long id) {
        RepliesRepository.deleteById(id);
        return id;
    }
}
