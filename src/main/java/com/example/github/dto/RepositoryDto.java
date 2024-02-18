package com.example.github.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class RepositoryDto {
    private String name;
    private String ownerLogin;
    private List<BranchDto> branches;


}
