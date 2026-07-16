# marinboy_mybatis

기존 `marinboy` 예약관리 서비스를 `track007_boot_api` 스타일의 MyBatis 구조로 재설계 복원하는 프로젝트입니다.

## 목표 구조

```text
src/main/java/com/marinboy
├── api
├── config
├── controller
├── dao
├── dto
├── llmrag
├── security
├── service
└── util

src/main/resources
└── mybatis/mapper
```

## 1차 검증 목표

```text
Oracle 연결 확인
-> TestDao
-> test-mapper.xml
-> SELECT SYSDATE FROM DUAL
-> /api/db-time 응답
```
