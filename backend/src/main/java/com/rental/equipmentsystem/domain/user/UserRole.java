package com.rental.equipmentsystem.domain.user;

/**
 * 사용자 권한
 */
public enum UserRole {
    ADMIN("관리자"),       // 전체 관리 권한
    USER("일반사용자");     // 조회 권한

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}