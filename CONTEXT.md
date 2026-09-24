# Domain Glossary: Leets Backend Blog (Week 1)

## Architecture & Response Models

### `BaseResponse`
모든 API 응답의 기반(부모)이 되는 공통 DTO.
- **역할**: 응답의 성공 여부(`success`)와 사용자 친화적인 메시지(`message`)를 공통으로 캡슐화.
- **설계**: 상속(Inheritance) 구조를 채택하여 자식 DTO의 필드들과 함께 최상위(Flat) JSON 프로퍼티로 직렬화됨.

### `RepeatStringRequest`
문자열 반복 API 호출 시 클라이언트가 전달하는 입력 DTO.
- **역할**: 입력 문자열(`value`)을 수신하며, 빈 값이나 공백 입력을 방지하기 위한 유효성 검증(`@NotBlank`)을 포함.

### `RepeatStringResponse`
문자열 반복 API의 정상 처리 결과를 반환하는 응답 DTO.
- **상속**: `BaseResponse` 상속
- **필드**: `string_one`, `string_two`
- **응답 형태**:
***
{
  "success": true,
  "message": "요청이 성공적으로 처리되었습니다.",
  "string_one": "hello",
  "string_two": "hello"
}
***

### `ErrorResponse`
유효성 검증 실패 또는 비즈니스 예외 발생 시 반환되는 에러 DTO.
- **상속**: `BaseResponse` 상속
- **필드**: `error_code` (`INVALID_INPUT`, `MALFORMED_JSON` 등)
- **응답 형태**:
***
{
  "success": false,
  "message": "값은 비어있을 수 없습니다.",
  "error_code": "INVALID_INPUT"
}
***

## Infrastructure & Controllers

### `GlobalExceptionHandler`
`@RestControllerAdvice` 기반의 전역 예외 처리기.
- **역할**: `@Valid` 유효성 검증 실패(`MethodArgumentNotValidException`), JSON 파싱 에러(`HttpMessageNotReadableException`) 등을 포착하여 통일된 `ErrorResponse` 및 적절한 HTTP 상태 코드(400 Bad Request 등)로 변환.

### `AssignmentController`
프레젠테이션 계층의 REST 컨트롤러.
- **엔드포인트**:
  - `GET /health` 및 `GET /api/v1/health`
  - `POST /string/repeat` 및 `POST /api/v1/string/repeat`

### `AssignmentService`
비즈니스 로직 계층의 서비스.
- **역할**: 헬스체크 상태 생성, 입력 문자열 검증 및 복제 가공 비즈니스 로직 전담.
