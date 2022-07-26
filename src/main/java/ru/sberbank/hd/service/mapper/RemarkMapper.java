package ru.sberbank.hd.service.mapper;

import org.mapstruct.Mapper;
import ru.sberbank.hd.controller.entity.RemarkDto;
import ru.sberbank.hd.repository.entity.RemarkDao;

import java.util.List;

@Mapper
public interface RemarkMapper {

    RemarkDao toRemarkDao(RemarkDto remarkDto);

    RemarkDto toRemarkDto(RemarkDao remarkDao);

    List<RemarkDto> map(List<RemarkDao> list);
}
