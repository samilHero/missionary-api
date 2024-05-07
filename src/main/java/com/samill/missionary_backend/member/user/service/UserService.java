package com.samill.missionary_backend.member.user.service;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.common.util.PasswordUtil;
import com.samill.missionary_backend.member.dto.CreateUserCommand;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.mapper.UserMapper;
import com.samill.missionary_backend.member.member.entity.Member;
import com.samill.missionary_backend.member.user.entity.User;
import com.samill.missionary_backend.member.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;

    public void createUser(CreateUserCommand request, Member member) {

        var password = passwordUtil.encodePassword(request.getPassword());
        userRepository.save(User.builder()
            .member(member)
            .name(request.getName())
            .phoneNumber(request.getPhoneNumber())
            .birthDate(request.getBirthDate())
            .identityNumber(request.getIdentityNumber())
            .gender(request.getGender())
            .loginId(request.getLoginId())
            .password(password)
            .email(request.getEmail())
            .is_baptized(request.getIs_baptized())
            .baptizedAt(request.getBaptizedAt())
            .build());
    }


    public GetUserDto getUserById(String id) throws Exception {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            return UserMapper.INSTANCE.userToGetUserDto(user.get());
        }
        throw new CommonException(ResponseCode.NOT_FOUND_ERROR);
    }

    public GetUserDto getUserByLoginId(String loginId) {
        var user = userRepository.findByLoginId(loginId);
        return UserMapper.INSTANCE.userToGetUserDto(user);
    }

    public GetUserDto getUserByMemberId(String memberId) throws Exception {
        var user = userRepository.findByMemberMemberId(memberId);
        if (user.isPresent()) {
            return UserMapper.INSTANCE.userToGetUserDto(user.get());
        }
        throw new CommonException(ResponseCode.NOT_FOUND_ERROR);
    }

    public Boolean isExistedUserByLoginId(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }


}
