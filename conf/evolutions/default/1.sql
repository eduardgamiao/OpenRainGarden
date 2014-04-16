# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table has_plant (
  id                        bigint auto_increment not null,
  constraint pk_has_plant primary key (id))
;

create table permeable_pavers (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  property_type             varchar(255),
  address                   varchar(255),
  hide_address              varchar(255),
  description               longtext,
  date_installed            varchar(255),
  material                  varchar(255),
  previous_material         varchar(255),
  size                      varchar(255),
  installer                 varchar(255),
  comment_key               varchar(255),
  image                     varbinary(255),
  owner_id                  bigint,
  constraint pk_permeable_pavers primary key (id))
;

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

create table rain_barrel (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  property_type             varchar(255),
  address                   varchar(255),
  hide_address              varchar(255),
  description               longtext,
  date_installed            varchar(255),
  rain_barrel_type          varchar(255),
  capacity                  varchar(255),
  color                     varchar(255),
  material                  varchar(255),
  estimated_cost            varchar(255),
  water_use                 varchar(255),
  overflow_frequency        varchar(255),
  cover                     varchar(255),
  obtained_from             varchar(255),
  installation_type         varchar(255),
  number_of_rain_barrels    varchar(255),
  comment_key               varchar(255),
  image                     longblob,
  owner_id                  bigint,
  constraint pk_rain_barrel primary key (id))
;

create table rain_garden (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  property_type             varchar(255),
  address                   varchar(255),
  hide_address              varchar(255),
  description               longtext,
  date_installed            varchar(255),
  rain_garden_size          varchar(255),
  water_flow_source_size    varchar(255),
  water_flow_description    longtext,
  infiltration_rate         varchar(255),
  comment_key               varchar(255),
  image                     longblob,
  owner_id                  bigint,
  constraint pk_rain_garden primary key (id))
;

create table user_info (
  id                        bigint auto_increment not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  telephone                 varchar(255),
  password                  varchar(255),
  admin                     tinyint(1) default 0,
  constraint pk_user_info primary key (id))
;


create table rain_garden_plant (
  rain_garden_id                 bigint not null,
  plant_id                       bigint not null,
  constraint pk_rain_garden_plant primary key (rain_garden_id, plant_id))
;
alter table permeable_pavers add constraint fk_permeable_pavers_owner_1 foreign key (owner_id) references user_info (id) on delete restrict on update restrict;
create index ix_permeable_pavers_owner_1 on permeable_pavers (owner_id);
alter table rain_barrel add constraint fk_rain_barrel_owner_2 foreign key (owner_id) references user_info (id) on delete restrict on update restrict;
create index ix_rain_barrel_owner_2 on rain_barrel (owner_id);
alter table rain_garden add constraint fk_rain_garden_owner_3 foreign key (owner_id) references user_info (id) on delete restrict on update restrict;
create index ix_rain_garden_owner_3 on rain_garden (owner_id);



alter table rain_garden_plant add constraint fk_rain_garden_plant_rain_garden_01 foreign key (rain_garden_id) references rain_garden (id) on delete restrict on update restrict;

alter table rain_garden_plant add constraint fk_rain_garden_plant_plant_02 foreign key (plant_id) references plant (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table has_plant;

drop table permeable_pavers;

drop table plant;

drop table rain_garden_plant;

drop table rain_barrel;

drop table rain_garden;

drop table user_info;

SET FOREIGN_KEY_CHECKS=1;

