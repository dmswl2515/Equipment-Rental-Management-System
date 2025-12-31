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
public class CampingItemUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String model;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal baseDailyRate;

}
