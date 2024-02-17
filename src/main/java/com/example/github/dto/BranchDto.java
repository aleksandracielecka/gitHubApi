package com.example.github.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BranchDto {

    private String name;
    private String lastCommitSha;
}
