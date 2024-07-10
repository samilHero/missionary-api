package com.samill.missionary_backend.member.user.repository;

import com.samill.missionary_backend.member.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByMemberMemberId(String memberId);

    User findByLoginId(String loginId);

    Boolean existsByLoginId(String loginId);

    List<User> findAllByNameOrderByName(String name, Pageable pageable);

    List<User> findAllByIdIn(List<String> userIds);

    List<User> findAllByNameContains(String name);
}
