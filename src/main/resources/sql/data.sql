/* ***************************************************************
***************************INSERTING DATA*************************
**************************************************************** */

insert into countries (country_id, country_name, created_at, updated_at)
values ('KR', '대한민국', now(), now());

insert into countries (country_id, country_name, created_at, updated_at)
values ('JP', '일본', now(), now());

insert into countries (country_id, country_name, created_at, updated_at)
values ('CN', '중국', now(), now());

insert into countries (country_id, country_name, created_at, updated_at)
values ('US', '미국', now(), now());


insert into locations (street_address, postal_code, city, state_province, country_id, created_at, updated_at)
values ('송파구 올림픽로 300, 롯데월드타워 35층', '05551', '서울특별시', '', 'KR', now(), now());

insert into locations (street_address, postal_code, city, state_province, country_id, created_at, updated_at)
values ('강남구 테헤란로 142, 4층, 10층, 11층, 12층, 13층, 22층, 23층 (역삼동, 아크플레이스)', '06236', '서울특별시', '', 'KR', now(), now());

insert into locations (street_address, postal_code, city, state_province, country_id, created_at, updated_at)
values ('서초구 강남대로 327, 2층 프로그래머스(서초동, 대륭서초타워)', '06627', '서울특별시', '', 'KR', now(), now());


insert into companies (company_name, company_description, location_id, created_at, updated_at)
values ('원티드', '일하는 사람들의 모든 가능성', '1', now(), now());

insert into companies (company_name, company_description, location_id, created_at, updated_at)
values ('토스', '금융의 모든 것, 토스에서 쉽고 간편하게', '2', now(), now());

insert into companies (company_name, company_description, location_id, created_at, updated_at)
values ('프로그래머스', '코드 중심의 개발자 채용. 스택 기반의 포지션 매칭. 프로그래머스의 개발자 맞춤형 프로필을 등록하고, 나와 기술 궁합이 잘 맞는 기업들을 매칭 받으세요', '3', now(), now());


insert into users (username, email, password, created_at, updated_at)
values ('홍길동', 'kdhong@gmail.com', '12345678', now(), now());

insert into users (username, email, password, created_at, updated_at)
values ('김철수', 'cskim@icloud.com', '12345678', now(), now());

insert into users (username, email, password, created_at, updated_at)
values ('김영희', 'yhkim@naver.com', '12345678', now(), now());


insert into job_postings (company_id, position, reward_amount, job_description, required_skills, created_at, updated_at)
values ('1', '백엔드 주니어 개발자', 1500000, '원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..', 'Python, Express.js', now(), now());

insert into job_postings (company_id, position, reward_amount, job_description, required_skills, created_at, updated_at)
values ('2', '백엔드 시니어 개발자', 700000, '토스에서 백엔드 시니어 개발자를 채용합니다. 자격요건은..', 'Java, Spring', now(), now());

insert into job_postings (company_id, position, reward_amount, job_description, required_skills, created_at, updated_at)
values ('3', '백엔드 신입 개발자', 800000, '원티드랩에서 백엔드 신입 개발자를 채용합니다. 자격요건은..', 'Javascript, Node.js', now(), now());