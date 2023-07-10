package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.api.dto.PostDtoForAdd;
import ru.yandex.practicum.catsgram.api.dto.PostDtoForRead;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<PostDtoForRead> findAll() {
        return postService.allPosts();
    }

    @GetMapping("/posts/serch")
    public List<PostDtoForRead> allAuthor(
            @RequestParam String author,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Integer size) {
        return postService.getAuthor(author, sort, size);
    }

    @GetMapping("posts/{id}")
    public PostDtoForRead getPost(@PathVariable Integer id) {
        return postService.getPost(id);
    }

    @PostMapping("/posts")
    public PostDtoForRead create(@RequestBody PostDtoForAdd post) {
        return postService.addPost(post);
    }
}
