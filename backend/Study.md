# 개발 지식 정리

## 목차

- [@Builder 애노테이션 정리](#builder-애노테이션-정리)

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