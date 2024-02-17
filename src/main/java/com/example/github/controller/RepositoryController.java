package com.example.github.controller;


import com.example.github.exception.UserNotFoundException;
import com.example.github.dto.RepositoryDto;
import com.example.github.service.GithubApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController

public class RepositoryController {

    private final GithubApiService githubApiService;

    public RepositoryController(GithubApiService githubApiService){
        this.githubApiService = githubApiService;
    }

    @GetMapping("/repositories")
    public ResponseEntity<?> getAllPublicRepositories() {

        List<RepositoryDto> repositories = githubApiService.getAllPublicRepositories();

        return ResponseEntity.ok(repositories);

    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username) {
        try {
            List<RepositoryDto> repositories = githubApiService.getUserRepositories(username);

            return ResponseEntity.ok(repositories);
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
//        }


    }

    private Map<String, Object> errorResponse(int status, String message) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status",status);
        errorMap.put("message", message);
        return errorMap;
    }
}
