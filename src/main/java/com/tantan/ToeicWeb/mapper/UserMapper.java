package com.tantan.ToeicWeb.mapper;


import com.tantan.ToeicWeb.auth.UserResponse;
import com.tantan.ToeicWeb.auth.entities.User;
import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.response.PartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "email",target = "email")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "username", target = "username")
    UserResponse toDTO(User user);
}
