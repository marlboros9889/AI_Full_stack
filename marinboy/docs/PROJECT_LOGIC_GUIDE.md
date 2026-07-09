# 마린보이 예약관리 서비스 로직 설명 가이드

## 1. 프로젝트를 한 문장으로 설명하기

마린보이는 1인 미용실과 뷰티샵을 위한 예약관리 서비스입니다. 고객은 로그인 후 시술 메뉴와 이미지를 보고 날짜와 시간을 선택해 예약을 신청하고, 관리자는 예약 상태, 휴무일, 시술 메뉴, 이미지 갤러리, 이달의 TOP3 메뉴를 관리합니다.

발표용 문장:

```text
이 프로젝트는 1인샵 운영자가 예약 중복과 노쇼를 줄이고, 고객은 시술 이미지와 가격을 확인한 뒤 예약할 수 있도록 만든 미용/뷰티 예약관리 서비스입니다.
```

## 2. 전체 실행 흐름

```text
브라우저 화면
-> JavaScript fetch 요청
-> Controller
-> Service
-> Repository
-> DB
-> Service에서 검증/변환
-> Controller 응답
-> JavaScript가 화면 다시 그림
```

설명 포인트:

Controller는 요청을 받는 입구입니다.

Service는 실제 비즈니스 규칙을 처리하는 핵심입니다.

Repository는 DB에 접근하는 통로입니다.

DTO는 화면과 서버가 주고받는 데이터 모양입니다.

Entity는 DB 테이블과 연결되는 Java 객체입니다.

## 3. 로그인과 권한 흐름

관련 파일:

```text
src/main/java/com/marinboy/marinboy/auth/AuthInterceptor.java
src/main/java/com/marinboy/marinboy/controller/AuthController.java
src/main/java/com/marinboy/marinboy/service/AuthService.java
src/main/resources/templates/login.html
```

실행 흐름:

```text
고객 또는 관리자 로그인
-> AuthController가 로그인 요청 받음
-> AuthService가 계정 정보 확인
-> 성공하면 SessionUser를 세션에 저장
-> AuthInterceptor가 이후 요청마다 세션 확인
-> 관리자 API는 ADMIN 권한인지 추가 확인
```

왜 필요한가:

예약 신청은 고객도 가능하지만, 메뉴 등록/수정/삭제, 예약 상태 변경, 휴무일 등록은 관리자만 해야 합니다. 그래서 세션 기반 로그인과 권한별 접근 제어가 필요합니다.

발표용 문장:

```text
로그인 성공 시 사용자 정보를 세션에 저장하고, 모든 요청은 AuthInterceptor에서 세션과 권한을 확인하도록 구성했습니다. 고객과 관리자의 화면 및 API 접근 범위를 분리했습니다.
```

## 4. 고객 예약 신청 흐름

관련 파일:

```text
src/main/resources/templates/index.html
src/main/java/com/marinboy/marinboy/controller/SalonReservationApiController.java
src/main/java/com/marinboy/marinboy/service/SalonReservationService.java
src/main/java/com/marinboy/marinboy/dto/CreateReservationRequest.java
src/main/java/com/marinboy/marinboy/model/SalonReservation.java
```

실행 흐름:

```text
고객이 시술 선택
-> 날짜 선택
-> /api/services/{serviceId}/available-slots 호출
-> 예약 가능한 시간 조회
-> 고객 정보와 메모 입력
-> /api/reservations POST 요청
-> Service에서 영업시간, 휴무일, 중복 예약, 노쇼 이력 검증
-> 예약 상태 REQUESTED로 저장
```

왜 필요한가:

1인샵은 한 시간대에 한 명만 받을 수 있기 때문에 중복 예약을 막아야 합니다. 또한 휴무일과 영업시간을 검증해야 실제 운영 가능한 예약만 저장됩니다.

발표용 문장:

```text
예약 요청은 바로 확정되지 않고 REQUESTED 상태로 저장됩니다. 원장이 확인 후 CONFIRMED 또는 CANCELED로 변경할 수 있어 1인샵 운영 흐름에 맞게 설계했습니다.
```

## 5. 예약 가능 시간 계산 흐름

관련 메서드:

```text
SalonReservationService.getAvailableSlots()
SalonReservationService.hasConflict()
SalonReservationService.overlaps()
```

