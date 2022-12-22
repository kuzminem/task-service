package ru.service.task.service.mapper;

import org.mapstruct.Mapper;
import ru.service.task.repository.entity.UserDao;
import ru.service.task.controller.entity.UserDto;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDao toUserDao(UserDto userDto);

    UserDto toUserDto(UserDao userDao);

    List<UserDto> map(List<UserDao> list);
}
