# Marinboy 구글 시트용 다이어그램 원본

## 사용자 흐름도

```mermaid
flowchart TD
    A["로그인 화면"] --> B{"역할 확인"}
    B -->|고객| C["고객 예약 화면"]
    B -->|관리자| D["관리자 화면"]
    C --> E["카테고리 선택"]
    E --> F["시술 선택"]
    F --> G["날짜 선택"]
    G --> H{"휴무일 또는 일요일?"}
    H -->|예| I["예약 차단 및 가능 시간 없음"]
    H -->|아니오| J["가능 시간 조회"]
    J --> K["고객 정보 입력"]
    K --> L{"노쇼 안내 동의?"}
    L -->|아니오| M["예약 신청 차단"]
    L -->|예| N{"중복 시간 존재?"}
    N -->|예| O["중복 예약 차단"]
    N -->|아니오| P["REQUESTED 예약 생성"]
    D --> Q["예약 상태 확인"]
    Q --> R{"현재 상태"}
    R -->|REQUESTED| S["확정 또는 취소"]
    R -->|CONFIRMED| T["완료 또는 노쇼"]
    R -->|종료 상태| U["상태 변경 불가"]
    D --> V["가격 수정"]
    D --> W["휴무일 등록/삭제"]
```

## ERD

```mermaid
erDiagram
    SALON_SERVICE_ITEM ||--o{ SALON_RESERVATION : "선택됨"
    SALON_SERVICE_ITEM {
        NUMBER ID PK
        VARCHAR NAME
        VARCHAR CATEGORY
        NUMBER DURATION_MINUTES
        NUMBER PRICE
        VARCHAR DESCRIPTION
    }
    SALON_RESERVATION {
        NUMBER ID PK
        NUMBER SERVICE_ID FK
        VARCHAR CUSTOMER_NAME
        VARCHAR CUSTOMER_EMAIL
        VARCHAR CUSTOMER_PHONE
        TIMESTAMP RESERVATION_DATE_TIME
        NUMBER NO_SHOW_POLICY_AGREED
        VARCHAR STATUS
        VARCHAR MEMO
    }
    SALON_HOLIDAY {
        DATE HOLIDAY_DATE PK
        VARCHAR REASON
    }
```

## 상태 전환도

```mermaid
stateDiagram-v2
    [*] --> REQUESTED: 고객 예약 신청
    REQUESTED --> CONFIRMED: 관리자 확정
    REQUESTED --> CANCELED: 관리자 취소
    CONFIRMED --> COMPLETED: 방문 완료
    CONFIRMED --> NO_SHOW: 미방문
    CANCELED --> [*]
    COMPLETED --> [*]
    NO_SHOW --> [*]
```

## 피그마 작성 메모

- 화면은 `로그인`, `고객 예약`, `관리자 대시보드` 3개 프레임으로 나눕니다.
- 고객 화면은 카테고리 선택, 시술 카드, 예약 폼을 한 흐름으로 배치합니다.
- 관리자 화면은 예약 상태 관리, 가격 수정, 휴무일 관리, 노쇼 알림 대상을 카드 섹션으로 분리합니다.
- 디자인은 추후 교체하기 쉽도록 기능 단위 컴포넌트 중심으로 작성합니다.
