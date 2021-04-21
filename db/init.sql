drop database if exists servlet_blog;

create database servlet_blog character set utf8mb4;
use servlet_blog;
create table user(
      id int primary key auto_increment,
      username varchar(20)  not null unique ,
      password varchar(20) not null ,
      nickname varchar(20) ,
      sex bit(2),
      birthday date,
      head varchar(50) comment '头像'



);


create table article(
   id int primary key auto_increment,
   title varchar(20) not null,
   content mediumtext not null comment '内容',
   create_time timestamp default now() '创建日期'
   view_count int default 0 comment '浏览量',
   user_id int '访问过的用户' ,
   foreign key(user_id) references user(id)

);

/**
表的设计注意，主键，外键，唯一值，默认值，自增，不为空，属性的类型
 */


insert into user(username,password) values ('妙妙','520');
insert into user(username,password) values ('珍妮','1314');
insert into user(username,password) values ('露丝','2020');

insert into article( title, content, user_id) values('冒泡排序','public void bubblesort',1);
insert into article( title, content, user_id) values('希尔排序','public void shellsort',2);
insert into article( title, content, user_id) values('堆排序','public void heapsort',1);
insert into article( title, content, user_id) values('选择排序','public void selectsort',2);
insert into article( title, content, user_id) values('快速排序','public void quicksort',1);
insert into article( title, content, user_id) values('归并排序','public void mergesort',1);
--主外键关联的表，默认创建的主键约束是restrict严格模式
--比如从表有数据关联到主表某一行数据x，则x不能删除
--真实的设计上是不删除，在每一张表上设计一个字段，表示是否有效


select id,username,password,nickname,sex,birthday,head from user where username = 'a';
select id,title from article where user_id=1;