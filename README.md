# 소셜 로그인 예제
> 카카오, 네이버, 애플 로그인을 이용한 예제입니다. 

✔️ 테스트 환경
* Spring Boot 2.7.8 
* JDK 1.8 
* Gradle

✔️ 프로젝트 구조
- - -
```
.
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── app/
        │           └── sociallogin/
        │               ├── apple/
        │               │   ├── controller
        │               │   ├── dto
        │               │   └── service
        │               ├── common
        │               └── config
        └── resources/
            ├── key
            ├── templates
            └── application.properteis
```
* src/main/java/com/app/sociallogin/apple : 애플 로그인 관련 클래스 관리 
* src/main/java/com/app/sociallogin/common : 공통 클래스 관리 
* src/main/java/com/app/sociallogin/config : Config 클래스 관리
* src/main/resources/key : 애플 로그인 키 관리
* src/main/resources/templates : Thymeleaf 파일 관리