package com.example.github.service;

import com.example.github.dto.MyRepositoryResponseDto;
import com.example.github.dto.RepositoryDto;
import com.example.github.exception.UserNotFoundException;
import com.example.github.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GithubApiService {
    private final String GITHUB_API_URL = "https://api.github.com/users/";
    private final String GITHUB_API_URL_ALL = "https://api.github.com/repositories";
    private final RestTemplate restTemplate;
    private final MyMapper myMapper;
    private final String githubAccessToken;

    @Autowired
    public GithubApiService(RestTemplate restTemplate, MyMapper myMapper, @Value("${github.access.token}")String githubAccessToken) {
        this.restTemplate = restTemplate;
        this.myMapper = myMapper;
        this.githubAccessToken = githubAccessToken;

    }



    public List<RepositoryDto> getUserRepositories(String username){

        String apiUrl = UriComponentsBuilder.fromHttpUrl(GITHUB_API_URL + username + "/repos")
                .queryParam("type", "owner")
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + githubAccessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<RepositoryDto[]> responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    entity,
                    RepositoryDto[].class
            );


            RepositoryDto[] repositories = responseEntity.getBody();


            if (repositories != null) {
                return Arrays.asList(repositories);
            } else {
                return Collections.emptyList();
            }
        }catch (HttpClientErrorException.NotFound exception){
            throw new UserNotFoundException("User not found", exception);
        }


    }

    public List<RepositoryDto> getAllPublicRepositories() {
        String apiUrl = UriComponentsBuilder.fromHttpUrl(GITHUB_API_URL_ALL)
                .build()
                .toUriString();

        ResponseEntity<RepositoryDto[]> responseEntity = restTemplate.getForEntity(
                apiUrl,
                RepositoryDto[].class
        );

        RepositoryDto[] repositories = responseEntity.getBody();

        if (repositories != null) {
            return Arrays.asList(repositories);
        } else {
            return Collections.emptyList();
        }
}

    }



