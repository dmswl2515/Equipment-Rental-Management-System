package com.rental.camprent.dto.request;

import com.rental.camprent.domain.campingitem.CampingCategory;
import com.rental.camprent.domain.campingitem.CampingItemStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CampingItemCreateRequest {

    @NotBlank
    private String name;

    @NotNull
    private CampingCategory category;

    @NotBlank
    private String model;

    @NotBlank
    private String description;

    @NotNull
    private Integer stockQuantity;

    @NotNull
    private BigDecimal baseDailyRate;

    @NotNull
    private CampingItemStatus status;
}
