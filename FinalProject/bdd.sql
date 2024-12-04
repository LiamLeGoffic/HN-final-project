drop database if exists final_project_db;
create database if not exists final_project_db;
#drop database final_project_db

use final_project_db;
   
create table user_type(
   id bigint not null auto_increment,
   label varchar(255),
   primary key (id)
   );

create table user(
   id bigint not null auto_increment,
   last_name varchar(255),
   first_name varchar(255),
   email varchar(255),
   user_type_id bigint,
   primary key (id),
   foreign key (user_type_id) references user_type(id) on delete set null
   );
   
#drop table user_type
#drop table user;