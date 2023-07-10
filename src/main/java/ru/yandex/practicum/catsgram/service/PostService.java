package ru.yandex.practicum.catsgram.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.api.dto.PostDtoForAdd;
import ru.yandex.practicum.catsgram.api.dto.PostDtoForRead;
import ru.yandex.practicum.catsgram.exeption.BaseExeption;
import ru.yandex.practicum.catsgram.exeption.InvalidAuthorExeption;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.List;
import java.util.Optional;

@Service
@Getter
public class PostService extends BaseService<Post> {

    private Integer postId = 0;

    private boolean checkAuthor (String author) {
        return items.stream().map(Post::getAuthor).noneMatch(author::equals);
    }

    private Optional<Post> getIdPost (Integer idPost) {
        return items.stream().filter(post -> post.getId().equals(idPost)).findAny();
    }

    public List<PostDtoForRead> allPosts() {
        return postUserMapper.toListPostDtoForRead(items);
    }

    public PostDtoForRead getPost(Integer id) {
        Post post = getIdPost(id).orElseThrow(() -> new BaseExeption("Такого индентификатора нет"));
        return postUserMapper.toPostDtoForRead(post);
    }

    private Integer generateId(Integer id) {
       return ++postId;
    }

    public PostDtoForRead addPost(PostDtoForAdd postDtoForAdd) {
        if (checkAuthor(postDtoForAdd.getAuthor())) {
            Post post = postUserMapper.postToPostDtoForAdd(postDtoForAdd);
            post.setId(generateId(postId));
            add(post);
            return postUserMapper.toPostDtoForRead(post);
        } else {
            throw new InvalidAuthorExeption("Такой автор уже есть");
        }
    }

}
