package com.rental.camprent.domain.campingitem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CampingItemRepository extends JpaRepository<CampingItem, Long> {

    List<CampingItem> findByCategory(CampingCategory category);
    List<CampingItem> findByStatus(CampingItemStatus status);
    List<CampingItem> findByCategoryAndStatus(CampingCategory category, CampingItemStatus status);

}
