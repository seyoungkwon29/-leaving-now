package com.develop.leavingnow.domain.auth.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureRestDocs // Spring REST Docs를 설정하고, 테스트 중에 자동으로 RESTful API를 테스트하면서 API 문서를 자동으로 생성
@AutoConfigureMockMvc // MockMvc 테스트를 자동으로 구성, 주로 통합 테스트에서 사용
@SpringBootTest // Spring Boot 애플리케이션의 통합 테스트를 위한 설정을 제공, 모든 Bean 로드
@ActiveProfiles("test") // 지정된 프로파일을 활성화하여, 해당 프로파일에 정의된 설정을 적용
@Transactional // 테스트 메서드가 트랜잭션 내에서 실행, 테스트가 끝난 후에는 트랜잭션 롤백
class AuthControllerTest {


}