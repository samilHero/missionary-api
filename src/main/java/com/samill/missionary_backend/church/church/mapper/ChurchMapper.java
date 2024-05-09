package com.samill.missionary_backend.church.church.mapper;

import com.samill.missionary_backend.church.church.entity.Church;
import com.samill.missionary_backend.church.dto.CreateChurchCommand;
import com.samill.missionary_backend.church.dto.GetChurchQueryResult;
import com.samill.missionary_backend.church.dto.GetChurchesQueryResult;
import com.samill.missionary_backend.church.dto.GetChurchesQueryResultChurch;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChurchMapper {

    ChurchMapper INSTANCE = Mappers.getMapper(ChurchMapper.class);

    @Mappings({
        @Mapping(target = "pastorName", source = "pastor.name"),
        @Mapping(target = "pastorPhone", source = "pastor.phone"),
        @Mapping(target = "address", source = "address.fullAddress")
    })
    GetChurchQueryResult churchToGetChurchResult(Church church);

    @Mappings({
        @Mapping(ignore = true, target = "id"),
        @Mapping(target = "pastor.name", source = "pastorName"),
        @Mapping(target = "pastor.phone", source = "pastorPhone"),
        @Mapping(target = "address.basic", source = "addressBasic"),
        @Mapping(target = "address.detail", source = "addressDetail"),
        @Mapping(target = "deletedAt", ignore = true)
    })
    Church createChurchRequestToChurch(CreateChurchCommand createChurchCommand);

    @Named("churchToGetChurchesChurch")
    GetChurchesQueryResultChurch churchToGetChurchesChurch(Church church);


    @Mapping(target = "churches", source = "churches", qualifiedByName = "churchToGetChurchesChurch")
    GetChurchesQueryResult churchesToGetChurchesResult(List<Church> churches, Boolean hasNext);


}
