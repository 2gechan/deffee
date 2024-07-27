# Footsal Reservation System Project API Document

### 회원가입
```
path : '/api/member/join'

초기 회원가입 시 필수 입력 값 : id, password, name, phone
회원가입 완료 후 응답 값은 JSON 형식으로 "RESULT" : 가입ID
(ui 구성 시 일반 사용자, 구장 운영자 두 개 방향으로 회원 등록, 구장 운영자로 가입 시 구장 등록 가능하도록 기능 구현 필요)
```