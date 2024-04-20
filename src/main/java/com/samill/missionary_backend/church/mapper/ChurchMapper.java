package com.samill.missionary_backend.church.mapper;

import com.samill.missionary_backend.church.dto.GetChurchDto;
import com.samill.missionary_backend.church.entity.Church;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChurchMapper {

    ChurchMapper INSTANCE = Mappers.getMapper(ChurchMapper.class);

    @Mapping(target = "pastorName", source = "pastor.name")
    @Mapping(target = "pastorPhone", source = "pastor.phone")
    @Mapping(target = "address", source = "address.fullAddress")
    GetChurchDto churchToGetChurchDto(Church church);


}
