# 소셜 로그인 설정 메모

## 적용 방향

- 기존 데모 로그인은 관리자 접속과 로컬 검증용으로 유지합니다.
- 소셜 로그인 사용자는 고객 권한(`CUSTOMER`)으로 세션에 저장합니다.
- 로그인 화면에는 Google, Naver, Kakao 진입 버튼을 표시합니다.
- 단, 실제 외부 소셜 로그인 완료는 각 개발자센터에서 발급한 Client ID/Secret이 환경 변수로 설정되어야 가능합니다.
- 키가 없는 상태에서도 기존 데모 계정 로그인과 예약 서비스는 그대로 실행됩니다.

## 실행 방법

OAuth2 키를 발급받은 뒤 환경 변수를 설정합니다. 현재 프로젝트는 기본 실행 시 `oauth` 프로필을 함께 읽도록 되어 있습니다.

```powershell
$env:GOOGLE_CLIENT_ID="발급받은 Google Client ID"
$env:GOOGLE_CLIENT_SECRET="발급받은 Google Client Secret"
$env:NAVER_CLIENT_ID="발급받은 Naver Client ID"
$env:NAVER_CLIENT_SECRET="발급받은 Naver Client Secret"
$env:KAKAO_CLIENT_ID="발급받은 Kakao REST API Key"
$env:KAKAO_CLIENT_SECRET="발급받은 Kakao Client Secret"
mvn spring-boot:run "-Dspring-boot.run.profiles=oauth"
```

기본 실행을 사용할 때는 아래처럼 실행해도 됩니다.

```powershell
mvn spring-boot:run
```

Oracle과 함께 실행할 때는 프로필을 같이 지정합니다.

```powershell
mvn spring-boot:run "-Dspring-boot.run.profiles=oracle,oauth"
```

## Redirect URI

각 개발자 콘솔에 아래 Redirect URI를 등록합니다.

```text
http://localhost:8080/login/oauth2/code/google
http://localhost:8080/login/oauth2/code/naver
http://localhost:8080/login/oauth2/code/kakao
```

## 현재 설정 확인

아래 API에서 세 제공자가 모두 반환되면 애플리케이션의 OAuth 진입 경로는 활성화된 상태입니다.

```text
http://localhost:8080/api/auth/social-providers
```

외부 provider 화면에서 `invalid client_id`, `잘못된 client_id`, `unauthorized_client` 오류가 보이면 코드 문제가 아니라 환경 변수에 실제 발급 키가 들어가지 않은 상태입니다.

기본로그인 -    customer / customer123
관리자로그인 -  admin / admin1234

## 포트폴리오 설명 포인트

- 고객은 소셜 계정으로 빠르게 예약할 수 있습니다.
- 관리자는 별도 관리자 계정으로 운영 기능에 접근합니다.
- 고객/관리자 권한은 세션에 저장된 `SessionUser.role` 기준으로 분리됩니다.
- 노쇼 이력, 휴무일, 중복 예약 검증은 로그인 방식과 무관하게 동일하게 적용됩니다.
