package com.tantan.ToeicWeb.mapper;


import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.entity.history.History;
import com.tantan.ToeicWeb.response.PartResponse;
import com.tantan.ToeicWeb.response.history.HistoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.sql.Time;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    HistoryMapper INSTANCE = Mappers.getMapper(HistoryMapper.class);
//    private Long id;
//    private int totalCorrect;
//    private Date dateCompleted;
//    private Time totalTime;
//    private int totalQuestion;
    @Mapping(source = "id",target = "id")
    @Mapping(source = "dateCompleted", target = "dateCompleted")
    @Mapping(source = "totalTime", target = "totalTime")
    @Mapping(source = "totalCorrect", target = "totalCorrect")
    @Mapping(source = "totalQuestion", target = "totalQuestion")
    HistoryResponse toDTO(History history);
}
