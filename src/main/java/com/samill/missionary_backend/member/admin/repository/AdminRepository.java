package com.samill.missionary_backend.member.admin.repository;

import com.samill.missionary_backend.member.admin.entity.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByMemberMemberId(String memberId);

    Admin findByLoginId(String loginId);

    Boolean existsByLoginId(String loginId);
}
