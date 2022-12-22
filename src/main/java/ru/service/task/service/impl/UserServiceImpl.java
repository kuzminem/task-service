package ru.service.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.service.task.controller.entity.UserDto;
import ru.service.task.repository.UserRepository;
import ru.service.task.repository.entity.UserDao;
import ru.service.task.service.UserService;
import ru.service.task.service.mapper.UserMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    UserServiceImpl(UserRepository userRepository,
                    UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void newUser(UserDto userDto) {
        userRepository.save(userMapper.toUserDao(userDto));
    }

    @Override
    public void updateUser(UserDto userDto) {
        userRepository.updateUser(
                userDto.getId(),
                userDto.getPassword(),
                userDto.getCredentials(),
                userDto.getLastName(),
                userDto.getFirstName(),
                userDto.getSecondName()
        );
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = userMapper.map(userRepository.getAll());
        users.sort(Comparator.comparing(UserDto::getUsername));
        users.removeIf(user -> user.getUsername().equals("admin"));
        return users;
    }

    @Override
    public UserDto getUserById(long id) {
        return userMapper.toUserDto(userRepository.getById(id));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        UserDao userDao = userRepository.findByUsernameEquals(username);
        if (userDao == null) {
            throw new UsernameNotFoundException("User username=" + username + " not found");
        }
        return userMapper.toUserDto(userDao);
    }

    @Override
    public List<String> getExecutors() {
        return userMapper.map(userRepository.getExecutors()).stream()
                .map(UserDto::getUsername)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUsernameNormal(String username) {
        return userMapper.toUserDto(userRepository.getUserByUsernameNormal(username));
    }

    @Override
    public long getUserId(String username) {
        UserDao userDao = userRepository.findByUsernameEquals(username);
        if (userDao == null) { return 0; }
        return userDao.getId();
    }

    @Override
    public String getUsername(long id) {
        return userRepository.getById(id).getUsername();
    }

    @Override
    public boolean approval (String username) {
        return !userRepository.existsByUsernameEquals(username);
    }
}
