package ru.service.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.service.task.controller.entity.TaskDto;
import ru.service.task.controller.entity.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewTaskService {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public NewTaskService(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    public long getClientIdByUsername(String client) {
        return userService.getUserByUsername(client).getId();
    }

    public List<String> getAllUsers() {
        List<String> users = userService.getAll()
                .stream()
                .map(UserDto::getUsername)
                .sorted()
                .collect(Collectors.toList());
        users.removeIf(user -> user.equals("admin"));
        return users;
    }

    public void newTask(TaskDto taskDto) {
        taskDto.setExecutorId(0L);
        taskDto.setStatusId(1L);
        taskDto.setFilingDate(LocalDateTime.now());
        taskDto.setCompletionDate(taskDto.getFilingDate().plusDays(3));
        taskService.newTask(taskDto);
    }
}
