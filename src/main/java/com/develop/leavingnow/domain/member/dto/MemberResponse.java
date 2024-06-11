package com.develop.leavingnow.domain.member.dto;

import com.develop.leavingnow.domain.member.domain.persist.Member;
import com.develop.leavingnow.domain.member.domain.vo.Email;
import com.develop.leavingnow.domain.member.domain.vo.Name;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private Long memberId;
    private Email email;
    private Name name;

    public static MemberResponse of(final Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getName());
    }
}
