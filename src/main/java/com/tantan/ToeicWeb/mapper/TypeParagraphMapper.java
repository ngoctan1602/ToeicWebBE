package com.tantan.ToeicWeb.mapper;


import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.entity.TypeParagraph;
import com.tantan.ToeicWeb.response.ParagraphResponse;
import com.tantan.ToeicWeb.response.TypeParagraphResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TypeParagraphMapper {
    TypeParagraphMapper INSTANCE = Mappers.getMapper(TypeParagraphMapper.class);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    TypeParagraphResponse toDTO(TypeParagraph typeParagraph);
}
