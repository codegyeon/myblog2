package com.sparta.myblog2.repository;

import com.sparta.myblog2.dto.RepliesRequestDto;
import com.sparta.myblog2.models.Replies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RepliesRepository extends JpaRepository<Replies, Long> {
    List<Replies> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
    List<Replies> findAllByNumAndModifiedAtBetweenOrderByModifiedAtDesc(Long num, LocalDateTime start, LocalDateTime end);
    List<Replies> findByNumOrderByModifiedAtDesc(Long num);
}
