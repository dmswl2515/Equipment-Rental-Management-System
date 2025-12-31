package com.rental.camprent.dto.request;

import com.rental.camprent.domain.campingitem.CampingItemStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상태 변경용
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateRequest {

    @NotNull
    private CampingItemStatus status;   // 변경할 상태
    
}
