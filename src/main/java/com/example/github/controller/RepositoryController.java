package com.example.github.controller;


import com.example.github.exception.UserNotFoundException;
import com.example.github.dto.RepositoryDto;
import com.example.github.service.GithubApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RepositoryController {

    private final GithubApiService githubApiService;

    public RepositoryController(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }


    @GetMapping("/repositories/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username) {

            List<RepositoryDto> repositories = githubApiService.getUserRepositories(username);
            return ResponseEntity.ok(repositories);

    }

}
