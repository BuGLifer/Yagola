# Package Structure

### Author
- Gillog
- Sora
- Jeje
- BJ

### Updated
- 2021.12.05

### Description
- Java Package 관련 구조 설계
---


### com.buglifer.yagola

- common : Project 공통 패키지
  - config : 환경설정 관련
  - interceptor : Interceptor 관련
  - resolver : Resolver 관련
  - dto : 공통 DTO 관련
  - domain : JPA Entity Class 관련
  - exception : 예외 상황 관련
  - enum : 공통 enum 관련
  - ...
  
- resource명 : API Resource 명
  - controller : controller class
  - service : service class
  - repository : repository interface
  - dto : 해당 Resource 관련 DTO
  - search : 해당 Resource 관련 검색, Specification 관련