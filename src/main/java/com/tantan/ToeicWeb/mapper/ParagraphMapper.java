package com.tantan.ToeicWeb.mapper;

import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.request.ParagraphRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParagraphMapper {
    ParagraphMapper INSTANCE = Mappers.getMapper(ParagraphMapper.class) ;
    @Mapping(source = "content", target = "content")
    @Mapping(source = "img", target = "img")
    Paragraph toEntity(ParagraphRequest paragraphRequest);


}
