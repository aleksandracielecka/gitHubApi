package com.example.github.service;

import com.example.github.dto.BranchDto;
import com.example.github.dto.CommitDto;
import com.example.github.dto.RepositoryDto;
import com.example.github.exception.CommitNotFoundException;
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


@Service
public class GithubApiService {
    private final String GITHUB_API_URL = "https://api.github.com/users/";
    private final String GITHUB_API_URL_BRANCHES = "https://api.github.com/repos/";
    private final RestTemplate restTemplate;
    private final MyMapper myMapper;
    private final String githubAccessToken;

    @Autowired
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
                List<RepositoryDto> mappedRepositories = Arrays.stream(repositories)
                        .map(repo -> {
                            repo.setOwnerLogin(username);
                            List<BranchDto> branches = getBranchesWithLastCommitSha(username, repo.getName());
                            repo.setBranches(branches);
                            return repo;
                        })
                        .collect(Collectors.toList());

                return myMapper.mapToMyRepositoryResponseDto(mappedRepositories).getRepositories();
            } else {
                return Collections.emptyList();
            }
        } catch (HttpClientErrorException.NotFound exception) {
            throw new UserNotFoundException("User not found", exception);
        }
    }

    public List<BranchDto> getBranchesWithLastCommitSha(String username, String repositoryName) {
        String branchesUrl = GITHUB_API_URL_BRANCHES + username + "/" + repositoryName + "/branches";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + githubAccessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<BranchDto[]> responseEntity = restTemplate.exchange(
                    branchesUrl,
                    HttpMethod.GET,
                    entity,
                    BranchDto[].class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                BranchDto[] branchesArray = responseEntity.getBody();
                List<BranchDto> branches = Arrays.asList(branchesArray);
                if (!branches.isEmpty()) {
                    for (BranchDto branch : branches) {
                        String lastCommitSha = getLastCommitShaForBranch(username, repositoryName, branch.getName());
                        branch.setCommitDto(new CommitDto(lastCommitSha));
                    }
                    return branches;
                }
            } else {
                throw new RuntimeException("Failed to retrieve branches for repository: " + repositoryName);
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RepositoryNotFoundException("Repository not found: " + repositoryName, ex);
            } else {
                throw ex;
            }
        }

        return Collections.emptyList();
    }


    private String getLastCommitShaForBranch(String username, String repositoryName, String branchName) {
        String commitsUrl = GITHUB_API_URL_BRANCHES + username + "/" + repositoryName + "/git/commits/" + branchName;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + githubAccessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CommitDto[]> commitsResponse = restTemplate.exchange(
                    commitsUrl,
                    HttpMethod.GET,
                    entity,
                    CommitDto[].class
            );

            if (commitsResponse.getStatusCode() == HttpStatus.OK) {
                CommitDto[] commits = commitsResponse.getBody();
                if (commits != null && commits.length > 0) {

                    return commits[0].getSha();
                }
            }
        } catch (HttpClientErrorException ex) {

        }

        return "nic";
    }
}





