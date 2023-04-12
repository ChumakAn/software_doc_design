create table detail (id integer not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
create table device (id integer not null auto_increment, battery_level tinyint, name varchar(255), type varchar(255), detail_id integer, hub_id integer, protection_type_id integer, user_id integer, primary key (id)) engine=InnoDB;
create table hub (id integer not null auto_increment, event_log varchar(255), type varchar(255), user_id integer, primary key (id)) engine=InnoDB;
create table notification (id integer not null auto_increment, message varchar(255), type varchar(255), device_id integer, hub_id integer, primary key (id)) engine=InnoDB;
create table protection_type (id integer not null auto_increment, device_state varchar(255), installation_method varchar(255), level_threshold integer, movement_count integer, name varchar(255), type varchar(255), sound_level integer, water_level integer, primary key (id)) engine=InnoDB;
create table sound_alarm (id integer not null auto_increment, timestamp datetime(6), type varchar(255), hub_id integer, primary key (id)) engine=InnoDB;
create table user (id integer not null auto_increment, login varchar(255), name varchar(255), password varchar(255), phone_number varchar(255), surname varchar(255), detail_id integer, primary key (id)) engine=InnoDB;
alter table device add constraint FKbqplu6ig9t4jlvk6fvl9w6eqi foreign key (detail_id) references detail (id);
alter table device add constraint FKhgl6qoefb2jvmcqmtbjkie3wh foreign key (hub_id) references hub (id);
alter table device add constraint FK2o3ap6neit2fud4mycncm02kp foreign key (protection_type_id) references protection_type (id);
alter table device add constraint FKk92m2qj36vn62ctp5pgbt4982 foreign key (user_id) references user (id);
alter table hub add constraint FKba35b7bhggmmcofqge1gjq249 foreign key (user_id) references user (id);
alter table notification add constraint FKlqe4patiia00l9aa5hlhlubxk foreign key (device_id) references device (id);
alter table notification add constraint FK1n42idc8jejmtrxgnyhfkoeg3 foreign key (hub_id) references hub (id);
alter table sound_alarm add constraint FK3trchvnv2j9td1ya0u0vsceyc foreign key (hub_id) references hub (id);
alter table user add constraint FKrenkibn148c005t3c51cblidj foreign key (detail_id) references detail (id);
create table detail (id integer not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
create table device (id integer not null auto_increment, battery_level tinyint, name varchar(255), type varchar(255), detail_id integer, hub_id integer, protection_type_id integer, user_id integer, primary key (id)) engine=InnoDB;
create table hub (id integer not null auto_increment, event_log varchar(255), type varchar(255), user_id integer, primary key (id)) engine=InnoDB;
create table notification (id integer not null auto_increment, message varchar(255), type varchar(255), device_id integer, hub_id integer, primary key (id)) engine=InnoDB;
create table protection_type (id integer not null auto_increment, device_state varchar(255), installation_method varchar(255), level_threshold integer, movement_count integer, name varchar(255), type varchar(255), sound_level integer, water_level integer, primary key (id)) engine=InnoDB;
create table sound_alarm (id integer not null auto_increment, timestamp datetime(6), type varchar(255), hub_id integer, primary key (id)) engine=InnoDB;
create table user (id integer not null auto_increment, login varchar(255), name varchar(255), password varchar(255), phone_number varchar(255), surname varchar(255), detail_id integer, primary key (id)) engine=InnoDB;
alter table device add constraint FKbqplu6ig9t4jlvk6fvl9w6eqi foreign key (detail_id) references detail (id);
alter table device add constraint FKhgl6qoefb2jvmcqmtbjkie3wh foreign key (hub_id) references hub (id);
alter table device add constraint FK2o3ap6neit2fud4mycncm02kp foreign key (protection_type_id) references protection_type (id);
alter table device add constraint FKk92m2qj36vn62ctp5pgbt4982 foreign key (user_id) references user (id);
alter table hub add constraint FKba35b7bhggmmcofqge1gjq249 foreign key (user_id) references user (id);
alter table notification add constraint FKlqe4patiia00l9aa5hlhlubxk foreign key (device_id) references device (id) ON DELETE CASCADE;
alter table notification add constraint FK1n42idc8jejmtrxgnyhfkoeg3 foreign key (hub_id) references hub (id);
alter table sound_alarm add constraint FK3trchvnv2j9td1ya0u0vsceyc foreign key (hub_id) references hub (id);
alter table user add constraint FKrenkibn148c005t3c51cblidj foreign key (detail_id) references detail (id);
