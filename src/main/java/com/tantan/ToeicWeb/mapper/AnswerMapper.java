package com.tantan.ToeicWeb.mapper;

import com.tantan.ToeicWeb.entity.Answer;
import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.request.AnswerRequest;
import com.tantan.ToeicWeb.request.ParagraphRequest;
import com.tantan.ToeicWeb.response.answer.AnswerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class) ;
    @Mapping(source = "content", target = "content")
    @Mapping(source = "isTrue", target = "isTrue")
    Answer toEntity(AnswerRequest answerRequest);
//    @Mapping(source = "content", target = "content")
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "isTrue", target = "isTrue")
//    AnswerResponse toAnswerResponse( Answer answer);
}
