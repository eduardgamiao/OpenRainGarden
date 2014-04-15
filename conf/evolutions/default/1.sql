# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table plant (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  scientific_name           varchar(255),
  placement                 varchar(255),
  growth                    varchar(255),
  climate_type              varchar(255),
  image                     longblob,
  constraint pk_plant primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table plant;

SET FOREIGN_KEY_CHECKS=1;

