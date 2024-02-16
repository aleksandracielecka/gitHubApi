package com.example.github.mapper;

import com.example.github.dto.GitHubErrorDto;
import com.example.github.dto.MyErrorResponseDto;
import com.example.github.dto.MyRepositoryResponseDto;
import com.example.github.dto.RepositoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyMapper {
    private final ModelMapper modelMapper;

    public MyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MyRepositoryResponseDto mapToMyRepositoryResponseDto(List<RepositoryDto> repositories) {
        MyRepositoryResponseDto myRepositoryResponseDto = new MyRepositoryResponseDto();
        myRepositoryResponseDto.setRepositories(repositories);
        return myRepositoryResponseDto;
    }

    public MyErrorResponseDto mapToMyErrorResponseDto(GitHubErrorDto gitHubErrorDto) {
        return modelMapper.map(gitHubErrorDto, MyErrorResponseDto.class);
    }

    public RepositoryDto mapToRepositoryDto(RepositoryDto repositories) {
        return modelMapper.map(repositories, RepositoryDto.class);
    }


}