실행 흐름:

```text
선택한 시술의 소요시간 조회
-> 날짜가 일요일인지 확인
-> 관리자가 등록한 휴무일인지 확인
-> 10:00부터 20:00까지 30분 단위로 시간 생성
-> 이미 예약된 시간과 겹치는지 확인
-> 가능한 시간만 응답
```

왜 필요한가:

시술마다 소요 시간이 다릅니다. 60분 시술이 10시에 있으면 10시 30분도 겹치기 때문에 단순히 같은 시작 시간만 비교하면 안 됩니다.

발표용 문장:

```text
예약 중복 검사는 시작 시간이 같은지만 보는 것이 아니라, 기존 예약의 종료 시간과 새 예약의 종료 시간을 비교해 시간 구간이 겹치는지 검사하도록 만들었습니다.
```

## 6. 예약 상태 관리 흐름

상태 종류:

```text
REQUESTED: 고객이 예약 신청한 상태
CONFIRMED: 관리자가 예약을 확정한 상태
CANCELED: 관리자가 예약을 취소한 상태
COMPLETED: 시술이 완료된 상태
NO_SHOW: 고객이 노쇼 처리된 상태
```

상태 변경 규칙:

```text
REQUESTED -> CONFIRMED
REQUESTED -> CANCELED
CONFIRMED -> COMPLETED
CONFIRMED -> NO_SHOW
```

왜 필요한가:

확정 전 예약만 취소/확정할 수 있고, 확정된 예약만 완료/노쇼 처리할 수 있어야 운영 흐름이 꼬이지 않습니다.

발표용 문장:

```text
예약 상태는 아무 상태로나 바뀌지 않게 제한했습니다. 예약 신청, 확정, 완료 또는 노쇼 처리라는 실제 매장 운영 순서를 코드로 강제했습니다.
```

## 7. 휴무일 관리 흐름

관련 파일:

```text
src/main/java/com/marinboy/marinboy/model/SalonHoliday.java
src/main/java/com/marinboy/marinboy/dto/HolidayRequest.java
src/main/resources/templates/admin.html
```

실행 흐름:

```text
관리자가 휴무 날짜와 사유 입력
-> /api/holidays POST 요청
-> SalonHoliday 저장
-> 고객이 해당 날짜 선택
-> available-slots가 빈 배열 반환
-> 예약 생성 요청도 Service에서 차단
```

왜 필요한가:

관리자가 특정일에 쉬는 경우 고객 화면에서 예약 가능 시간이 보이면 안 되고, 직접 API 요청을 해도 예약이 저장되면 안 됩니다.

발표용 문장:

```text
휴무일은 화면에서만 막는 것이 아니라 예약 생성 로직에서도 한 번 더 검증합니다. 그래서 사용자가 직접 요청을 보내도 휴무일 예약은 저장되지 않습니다.
```

## 8. 시술 메뉴 관리 흐름

관련 파일:

```text
src/main/java/com/marinboy/marinboy/model/SalonServiceItem.java
src/main/java/com/marinboy/marinboy/dto/ServiceItemRequest.java
src/main/java/com/marinboy/marinboy/dto/SalonServiceResponse.java
src/main/resources/templates/admin.html
```

관리자가 입력하는 값:

```text
시술명
카테고리
소요 시간
가격
설명
대표 이미지
추가 이미지 최대 3장
TOP3 순위
```

검증 규칙:

```text
시간은 최소 10분 이상
시간은 10분 단위
가격은 최소 1,000원 이상
가격은 1,000원 단위
대표 이미지는 URL 또는 업로드 파일 필수
추가 이미지는 최대 3장
TOP3 순위는 1~3만 가능
```

왜 필요한가:

메뉴 정보는 고객이 예약을 결정하는 가장 중요한 데이터입니다. 가격, 시간, 이미지가 정확해야 예약 선택 과정이 자연스럽습니다.

발표용 문장:

```text
관리자는 시술 메뉴를 직접 등록하고 수정할 수 있습니다. 가격과 시간은 실제 매장 운영 기준에 맞춰 천원 단위, 10분 단위로 제한했습니다.
```

## 9. 이미지 업로드와 갤러리 흐름

관련 파일:

