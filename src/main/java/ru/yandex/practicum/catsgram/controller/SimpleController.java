package ru.yandex.practicum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class SimpleController {

    @GetMapping("/home")
    public String homePage() {
        log.info("Получен запрос");
        return "Котограмм";
    }
}
