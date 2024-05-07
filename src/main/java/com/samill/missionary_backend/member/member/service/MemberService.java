package com.samill.missionary_backend.member.member.service;

import com.samill.missionary_backend.member.dto.CreateMemberDto;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.mapper.MemberMapper;
import com.samill.missionary_backend.member.member.entity.Member;
import com.samill.missionary_backend.member.member.enums.ServiceType;
import com.samill.missionary_backend.member.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public CreateMemberDto createUserMember() {
        var member = memberRepository.save(Member.builder()
            .serviceType(ServiceType.USER_SERVICE)
            .build());
        return MemberMapper.INSTANCE.memberToCreateMemberDto(member);
    }

    public CreateMemberDto createAdminMember() {
        var member = memberRepository.save(Member.builder()
            .serviceType(ServiceType.ADMIN_SERVICE)
            .build());
        return MemberMapper.INSTANCE.memberToCreateMemberDto(member);
    }


    public Claims createClaims(GetUserDto userDto, OffsetDateTime currentUtc) {
        var tokenClaims = Jwts.claims()
            .setSubject(userDto.getMemberDto().memberId())
            .setIssuedAt(java.util.Date.from(currentUtc.toInstant()));
        tokenClaims.put("userId", userDto.id());
        tokenClaims.put("name", userDto.name());
        return tokenClaims;
    }
}
