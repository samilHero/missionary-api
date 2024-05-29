package com.samill.missionary_backend.member;


import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.util.PasswordUtil;
import com.samill.missionary_backend.member.admin.service.AdminService;
import com.samill.missionary_backend.member.dto.CreateAdminCommand;
import com.samill.missionary_backend.member.dto.CreateUserCommand;
import com.samill.missionary_backend.member.dto.GetAdminDto;
import com.samill.missionary_backend.member.dto.GetMemberServiceTypeDto;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.dto.LoginAdminQuery;
import com.samill.missionary_backend.member.dto.LoginAdminQueryResult;
import com.samill.missionary_backend.member.dto.LoginUserQuery;
import com.samill.missionary_backend.member.dto.LoginUserQueryResult;
import com.samill.missionary_backend.member.exception.MemberException;
import com.samill.missionary_backend.member.mapper.MemberMapper;
import com.samill.missionary_backend.member.member.service.MemberService;
import com.samill.missionary_backend.member.user.service.UserService;
import com.samill.missionary_backend.token.provider.TokenProvider;
import java.time.OffsetDateTime;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberManagement implements MemberExternalService {

    private final MemberService memberService;
    private final UserService userService;
    private final AdminService adminService;
    private final PasswordUtil passwordUtil;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public void createUser(@NonNull CreateUserCommand request) throws MemberException {
        var isExistedUser = isExistedUserByLoginId(request.getLoginId());

        if (isExistedUser) {
            throw new MemberException(ResponseCode.ALREADY_EXITED_USER_ERROR);
        }

        var createMemberDto = memberService.createUserMember();
        var member = MemberMapper.INSTANCE.createMemberDtoToMember(createMemberDto);
        userService.createUser(request, member);
    }

    @Override
    @Transactional
    public LoginUserQueryResult loginUser(@NonNull LoginUserQuery request) throws MemberException {
        var isExistedUser = isExistedUserByLoginId(request.getLoginId());
        if (!isExistedUser) {
            throw new MemberException(ResponseCode.IS_NOT_EXITED_LOGIN_ID_ERROR);
        }

        var userDto = getUserByLoginId(request.getLoginId());
        var isMatchedPassword = passwordUtil.matches(request.getPassword(), userDto.password());
        if (!isMatchedPassword) {
            throw new MemberException(ResponseCode.INVALID_LOGIN_PASSWORD_ERROR);
        }
        return createUserToken(userDto);
    }

    @Override
    @Transactional
    public LoginAdminQueryResult loginAdmin(@NonNull LoginAdminQuery request) throws Exception {
        var isExistedAdmin = isExistedAdminByLoginId(request.getLoginId());
        if (!isExistedAdmin) {
            throw new MemberException(ResponseCode.IS_NOT_EXITED_LOGIN_ID_ERROR);
        }

        var adminDto = getAdminByLoginId(request.getLoginId());
        var isMatchedPassword = passwordUtil.matches(request.getPassword(), adminDto.password());
        if (!isMatchedPassword) {
            throw new MemberException(ResponseCode.INVALID_LOGIN_PASSWORD_ERROR);
        }
        return createAdminToken(adminDto);
    }


    @Override
    @Transactional
    public void createAdmin(@NonNull CreateAdminCommand request) throws MemberException {
        var isExistedAdmin = isExistedAdminByLoginId(request.getLoginId());

        if (isExistedAdmin) {
            throw new MemberException(ResponseCode.ALREADY_EXITED_USER_ERROR);
        }

        var createMemberDto = memberService.createAdminMember();
        var member = MemberMapper.INSTANCE.createMemberDtoToMember(createMemberDto);
        adminService.createAdmin(request, member);
    }

    @Override
    public void getMember() {

    }

    @Override
    @Transactional(readOnly = true)
    public GetUserDto getUserById(@NonNull String userId) throws Exception {
        return userService.getUserById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public GetUserDto getUserByLoginId(@NonNull String loginId) {
        return userService.getUserByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public GetAdminDto getAdminByLoginId(@NonNull String loginId) {
        return adminService.getAdminByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public GetUserDto getUserByMemberId(@NonNull String memberId) throws MemberException {
        return userService.getUserByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    public GetAdminDto getAdminByMemberId(@NonNull String memberId) throws Exception {
        return adminService.getAdminByMemberId(memberId);
    }

    @Override
    public void getUsers() {

    }


    @Override
    @Transactional(readOnly = true)
    public GetMemberServiceTypeDto getMemberServiceType(@NonNull String memberId) throws MemberException {
        return memberService.getMemberServiceType(memberId);
    }


    private LoginUserQueryResult createUserToken(@NonNull GetUserDto request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // JWT 토큰 생성
        var claims = memberService.createClaims(request, OffsetDateTime.now());
        var token = tokenProvider.createToken(authentication, claims);
        return LoginUserQueryResult.builder().token(token).build();
    }

    private LoginAdminQueryResult createAdminToken(@NonNull GetAdminDto request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // JWT 토큰 생성
        var claims = memberService.createClaims(request, OffsetDateTime.now());
        var token = tokenProvider.createToken(authentication, claims);
        return LoginAdminQueryResult.builder().token(token).build();
    }

    private Boolean isExistedUserByLoginId(@NonNull String loginId) {
        return userService.isExistedUserByLoginId(loginId);
    }

    private Boolean isExistedAdminByLoginId(@NonNull String loginId) {
        return adminService.isExistedAdminByLoginId(loginId);
    }


}
