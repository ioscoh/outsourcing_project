# ✒️Outsourcing Project

***
아웃소싱 프로젝트

# 📌프로젝트 소개

***
기업용 태스크 관리 시스템 (TaskFlow)의 백엔드 개발

### 💡핵심 기능

***
1. 회원가입 / 로그인 : 회원 CRUD, 로그인, 회원탈퇴
2. 태스크 관리 : 태스크 CRUD(Soft Delete 처리), 상태 변경
3. 댓글 관리 : 댓글 CRUD(검색 기능)
4. 대시보드 : 통계 정보 제공, 태스크 요약
5. 활동로그 : 활동 기록, 저장, 조회

### 🔧사용하는 기술
`Java` `Spring Boot` `Github` `MySQL` `JPA` `JWT`

# 📃설계

***
와이어프레임

![Image](https://github.com/user-attachments/assets/5c593282-83d7-481c-9db1-b5c18bad4dac)
![Image](https://github.com/user-attachments/assets/f85137b9-9cfd-49ae-81be-83559597dc71)

ERD

![Image](https://github.com/user-attachments/assets/615573bf-5cfc-4826-9e73-03c85349be78)

API


### 📁 Project Tree
```
src/
└── main/
    └── java/
        └── com/example/outsourcing_project/
            ├── task/
            │   ├── controller/
            │   │   └── TaskController.java
            │   ├── dto/
            │   │   ├── TaskReqDto.java
            │   │   └── TaskResDto.java
            │   ├── domain/
		        │   │   ├── entity/
            │   │   └── Task.java
            │   ├── repository/
            │   │   └── TaskRepository.java
            │   └── service/
            │       └── TaskService.java
            │
            ├── comment/
            │   ├── controller/
            │   ├── dto/
            │   ├── domain/
            │   ├── repository/
            │   └── service/
            │
            ├── dashboard/
            │   ├── controller/
            │   └── service/
            │
            ├── user/
            │   ├── controller/
            │   ├── dto/
            │   ├── domain/
            │   ├── repository/
            │   └── service/
            │
            ├── global/
            │   ├── config/          
            │   ├── exception/       
            │   ├── filter/          
            │   ├── jwt/             
            │   ├── logging/        
            │   └── util/            
            │
            └── OutsourcingProjectApplication.java
```
## 환경변수 설정
1. 환경변수 설정하기
   ![Image](https://github.com/user-attachments/assets/27627c32-160d-4514-a4d9-b455e8dd9d78)

2. 옵션 추가하기
   `Build and run` 항목의 `modify options` 선택

아래 내용 체크 표시
````
Environment variables
````

환경변수
Environment variables 의 파일모양 📄 선택
+ 눌러서 아래 내용 추가
````
MYSQL_USERNAME = 유저이름
MYSQL_PASSWORD = 비밀번호
MYSQL_PORT = 포트번호
MYSQL_DBNAME = DB이름
SERVER_PORT = 포트번호
JWT_SECRET_KEY = SecretKey3225345@DVA@34232asafdsv!
````

