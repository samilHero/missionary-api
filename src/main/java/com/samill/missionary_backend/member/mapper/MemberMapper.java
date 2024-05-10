package com.samill.missionary_backend.member.mapper;

import com.samill.missionary_backend.member.dto.CreateMemberDto;
import com.samill.missionary_backend.member.dto.GetMemberServiceTypeDto;
import com.samill.missionary_backend.member.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mappings({
        @Mapping(target = "memberId", source = "member.memberId"),
        @Mapping(target = "serviceType", source = "member.serviceType")
    })
    CreateMemberDto memberToCreateMemberDto(Member member);

    @Mappings({
        @Mapping(target = "memberId", source = "memberId"),
        @Mapping(target = "serviceType", source = "serviceType")
    })
    Member createMemberDtoToMember(CreateMemberDto getMemberDto);


    @Mappings({
        @Mapping(target = "serviceType", source = "serviceType")
    })
    GetMemberServiceTypeDto memberToGetMemberServiceTypeDto(Member member);

}
