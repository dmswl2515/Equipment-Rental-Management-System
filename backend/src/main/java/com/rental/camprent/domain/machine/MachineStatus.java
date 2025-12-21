package com.rental.camprent.domain.machine;

/**
 * 기계 상태
 * */

public enum MachineStatus {
    PENDING_ARRIVAL("입고예정"),  // 구매했지만 입고 예정 ;
    AVAILABLE("대여가능"),        // 대여 가능한 상태
    RENTED("대여중"),            // 현재 대여 중인 상태
    UNDER_REPAIR("수리중"),      // 수리 중인 상태
    OUT_OF_SERVICE("서비스중단"); // 더이상 대여를 제공하지 않음

    private final String description;

    MachineStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

