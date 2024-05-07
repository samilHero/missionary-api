package com.samill.missionary_backend.member;


import com.samill.missionary_backend.authentication.security.TokenProvider;
import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.util.PasswordUtil;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.dto.PostTokenDto;
import com.samill.missionary_backend.member.dto.PostTokenRequest;
import com.samill.missionary_backend.member.dto.PutUserRequest;
import com.samill.missionary_backend.member.exception.MemberException;
import com.samill.missionary_backend.member.mapper.MemberMapper;
import com.samill.missionary_backend.member.member.service.MemberService;
import com.samill.missionary_backend.member.user.service.UserService;
import java.time.OffsetDateTime;
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
    private final PasswordUtil passwordUtil;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public void createUser(PutUserRequest request) throws MemberException {
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
    public PostTokenDto login(PostTokenRequest request) throws MemberException {
        var isExistedUser = isExistedUserByLoginId(request.getLoginId());
        if (!isExistedUser) {
            throw new MemberException(ResponseCode.IS_NOT_EXITED_LOGIN_ID_ERROR);
        }

        var userDto = getUserByLoginId(request.getLoginId());
        var isMatchedPassword = passwordUtil.matches(request.getPassword(), userDto.password());
        if (!isMatchedPassword) {
            throw new MemberException(ResponseCode.INVALID_LOGIN_PASSWORD_ERROR);
        }
        return createToken(userDto);
    }


    @Override
    public void createAdmin() {

    }

    @Override
    public void getMember() {

    }

    @Override
    @Transactional(readOnly = true)
    public GetUserDto getUserById(String userId) throws Exception {
        return userService.getUserById(userId);
    }

    @Override
    public GetUserDto getUserByLoginId(String loginId) {
        return userService.getUserByLoginId(loginId);
    }

    @Override
    @Transactional(readOnly = true)
    public GetUserDto getUserByMemberId(String memberId) throws Exception {
        return userService.getUserByMemberId(memberId);
    }

    @Override
    public void getUsers() {

    }

    @Override
    public boolean isExistedUserByLoginId(String loginId) {
        return userService.isExistedUserByLoginId(loginId);
    }

    @Override
    public boolean isExistAdmin() {
        return false;
    }

    private PostTokenDto createToken(GetUserDto request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // JWT 토큰 생성
        var claims = memberService.createClaims(request, OffsetDateTime.now());
        var token = tokenProvider.createToken(authentication, claims);
        return PostTokenDto.builder().token(token).build();
    }


}
