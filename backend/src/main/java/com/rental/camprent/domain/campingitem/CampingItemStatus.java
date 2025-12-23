package com.rental.camprent.domain.campingitem;

import lombok.Getter;

/**
 * 캠핑 장비 상태
 * */

@Getter
public enum CampingItemStatus {
    AVAILABLE("대여가능"),
    RENTED("대여중"),
    UNDER_REPAIR("수리중"),
    OUT_OF_STOCK("재고없음");

    private final String description;

    CampingItemStatus(String description) {
        this.description = description;
    }

}

