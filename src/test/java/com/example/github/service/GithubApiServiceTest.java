package com.example.github.service;


import com.example.github.dto.RepositoryDto;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GithubApiServiceTest {

    private static final String MOCK_ACCESS_TOKEN = "mock_access_token";
    private static final String MOCK_USERNAME = "mock_username";


   @Test
    public void shouldReturnUserRepositoriesNames() {


       RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
       GithubApiService githubApiService = new GithubApiService(restTemplateMock, null, MOCK_ACCESS_TOKEN);

       RepositoryDto[] mockRepositories = {
               new RepositoryDto("repo1"),
               new RepositoryDto("repo2")
       };
       ResponseEntity<RepositoryDto[]> mockResponseEntity = new ResponseEntity<>(mockRepositories, HttpStatus.OK);

       Mockito.when(restTemplateMock.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class), Mockito.eq(RepositoryDto[].class)))
               .thenReturn(mockResponseEntity);

       // When
       List<RepositoryDto> repositories = githubApiService.getUserRepositories(MOCK_USERNAME);

       // Then
        assertNotNull(repositories);
        assertEquals(2, repositories.size());
        assertEquals("repo1", repositories.get(0).getName());
        assertEquals("repo2", repositories.get(1).getName());
    }



}