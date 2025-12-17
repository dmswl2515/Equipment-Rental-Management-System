package com.rental.equipmentsystem.domain.machine;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 기계 엔티티
 */
@Entity
@Table(name = "machines")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;                // 기계명 (예 : 굴착기, 포크레인)

    @Column(length = 100)
    private String model;               // 모델명

    @Column(length = 200)
    private String location;            // 현재 위치

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal dailyRate;       // 일일 대여료

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MachineStatus status;       // 기계 상태

    @Column(updatable = false)
    private LocalDateTime createdAt;    // 등록일시

    private LocalDateTime updatedAt;    // 수정일시

    @Builder
    public Machine(String name, String model, String location, BigDecimal dailyRate,
                   MachineStatus status) {
        this.name = name;
        this.model = model;
        this.location = location;
        this.dailyRate = dailyRate;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 비즈니스 메서드 (Setter 대신)
    public void updateInfo(String name, String model, BigDecimal dailyRate) {
        this.name = name;
        this.model = model;
        this.dailyRate = dailyRate;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateLocation(String location) {
        this.location = location;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateStatus(MachineStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
}

