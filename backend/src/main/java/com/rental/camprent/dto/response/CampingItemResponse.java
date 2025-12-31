package com.rental.camprent.dto.response;

import com.rental.camprent.domain.campingitem.CampingCategory;
import com.rental.camprent.domain.campingitem.CampingItem;
import com.rental.camprent.domain.campingitem.CampingItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CampingItemResponse {

    private Long id;
    private String name;
    private CampingCategory category;
    private String model;
    private String description;
    private Integer stockQuantity;
    private BigDecimal baseDailyRate;
    private CampingItemStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    //static factory method
    public static CampingItemResponse from(CampingItem entity) {
        return new CampingItemResponse(
                entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getModel(),
                entity.getDescription(),
                entity.getStockQuantity(),
                entity.getBaseDailyRate(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
