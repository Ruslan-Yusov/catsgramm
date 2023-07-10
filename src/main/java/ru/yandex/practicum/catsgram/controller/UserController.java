package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.api.dto.UserDtoForAdd;
import ru.yandex.practicum.catsgram.api.dto.UserDtoForRead;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDtoForRead> findAllUsers() {
        return userService.allUser();
    }

    @PostMapping("/users")
    public UserDtoForRead create(@RequestBody UserDtoForAdd user) {
        return userService.addUser(user);
    }

    @PutMapping("/users")
    public UserDtoForRead putUser(@RequestBody UserDtoForRead user) {
        return userService.updateUser(user);
    }
}
