package com.sparta.myblog2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentsRequestDto {
    private String title;
    private String name;
    private String contents;
}