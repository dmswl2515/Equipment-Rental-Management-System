# CampRent - 캠핑 장비 대여 플랫폼

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-blue.svg)](https://reactjs.org/)

캠핑 장비 전문 대여 플랫폼입니다. 계절별 가격 정책, 패키지 시스템, 날씨 기반 추천 등 복잡한 비즈니스 로직을 포함한 풀스택 프로젝트입니다.

## 프로젝트 목적

- **포트폴리오**: 복잡한 도메인 로직과 외부 API 연동 경험 증명
- **기술 학습**: 최신 기술 스택과 Best Practice 적용
- **확장성**: 웹에서 모바일로 확장 가능한 아키텍처
- **실제 서비스**: MVP 이후 실제 런칭 검토

## 프로젝트 구조

```
equipment-rental-system/
├── backend/              # Spring Boot REST API
│   ├── src/main/java/   # Java 소스 코드
│   ├── src/main/resources/  # 설정 파일
│   ├── build.gradle     # Gradle 빌드 설정
│   └── README.md        # 백엔드 상세 문서
│
├── frontend/            # React Web App (예정)
│   ├── src/
│   ├── package.json
│   └── README.md
│
├── mobile/              # React Native App (예정)
│
├── docs/                # 프로젝트 문서
│   ├── api/            # API 명세서
│   ├── architecture/   # 아키텍처 문서
│   └── guides/         # 개발 가이드
│
├── docker-compose.yml   # 전체 서비스 오케스트레이션
├── CLAUDE.md           # 개발 가이드라인
└── README.md           # 이 파일
```

## 주요 기능

### 캠핑 장비 관리
- 카테고리별 장비 분류 (텐트, 침낭, 버너 등)
- 장비 등록/수정/삭제
- 재고 상태 관리 (대여가능/대여중/수리중/재고없음)
- 계절별 차등 가격 설정 (성수기 할증)

### 대여 관리
- 대여 신청 및 승인 프로세스
- 계절별 가격 자동 적용
- 대여 기간 및 비용 자동 계산
- 보증금 관리
- 대여 이력 추적 및 연체 관리

### 패키지 시스템
- 여러 장비를 묶은 패키지 상품
- 패키지 할인 자동 적용
- 캠핑 레벨별 패키지 추천 (입문자/중급/고급)

### 날씨 기반 추천 (Phase 2)
- 날씨 API 연동
- 날씨에 적합한 장비 추천
- 캠핑 적합도 평가

### 고객 관리
- 개인/기업 고객 분류
- 캠핑 레벨 관리 (추천 시스템용)
- 연락처 및 주소 관리
- 고객별 대여 이력 조회

## 기술 스택

### Backend
- **Language**: Java 21
- **Framework**: Spring Boot 4.x
- **Security**: Spring Security + JWT
- **Database**: PostgreSQL (운영), H2 (개발)
- **Cache**: Redis
- **ORM**: Spring Data JPA
- **Build**: Gradle
- **Lombok**: 보일러플레이트 코드 제거

### Frontend (예정)
- **Language**: TypeScript
- **Framework**: React 18
- **Styling**: Tailwind CSS
- **State**: React Query
- **Build**: Vite

### 외부 API
- **날씨 API**: 기상청 API 또는 OpenWeatherMap
- **지도 API**: 카카오맵 또는 Google Maps
- **결제 API**: 토스페이먼츠 또는 아임포트 (Phase 3)

### DevOps
- **Container**: Docker, Docker Compose
- **CI/CD**: GitHub Actions (예정)
- **Monitoring**: Spring Boot Actuator
- **Deployment**: Railway/Render (Backend), Vercel (Frontend)

## 시작하기

### 사전 요구사항
- Java 21
- Gradle 8.x
- PostgreSQL 15+ (또는 H2 사용)
- Docker & Docker Compose (선택)

### 백엔드 실행

```bash
# 백엔드 디렉토리로 이동
cd backend

# 애플리케이션 실행 (개발 모드)
./gradlew bootRun

# 테스트 실행
./gradlew test

# 프로덕션 빌드
./gradlew build
```

### Docker Compose로 전체 실행 (예정)

```bash
# 전체 서비스 시작
docker-compose up -d

# 로그 확인
docker-compose logs -f

# 서비스 중지
docker-compose down
```

## 문서

- [개발 가이드라인](./CLAUDE.md)
- [백엔드 개발 가이드](./backend/README.md) (예정)
- [API 문서](./docs/api/) (예정)
- [아키텍처 설계](./docs/architecture/) (예정)

## 개발 로드맵

### Phase 1: MVP (진행 중)
- [x] 프로젝트 초기 설정
- [x] 모노레포 구조 구성
- [x] 기본 Entity 작성 (Machine, Customer, Rental, User)
- [ ] 캠핑 도메인 Enum 추가 (CampingCategory, Season)
- [ ] 캠핑 도메인 Entity 작성 (CampingItem, CampingRental)
- [ ] 계절별 가격 정책 구현
- [ ] Repository 및 Service 계층 개발
- [ ] REST API 개발
- [ ] JWT 인증 구현
- [ ] 프론트엔드 기본 화면

### Phase 2: 고급 기능 (예정)
- [ ] 패키지 시스템 구현
- [ ] 날씨 API 연동 및 추천 시스템
- [ ] 캠핑장 지도 연동
- [ ] 실시간 재고 현황 (Redis)
- [ ] 이메일/SMS 알림
- [ ] 캠핑 초보자 가이드
- [ ] 리뷰 시스템

### Phase 3: 확장 (예정)
- [ ] 모바일 앱 개발 (React Native)
- [ ] AI 기반 개인화 추천
- [ ] 결제 시스템 연동
- [ ] 고급 통계 및 리포트
- [ ] 다양한 아웃도어 활동으로 확장

## 핵심 비즈니스 로직

### 계절별 가격 정책
```java
// 성수기(여름) 할증 가격 적용
public BigDecimal getSeasonalPrice(LocalDate rentalDate) {
    Season currentSeason = Season.fromDate(rentalDate);
    if (currentSeason == Season.SUMMER) {
        return peakSeasonRate;  // 50% 할증
    }
    return dailyRate;
}
```

### 패키지 할인
```java
// 여러 장비를 묶어서 20% 할인 제공
public BigDecimal calculateDiscountedPrice() {
    BigDecimal totalIndividual = packageItems.stream()
        .map(item -> item.getPrice().multiply(item.getQuantity()))
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return totalIndividual.multiply(BigDecimal.valueOf(0.8));
}
```

### 날씨 기반 추천
```java
// 날씨 API 연동하여 적합한 캠핑 장비 추천
public List<CampingItem> getWeatherBasedRecommendation(String location, LocalDate date) {
    Weather weather = weatherApiClient.getWeather(location, date);

    if (weather.isRainy()) {
        return itemRepository.findByCategory(CampingCategory.RAIN_GEAR);
    }
    // 날씨별 추천 로직
}
```

## 보안

- JWT 토큰 기반 인증/인가
- 역할 기반 접근 제어 (RBAC)
- BCrypt 비밀번호 암호화
- SQL Injection 방지
- CORS 정책 설정
- 입력값 검증 (Validation)
- 외부 API Key 환경변수 관리

## 성능 최적화

- Redis 캐싱 전략
- 데이터베이스 인덱싱
- JPA 지연 로딩 (Lazy Loading)
- 페이지네이션
- N+1 쿼리 최적화
- API 응답 압축

## 포트폴리오 어필 포인트

### 기술적 도전
- 복잡한 도메인 로직 (계절성 가격, 패키지 시스템)
- 외부 API 연동 (날씨 API, 지도 API)
- 실시간 시스템 (Redis 기반 재고 현황)
- 추천 알고리즘 구현

### 비즈니스 이해도
- 트렌디한 캠핑 시장 타겟
- 실제 서비스로 런칭 가능한 구조
- 사용자 경험 중심 설계

---

**Last Updated**: 2025-12-19
**Current Phase**: Phase 1 - MVP Development
**Status**: In Active Development
