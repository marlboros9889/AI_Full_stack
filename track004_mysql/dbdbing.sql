create database dbdbing;
show databases;
use dbdbing;
create table mvcdoard1;
show tables;
CREATE TABLE mvcboard1 (
    bno       INT AUTO_INCREMENT PRIMARY KEY, 
    bname      VARCHAR(200) NOT NULL,          
    bpass  VARCHAR(50) NOT NULL,         
    btitle     VARCHAR(1000) NOT NULL,       
    bcontent   TEXT NOT NULL,                
    bdate  DATETIME DEFAULT NOW(),        
    bhit INT DEFAULT 0,                
    bip  VARCHAR(50) NOT NULL
);
desc mvcboard1;
drop table mvcboard1;