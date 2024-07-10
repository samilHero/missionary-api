package com.samill.missionary_backend.member.user.service;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.common.util.PasswordUtil;
import com.samill.missionary_backend.member.dto.CreateUserCommand;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.dto.GetUsersDto;
import com.samill.missionary_backend.member.dto.GetUsersQuery;
import com.samill.missionary_backend.member.exception.MemberException;
import com.samill.missionary_backend.member.mapper.UserMapper;
import com.samill.missionary_backend.member.member.entity.Member;
import com.samill.missionary_backend.member.user.entity.User;
import com.samill.missionary_backend.member.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;

    public void createUser(@NonNull CreateUserCommand request, @NonNull Member member) {

        var password = passwordUtil.encodePassword(request.getPassword());
        userRepository.save(User.builder()
            .member(member)
            .name(request.getName())
            .phoneNumber(request.getPhoneNumber())
            .birthDate(request.getBirthDate())
            .gender(request.getGender())
            .loginId(request.getLoginId())
            .password(password)
            .email(request.getEmail())
            .isBaptized(request.getIsBaptized())
            .baptizedAt(request.getBaptizedAt())
            .build());
    }


    public GetUserDto getUserById(@NonNull String id) throws Exception {
        var user = userRepository.findById(id).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_ERROR));
        return UserMapper.INSTANCE.userToGetUserDto(user);
    }

    public GetUserDto getUserByLoginId(@NonNull String loginId) {
        var user = userRepository.findByLoginId(loginId);
        return UserMapper.INSTANCE.userToGetUserDto(user);
    }

    public GetUserDto getUserByMemberId(@NonNull String memberId) throws MemberException {
        var user = userRepository.findByMemberMemberId(memberId);
        if (user.isPresent()) {
            return UserMapper.INSTANCE.userToGetUserDto(user.get());
        }
        throw new MemberException(ResponseCode.NOT_FOUND_ERROR);
    }

    public Boolean isExistedUserByLoginId(@NonNull String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    public GetUsersDto getUsers(GetUsersQuery request) {
        final var pageable = PageRequest.of(request.getPageNo(), request.getPageSize());
        var users = userRepository.findAllByNameOrderByName(request.getName(), pageable);

        return GetUsersDto.builder()
            .getUserDtos(
                users.stream().map(UserMapper.INSTANCE::userToGetUserDto).collect(Collectors.toList())
            )
            .build();

    }

    public List<GetUserDto> getUsersByUserIds(List<String> userIds) {
        var users = userRepository.findAllByIdIn(userIds);
        return users.stream().map(UserMapper.INSTANCE::userToGetUserDto).collect(Collectors.toList());

    }

    public List<GetUserDto> getUsersByName(String name) {
        var users = userRepository.findAllByNameContains(name);
        return users.stream().map(UserMapper.INSTANCE::userToGetUserDto).collect(Collectors.toList());

    }
}