```text
src/main/java/com/marinboy/marinboy/service/ImageStorageService.java
src/main/java/com/marinboy/marinboy/config/WebConfig.java
src/main/resources/templates/admin.html
src/main/resources/templates/index.html
```

실행 흐름:

```text
관리자가 이미지 파일 선택
-> /api/service-images POST 요청
-> ImageStorageService가 uploads/service-images 폴더에 파일 저장
-> /uploads/service-images/파일명 URL 반환
-> 메뉴 저장 시 imageUrl 또는 additionalImageUrls에 저장
-> 고객 화면에서 이미지 표시
```

왜 URL과 파일을 둘 다 허용했는가:

URL 방식은 빠르게 샘플 이미지를 등록하기 좋고, 파일 업로드 방식은 실제 매장에서 직접 찍은 시술 사진을 올릴 수 있습니다.

왜 대표 이미지와 추가 이미지를 나눴는가:

대표 이미지는 목록 카드에서 빠르게 보여주는 용도입니다. 추가 이미지는 고객이 시술 결과를 더 자세히 비교할 수 있게 하는 갤러리 용도입니다.

발표용 문장:

```text
고객은 시술 결과 이미지를 보고 예약을 결정하는 경우가 많기 때문에 대표 이미지와 추가 이미지 갤러리를 분리했습니다. 관리자는 URL 또는 파일 업로드 중 편한 방식으로 이미지를 등록할 수 있습니다.
```

## 10. 이달의 헤어 TOP3 흐름

관련 필드:

```text
SalonServiceItem.topRank
```

실행 흐름:

```text
관리자가 메뉴 수정 시 TOP 1~3 선택
-> topRank 저장
-> 같은 순위가 이미 있으면 기존 메뉴의 순위 해제
-> 고객과 관리자 화면에서 topRank 순서대로 표시
```

왜 필요한가:

고객이 메뉴가 많을 때 무엇을 선택할지 고민할 수 있습니다. TOP3 추천은 고객 선택을 도와주고, 관리자는 이달에 밀고 싶은 메뉴를 노출할 수 있습니다.

발표용 문장:

```text
TOP3 기능은 관리자가 추천 메뉴를 직접 큐레이션하는 기능입니다. 고객은 로그인 후 바로 추천 메뉴를 확인할 수 있어 예약 선택까지의 시간을 줄일 수 있습니다.
```

## 11. 고객 이력 조회 흐름

관련 메서드:

```text
SalonReservationService.getCustomerHistory()
```

실행 흐름:

```text
고객이 로그인
-> 연락처 또는 소셜 로그인 정보 기준으로 고객 조회
-> 과거 예약 내역 조회
-> 시술명, 카테고리, 예약일시, 상태, 메모 반환
```

왜 필요한가:

1인샵은 재방문 고객 관리가 중요합니다. 고객이 이전에 어떤 시술을 받았는지 확인할 수 있으면 상담과 재예약에 도움이 됩니다.

발표용 문장:

```text
고객 정보는 한 번 저장되면 이후 예약 이력과 연결됩니다. 같은 고객이 다시 예약할 때 이전 시술 내역을 확인할 수 있도록 구성했습니다.
```

## 12. 노쇼 방지 흐름

관련 기능:

```text
노쇼 안내 동의
NO_SHOW 상태
노쇼 연락처 재예약 차단
관리자 알림 대상 조회
```

실행 흐름:

```text
고객이 예약 시 노쇼 안내 동의
-> 관리자가 확정 예약을 NO_SHOW 처리
-> 같은 연락처로 새 예약 시도
-> Service에서 노쇼 이력 확인
-> 예약 차단
```

왜 필요한가:

1인샵은 한 명이 예약을 놓치면 그 시간 전체 매출이 사라집니다. 노쇼 이력 관리는 운영 리스크를 줄이는 기능입니다.

발표용 문장:

```text
1인샵은 시간 약속이 중요하기 때문에 노쇼 방지 기능을 넣었습니다. 노쇼 처리된 연락처는 새 예약을 바로 등록할 수 없고 관리자 문의가 필요하도록 했습니다.
```

## 13. DTO가 필요한 이유

DTO 예시:

```text
CreateReservationRequest
SalonReservationResponse
ServiceItemRequest
SalonServiceResponse
HolidayRequest
ApiErrorResponse
```

