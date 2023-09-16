package com.anuradha.annexservice.controller;

import com.anuradha.annexservice.dto.PostDto;
import com.anuradha.annexservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @PostMapping
    public PostDto save(@RequestParam String putha,String kawwa, @RequestBody PostDto postDto) {
        return postService.save(postDto);
    }

    @PutMapping
    public PostDto update(@RequestBody PostDto postDto) {
        return postService.update(postDto);
    }

    @GetMapping("id")
    public PostDto findById(@RequestParam String query) {
        return postService.findById(query);
    }

    @GetMapping()
    public Page<PostDto> findAll(@RequestParam int page, int limit) {
        return postService.findAll(PageRequest.of(page, limit));
    }

    @DeleteMapping("id")
    public void delete(@RequestParam String query) {
        postService.delete(query);
    }

}
