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
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping()
    public List<PostDtoForRead> findAll() {
        return postService.allPosts();
    }

    @GetMapping("/search") // этот элемент был в задании
    public List<PostDtoForRead> allAuthor(
            @RequestParam String author,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Integer size) {
        return postService.getAuthor(author, sort, size);
    }

    @GetMapping("{id}")
    public PostDtoForRead getPost(@PathVariable("id") Integer id) {
        return postService.getPost(id);
    }

    @PostMapping()
    public PostDtoForRead create(@RequestBody PostDtoForAdd post) {
        return postService.addPost(post);
    }
}
