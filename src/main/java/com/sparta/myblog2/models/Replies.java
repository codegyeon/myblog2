package com.sparta.myblog2.models;

import com.sparta.myblog2.dto.RepliesRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Replies extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long num;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comments;

    //생성
    public Replies(RepliesRequestDto requestDto){
        this.num = requestDto.getNum();
        this.username = requestDto.getUsername();
        this.comments = requestDto.getComments();
    }

    //수정
    public void update(RepliesRequestDto requestDto){
        this.num = requestDto.getNum();
        this.username = requestDto.getUsername();
        this.comments = requestDto.getComments();
    }
}
