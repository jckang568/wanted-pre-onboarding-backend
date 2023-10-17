drop schema if exists wanted;
create schema wanted collate = utf8_general_ci;
use wanted;

/* ***************************************************************
***************************creating tables************************
**************************************************************** */

-- 채용공고 테이블
create table job_postings
(
    job_posting_id  int(10) unsigned not null auto_increment comment '채용공고id',
    company_id      int(10) unsigned comment '회사id',
    position        varchar(255) comment '채용포지션',
    reward_amount   int(10) comment '채용보상금',
    job_description text comment '채용내용',
    required_skills text comment '사용기술',
    created_at      timestamp comment '생성일시',
    updated_at      timestamp comment '수정일시',
    primary key (job_posting_id)
) comment '채용공고';

-- 사용자 지원 테이블
create table user_applications
(
    application_id int(10) unsigned not null auto_increment comment '지원자id',
    job_posting_id int(10) unsigned not null comment '채용공고id',
    user_id        int(10) unsigned not null comment '사용자id',
    created_at     timestamp comment '생성일시',
    updated_at     timestamp comment '수정일시',
    primary key (application_id)
) comment '사용자 지원';

-- 회사 테이블
create table companies
(
    company_id          int(10) unsigned not null auto_increment comment '회사id',
    company_name        varchar(255)     not null comment '회사명',
    company_description text comment '회사설명',
    location_id         int(10) unsigned comment '위치id',
    created_at          timestamp comment '생성일시',
    updated_at          timestamp comment '수정일시',
    primary key (company_id)
) comment '회사';

-- 국가 테이블
create table countries
(
    country_id   char(2)     not null comment '국가id',
    country_name varchar(50) not null comment '국가이름',
    created_at   timestamp comment '생성일시',
    updated_at   timestamp comment '수정일시',
    primary key (country_id)
) comment '국가';

-- 지역 테이블
create table locations
(
    location_id    int(10) unsigned not null auto_increment comment '위치id',
    street_address varchar(255)     not null comment '주소',
    postal_code    varchar(12) comment '우편번호',
    city           varchar(30) comment '도시명',
    state_province varchar(30) comment '주/도(행정구역)',
    country_id     char(2)          not null comment '국가id',
    created_at     timestamp comment '생성일시',
    updated_at     timestamp comment '수정일시',
    primary key (location_id)
) comment '위치';

-- 사용자 테이블
create table users
(
    user_id    int(10) unsigned not null auto_increment comment '사용자id',
    username   varchar(255) comment '이름',
    email      varchar(255) comment '이메일',
    password   varchar(255) comment '비밀번호',
    created_at timestamp comment '생성일시',
    updated_at timestamp comment '수정일시',
    primary key (user_id)
) comment '사용자';


-- 외래 키 제약 조건 추가
alter table job_postings
    add constraint fk_company_id foreign key (company_id) references companies (company_id);

alter table user_applications
    add constraint fk_job_posting_id foreign key (job_posting_id) references job_postings (job_posting_id);

alter table user_applications
    add constraint fk_user_id foreign key (user_id) references users (user_id);

alter table companies
    add constraint fk_location_id foreign key (location_id) references locations (location_id);

alter table locations
    add constraint fk_country_id foreign key (country_id) references countries (country_id);