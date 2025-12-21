# 개발 지식 정리

## 목차

- [@Builder 애노테이션 정리](#builder-애노테이션-정리)
- [JPA 연관관계 매핑 정리](#jpa-연관관계-매핑-정리)
    - [@ManyToOne (다대일 관계)](#manytoone-다대일-관계)
    - [FetchType.LAZY (지연 로딩)](#fetchtypelazy-지연-로딩)
- [비즈니스 로직 위치: Entity vs Service](#비즈니스-로직-위치-entity-vs-service)
---

## @Builder 애노테이션 정리

**@Builder란:**
> **롬복(Lombok)에서 제공하는 애노테이션으로, 빌더 패턴(Builder Pattern)을 자동으로 구현해주는 도구**

### 기본 개념
```java
// 빌더 패턴: 복잡한 객체를 단계별로 생성하는 디자인 패턴
// 롬복 @Builder: 이 패턴을 자동으로 코드 생성해주는 애노테이션
```

### 동작 원리
```java
@Builder  // 이 애노테이션 하나로
public Customer(매개변수들...) { ... }

// 롬복이 자동으로 이런 코드들을 생성:
// 1. CustomerBuilder 클래스
// 2. builder() 정적 메서드  
// 3. 메서드 체이닝을 위한 setter들
// 4. build() 메서드 (실제 객체 생성)
```

### 사용법
```java
// 메서드 체이닝 방식으로 객체 생성
Customer customer = Customer.builder()
                .name("홍길동")           // 필드명.설정값 형태
                .phone("010-1234-5678")  // 순서 상관없이 설정
                .type(CustomerType.INDIVIDUAL)
                .build();                // 마지막에 build()로 객체 생성
```

## 결론

### @Builder 정의
**롬복에서 제공하는 애노테이션으로, 빌더 패턴을 자동 구현하여 객체를 메서드 체이닝 방식으로 생성할 수 있게 해주는 도구**

### @Builder를 생성자에 적용한 이유

1. **기본값 자동 설정** - createdAt, updatedAt이 항상 자동으로 설정됨
2. **선택적 매개변수** - 필요한 필드만 선택해서 설정 가능
3. **가독성** - 메서드 체이닝으로 읽기 쉬운 코드
4. **실수 방지** - 매개변수 순서 헷갈림 방지, null 체크 자동화
5. **유연성** - 개인/기업 고객 상황에 맞춰 다르게 생성 가능

### 핵심
> **빌더 패턴의 편리함 + 생성자의 초기화 로직 = 실무에서 선호하는 객체 생성 방식**

## 예시 코드

### 실제 사용 예시
```java
@Entity
public class Customer {
    // 필드들...

    @Builder
    public Customer(String name, String phone, String email, CustomerType type,
                    String address, String businessNumber) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.address = address;
        this.businessNumber = businessNumber;
        this.createdAt = LocalDateTime.now();      // 자동 설정
        this.updatedAt = LocalDateTime.now();      // 자동 설정
    }
}
```

### 다양한 생성 패턴
```java
// 개인 고객 생성 (최소 정보)
Customer individual = Customer.builder()
                .name("홍길동")
                .phone("010-1234-5678")
                .type(CustomerType.INDIVIDUAL)
                .build();

// 기업 고객 생성 (완전한 정보)
Customer business = Customer.builder()
        .name("ABC 건설")
        .phone("02-123-4567")
        .email("info@abc.co.kr")
        .type(CustomerType.BUSINESS)
        .address("서울시 중구")
        .businessNumber("123-45-67890")
        .build();
```
---

## JPA 연관관계 매핑 정리

### @ManyToOne (다대일 관계)
```java
@ManyToOne
@JoinColumn(name = "machine_id")
private Machine machine;
```

#### 의미
- 여러 개의 Rental → 하나의 Machine
- 한 기계가 여러 번 대여될 수 있음

#### 예시
```
굴착기(Machine)
  ← Rental #1 (2024-01-01 ~ 2024-01-10)
  ← Rental #2 (2024-02-01 ~ 2024-02-15)
  ← Rental #3 (2024-03-01 ~ 2024-03-20)
```

#### 데이터베이스 구조
```sql
-- rental 테이블
CREATE TABLE rental (
    id BIGINT PRIMARY KEY,
    machine_id BIGINT,          -- 외래키
    customer_id BIGINT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (machine_id) REFERENCES machine(id)
);
```

### FetchType.LAZY (지연 로딩)
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "machine_id")
private Machine machine;
```

#### 의미
- Rental을 조회할 때 Machine 정보는 나중에 필요할 때 가져옴
- 성능 최적화!

#### 예시

**LAZY 로딩 (추천)**
```java
// LAZY: Rental만 먼저 조회 (빠름!)
Rental rental = rentalRepository.findById(1L);

// Machine이 필요할 때 그때 DB 조회
String machineName = rental.getMachine().getName();  // 이때 DB 조회!
```

**EAGER 로딩 vs LAZY 로딩 비교**
```java
// EAGER: 항상 Machine까지 함께 조회 (무거움)
@ManyToOne(fetch = FetchType.EAGER)  // 사용 비추천
private Machine machine;

// LAZY: 필요할 때만 조회 (가벼움) 
@ManyToOne(fetch = FetchType.LAZY)   // 추천!
private Machine machine;
```

#### 실무 활용 예시
```java
// 대여 목록 조회 (Machine 정보 불필요)
List rentals = rentalRepository.findAll();
for (Rental rental : rentals) {
    System.out.println("대여 ID: " + rental.getId());
    // Machine 정보는 조회하지 않음 → 빠른 성능!
}

// 특정 대여의 상세 정보 (Machine 정보 필요)
Rental rental = rentalRepository.findById(1L);
System.out.println("기계명: " + rental.getMachine().getName());  // 이때 DB 조회
```

### @JoinColumn 외래키 설정
```java
@JoinColumn(name = "machine_id")
private Machine machine;
```

#### 데이터베이스 테이블 구조
```sql
-- machine 테이블 (부모 테이블)
CREATE TABLE machine (
    id BIGINT PRIMARY KEY,          -- 기본키
    name VARCHAR(100),
    model VARCHAR(50),
    daily_rate DECIMAL(10,2)
);

-- rental 테이블 (자식 테이블)  
CREATE TABLE rental (
    id BIGINT PRIMARY KEY,          -- 기본키
    machine_id BIGINT,              -- 외래키 ← 여기가 핵심!
    customer_id BIGINT,
    start_date DATE,
    end_date DATE,
    total_amount DECIMAL(12,2),
    
    FOREIGN KEY (machine_id) REFERENCES machine(id)  -- 외래키 제약 조건
);
```

#### @JoinColumn의 의미
```
@JoinColumn(name = "machine_id")의 뜻:

1. "rental 테이블에 machine_id라는 컬럼을 만들어라"
2. "이 컬럼은 machine 테이블의 id를 참조하는 외래키다"
3. "Rental 객체의 machine 필드와 이 컬럼을 연결해라"
```

#### 외래키가 필요한 이유

**1. 데이터 무결성 보장**
```sql
-- 올바른 데이터 입력
INSERT INTO rental (id, machine_id, customer_id, start_date) 
VALUES (1, 100, 200, '2024-01-01');  -- machine_id = 100이 machine 테이블에 존재

-- ❌ 잘못된 데이터 입력 시도
INSERT INTO rental (id, machine_id, customer_id, start_date) 
VALUES (2, 999, 200, '2024-01-01');  -- machine_id = 999가 존재하지 않음

-- 결과: 외래키 제약 조건 위반 에러!
-- ERROR: Foreign key constraint violation
```

**2. 참조 무결성 유지**
```sql
-- ❌ 이런 상황을 방지!
-- machine 테이블에서 기계 삭제 시도
DELETE FROM machine WHERE id = 100;

-- 하지만 rental 테이블에서 machine_id = 100인 대여 기록이 있으면?
-- 외래키가 있으면: 삭제 불가 (참조 무결성 보장)
-- 외래키가 없으면: 삭제됨 (고아 레코드 발생!)
```

#### 실제 동작 예시

**Java 코드에서 사용**
```java
// 대여 생성
Rental rental = Rental.builder()
    .machine(existingMachine)  // Machine 객체 할당
    .customer(existingCustomer)
    .startDate(LocalDate.now())
    .endDate(LocalDate.now().plusDays(7))
    .build();

rentalRepository.save(rental);

// JPA가 실행하는 SQL:
// INSERT INTO rental (id, machine_id, customer_id, start_date, end_date) 
// VALUES (1, 100, 200, '2024-01-01', '2024-01-08');
//            ↑ machine.getId() 값이 자동으로 들어감!
```

### 결론

#### @ManyToOne 핵심
- **다대일 관계 표현** - 여러 대여가 하나의 기계를 참조
- **외래키 관리** - @JoinColumn으로 데이터베이스 연결
- **객체 지향적 접근** - rental.getMachine()으로 연관된 객체에 쉽게 접근

#### FetchType.LAZY 핵심
- **성능 최적화** - 필요한 데이터만 조회
- **N+1 문제 방지** - 불필요한 쿼리 실행 방지
- **실무 표준** - 대부분의 연관관계에서 LAZY 사용 권장

#### @JoinColumn 핵심
- **데이터 무결성** - 존재하지 않는 기계에 대한 대여 생성 방지
- **참조 무결성** - 기계가 삭제되면 관련 대여 처리 방식 제어
- **비즈니스 규칙** - "대여는 반드시 특정 기계에 속해야 함" 규칙 강제

## 비즈니스 로직 위치: Entity vs Service

### 두 가지 설계 방식

#### 1. Rich Domain Model (도메인 모델 패턴) - Entity에 로직
```java
// Entity
public class Rental {
    public void approve() {
        if (this.status != RentalStatus.PENDING) {
            throw new IllegalStateException("승인 대기 상태가 아닙니다");
        }
        this.status = RentalStatus.APPROVED;
        this.updatedAt = LocalDateTime.now();
    }
}

// Service (얇게 유지)
public class RentalService {
    public void approveRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId);
        rental.approve();  // ← Entity의 메서드 호출
        rentalRepository.save(rental);
    }
}
```

#### 2. Anemic Domain Model (빈약한 도메인 모델) - Service에 로직
```java
// Entity (getter/setter만)
public class Rental {
    // 비즈니스 로직 없음, getter/setter만
}

// Service (두꺼움)
public class RentalService {
    public void approveRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId);
        
        // 모든 로직이 Service에!
        if (rental.getStatus() != RentalStatus.PENDING) {
            throw new IllegalStateException("승인 대기 상태가 아닙니다");
        }
        rental.setStatus(RentalStatus.APPROVED);
        rental.setUpdatedAt(LocalDateTime.now());
        
        rentalRepository.save(rental);
    }
}
```

### 비교표

| 항목 | Rich Domain Model (Entity에) | Anemic Model (Service에) |
|------|------------------------------|--------------------------|
| 로직 위치 | Entity | Service |
| Entity 역할 | 데이터 + 행동 | 데이터만 |
| Service 역할 | 조율만 (얇음) | 모든 로직 처리 (두꺼움) |
| 장점 | 객체지향적, 재사용성 높음, 캡슐화 | 이해하기 쉬움, 단순함 |
| 단점 | 복잡할 수 있음 | Entity가 데이터 덩어리, 중복 발생 가능 |
| 적합한 경우 | 복잡한 도메인 로직 | 단순한 CRUD |

### 언제 어디에 둘까?

#### Entity에 두는 것이 좋은 경우

**1. 단일 Entity의 상태 변경**
```java
//  Entity에!
public void approve() {
    if (this.status != RentalStatus.PENDING) {
        throw new IllegalStateException("승인 대기 상태가 아닙니다");
    }
    this.status = RentalStatus.APPROVED;
    this.updatedAt = LocalDateTime.now();
}
```

**2. 유효성 검증**
```java
//  Entity에!
public void extend(LocalDate newEndDate) {
    if (newEndDate.isBefore(this.endDate)) {
        throw new IllegalArgumentException("연장일은 기존 종료일보다 늦어야 합니다");
    }
    this.endDate = newEndDate;
    this.updatedAt = LocalDateTime.now();
}
```

**3. 계산 로직**
```java
//  Entity에!
public long getRentalDays() {
    return ChronoUnit.DAYS.between(startDate, endDate) + 1;
}
```

#### Service에 두는 것이 좋은 경우

**1. 여러 Entity를 조합**
```java
//  Service에!
public void createRental(CreateRentalRequest request) {
    Machine machine = machineRepository.findById(request.getMachineId());
    Customer customer = customerRepository.findById(request.getCustomerId());

    // Machine 상태도 변경
    machine.updateStatus(MachineStatus.RENTED);

    // Rental 생성
    Rental rental = Rental.builder()
        .machine(machine)
        .customer(customer)
        .build();

    rentalRepository.save(rental);
}
```

**2. 외부 서비스 호출**
```java
// ✅ Service에!
public void completeRental(Long rentalId) {
    Rental rental = rentalRepository.findById(rentalId);
    rental.complete(LocalDate.now());

    // 이메일 발송 (외부 서비스)
    emailService.sendReturnConfirmation(rental);

    // SMS 발송 (외부 서비스)
    smsService.sendReturnNotification(rental);
}
```

**3. 트랜잭션 관리**
```java
// ✅ Service에!
@Transactional
public void cancelRentalAndRefund(Long rentalId) {
    Rental rental = rentalRepository.findById(rentalId);
    rental.cancel();

    // 보증금 환불 처리
    paymentService.refund(rental.getDeposit());
}
```

### 실무 권장 패턴

**Entity: 자기 자신에 대한 로직**
```java
@Entity
public class Rental {
    // ✅ 상태 변경
    public void approve() { ... }

    // ✅ 유효성 검증
    public void extend(LocalDate newEndDate) { ... }

    // ✅ 계산
    public long getRentalDays() { ... }
}
```

**Service: 여러 객체 조율, 외부 연동**
```java
@Service
public class RentalService {
    // ✅ 여러 Entity 조합
    public void createRental(CreateRentalRequest request) {
        Machine machine = ...;
        Customer customer = ...;
        Rental rental = Rental.builder()...;
    }

    //  외부 서비스 호출
    public void completeWithNotification(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId);
        rental.complete(LocalDate.now());  // ← Entity 메서드
        emailService.send(...);  // ← Service에서 처리
    }
}
```

### 이론적 배경

이것이 바로 **DDD (Domain-Driven Design, 도메인 주도 설계)**의 핵심 개념입니다!

- **Martin Fowler**: "Rich Domain Model을 권장"
- **Spring 공식**: 두 가지 모두 지원하지만 Rich Model 선호
- **실무**: 프로젝트 복잡도에 따라 선택

### 핵심 원칙
> **"Entity는 자신의 상태와 행동을 책임지고, Service는 여러 Entity를 조율한다"**