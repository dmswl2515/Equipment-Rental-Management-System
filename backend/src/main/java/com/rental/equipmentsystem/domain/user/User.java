package com.rental.equipmentsystem.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 사용자 엔티티 (로그인 계정)
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;                            // 로그인 아이디

    @Column(nullable = false)
    private String password;                            // 비밀번호(암호화)

    @Column(nullable = false, length = 100)
    private String name;                                // 실명

    @Column(length = 100)
    private String email;                               // 이메일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;                              // 권한 (ADMIN / USER)

    @Column(nullable = false)
    private boolean enabled;                            // 계정 활성화 여부

    @Column(updatable = false)
    private LocalDateTime createdAt;                    // 가입일시

    private LocalDateTime updatedAt;                    // 수정일시

    private LocalDateTime lastLoginAt;                  // 마지막 로그인 시간

    @Builder
    public User(String username, String password, String name, String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.enabled = true;      // 기본값: 활성화
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 비즈니스 메서드
    public void updateInfo(String name, String email) {
        this.name = name;
        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateLastLogin() {
        this.lastLoginAt = LocalDateTime.now();
    }

    public void disable() {
        this.enabled = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void enable() {
        this.enabled = true;
        this.updatedAt = LocalDateTime.now();
    }

}
