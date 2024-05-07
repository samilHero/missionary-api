package com.samill.missionary_backend.member.mapper;

import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
        @Mapping(target = "id", source = "user.id"),
        @Mapping(target = "name", source = "user.name"),
        @Mapping(target = "password", source = "user.password"),
        @Mapping(target = "getMemberDto", source = "user.member")
    })
    GetUserDto userToGetUserDto(User user);

}
