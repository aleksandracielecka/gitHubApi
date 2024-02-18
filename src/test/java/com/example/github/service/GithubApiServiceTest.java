package com.example.github.service;


import com.example.github.dto.BranchDto;
import com.example.github.dto.RepositoryDto;
import com.example.github.exception.UserNotFoundException;
import com.example.github.mapper.MyMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


public class GithubApiServiceTest {

    @Mock
    private ModelMapper modelMapperMock;

    @Test
    public void shouldReturnBranchesForRepository() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);

        GithubApiService githubApiService = new GithubApiService(restTemplateMock, new MyMapper(modelMapperMock), "dummyToken");


        List<BranchDto> mockBranches = new ArrayList<>();

        mockBranches.add(new BranchDto("branch1"));
        mockBranches.add(new BranchDto("branch2"));
        ResponseEntity<List<BranchDto>> mockResponseEntity = new ResponseEntity<>(mockBranches, HttpStatus.OK);


        when(restTemplateMock.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(), Mockito.eq(new ParameterizedTypeReference<List<BranchDto>>() {
                })))
                .thenReturn(mockResponseEntity);

        List<BranchDto> branches = githubApiService.getBranchesForRepository("testUser", "testRepo");

        Assert.assertNotNull(branches);
        Assert.assertEquals(2, branches.size());

    }

}




