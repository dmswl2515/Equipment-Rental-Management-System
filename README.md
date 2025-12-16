# 기계 대여 관리 시스템 (Equipment Rental Management System)

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-blue.svg)](https://reactjs.org/)

실제 기계 대여 사업을 위한 통합 관리 시스템입니다. 백엔드, 웹 프론트엔드, 모바일 앱을 포함하는 모노레포 프로젝트입니다.

## 🎯 프로젝트 목적

- **포트폴리오**: 실무 중심의 풀스택 개발 역량 증명
- **상업적 활용**: 실제 기계 대여 사업 운영 도구
- **기술 학습**: 최신 기술 스택과 Best Practice 적용
- **확장성**: 웹에서 모바일로 확장 가능한 아키텍처

## 🏗️ 프로젝트 구조

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
├── mobile/              # React Native/Flutter App (예정)
│
├── docs/                # 프로젝트 문서
│   ├── api/            # API 명세서
│   ├── architecture/   # 아키텍처 문서
│   └── guides/         # 개발 가이드
│
├── docker-compose.yml   # 전체 서비스 오케스트레이션
├── CLAUDE.md           # AI 개발 가이드라인
└── README.md           # 이 파일
```

## 🚀 주요 기능

### 기계 관리
- 기계 등록/수정/삭제
- 기계명/위치 기반 검색
- 실시간 상태 관리 (사용가능/대여중/수리중)
- 일일 대여료 설정

### 대여 관리
- 대여 신청 및 승인 프로세스
- 대여 기간 및 비용 자동 계산
- 보증금 관리
- 대여 이력 추적

### 고객 관리
- 개인/기업 고객 분류
- 연락처 및 주소 관리
- 고객별 대여 이력 조회

### 수익 분석
- 기간별 매출 통계
- 기계별 수익성 분석
- 대여율 및 가동률 리포트
- 보증금 현황 관리

## 💻 기술 스택

### Backend
- **Language**: Java 21
- **Framework**: Spring Boot 3.x
- **Security**: Spring Security + JWT
- **Database**: PostgreSQL (운영), H2 (개발)
- **Cache**: Redis
- **ORM**: Spring Data JPA
- **Build**: Gradle

### Frontend (예정)
- **Language**: TypeScript
- **Framework**: React 18
- **Styling**: Tailwind CSS
- **State**: React Query
- **Build**: Vite

### DevOps
- **Container**: Docker, Docker Compose
- **CI/CD**: GitHub Actions (예정)
- **Monitoring**: Spring Boot Actuator

## 🛠️ 시작하기

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

## 📚 문서

- [백엔드 개발 가이드](./backend/README.md)
- [API 문서](./docs/api/) (예정)
- [아키텍처 설계](./docs/architecture/) (예정)
- [개발 가이드라인](./CLAUDE.md)

## 🗓️ 개발 로드맵

### Phase 1: MVP (진행 중)
- [x] 프로젝트 초기 설정
- [x] 모노레포 구조 구성
- [ ] 백엔드 도메인 모델 설계
- [ ] 핵심 CRUD API 개발
- [ ] JWT 인증 구현
- [ ] 프론트엔드 기본 화면

### Phase 2: 고도화 (예정)
- [ ] Google Maps API 연동
- [ ] 이메일/SMS 알림
- [ ] 고급 대시보드 및 통계
- [ ] 모바일 반응형 UI

### Phase 3: 확장 (예정)
- [ ] 모바일 앱 개발
- [ ] 고객 포털
- [ ] 결제 시스템 연동
- [ ] 고급 보고서 기능

## 🔐 보안

- JWT 토큰 기반 인증/인가
- 역할 기반 접근 제어 (RBAC)
- BCrypt 비밀번호 암호화
- SQL Injection 방지
- CORS 정책 설정
- 입력값 검증 (Validation)

## 📈 성능 최적화

- Redis 캐싱 전략
- 데이터베이스 인덱싱
- 페이지네이션
- N+1 쿼리 최적화
- API 응답 압축

## 🤝 기여

이 프로젝트는 개인 포트폴리오 및 실제 사업용으로 개발 중입니다.

## 📄 라이선스

Private - All Rights Reserved

## 📞 문의

프로젝트 관련 문의 또는 피드백은 이슈로 남겨주세요.

---

**Last Updated**: 2025-12-16
**Current Phase**: Phase 1 - MVP Development
**Status**: 🚧 In Active Development
