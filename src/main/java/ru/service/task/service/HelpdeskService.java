package ru.service.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.service.task.controller.entity.TaskDto;
import ru.service.task.controller.entity.TaskFront;
import ru.service.task.controller.entity.UserDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HelpdeskService {

    private final TaskService taskService;
    private final UserService userService;
    private final StatusService statusService;

    @Autowired
    public HelpdeskService(TaskService taskService, UserService userService, StatusService statusService) {
        this.taskService = taskService;
        this.userService = userService;
        this.statusService = statusService;
    }

    /**
     * Получить список заявок из public.tasks.
     */
    public List<TaskFront> getAllTaskFront() {
        return map(taskService.getAll());
    }

    /**
     * Собираем список заявок для пользователя.
     */
    public List<TaskFront> getTasks(String username, long view) {
        List<TaskFront> tasks;
        UserDto userDto = userService.getUserByUsernameNormal(username);
        if (userDto.getCredentials().equals("ROLE_USER")) {
            tasks = map(taskService.getUsersTask(userDto.getId(), view));
        } else {
            tasks = map(taskService.getExecutorsTask(userDto.getId(), view));
        }
        return tasks;
    }

    /**
     * Собираем Task, который можно отдать фронту.
     */
    public TaskFront toTaskFront(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }

        TaskFront taskFront = new TaskFront();

        taskFront.setId(taskDto.getId());
        taskFront.setClient(userService.getUsername(taskDto.getClientId()));
        taskFront.setSubject(taskDto.getSubject());
        taskFront.setDescription(taskDto.getDescription());
        taskFront.setFilingDate(dateToString(taskDto.getFilingDate()));
        taskFront.setCompletionDate(dateToString(taskDto.getCompletionDate()));
        taskFront.setStatus(statusService.getStatus(taskDto.getStatusId()));
        long executorId = taskDto.getExecutorId();
        taskFront.setExecutor(executorId == 0L ? "Исп. не назначен" : userService.getUsername(executorId));

        return taskFront;
    }

    /**
     * Форматирование LocalDateTime.
     *
     * @param localDateTime - дата из public.tasks.
     * @return - дата в виде текста "дд.ММ.гггг ЧЧ:мм"
     */
    private String dateToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return localDateTime.format(formatter);
    }

    /**
     * Преобразование списка TaskDao в список TaskFront.
     */
    public List<TaskFront> map(List<TaskDto> tasks) {
        if (tasks == null) {
            return null;
        }

        List<TaskFront> list = new ArrayList<>();
        for (TaskDto task : tasks) {
            list.add(toTaskFront(task));
        }

        return list;
    }
}
