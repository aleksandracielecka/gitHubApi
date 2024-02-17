package com.example.github.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommitDto {
    private String sha;
    private LocalDateTime date;

}
