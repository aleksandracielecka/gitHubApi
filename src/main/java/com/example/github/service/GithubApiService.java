package com.example.github.service;

import com.example.github.dto.BranchDto;
import com.example.github.dto.RepositoryDto;
import com.example.github.exception.RepositoryNotFoundException;
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


import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Service
public class GithubApiService {
    private final String GITHUB_API_URL = "https://api.github.com/users/";
    private final String GITHUB_API_URL_BRANCHES = "https://api.github.com/repos/";
    private final RestTemplate restTemplate;
    private final MyMapper myMapper;
    private final String githubAccessToken;


    public GithubApiService(RestTemplate restTemplate, MyMapper myMapper, @Value("${github.access.token}") String githubAccessToken) {
        this.restTemplate = restTemplate;
        this.myMapper = myMapper;
        this.githubAccessToken = githubAccessToken;
    }

    public List<RepositoryDto> getUserRepositories(String username) {

        String apiUrl = UriComponentsBuilder.fromHttpUrl(GITHUB_API_URL + username + "/repos")
                .queryParam("type", "owner")
                .build()
                .toUriString();

        HttpEntity<String> entity = getStringHttpEntity();


        ResponseEntity<List<RepositoryDto>> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );
        List<RepositoryDto> repositories = responseEntity.getBody();

        if (isNull(repositories)) {
            return Collections.emptyList();
        }
        repositories = repositories.stream()
                .map(repo -> {
                    repo.setOwnerLogin(username);
                    List<BranchDto> branches = getBranchesForRepository(username, repo.getName());
                    repo.setBranches(branches);
                    return repo;
                })
                .collect(Collectors.toList());

        return myMapper.mapToMyRepositoryResponseDto(repositories).getRepositories();


    }


    public List<BranchDto> getBranchesForRepository(String username, String repositoryName) {
        String branchesUrl = GITHUB_API_URL_BRANCHES + username + "/" + repositoryName + "/branches";
        HttpEntity<String> entity = getStringHttpEntity();

        ResponseEntity<List<BranchDto>> branchesResponse = restTemplate.exchange(
                branchesUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );
        if (branchesResponse.getStatusCode() == HttpStatus.OK) {
            return branchesResponse.getBody();

        } else {
            throw new RuntimeException("Failed to retrieve branches for repository: " + repositoryName);
        }


    }



//wywalic nullpointer (if != not null to cos tam,
// wywalic try-catche
// validacja gdy ktos nie poda access token - controller-advice - ustaw token w app prperties
    // w kazdej klasi ctr+alt+all+l czyli susunac
//wywalic settery, lepiej przez konstruktor
    // wywalic inne info o bledach, nie pokazujemy stacktraceów!!!
    //dodac loggi, np.szukasz repo dla usera x,
    //

    //httpEntity zamienic na HttpRequest??

    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + githubAccessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }
}




