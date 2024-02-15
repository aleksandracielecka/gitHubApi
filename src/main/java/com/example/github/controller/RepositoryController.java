package com.example.github.controller;


import com.example.github.exception.UserNotFoundException;
import com.example.github.dto.Repository;
import com.example.github.service.GithubApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {

    private final GithubApiService githubApiService;

    public RepositoryController(GithubApiService githubApiService){
        this.githubApiService = githubApiService;
    }


    @GetMapping("/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username) {
        try {
            List<Repository> repositories = githubApiService.getUserRepositories(username);

            return ResponseEntity.ok(repositories);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse("User not found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse("Internal server error", e.getMessage()));
        }


    }

    private Map<String, String> errorResponse(String error, String message) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", error);
        errorMap.put("message", message);
        return errorMap;
    }
}
