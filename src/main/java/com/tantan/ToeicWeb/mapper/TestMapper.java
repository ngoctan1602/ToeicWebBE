package com.tantan.ToeicWeb.mapper;


import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.entity.Test;
import com.tantan.ToeicWeb.response.PartResponse;
import com.tantan.ToeicWeb.response.TestResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TestMapper {

    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);
    @Mapping(source = "id",target = "idTest")
    @Mapping(source = "totalQuestion", target = "totalQuestion")
    @Mapping(source = "totalTime", target = "totalTime")
    @Mapping(source = "name", target = "name")
    TestResponse toDTO(Test test);
}
