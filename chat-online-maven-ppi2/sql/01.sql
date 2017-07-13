--Início do banco, criação das tabelas.
drop table users;
drop table msg;
drop view chat_log;

create table users (
	id serial primary key,
	login varchar(20) not null,
	pwd varchar(20) not null,
	email varchar(30) not null
);

create table msg (
	id serial primary key,
	uid int not null references users (id),
	data timestamp,
	msg text
	
);

create or replace view chat_log as (
 select msg.id, users.id as userid, login, data, msg
 from users join msg on msg.id = uid)
