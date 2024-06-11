package com.develop.leavingnow.domain.member.domain.persist;

import com.develop.leavingnow.domain.member.domain.vo.Email;
import com.develop.leavingnow.domain.member.domain.vo.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(final Email email);

    boolean existsByNickname(final Nickname nickname);

    Optional<Member> findByEmail(final Email email);

}