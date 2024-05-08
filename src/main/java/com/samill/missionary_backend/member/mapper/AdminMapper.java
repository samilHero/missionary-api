package com.samill.missionary_backend.member.mapper;

import com.samill.missionary_backend.member.admin.entity.Admin;
import com.samill.missionary_backend.member.dto.GetAdminDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    @Mappings({
        @Mapping(target = "id", source = "admin.id"),
        @Mapping(target = "name", source = "admin.name"),
        @Mapping(target = "password", source = "admin.password"),
        @Mapping(target = "getMemberDto", source = "admin.member")
    })
    GetAdminDto adminToGetAdminDto(Admin admin);

}
