package com.samill.missionary_backend.church.church.mapper;

import com.samill.missionary_backend.church.church.dto.CreateChurchRequest;
import com.samill.missionary_backend.church.church.dto.GetChurchResult;
import com.samill.missionary_backend.church.church.dto.GetChurchesChurch;
import com.samill.missionary_backend.church.church.dto.GetChurchesResult;
import com.samill.missionary_backend.church.church.entity.Church;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChurchMapper {

    ChurchMapper INSTANCE = Mappers.getMapper(ChurchMapper.class);

    @Mappings({
            @Mapping(target = "pastorName", source = "pastor.name"),
            @Mapping(target = "pastorPhone", source = "pastor.phone"),
            @Mapping(target = "address", source = "address.fullAddress")
    })
    GetChurchResult churchToGetChurchResult(Church church);

    @Mappings({
            @Mapping(ignore = true, target = "id"),
            @Mapping(target = "pastor.name", source = "pastorName"),
            @Mapping(target = "pastor.phone", source = "pastorPhone"),
            @Mapping(target = "address.basic", source = "addressBasic"),
            @Mapping(target = "address.detail", source = "addressDetail"),
            @Mapping(target = "deletedAt", ignore = true)
    })
    Church createChurchRequestToChurch(CreateChurchRequest createChurchRequest);

    @Named("churchToGetChurchesChurch")
    GetChurchesChurch churchToGetChurchesChurch(Church church);


    @Mapping(target = "churches", source = "churches", qualifiedByName = "churchToGetChurchesChurch")
    GetChurchesResult churchesToGetChurchesResult(List<Church> churches, Boolean hasNext);


}
