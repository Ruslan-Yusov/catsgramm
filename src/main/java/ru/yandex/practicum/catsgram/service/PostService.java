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
import java.util.stream.Collectors;

@Service
@Getter
public class PostService extends BaseService<Post> {

    private Integer postId = 0;

    private List<Post> checkAuthor (String author, String sort, Integer size) {
        return items.stream()
                .filter(post -> post.getAuthor().equals(author))
                .sorted((o1, o2) -> {
                    int com = o1.getCreationDate().compareTo(o2.getCreationDate());
                    if (sort.equals("desc")) {
                        com = -1 * com;
                    }
                    return com;
                })
                .limit(size)
                .collect(Collectors.toList());
    }

    private Optional<Post> getIdPost (Integer idPost) {
        return items.stream().filter(post -> post.getId().equals(idPost)).findAny();
    }

    public List<PostDtoForRead> allPosts() {
        return postUserMapper.toListPostDtoForRead(items);
    }

    public List<PostDtoForRead> getAuthor(String author, String sort, Integer size) {
        List<Post> post = checkAuthor(author, sort, size);
        return postUserMapper.toListPostDtoForRead(post);
    }

    public PostDtoForRead getPost(Integer id) {
        Post post = getIdPost(id).orElseThrow(() -> new BaseExeption("Такого индентификатора нет"));
        return postUserMapper.toPostDtoForRead(post);
    }

    private Integer generateId() {
       return ++postId;
    }

    public PostDtoForRead addPost(PostDtoForAdd postDtoForAdd) {
        Post post = postUserMapper.postToPostDtoForAdd(postDtoForAdd);
        post.setId(generateId());
        add(post);
        return postUserMapper.toPostDtoForRead(post);
    }

}
