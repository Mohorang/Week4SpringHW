package com.sparta.week4hw.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class boardRequestDto {
    private String username;
    private String contents;
    private String password;
    private String title;
}
