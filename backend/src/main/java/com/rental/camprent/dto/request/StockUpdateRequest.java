package com.rental.camprent.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 재고 증감용
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockUpdateRequest {

    @NotNull
    private Integer quantity;   // 증감할 수량

}
