package com.tantan.ToeicWeb.mapper;

import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.entity.Question;
import com.tantan.ToeicWeb.request.QuestionRequest;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);
    @Mapping(source = "contentQues",target = "content")
    @Mapping(source = "descriptionQues",target = "description")
    @Mapping(source = "audioQues",target = "image")
    @Mapping(source = "imageQues",target = "audio")

    Question toEntity (QuestionRequest questionRequest);

}
