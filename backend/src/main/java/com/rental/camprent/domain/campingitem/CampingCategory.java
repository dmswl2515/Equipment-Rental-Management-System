package com.rental.camprent.domain.campingitem;

import lombok.Getter;

/**
 * 캠핑 카테고리
 */
@Getter
public enum CampingCategory {
    TENT("텐트"),
    SLEEPING_BAG("침낭"),
    CAMP_STOVE("버너/스토브"),
    CAMPING_FURNITURE("캠핑 가구"),
    COOKING_GEAR("조리용품"),
    LIGHTING("조명"),
    RAIN_GEAR("우천용품"),
    COOLER("아이스박스/쿨러");

    private final String description;

    CampingCategory(String description) {
        this.description = description;
    }
}
