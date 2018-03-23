# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  email                         varchar(255) not null,
  name                          varchar(255),
  username                      varchar(255),
  password                      varchar(255),
  role                          varchar(255),
  address1                      varchar(255),
  address2                      varchar(255),
  city                          varchar(255),
  constraint pk_user primary key (email)
);


# --- !Downs

drop table if exists user;

