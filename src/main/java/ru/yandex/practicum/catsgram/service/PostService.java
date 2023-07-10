package ru.yandex.practicum.catsgram.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.api.dto.PostDtoForAdd;
import ru.yandex.practicum.catsgram.api.dto.PostDtoForRead;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.List;

@Service
@Getter
public class PostService extends BaseService<Post> {

    public List<PostDtoForRead> allPosts() {
        return postUserMapper.toListPostDtoForRead(items);
    }

    public PostDtoForRead addPost(PostDtoForAdd postDtoForAdd) {
        Post post = postUserMapper.postToPostDtoForAdd(postDtoForAdd);
        add(post);
        return postUserMapper.toPostDtoForRead(post);
    }

}
