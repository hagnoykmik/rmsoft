# RM 소프트

<aside>
	
💡 주제 : 도서관리 시스템

산출물 : ERD, 테이블 정의서, 시스템 구성도, 기능정의서, github( 본인의 개발위치 ), AWS 구성 내역 사진 ( Free tier를 이용 ), AWS 내부 구축 내용

산출물 위치 : 프로젝트 내 outputs의 폴더를 생성하여 사진 및 파일 업로드

구현 내용 : Rest API 구조 형태에 Java, Spring Boot 를 이용하여 백엔드 API 서버 구성 및 AWS CI/CD 시스템 구축

</aside>

## **테이블 정의서**

- 회원 테이블
- 도서 테이블
- 대출 테이블 (회원_도서_중개 테이블)

**1. 사용자 테이블 (User)**

| PK | FK | 컬럼명 | 데이터 타입 | 설명 | 비고 | null 여부 |
| --- | --- | --- | --- | --- | --- | --- |
| ✅ |  | user_id | bigint | 사용자의 고유 식별자 | auto_increment | NOT NULL |
|  |  | name | varchar(255) | 사용자의 이름 |  | NOT NULL |
|  |  | password | varchar(255) | 사용자의 비밀번호 |  | NOT NULL |
|  |  | email | varchar(255) | 사용자의 이메일 주소 |  | NOT NULL |

**2. 도서 테이블 (Book)**

| PK | FK | 컬럼명 | 데이터 타입 | 설명 | 비고 | null 여부 |
| --- | --- | --- | --- | --- | --- | --- |
| ✅ |  | book_id  | bigint | 도서의 고유 식별자 | auto_increment | NOT NULL |
|  |  | title | varchar(255) | 도서의 제목 |  | NOT NULL |
|  |  | is_borrow | tinyint(1) | 도서의 대출 여부(대출 중인지 아닌지를 나타내는 상태) | 1: 대출 중 0: 대출가능 | NOT NULL |

**3. 대출 테이블 (Loan)**

| PK | FK | 컬럼명 | 데이터 타입 | 설명 | 비고 | null 여부 |
| --- | --- | --- | --- | --- | --- | --- |
| ✅ |  | loan_id  | bigint | 대출 이력의 고유 식별자 | auto_increment | NOT NULL |
|  | ✅ | user_id  | bigint | 사용자 테이블의 user_id와 연결 (어떤 사용자가 대출했는지를 나타냄) | auto_increment | NOT NULL |
|  | ✅ | book_id  | bigint | 도서 테이블의 book_id와 연결 (어떤 도서가 대출되었는지를 나타냄) | auto_increment | NOT NULL |
|  |  | loan_date | timestamp | 대출이 발생한 날짜 |  | NOT NULL |
|  |  | return_date | timestamp | 대출한 도서를 반납한 날짜 | 반납 전에는 null | NULL |

## ERD

- `회원 테이블`과 `대출 테이블` : 일대다 관계
- `도서 테이블`과 `대출 테이블` : 일대다 관계


![rmsoft (1)](https://github.com/hagnoykmik/rmsoft/assets/109258144/ec815ba9-95b9-4f4e-a10e-995cb232b586)


## 기능 정의서

<img width="638" alt="스크린샷 2023-12-12 011950" src="https://github.com/hagnoykmik/rmsoft/assets/109258144/a518f796-8435-46ca-83de-258d52246860">

## REST API 문서

<img width="629" alt="스크린샷 2023-12-16 165828" src="https://github.com/hagnoykmik/rmsoft/assets/109258144/fc2385e6-a59e-4766-9272-4339ec4d848c">

### 회원가입

**request**

```json
{
    "name": "김경아",
    "email": "rlaryddk1221@gmail.com",
    "password": "1234"
}
```

**response**

```json
성공
status: 201

{
    "userId": 1,
    "name": "김경아",
    "email": "rlaryddk1221@gmail.com",
    "password": "1234"
}
```
```json
실패
status: 409

{
    "timestamp": "2024-01-07T19:26:42.303",
    "status": "CONFLICT",
    "message": "이미 가입된 유저입니다."
}
```

### 도서생성

**request**

```json
{
    "title": "세이노의 가르침"
}
```

**response**

```json
성공
status: 201
{
    "book_id": 1,
    "title": "세이노의 가르침",
    "is_borrow": 0
}
```

### 도서 수정

request

```json
{
    "bookId": 1,
    "title": "더 마인드", 
    "isBorrow": 0
}
```

response

```json
성공
status: 200

{
    "bookId": 1,
    "title": "더 마인드", 
    "isBorrow": 0
}
```
```json
실패
status: 404

{
    "timestamp": "2023-12-19T00:33:40.066",
    "status": "NOT_FOUND",
    "message": "존재하지 않는 도서입니다."
}
```

### 대출 이력 조회

**response**

```json
성공
status: 200

{
    {
         "loanId": 7,
         "loanDate": "2023-12-11T21:45:34.522",
         "returnDate": null,
         "userId": 1,
	 "bookId": 1
    },
    {
    	"loanId": 2,
    	"loanDate": "2023-12-10T00:00:00",
    	"returnDate": "2023-12-11T22:32:27.659",
    	"userId": 2,
    	"bookId": 1
    }
}
```

### 대출 처리

**request**

```json
{
    "userId": 1,
    "bookId": 1
}
```

**response**

```json
성공
status: 201
{
    "loanId": 7,
    "loanDate": "2023-12-11T21:45:34.522",
    "returnDate": null,
    "userId": 1,
    "bookId": 1
}
```
```json
실패
status: 409
{
    "timestamp": "2023-12-19T00:40:31.328",
    "status": "CONFLICT",
    "message": "이미 대출중인 도서입니다."
}
```


### 반납 처리

**request**

```json
{
    "userId": 1,
    "bookId": 1
}
```

**response**

```json
성공
status: 200
{
    "loanId": 2,
    "loanDate": "2023-12-10T00:00:00",
    "returnDate": "2023-12-11T22:32:27.659", // 반납 일자 생성 
    "userId": 1,
    "bookId": 1
}
```
```json
실패
status: 409

{
    "timestamp": "2023-12-19T18:20:15.168",
    "status": "CONFLICT",
    "message": "이미 반납처리된 도서입니다."
}
```

## 시스템 구성도

| 인프라 | EC2, Nginx, Docker |
| --- | --- |
| 데이터베이스 | MySQL |
| 백엔드 | Spring Boot, JPA |
| 프론트엔드 | React, Node.js, Recoil |

### 시스템 아키텍처

![rm소프트 drawio](https://github.com/hagnoykmik/rmsoft/assets/109258144/dcf7596f-270c-4913-90d7-95bb91732e3e)

## AWS 구성 내역 사진

<img width="1101" alt="제목 없음" src="https://github.com/hagnoykmik/rmsoft/assets/109258144/27ea0553-c475-463c-a359-97bcfa92b6c7">

