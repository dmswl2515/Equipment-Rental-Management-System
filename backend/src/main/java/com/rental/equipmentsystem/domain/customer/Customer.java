package com.rental.equipmentsystem.domain.customer;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 고객 엔티티
 */
@Entity
@Table(name = "customers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;                    // 고객명

    @Column(nullable = false, length = 20)
    private String phone;                   // 연락처

    @Column(length = 100)
    private String email;                   // 이메일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CustomerType type;              // 고객 유형(개인/기업)

    @Column(length = 200)
    private String address;                 // 주소

    @Column(length = 50)
    private String businessNumber;          // 사업자등록번호 (기업 고객인 경우)

    @Column(updatable = false)
    private LocalDateTime createdAt;        // 등록일시

    private LocalDateTime updatedAt;        // 수정일시

    @Builder
    public Customer(String name, String phone, String email, CustomerType type,
                    String address, String businessNumber) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.address = address;
        this.businessNumber = businessNumber;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 비즈니스 메서드
    public void updateInfo(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
        this.updatedAt = LocalDateTime.now();
    }
}