왜 필요한가:

Entity를 그대로 화면에 보내면 DB 구조가 외부에 노출됩니다. DTO를 사용하면 화면에 필요한 데이터만 안전하게 주고받을 수 있습니다.

발표용 문장:

```text
요청과 응답에는 DTO를 사용했습니다. Entity를 직접 노출하지 않고, 화면에 필요한 데이터만 전달하기 위해서입니다.
```

## 14. 예외 처리 흐름

관련 파일:

```text
src/main/java/com/marinboy/marinboy/exception/ApiExceptionHandler.java
```

실행 흐름:

```text
Service에서 IllegalArgumentException 또는 IllegalStateException 발생
-> ApiExceptionHandler가 JSON 에러 응답으로 변환
-> 프론트가 message 값을 읽어 화면에 표시
```

왜 필요한가:

오류가 HTML 에러 페이지로 나오면 JavaScript가 처리하기 어렵습니다. 공통 JSON 구조로 내려주면 화면에서 같은 방식으로 오류 메시지를 보여줄 수 있습니다.

발표용 문장:

```text
API 오류는 공통 예외 처리 클래스를 통해 JSON 형태로 통일했습니다. 프론트에서는 message 값을 읽어 사용자에게 바로 보여줄 수 있습니다.
```

## 15. 발표 때 자주 나올 질문과 답변

질문: 왜 예약 상태를 바로 확정하지 않았나요?

답변:

```text
1인샵은 원장 일정이나 시술 가능 여부를 확인해야 하므로 고객 요청은 REQUESTED로 저장하고, 관리자가 확인 후 확정하도록 만들었습니다.
```

질문: 왜 시간 중복 검사를 복잡하게 했나요?

답변:

```text
시술마다 소요 시간이 다르기 때문입니다. 60분 시술이 10시에 있으면 10시 30분 예약도 겹치므로 시간 구간 기준으로 검사했습니다.
```

질문: 왜 이미지 업로드 기능을 넣었나요?

답변:

```text
미용과 뷰티 서비스는 결과 이미지가 고객 선택에 큰 영향을 줍니다. 그래서 대표 이미지와 추가 이미지 갤러리를 제공해 고객이 시술 결과를 보고 예약할 수 있게 했습니다.
```

질문: 왜 TOP3 기능이 필요한가요?

답변:

```text
메뉴가 많으면 고객이 선택을 어려워할 수 있습니다. 관리자가 추천 메뉴를 TOP3로 노출하면 고객의 선택을 도와주고 매장에서 밀고 싶은 메뉴도 강조할 수 있습니다.
```

질문: 왜 가격과 시간을 단위 제한했나요?

답변:

```text
실제 미용실은 보통 가격을 천원 단위로, 시술 시간을 10분 단위로 관리합니다. 그래서 입력값도 실제 운영 기준에 맞게 제한했습니다.
```

## 16. 외우는 순서 추천

1. 프로젝트 한 문장 설명
2. 고객 예약 흐름
3. 관리자 예약 상태 변경
4. 메뉴 등록과 이미지 갤러리
5. 휴무일과 중복 예약 차단
6. 로그인과 권한 분리
7. 노쇼 방지
8. TOP3 추천 메뉴
9. DTO와 예외 처리 이유

## 17. 짧은 발표 스크립트

```text
마린보이는 1인 미용실과 뷰티샵을 위한 예약관리 서비스입니다.
고객은 로그인 후 시술 메뉴, 가격, 소요 시간, 대표 이미지와 추가 이미지 갤러리를 확인하고 예약을 신청할 수 있습니다.
예약 신청은 바로 확정되지 않고 REQUESTED 상태로 저장되며, 관리자가 확인 후 확정, 취소, 완료, 노쇼 상태로 변경할 수 있습니다.
예약 시간은 시술 소요 시간을 기준으로 중복 여부를 검사하고, 휴무일이나 영업시간 외 예약은 저장되지 않게 막았습니다.
관리자는 메뉴 등록과 수정, 이미지 업로드, 이달의 TOP3 추천 메뉴, 휴무일을 관리할 수 있습니다.
또한 노쇼 이력이 있는 연락처는 새 예약을 제한해 1인샵 운영에서 중요한 시간 손실을 줄이도록 설계했습니다.
```
