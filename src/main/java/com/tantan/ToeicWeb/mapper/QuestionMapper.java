package com.tantan.ToeicWeb.mapper;

import com.tantan.ToeicWeb.entity.Question;
import com.tantan.ToeicWeb.request.QuestionRequest;
import com.tantan.ToeicWeb.response.question.QuestionNoneAnswerResponse;
import com.tantan.ToeicWeb.response.question.QuestionWithAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);
    @Mapping(source = "contentQues",target = "content")
    @Mapping(source = "descriptionQues",target = "description")

    Question toEntity (QuestionRequest questionRequest);

    @Mapping(source = "content",target = "content")
    @Mapping(source = "id",target = "id")
    QuestionNoneAnswerResponse toQuestionResponse(Question question);

//    @Mapping(source = "content",target = "content")
//    @Mapping(source = "id",target = "id")
//    @Mapping(source = "description",target = "description")
//    QuestionWithAnswer toQuestionWithAnswer(Question question);


}
