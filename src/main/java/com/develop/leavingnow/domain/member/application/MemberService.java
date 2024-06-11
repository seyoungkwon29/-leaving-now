package com.develop.leavingnow.domain.member.application;

import com.develop.leavingnow.domain.member.domain.persist.Member;
import com.develop.leavingnow.domain.member.domain.persist.MemberRepository;
import com.develop.leavingnow.domain.member.domain.vo.Email;
import com.develop.leavingnow.domain.member.domain.vo.Nickname;
import com.develop.leavingnow.domain.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 계정 생성
    public MemberResponse join(final Member member) {
        existedEmail(member.getEmail());
        existedNickname(member.getNickname());

        Member savedMember = memberRepository.save(member.encode(passwordEncoder));
        return MemberResponse.of(savedMember);
    }

    private void existedEmail(final Email email) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    private void existedNickname(final Nickname nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException(ErrorCode.DUPLICATE_NICKNAME);
        }
    }
}
