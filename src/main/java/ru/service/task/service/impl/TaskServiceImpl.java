package ru.service.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.service.task.controller.entity.TaskDto;
import ru.service.task.repository.TaskRepository;
import ru.service.task.service.TaskService;
import ru.service.task.service.mapper.TaskMapper;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    /**
     * Создать новую заявку.
     *
     * @param taskDto - новая заявка.
     */
    @Override
    public void newTask(TaskDto taskDto) {
        taskRepository.save(taskMapper.toTaskDao(taskDto));
    }

    @Override
    public List<TaskDto> getAll() {
        return taskMapper.map(taskRepository.getAll());
    }

    @Override
    public List<TaskDto> getUsersTask(long usersId, long view) {
        if (view == 0) {
            return taskMapper.map(taskRepository.getUsersTask0(usersId));
        }
        if (view == 5) {
            return taskMapper.map(taskRepository.getUsersTask5(usersId));
        }
        return taskMapper.map(taskRepository.getUsersTask(usersId, view));
    }

    @Override
    public List<TaskDto> getExecutorsTask(long usersId, long view) {
        if (view == 0) {
            return taskMapper.map(taskRepository.getExecutorsTask0(usersId));
        }
        if (view == 5) {
            return taskMapper.map(taskRepository.getExecutorsTask5(usersId));
        }
        return taskMapper.map(taskRepository.getExecutorsTask(usersId, view));
    }

    @Override
    public TaskDto getTaskById(long id) {
        return taskMapper.toTaskDto(taskRepository.getById(id));
    }

    @Override
    public void updateExecutor(long id, long executorId) {
        taskRepository.updateExecutor(id, executorId);
    }

    @Override
    public void updateStatus(long id, long statusId) {
        taskRepository.updateTaskStatus(id, statusId);
    }
}
