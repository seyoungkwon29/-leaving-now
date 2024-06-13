package com.develop.leavingnow.domain.member.application;

import com.develop.leavingnow.domain.member.domain.persist.MemberRepository;
import com.develop.leavingnow.domain.member.domain.vo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * 전체 테스트 시 IDENTITY가 증가하므로 1L, 2L, 3L
 * 하나씩 단위테스트 할 땐 1L로 테스트
 */
@SpringBootTest
@Transactional // 각각 테스트 메서드에 대해서 트랜잭션 시작, 테스트 종료되면 롤백
@ActiveProfiles("test") // 테스트 수행 시 사용할 프로필 설정 (application-test.yml 로드하여 테스트 수행)
class MemberServiceTest{

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    static Email email;
    static Long memberId;

}