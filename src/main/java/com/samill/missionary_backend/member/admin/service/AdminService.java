package com.samill.missionary_backend.member.admin.service;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.common.util.PasswordUtil;
import com.samill.missionary_backend.member.admin.entity.Admin;
import com.samill.missionary_backend.member.admin.repository.AdminRepository;
import com.samill.missionary_backend.member.dto.CreateAdminCommand;
import com.samill.missionary_backend.member.dto.GetAdminDto;
import com.samill.missionary_backend.member.mapper.AdminMapper;
import com.samill.missionary_backend.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordUtil passwordUtil;

    public void createAdmin(CreateAdminCommand request, Member member) {

        var password = passwordUtil.encodePassword(request.getPassword());
        adminRepository.save(Admin.builder()
            .member(member)
            .name(request.getName())
            .phoneNumber(request.getPhoneNumber())
            .birthDate(request.getBirthDate())
            .identityNumber(request.getIdentityNumber())
            .gender(request.getGender())
            .loginId(request.getLoginId())
            .password(password)
            .email(request.getEmail())
            .build());
    }

    public GetAdminDto getAdminById(String id) throws Exception {
        var admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            return AdminMapper.INSTANCE.adminToGetAdminDto(admin.get());
        }
        throw new CommonException(ResponseCode.NOT_FOUND_ERROR);
    }

    public GetAdminDto getAdminByLoginId(String loginId) {
        var admin = adminRepository.findByLoginId(loginId);
        return AdminMapper.INSTANCE.adminToGetAdminDto(admin);
    }

    public GetAdminDto getAdminByMemberId(String memberId) throws Exception {
        var admin = adminRepository.findByMemberMemberId(memberId).orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_ERROR));
        return AdminMapper.INSTANCE.adminToGetAdminDto(admin);
    }

    public Boolean isExistedAdminByLoginId(String loginId) {
        return adminRepository.existsByLoginId(loginId);
    }

}
