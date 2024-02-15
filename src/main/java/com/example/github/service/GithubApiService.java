package com.example.github.service;

import com.example.github.dto.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubApiService {
    private final String GITHUB_API_URL = "https://api.github.com/users/";


    public List<Repository> getUserRepositories(String username) {

        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = UriComponentsBuilder.fromHttpUrl(GITHUB_API_URL + username + "/repos")
                .build()
                .toUriString();

        Repository[] repositories = restTemplate.getForObject(apiUrl, Repository[].class);

        return Arrays.asList(repositories);
    }

}
