package com.sparta.myblog2.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RepliesRequestDto {
    private Long num;
    private String username;
    private String comments;


}
