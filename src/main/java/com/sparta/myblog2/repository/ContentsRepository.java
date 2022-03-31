package com.sparta.myblog2.repository;

import com.sparta.myblog2.models.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface ContentsRepository extends JpaRepository<Contents, Long> {
    //List<Contents> findAllByOrderByCreatedAtDesc();
    List<Contents> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
}