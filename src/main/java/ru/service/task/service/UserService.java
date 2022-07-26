package ru.service.task.service;

import ru.service.task.controller.entity.UserDto;

import java.util.List;

public interface UserService {

    void newUser(UserDto userDto);

    void updateUser(UserDto userDto);

    List<UserDto> getAll();

    UserDto getUserById(long id);

    UserDto getUserByUsername(String username);

    List<String> getExecutors();

    UserDto getUserByUsernameNormal(String username);

    long getUserId(String executor);

    String getUsername(long id);

    boolean approval(String username);
}
