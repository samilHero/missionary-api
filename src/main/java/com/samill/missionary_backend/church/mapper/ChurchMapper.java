package com.samill.missionary_backend.church.mapper;

import com.samill.missionary_backend.church.dto.CreateChurchDto;
import com.samill.missionary_backend.church.dto.GetChurchDto;
import com.samill.missionary_backend.church.entity.Church;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChurchMapper {

    ChurchMapper INSTANCE = Mappers.getMapper(ChurchMapper.class);

    @Mappings({
            @Mapping(target = "pastorName", source = "pastor.name"),
            @Mapping(target = "pastorPhone", source = "pastor.phone"),
            @Mapping(target = "address", source = "address.fullAddress")
    })
    GetChurchDto churchToGetChurchDto(Church church);

    @Mappings({
            @Mapping(ignore = true, target = "id"),
            @Mapping(target = "pastor.name", source = "pastorName"),
            @Mapping(target = "pastor.phone", source = "pastorPhone"),
            @Mapping(target = "address.basic", source = "addressBasic"),
            @Mapping(target = "address.detail", source = "addressDetail"),
            @Mapping(target = "deletedAt", ignore = true)
    })
    Church createChurchDtoToChurch(CreateChurchDto createChurchDto);

}
