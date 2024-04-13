package com.tantan.ToeicWeb.mapper;


import com.tantan.ToeicWeb.auth.UserResponse;
import com.tantan.ToeicWeb.auth.entities.Account;
import com.tantan.ToeicWeb.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "email",target = "email")
//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "username", target = "username")
    UserResponse toDTO(Account account);

}
