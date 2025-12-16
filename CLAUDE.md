# 기계 대여 관리 시스템 - 개발 가이드라인

## 프로젝트 개요

### 목적
- **포트폴리오**: 백엔드 개발자 역량 증명
- **상업적 활용**: 아버지의 기계 대여 사업 관리
- **학습**: 최신 기술 스택과 실무 패턴 학습
- **확장성**: 웹 → 모바일 앱으로 확장 예정

### 비즈니스 요구사항
1. 기계명 검색으로 위치 확인
2. 대여 정보 관리 (고객, 기간, 비용, 보증금)
3. 수익 계산 및 통계
4. 기계 상태 관리 (사용가능/대여중/수리중)

---

## 기술 스택

### 현재 스택
- **Java 21** (LTS)
- **Spring Boot 3.x**
- **Spring Security** (JWT 인증)
- **Spring Data JPA**
- **PostgreSQL** (운영), **H2** (개발)
- **Redis** (캐싱, 세션)
- **Spring Boot Actuator** (모니터링)
- **Gradle**

### 향후 확장
- **Frontend**: React 18 + TypeScript, Tailwind CSS, React Query, Vite
- **배포**: Docker, Railway/Render, Vercel/Netlify
- **Database**: Supabase PostgreSQL

---

## 데이터베이스 설계

### 핵심 엔티티

#### Machine (기계)
- id, name, model, location, dailyRate, status (사용가능/대여중/수리중)

#### Customer (고객)
- id, name, phone, email, type (개인/기업), address

#### Rental (대여)
- id, machine(FK), customer(FK), startDate, endDate, totalCost, deposit, status (진행중/완료/취소)

#### User (시스템 사용자)
- id, username, password, role (관리자/일반사용자)

---

## 개발 단계 (Phase 1: MVP)

### Week 1-2: 백엔드 기초 (현재)
- [x] 프로젝트 초기 설정
- [ ] 도메인 주도 설계 패턴으로 구조 설정
- [ ] 엔티티 생성
- [ ] 기본 CRUD API

### Week 3-4: 핵심 API
- [ ] 기계 검색 API (이름, 위치)
- [ ] 대여 관리 API (CRUD)
- [ ] 수익 계산 API
- [ ] JWT 인증/인가

### Week 5-6: 프론트엔드
- [ ] 기계 검색 화면
- [ ] 대여 관리 화면
- [ ] 수익 대시보드
- [ ] 로그인/회원가입

---

## 핵심 기능

### 1. 기계 관리
- 등록/수정/삭제, 검색, 위치 표시, 상태 관리, 대여료 설정

### 2. 대여 관리
- 신청/승인, 기간 설정, 비용 자동 계산, 보증금 관리, 상태 추적

### 3. 고객 관리
- 개인/기업 구분, 연락처, 대여 이력

### 4. 수익 관리
- 기간별 매출, 기계별 수익성, 대여율, 보증금 현황

---

## 보안 & 아키텍처

### 보안
- JWT 토큰 인증
- 역할 기반 권한 (ADMIN/USER)
- CORS 설정
- 입력값 검증, SQL Injection 방지
- BCrypt 비밀번호 암호화

### 아키텍처
- **레이어드 아키텍처**: Controller → Service → Repository
- **도메인 주도 설계** 패턴 적용
- RESTful API 설계
- 캐싱 전략 (Redis)
- 페이징, 인덱싱 최적화

---

## 개발 가이드

### 추천 개발 순서
1. **도메인 모델**: Entity, Enum 정의
2. **Repository**: JPA Repository 구현
3. **Service**: 비즈니스 로직
4. **Controller**: REST API
5. **Security**: 인증/인가
6. **Exception Handler**: 전역 예외 처리
7. **Validation**: 검증 로직
8. **Test**: 단위/통합 테스트

### 코드 작성 원칙
- 단계별로 하나씩 구현 (한 번에 하나의 기능)
- 테스트 주도 개발 권장
- 명확한 네이밍 (한글 주석 OK)
- 비즈니스 로직은 Service 계층에
- Controller는 얇게 유지

---

## 향후 계획

### Phase 2: 고도화
- Google Maps API 연동
- 이메일/SMS 알림
- 고급 대시보드 (차트)
- 모바일 반응형

### Phase 3: 확장
- 모바일 앱 (React Native/Flutter)
- 고객 포털
- 고급 보고서
- 결제 시스템

---

## 개발 시 참고사항

### 사용자 의도
- 포트폴리오용이므로 **Best Practice** 적용 중요
- 실제 사업에 사용할 것이므로 **실용성** 우선
- 하나씩 배우며 만들고 싶음 → **설명 필요**
- 확장 가능한 구조 → **모듈화, 재사용성**

### 응답 방식
- 코드 제공 시 왜 이렇게 작성했는지 설명
- 단계별로 진행 (한 번에 다 하지 말 것)
- 실무 패턴과 이유 설명
- 대안이 있으면 함께 제시

---

**작성일**: 2025-12-16
**현재 상태**: 프로젝트 초기 설정 완료, 도메인 모델 설계 시작 전
