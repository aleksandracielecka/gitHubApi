package com.example.github.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MyErrorResponseDto {
    private int status;
    private String message;
}
