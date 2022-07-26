package ru.sberbank.hd.service.mapper;

import org.mapstruct.Mapper;
import ru.sberbank.hd.repository.entity.UserDao;
import ru.sberbank.hd.controller.entity.UserDto;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDao toUserDao(UserDto userDto);

    UserDto toUserDto(UserDao userDao);

    List<UserDto> map(List<UserDao> list);
}
