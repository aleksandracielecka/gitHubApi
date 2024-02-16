package com.example.github.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RepositoryDto {
    private String name;
    private String ownerLogin;
    private List<BranchDto> branches;
}
