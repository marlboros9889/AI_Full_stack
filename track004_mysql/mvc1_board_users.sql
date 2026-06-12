-- mysql>
-- mysql> desc users;
-- +----------+--------------+------+-----+-------------------+-------------------+
-- | Field    | Type         | Null | Key | Default           | Extra             |
-- +----------+--------------+------+-----+-------------------+-------------------+
-- | uno      | int          | NO   | PRI | NULL              | auto_increment    |
-- | nickname | varchar(20)  | NO   |     | NULL              |                   |
-- | bpass    | varchar(50)  | NO   |     | NULL              |                   |
-- | email    | varchar(100) | NO   |     | NULL              |                   |
-- | mobile   | varchar(50)  | NO   |     | NULL              |                   |
-- | udate    | timestamp    | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
-- | bip      | varchar(50)  | NO   |     | NULL              |                   |
-- +----------+--------------+------+-----+-------------------+-------------------+
-- 7 rows in set (0.00 sec)

-- mysql>
-- mysql>

use mbasic;

show databases;
show tables;
select * from users;
drop table users;

create table users(
	uno int not null auto_increment   primary key,
    nickname varchar(20) not null,
    bpass varchar(50) not null,
    email varchar(100) not null,
    mobile varchar(50) not null,
    udate timestamp not null default current_timestamp,
    bip varchar(50) not null
);

DESCRIBE users;

insert into users (nickname, bpass, email, mobile, bip )
values ('aaa','1111','aaa@gmail.com','010-1111-1111', '127.0.0.1' );

insert into users (nickname, bpass, email, mobile, bip )



values ('bbb','2222','bbb@gmail.com','010-2222-2222', '127.0.0.1' ); 

-- select( login,,,,)
select count(*) from users where bpass='1111' and email='aaa@gmail.com';
select       *  from users where bpass='2222' and email='bbb@gamil.com';

select * from users;
    
ALTER TABLE board ADD bfile VARCHAR(500) DEFAULT 'the703.png';

SHOW TABLES;
use mvcboard2;

USE mvcboard2;
SHOW DATABASES;
use mbasic;
use mvcboard1;


desc mvcboard2;
ALTER TABLE mvcboard2 ADD bfile VARCHAR(500) DEFAULT 'the703.png';


SHOW TABLES;
SELECT COUNT(*) FROM mvcboard2;

DESC mvcboard1;
DESC mvcboard2;


insert into mvcboard (bname, bpass, btitle, bcontent, bip, bfile)
select bname, bpass, btitle, bcontent, bip, bfile from mvcboard2;


-- SELECT를 여러 번 UNION으로 묶어서 한방에 삽입
INSERT INTO mvcboard (bname, bpass, btitle, bcontent, bip, bfile)
SELECT '홍길동', '1234', CONCAT('제목 ', seq), CONCAT('내용 ', seq), '127.0.0.1', ''
FROM (
    SELECT 1 seq UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
    UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10
) t;
ALTER TABLE mvcboard1 ADD COLUMN bfile varchar(500) DEFAULT 'the703.png';


DESC mvcboard1;
INSERT INTO mvcboard2 (bname, bpass, btitle, bcontent, bip, bfile)
SELECT bname, bpass, btitle, bcontent, bip, bfile FROM mvcboard1;

INSERT INTO mvcboard2 (bname, bpass, btitle, bcontent, bip, bfile)
SELECT bname, bpass, btitle, bcontent, bip, bfile FROM mvcboard1;

INSERT INTO mvcboard2 (bname, bpass, btitle, bcontent, bip, bfile)
SELECT bname, bpass, btitle, bcontent, bip, bfile FROM mvcboard2;




select * from mvcboard2 order by bno desc limit 0 ,10; 
select * from mvcboard2 order by bno desc limit 10 ,10;  -- 그다음 10개부터 , 10개
select * from mvcboard2 order by bno desc limit 20 ,10;


select count(*) from mvcboard2;

TRUNCATE TABLE mvcboard2;
