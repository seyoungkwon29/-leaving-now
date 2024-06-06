package com.develop.leavingnow.domain.member.persist;

import com.develop.leavingnow.domain.member.vo.*;
import com.develop.leavingnow.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = "activated = true")
@Table(indexes = {
        @Index(name = "i_email", columnList = "email"),
        @Index(name = "i_nickname", columnList = "nickname")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false, updatable = false)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Name name;

    @Embedded
    private Nickname nickname;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Tendency tendency;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    @Column(name = "activated")
    private Boolean activated = true;

    @Builder
    private Member(Long id, Email email, Password password, Name name, Nickname nickname,
                   Tendency tendency, RoleType role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.tendency = tendency;
        this.role = role;
        this.activated = true;
    }
    
    // 비즈니스 로직 추가
}
