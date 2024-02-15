//package com.example.github.controller;
//
//import com.example.github.entity.GitHubEntity;
//import com.example.github.mapper.RetrieveUtil;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class GitHubControllerTest {
//
//    @Test
//    public void contextLoads() {
//    }
//
//    @Test
//    public void checkStatusCode() throws IOException {
//
//        // Given
//        HttpUriRequest request = new HttpGet( "http://localhost:8080//repositories/mwarycha/SpringMVC4");
//
//        // When
//        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
//
//        // Then
////        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(httptatus.SC_OK));
//        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.OK);
//    }
//
//    @Test
//    public void checkNameOfRepository() throws IOException {
//
//        // Given
//        HttpUriRequest request = new HttpGet( "http://localhost:8080//repositories/mwarycha/SpringMVC4" );
//
//        // When
//        HttpResponse response = HttpClientBuilder.create().build().execute( request );
//
//        // Then
//        GitHubEntity resource = RetrieveUtil.retrieveResourceFromResponse(response, GitHubEntity.class);
////        assertThat( "mwarycha/SpringMVC4", Matchers.is(resource.getNameOfRepository()));
//        assertEquals( "mwarycha/SpringMVC4", Matchers.is(resource.getNameOfRepository()));
//    }
//
//
//}