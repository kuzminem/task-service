package ru.service.task.service.mapper;

import org.mapstruct.Mapper;
import ru.service.task.controller.entity.RemarkDto;
import ru.service.task.repository.entity.RemarkDao;

import java.util.List;

@Mapper
public interface RemarkMapper {

    RemarkDao toRemarkDao(RemarkDto remarkDto);

    RemarkDto toRemarkDto(RemarkDao remarkDao);

    List<RemarkDto> map(List<RemarkDao> list);
}
