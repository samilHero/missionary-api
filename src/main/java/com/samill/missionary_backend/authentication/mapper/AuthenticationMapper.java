package com.samill.missionary_backend.authentication.mapper;

import com.samill.missionary_backend.authentication.dto.GetMemberServiceTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthenticationMapper {

    AuthenticationMapper INSTANCE = Mappers.getMapper(AuthenticationMapper.class);

    @Mappings({
        @Mapping(target = "serviceType", source = "serviceType")
    })
    GetMemberServiceTypeDto memberToGetMemberServiceTypeDto(com.samill.missionary_backend.member.dto.GetMemberServiceTypeDto member);

}
