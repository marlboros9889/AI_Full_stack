create database dbdbing;
show databases;
use dbdbing;
create table mvcdoard1;
show tables;
use mvcboard1;
select * from mvcboard1;
CREATE TABLE mbasic.mvcboard1 (
    bno       INT AUTO_INCREMENT PRIMARY KEY, 
    bname      VARCHAR(200) NOT NULL,          
    bpass  VARCHAR(50) NOT NULL,         
    btitle     VARCHAR(1000) NOT NULL,       
    bcontent   TEXT NOT NULL,                
    bdate  DATETIME DEFAULT NOW() NOT NULL,        
    bhit INT DEFAULT 0 NOT NULL,                
    bip  VARCHAR(50) NOT NULL
);
desc mvcboard1;
drop table mvcboard1;


select *
from mvcboard1
order by bdate desc;

select count(*)`cnt` from mvcboard1;

insert into mvcboard1 (bname, bpass, btitle, bcontent, bip) values (?,?,?,?);

select * from mbasic.mvcboard1;



