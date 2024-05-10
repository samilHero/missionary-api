package com.samill.missionary_backend.member.member.repository;

import com.samill.missionary_backend.member.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findMemberByMemberId(String memberId);

}
