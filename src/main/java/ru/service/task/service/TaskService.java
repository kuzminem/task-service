package ru.service.task.service;

import ru.service.task.controller.entity.TaskDto;

import java.util.List;

public interface TaskService {

    /**
     * Создать новую заявку.
     */
    void newTask(TaskDto taskDto);

    /**
     * Получить все заявки.
     *
     * @return - список всех заявок.
     */
    List<TaskDto> getAll();

    List<TaskDto> getUsersTask(long usersId, long view);

    List<TaskDto> getExecutorsTask(long usersId, long view);

    /**
     * Получить заявку.
     *
     * @param id - номер заявки.
     * @return - заявка.
     */
    TaskDto getTaskById(long id);

    /**
     * Изменить исполнителя
     */
    void updateExecutor(long id, long executorId);

    /**
     * Изменить статус заявки.
     */
    void updateStatus(long id, long statusId);
}
