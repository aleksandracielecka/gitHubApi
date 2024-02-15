package com.example.github.service;

import com.example.github.dto.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GithubApiService {
    private final String GITHUB_API_URL = "https://api.github.com/users/";

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Repository> getUserRepositories(String username) {


        String apiUrl = UriComponentsBuilder.fromHttpUrl(GITHUB_API_URL + username + "/repos")
                .queryParam("type", "owner")
                .build()
                .toUriString();

        Repository[] repositories = restTemplate.getForObject(apiUrl, Repository[].class);

        if (repositories != null) {
            return Arrays.asList(repositories);
        } else {
            return Collections.emptyList();
        }

    }
}