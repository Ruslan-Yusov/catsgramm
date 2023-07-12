package ru.yandex.practicum.catsgram.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
public class PostDtoForAdd {
    private String author;
    private String description;
    private String photoUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDtoForAdd post = (PostDtoForAdd) o;
        return Objects.equals(author, post.author)
                && Objects.equals(description, post.description)
                && Objects.equals(photoUrl, post.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, description, photoUrl);
    }
}