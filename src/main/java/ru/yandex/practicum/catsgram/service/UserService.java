package ru.yandex.practicum.catsgram.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.api.dto.UserDtoForAdd;
import ru.yandex.practicum.catsgram.api.dto.UserDtoForRead;
import ru.yandex.practicum.catsgram.exeption.InvalidEmailException;
import ru.yandex.practicum.catsgram.exeption.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Getter
public class UserService extends BaseService<User> {

    private final Pattern pattern = Pattern.compile("([\\w\\.\\-]*)\\@([\\w\\-]*)\\.(\\p{Lower}{2,4})");

    private boolean checkEmail(String email) {
        return items.stream().map(User::getEmail).noneMatch(email::equals);
    }

    private Optional<User> getEmail (String email) {
        return items.stream().filter(i->i.getEmail().equals(email)).findAny();
    }

    public List<UserDtoForRead> allUser() {
        return postUserMapper.toListUserDtoForRead(items);
    }

    public UserDtoForRead addUser(UserDtoForAdd userDtoForAdd) {
        Matcher matcher = pattern.matcher(userDtoForAdd.getEmail());
        if (checkEmail(userDtoForAdd.getEmail())) {
            if (matcher.find()) {
                User user = postUserMapper.userToUserDtoForAdd(userDtoForAdd);
                add(user);
                return postUserMapper.toUserDtoForRead(user);
            } else {
                throw new InvalidEmailException("Электронный адрес не валидный");
            }
        } else {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
    }

    public UserDtoForRead updateUser(UserDtoForRead userDtoForRead) {
        Matcher matcher = pattern.matcher(userDtoForRead.getEmail());
        if (!matcher.find()) {
            throw new InvalidEmailException("Электронный адрес не валидный");
        }
        User upUser = getEmail(userDtoForRead.getEmail())
                .map(user1 -> {
                    items.remove(user1);
                    return postUserMapper.userToUserDtoForRead(userDtoForRead);
                }).orElseThrow(() -> new UserAlreadyExistException("Такого пользователя нет"));
        items.add(upUser);
        return userDtoForRead;
    }
}
