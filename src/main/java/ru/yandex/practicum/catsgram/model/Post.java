package ru.yandex.practicum.catsgram.model;

import lombok.Data;

import java.time.Instant;
import java.util.Objects;

@Data
public class Post {

    private String author; // автор
    private Instant creationDate = Instant.now(); // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии

    public Post(String author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(author, post.author)
                && Objects.equals(creationDate, post.creationDate)
                && Objects.equals(description, post.description)
                && Objects.equals(photoUrl, post.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, creationDate, description, photoUrl);
    }
}