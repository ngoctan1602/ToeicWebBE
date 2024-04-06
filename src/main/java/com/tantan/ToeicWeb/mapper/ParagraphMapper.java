package com.tantan.ToeicWeb.mapper;


import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.response.ParagraphByPartResponse;
import com.tantan.ToeicWeb.response.ParagraphResponse;
import com.tantan.ToeicWeb.response.PartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParagraphMapper {
    ParagraphMapper INSTANCE = Mappers.getMapper(ParagraphMapper.class);

//    private Long id;
//
//    private String content;
//    private String img;
//    private String audio;
    @Mapping(source = "id",target = "id")
    @Mapping(source = "content",target = "content")
    @Mapping(source = "img",target = "img")
    @Mapping(source = "audio",target = "audio")
    ParagraphResponse toDTO(Paragraph paragraph);
}
