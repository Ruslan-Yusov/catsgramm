package ru.yandex.practicum.catsgram.model;

import org.mapstruct.Mapper;
import ru.yandex.practicum.catsgram.api.dto.PostDtoForAdd;
import ru.yandex.practicum.catsgram.api.dto.PostDtoForRead;
import ru.yandex.practicum.catsgram.api.dto.UserDtoForAdd;
import ru.yandex.practicum.catsgram.api.dto.UserDtoForRead;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostUserMapper {

    List<UserDtoForRead> toListUserDtoForRead (Collection<User> value);

    UserDtoForRead toUserDtoForRead (User value);

    User userToUserDtoForAdd (UserDtoForAdd value);

    User userToUserDtoForRead (UserDtoForRead value);

    List<PostDtoForRead> toListPostDtoForRead (Collection<Post> value);

    PostDtoForRead toPostDtoForRead (Post value);

    Post postToPostDtoForRead (PostDtoForRead value);

    Post postToPostDtoForAdd (PostDtoForAdd value);
}
