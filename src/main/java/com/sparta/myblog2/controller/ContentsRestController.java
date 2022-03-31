package com.sparta.myblog2.controller;


import com.sparta.myblog2.models.Contents;
import com.sparta.myblog2.repository.ContentsRepository;
import com.sparta.myblog2.dto.ContentsRequestDto;
import com.sparta.myblog2.security.UserDetailsImpl;
import com.sparta.myblog2.service.ContentsService;
import jdk.jfr.internal.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentsRestController {

    private final ContentsRepository ContentsRepository;
    private final ContentsService ContentsService;

    // 게시글 전체 조회
    @GetMapping("/api/contents")
    public List<Contents> getContents() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        return ContentsRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
    }

    // 게시글 특정 조회
    @GetMapping("/api/contents/{id}")
    public Contents getContents(@PathVariable Long id) {
        Contents contents =  ContentsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        return contents;
    }

    // 게시글 생성
    @PostMapping("/api/contents")
    public Contents createContents(@RequestBody ContentsRequestDto requestDto ,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setName(userDetails.getUsername());
        Contents Contents = new Contents(requestDto);

        return ContentsRepository.save(Contents);
    }

    // 게시글 수정
    @PutMapping("/api/contents/{id}")
    public Long updateContents(@PathVariable Long id, @RequestBody ContentsRequestDto requestDto) {
        ContentsService.update(id, requestDto);
        return id;
    }

    // 게시글 삭제
    @DeleteMapping("/api/delete/{id}")
    public Boolean deleteContents(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails ) {

        Contents contents = ContentsRepository.findById(id).get();
        if(contents.getName().equals(userDetails.getUsername())){
            ContentsRepository.deleteById(id);

            return true;
        }
        //if (name == userDetails.getUsername()) {


        return false;
    }

}