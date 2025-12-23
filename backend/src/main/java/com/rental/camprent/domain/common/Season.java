package com.rental.camprent.domain.common;

import lombok.Getter;

import java.time.LocalDate;
import java.time.Month;

/**
 * 계절 (가격 정책용)
 */
public enum Season {
    SPRING("봄", false),
    SUMMER("여름", true),
    AUTUMN("가을", false),
    WINTER("겨울", false);

    @Getter
    private final String description;
    private final boolean isPeakSeason;

    Season(String description, boolean isPeakSeason) {
        this.description = description;
        this.isPeakSeason = isPeakSeason;
    }

    public boolean isPeakSeason() {
        return isPeakSeason;
    }

    /**
     * 날짜로 계절 계산
     */
    public static Season fromDate(LocalDate date) {
        Month month = date.getMonth();

        return switch (month) {
            case MARCH, APRIL, MAY -> SPRING;
            case JUNE, JULY, AUGUST -> SUMMER;
            case SEPTEMBER, OCTOBER, NOVEMBER -> AUTUMN;
            case DECEMBER, JANUARY, FEBRUARY -> WINTER;
        };
    }
}
