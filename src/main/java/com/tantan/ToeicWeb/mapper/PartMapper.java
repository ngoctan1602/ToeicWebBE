package com.tantan.ToeicWeb.mapper;


import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.response.PartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartMapper {

    PartMapper INSTANCE = Mappers.getMapper(PartMapper.class);
    @Mapping(source = "id",target = "id")
    @Mapping(source = "name", target = "name")
    PartResponse toDTO(Part part);
}
