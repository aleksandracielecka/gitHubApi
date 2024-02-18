package com.example.github.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GitHubErrorDto {

    private int status;
    private String message;

}
