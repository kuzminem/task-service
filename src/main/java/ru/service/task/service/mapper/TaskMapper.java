package ru.service.task.service.mapper;

import org.mapstruct.Mapper;
import ru.service.task.repository.entity.TaskDao;
import ru.service.task.controller.entity.TaskDto;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskDao toTaskDao(TaskDto taskDto);

    TaskDto toTaskDto(TaskDao taskDao);

    List<TaskDto> map(List<TaskDao> tasks);
}
