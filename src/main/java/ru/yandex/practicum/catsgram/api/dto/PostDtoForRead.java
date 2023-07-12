package ru.yandex.practicum.catsgram.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
public class PostDtoForRead {
    private Integer id;
    private String author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate creationDate = LocalDate.now();
    private String description;
    private String photoUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDtoForRead post = (PostDtoForRead) o;
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