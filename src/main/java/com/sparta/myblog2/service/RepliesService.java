package com.sparta.myblog2.service;

import com.sparta.myblog2.dto.ContentsRequestDto;
import com.sparta.myblog2.dto.RepliesRequestDto;
import com.sparta.myblog2.models.Contents;
import com.sparta.myblog2.models.Replies;
import com.sparta.myblog2.repository.ContentsRepository;
import com.sparta.myblog2.repository.RepliesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RepliesService {
    private final RepliesRepository repliesRepository;

    @Transactional
    public Long update(Long id, RepliesRequestDto requestDto) {
        Replies Replies = repliesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Replies.update(requestDto);
        return Replies.getId();
    }
}
