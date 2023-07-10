package ru.yandex.practicum.catsgram.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.practicum.catsgram.model.PostUserMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public abstract class BaseService<T> {

    @Autowired
    protected PostUserMapper postUserMapper;

    protected List<T> items = new ArrayList<>();

    protected void add(T value) {
        items.add(value);
    }
}
