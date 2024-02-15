//package com.example.github.service;
//
//import com.example.github.entity.GitHubEntity;
//import com.example.github.repository.GitHubRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GitHubService {
//
//    @Autowired
//    private GitHubRepository gitHubRepository;
//
//    public List<GitHubEntity> getAllRepositories() {
//        return gitHubRepository.findAll();
//    }
//
//    public GitHubEntity getRepositoryById(String id) {
//        return gitHubRepository.findById(id).orElse(null);
//    }
//
//    public GitHubEntity createRepository(GitHubEntity repository) {
//        return gitHubRepository.save(repository);
//    }
//
//    public void deleteRepository(String id) {
//        gitHubRepository.deleteById(id);
//    }
//}
