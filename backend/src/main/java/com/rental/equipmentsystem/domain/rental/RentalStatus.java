package com.rental.equipmentsystem.domain.rental;

/**
 * 대여 상태
 */
public enum RentalStatus {
    PENDING("대여신청"),                // 고객이 신청했지만 아직 승인 전
    APPROVED("승인됨"),                // 관리자가 승인
    IN_PROGRESS("대여중"),             // 실제 대여 진행 중
    COMPLETED("반납완료"),             // 정상 반납 완료
    CANCELLED("취소됨"),              // 대여 취소
    EXTENSION_REQUESTED("연장요청"),  // 대여 기간 연장 요청
    OVERDUE("연체중"),               // 반납 기한 초과
    DEPOSIT_PENDING("보증금대기"),    // 보증금 입금 전
    RETURED_DAMAGED("파손반납");    // 기계 파손 상태로 반납

    private final String description;

    RentalStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}