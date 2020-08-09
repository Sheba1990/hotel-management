insert into address_table (id, postal_code, country, province, city, street, home_number, apartment_number)
values
(1, '94304','USA','CA','Palo Alto','Deep Creek Road','3500',null);

insert into contact_data_table (id, phone_number, address_id)
  values
  (1, '+1-555-0-3345-78-29', 1);

insert into passport_data_table(id, passport_number, date_of_issue, date_of_expiry, country_of_issue)
values
(1, 'KH 2467097', '', '', 'USA');

insert into user_details_table(id, first_name, last_name, middle_name, birth_date, age, gender, passport_id, contact_data_id)
values
(1, 'Elon', 'Musk', 'Reeve', '1971-06-28', 49, 'MALE', 1, 1);

insert into user_table (id, username, email, password, active, user_details_id)
  values
  (1,'elonmusk', 'Press@tesla.com', '123456', true, 1);

insert into user_role_table(user_id, roles)
values
(1,'ADMIN');

insert into room_details_table (id, price_per_night, floor, amount_of_rooms, capacity, description, see_view, bath, babybed, wifi, breakfast)
values
(1, 150.00, 3, 5, 5, '', true, true, true, true, true);

insert into room_category_table (id, category_name, description)
  values
  (1, 'LUXUARY', '');

insert into room_table (id, room_number, room_status, room_category_id, room_details_id)
  values
  (1, 3, 'OCCUPIED', 1, 1);

insert into order_table(id, order_number, room_category, amount_of_guests, date_of_check_in, date_of_check_out, staying_period, approved, user_id, room_id)
values
(1, 1, 'LUXUARY', 3, '2020-05-01', '2020-05-06', 5, true, 1, 1);