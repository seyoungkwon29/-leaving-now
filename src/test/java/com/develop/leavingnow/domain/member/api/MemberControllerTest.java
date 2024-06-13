package com.develop.leavingnow.domain.member.api;

import com.develop.leavingnow.domain.member.application.MemberService;
import com.develop.leavingnow.domain.member.dto.JoinRequest;
import com.develop.leavingnow.domain.member.dto.MemberResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.develop.leavingnow.domain.member.util.GivenMember.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // MOCK 환경에서 테스트
@AutoConfigureMockMvc // MockMvc를 자동으로 구성하여 테스트 클래스에서 사용할 수 있게 설정
@AutoConfigureRestDocs // 테스트 코드 기반으로 자동으로 Rest API 문서 작성
@ActiveProfiles("test") // application-test.yml 파일 사용
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean // Mock 객체
    MemberService memberService;
    @Autowired
    ObjectMapper objectMapper; // 객체 <-> JSON 직렬화/역직렬화 라이브러리

    @Test
    @DisplayName("정상적으로 회원 생성")
    @WithMockUser
    void 회원가입() throws Exception {
        // given : 테스트에 사용할 JoinRequest 객체 생성
        JoinRequest request = JoinRequest.builder()
                .email(GIVEN_EMAIL)
                .password(GIVEN_PASSWORD)
                .nickname(GIVEN_NICKNAME)
                .name(GIVEN_NAME)
                .birth(GIVEN_BIRTH)
                .build();
        // request -> JSON 문자열로 반환
        String requestBody = objectMapper.writeValueAsString(request);

        MemberResponse memberResponse = MemberResponse.of(request.toEntity());

        // when : memberService.join 메서드가 호출될 때 memberResponse를 반환하도록 설정
        when(memberService.join(any())).thenReturn(memberResponse);

        // then
        회원가입_요청(requestBody)
                .andExpect(status().isCreated()) // 예상값을 검증한다.
//                .andDo(document("/api/members/public/join",
//                        // Spring Rest Docs 설정, 요청 필드와 응답 필드를 정의하여 문서 생성
//                        requestFields(
//                                fieldWithPath("email").description("이메일"),
//                                fieldWithPath("password").description("비밀번호"),
//                                fieldWithPath("nickname").description("닉네임"),
//                                fieldWithPath("name").description("이름"),
//                                fieldWithPath("birth").description("생년월일")
//                        ),
//                        requestFields(
//                                fieldWithPath("memberId").description("회원 고유 식별자"),
//                                fieldWithPath("email").description("이메일"),
//                                fieldWithPath("name").description("이름")
//                                )
//                ))
                .andDo(print()); // 요청에 대한 처리, print()가 일반적
    }

    // MockMvc Class Method - perform()
    // MockMvc를 사용하여 가상의 HTTP 요청 수행, 응답 테스트
    private ResultActions 회원가입_요청(String requestBody) throws Exception {
        // "/api/members/public/join" end-point로 JSON 타입의 POST 요청
        return mockMvc.perform(post("/api/members/public/join")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
    }

}