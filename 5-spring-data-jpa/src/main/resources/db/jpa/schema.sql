drop table if exists CUSTOMER_TBL;
drop table if exists STUDENT_TBL;
drop table if exists DEPARTMENT_TBL;
drop table if exists COURSE_TBL;
drop table if exists ENROLLMENT_TBL;

create table CUSTOMER_TBL(
	ID integer auto_increment primary key,
	NAME varchar(100) not null,
	BIRTHDAY timestamp not null
);

create table STUDENT_TBL(
	STUDENT_ID integer auto_increment primary key,
	STUDENT_fulltime tinyint(1) not null,
	STUDENT_BIRTHDAY timestamp not null
);
