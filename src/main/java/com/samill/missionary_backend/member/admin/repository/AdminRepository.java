package com.samill.missionary_backend.member.admin.repository;

import com.samill.missionary_backend.member.admin.entity.Admin;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByMemberMemberId(@NonNull String memberId);

    Admin findByLoginId(@NonNull String loginId);

    Boolean existsByLoginId(@NonNull String loginId);
}
