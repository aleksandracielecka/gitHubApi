package com.example.github.utils;


import org.springframework.http.HttpHeaders;

public class GitHubHelper {
    public static HttpHeaders getUserAgentHeader(){
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("User-Agent","http://developer.github.com/v3/#user-agent-required");
        return headers;
    }
}
