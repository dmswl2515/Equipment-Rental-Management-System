package com.rental.camprent.domain.customer;

/**
 * 고객 유형
 */
public enum CustomerType {
    INDIVIDUAL("개인"),       // 개인 고객
    BUSINESS("기업");         // 기업 고객

    private final String description;

    CustomerType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}