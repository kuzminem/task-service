package ru.sberbank.hd.service.mapper;

import org.mapstruct.Mapper;
import ru.sberbank.hd.repository.entity.TaskDao;
import ru.sberbank.hd.controller.entity.TaskDto;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskDao toTaskDao(TaskDto taskDto);

    TaskDto toTaskDto(TaskDao taskDao);

    List<TaskDto> map(List<TaskDao> tasks);
}
