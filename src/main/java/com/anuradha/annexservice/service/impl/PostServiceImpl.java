package com.anuradha.annexservice.service.impl;

import com.anuradha.annexservice.dto.PostDto;
import com.anuradha.annexservice.model.Post;
import com.anuradha.annexservice.repository.PostRepository;
import com.anuradha.annexservice.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto save(PostDto postDto) {
        return modelMapper.map(
                postRepository.save(modelMapper.map(postDto, Post.class)),
                PostDto.class
        );
    }

    @Override
    public PostDto update(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post not found"));
        post.setName(postDto.getName());
        post.setDescription(postDto.getDescription());
        post.setContactNumber(postDto.getContactNumber());
        return modelMapper.map(postRepository.save(post), PostDto.class);

    }

    @Override
    public PostDto findById(String id) {
        return modelMapper.map(postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post not found")), PostDto.class);
    }

    @Override
    public void delete(String id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(x -> modelMapper.map(x, PostDto.class));

    }
}
