package com.develop.leavingnow.domain.member.api;

import com.develop.leavingnow.domain.member.application.MemberService;
import com.develop.leavingnow.domain.member.dto.JoinRequest;
import com.develop.leavingnow.domain.member.dto.MemberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    // join
    @PostMapping("/public/join")
    public ResponseEntity<MemberResponse> join(@Valid @RequestBody JoinRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.join(request.toEntity()));
    }
}
