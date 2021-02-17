--============================================================================
--1.관리자 계정으로 먼저실행  관리자 계정으로 입력해야할부분 
--============================================================================
--2.student 계정 생성 및 궈난 부여

create user student 
identified by student
default tablespace users;

grant connect, resource to student;


--==============================================================
--STUDENT 계정으로 바꾸고 작성을이어간다.
--=============================================================
CREATE TABLE MEMBER(
    member_id varchar2(20),
    password varchar2(20) not null,
    member_name varchar2(100) not null,
    gender char(1),
    age number,
    email varchar2(200),
    phone char(11) not null,
    address varchar2(1000),
    hobby varchar2(100), --농구 , 음악감상, 영화 <=이런식으로작성할것이다.,ㅁㅁ,ㅁㅁ,
    enroll_date date default sysdate,
    constraint pk_member_id primary key(member_id),
    CONSTRAINT ck_member_gender check(gender in ('M','F'))
    
);


INSERT Into MEMBER
VALUES('honggd','1234','홍길동','M','33','honggd@naver.com','01012344567',
        '서울시 강남구 테헤란로' , '등산, 그림,요리' ,default
        );
        INSERT Into MEMBER
VALUES('sissa','1234','신사입당','F','150','SISSA@naver.com','01012344567',
        '서울시 고궁' , '한자,서예' ,default
        );
INSERT Into MEMBER
VALUES('SEJONG','1234','세종대왕','M','160','SEJJONG@naver.com','01012344567',
        '서울시 집현전' , '공부하기,일하기,신하부려먹기' ,default
        );
INSERT Into MEMBER
VALUES('징구','1234','김진구','M','33','JONGU@naver.com','01012344567',
        '일본 ' , '도라에몽괴롭히기,개짓거리하기' ,default
        );

