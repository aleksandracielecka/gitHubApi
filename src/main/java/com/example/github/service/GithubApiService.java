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

    // Metoda do pobierania informacji o repozytoriach użytkownika z GitHub API
    public List<Repository> getUserRepositories(String username) {
        // Tworzymy instancję RestTemplate do wykonywania zapytań HTTP
        RestTemplate restTemplate = new RestTemplate();

        // Budujemy URL do zapytania HTTP
        String apiUrl = UriComponentsBuilder.fromHttpUrl(GITHUB_API_URL + username + "/repos")
                .build()
                .toUriString();

        // Wykonujemy zapytanie HTTP GET do GitHub API
        Repository[] repositories = restTemplate.getForObject(apiUrl, Repository[].class);

        // Zwracamy listę repozytoriów
        return Arrays.asList(repositories);
    }

}
