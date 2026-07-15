######  복습-- 문제 확인 및 깃허브




-- 복습문제1) 
-- 1. 다음과 같이 테이블을 작성하는 코드를 적으시오.
-- -- mysql> desc userinfo;
create table userinfo ( 
	no int  not null auto_increment primary key,
    name varchar(100) not null,
    age int not null
);

-- -- +-------+--------------+------+-----+---------+----------------+
-- -- | Field | Type         | Null | Key | Default | Extra          |
-- -- +-------+--------------+------+-----+---------+----------------+
-- -- | no    | int          | NO   | PRI | NULL    | auto_increment |
-- -- | name  | varchar(100) | NO   |     | NULL    |                |
-- -- | age   | int          | NO   |     | NULL    |                |
-- -- +-------+--------------+------+-----+---------+----------------+
-- -- 3 rows in set (0.00 sec)

-- 2. 마지막에 5, five, 50을  insert하는 코드를 작성하시오. ( insert )
insert into userinfo value ( 5, 'five', 50 );
insert into userinfo ( name, age ) value ( 'five' , 50 );
insert into userinfo (no, name, age ) value ( 5, 'five' 50);
-- 3. no가 5인 데이터의 5, five, 55로  수정하는 코드를 작성하시오. ( update)
update userinfo
set age = 55;  
-- 4. no가 5인 데이터를 삭제하는 코드를 작성하시오.  ( delete)
delete from userinfo where no = 5;
-- 5. 다음과 같이 나이를 오름차순으로 정렬하는 코드를 작성하시오. ( select )
select *
from userinfo
order by age asc;

-- -- mysql> select * from userinfo_re1;
-- -- +----+--------+-----+
-- -- | no | name   | age |
-- -- +----+--------+-----+
-- -- |  1 | first  |  11 |
-- -- |  2 | second |  22 |
-- -- |  3 | third  |  33 |
-- -- |  4 | fourth |  44 |
-- -- +----+--------+-----+

-- 복습문제2)   
-- 1.  jdbc 연동을 하려고한다.   드라이버로딩시 사용되는 코드는?
Class.forName ( "com.mysql.cj.jdbc.Driver ");
-- 2.  DriverManager를 이용해서 url, root, pass를 이용해서 Connection을 만들려고할때 사용되는 코드는?
Connection conn = 
DriverManager.getConnection ( "jdbc:mysql://localhost:3306/mbasic" , "root", "1234");
-- 3.  PreparedStatement를 이용해서 sql을 실행하려고할때  insert, update, delete 에서 사용되는 코드는?
conn.preparedStatement("insert into userinfo values(?, ?, ?)")
pstmt.executeupdate();
-- 4.  PreparedStatement를 이용해서 sql을 실행하려고할때  select   에서 사용되는 코드는?
pstmt.executeQuery();
-- 5.  jdbc의 주의사항은? 항상  (  close   )를 해야한다.
