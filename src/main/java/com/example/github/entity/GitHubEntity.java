//package com.example.github.entity;
//
////import com.fasterxml.jackson.annotation.JsonProperty;
////import lombok.Getter;
////import lombok.Setter;
////
////
////@Getter
////@Setter
////public class GitHubEntity {
////
////    @JsonProperty("fullName")
////    private String nameOfRepository;
////
////    @JsonProperty("description")
////    private String descriptionOfRepository;
////
////    @JsonProperty("cloneUrl")
////    private String gitCloneUrlRepository;
////
////    @JsonProperty("stars")
////    private Integer numberOfStargazerRepository;
////
////    @JsonProperty("createdAt")
////    private String dateOfCreationRepository;
////
////
////
////}
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Document(collection = "repositories")
//public class GitHubEntity {
//
//    @Id
//    private String id;
//
//    private String nameOfRepository;
//    private String descriptionOfRepository;
//    private String gitCloneUrlRepository;
//    private Integer numberOfStargazerRepository;
//    private String dateOfCreationRepository;
//}