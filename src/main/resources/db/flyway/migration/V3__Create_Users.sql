create table user_roles (user_id uuid not null, roles int4);
create table users (id uuid not null, password varchar(255), username varchar(255), primary key (id));
alter table if exists users drop constraint if exists UK_r43af9ap4edm43mmtq01oddj6;

alter table if exists users add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);
alter table if exists user_roles add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users;