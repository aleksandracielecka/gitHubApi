package com.example.github.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitHubEntity {

    @JsonProperty("fullName")
    private String nameOfRepository;

    @JsonProperty("description")
    private String descriptionOfRepository;

    @JsonProperty("cloneUrl")
    private String gitCloneUrlRepository;

    @JsonProperty("stars")
    private Integer numberOfStargazerRepository;

    @JsonProperty("createAt")
    private String dateOfCreationRepository;



}
