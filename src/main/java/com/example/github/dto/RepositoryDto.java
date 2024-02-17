package com.example.github.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class RepositoryDto {
    private String name;
    private String ownerLogin;
    private List<BranchDto> branches;


    public RepositoryDto(String name) {
        this.name = name;
    }
}
