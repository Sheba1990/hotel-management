create sequence hibernate_sequence start 5 increment 1;

create table address_table (
  id bigserial not null,
  postal_code varchar(255),
  country varchar(255),
  province varchar(255),
  city varchar(255),
  street varchar(255),
  home_number varchar(255),
  apartment_number varchar(255),
  primary key (id)
);

create table contact_data_table (
  id bigserial not null,
  phone_number varchar(255),
  address_id int8,
  primary key (id)
);

create table passport_data_table (
  id bigserial not null,
  passport_number varchar(255),
  date_of_issue varchar(255),
  date_of_expiry varchar(255),
  country_of_issue varchar(255),
  primary key (id)
);

create table user_details_table (
  id bigserial not null,
  first_name varchar(255),
  last_name varchar(255),
  middle_name varchar(255),
  birth_date date,
  gender varchar(255),
  passport_data_id int8,
  contact_data_id int8,
  primary key (id)
);

create table user_table (
  id bigserial not null,
  username varchar(255),
  email varchar(255),
  password varchar(255),
  active boolean,
  user_details_id int8,
  primary key (id)
);

create table user_role_table (
  user_id int8 not null,
  roles varchar(255)
);

create table room_details_table (
  id bigserial not null,
  price_per_night float8,
  floor int4,
  amount_of_rooms int4,
  capacity int4,
  see_view boolean,
  bath boolean,
  babybed boolean,
  wifi boolean,
  breakfast boolean,
  primary key (id)
);

create table room_category_table (
  id bigserial not null,
  category_name varchar(255),
  description varchar(2048),
  primary key (id)
);

create table room_table (
  id bigserial not null,
  room_number int4,
  room_status varchar(255),
  room_category_id int8,
  room_details_id int8,
  primary key (id)
);

create table order_table (
  id bigserial not null,
  order_number int4,
  room_category varchar(255),
  amount_of_guests int,
  date_of_check_in date,
  date_of_check_out date,
  approved boolean,
  user_id int8,
  room_id int8,
  primary key (id)
);

alter table if exists contact_data_table
  add constraint contact_data_addres_fk
  foreign key (address_id) references address_table (id);

alter table user_details_table
  add constraint user_details_passport_fk
  foreign key (passport_data_id) references passport_data_table (id);

alter table user_details_table
  add constraint user_details_contact_data_fk
  foreign key (contact_data_id) references contact_data_table (id);

alter table user_table
  add constraint user_user_details_fk
  foreign key (user_details_id) references user_details_table (id);

alter table room_table
  add constraint room_room_category_fk
  foreign key (room_category_id) references room_category_table (id);

alter table room_table
  add constraint room_room_details_fk
  foreign key (room_details_id) references room_details_table (id);

alter table order_table
  add constraint order_user_fk
  foreign key (user_id) references user_table (id);

alter table order_table
  add constraint order_room_fk
  foreign key (room_id) references room_table (id);

alter table user_role_table
  add constraint user_role_user_fk
  foreign key (user_id) references user_table;