insert into address_table (id, postal_code, country, province, city, street, home_number, apartment_number)
values
  (1, '94304','USA','CA','Palo Alto','Deep Creek Road','3500', null),
  (2, '220010', 'Belarus', 'Minskaya oblast', 'Minsk', 'Sovetskaya street', '34', '52'),
  (3, '230003', 'Belarus', 'Grodnenskaya oblast', 'Grodno', 'Schorsa street', '54', '64');

insert into contact_data_table (id, phone_number, address_id)
  values
  (1, '+1-555-0-3345-78-29', 1),
  (2, '+375(29)897-11-22', 2),
  (3, '+375(29)527-43-32', 3);

insert into passport_data_table(id, passport_number, date_of_issue, date_of_expiry, country_of_issue)
values
  (1, '453716397', '2017-04-13', '2027-04-12', 'USA'),
  (2, '292-000', '2014-05-20', '2024-05-19', 'RUSSIA');

insert into user_details_table(id, first_name, last_name, middle_name, birth_date, gender, passport_data_id, contact_data_id)
values
  (1, 'Elon', 'Musk', 'Reeve', '1971-06-28', 'MALE', 1, 1),
  (2, 'Иван', 'Иванов', 'Иванович', '1992-08-09', 'MALE', 2, 2),
  (3, 'Никита', 'Шебин', 'Андреевич', '1990-11-02', 'MALE', null, 3);

insert into user_table (id, username, email, password, active, user_details_id)
  values
  (1, 'elonmusk', 'Press@tesla.com', '123456', true, 1),
  (2, 'ivanovivan', 'ivanovivan@gmail.com', '0987654', true, 2),
  (3, 'sheba1990', 'nikitashebin@gmail.com', 'Sheba1990', true, 3);

insert into user_role_table(user_id, roles)
values
  (1, 'USER'),
  (2, 'USER'),
  (3, 'ADMIN');

insert into room_details_table (id, price_per_night, floor, amount_of_rooms, capacity, see_view, bath, babybed, wifi, breakfast)
values
  (1, 200.00, 4, 5, 5, true, true, true, true, true),
  (2, 150.00, 3, 3, 3, false, true, false, true, true),
  (3, 100.00, 2, 2, 2, false, true, true, true, true),
  (4, 50.00, 1, 1, 1, false, true, false, true, false);

insert into room_category_table (id, category_name, description)
  values
  (1, 'DELUXE', 'Deluxe room 20 – 25 m² for two to four persons with en suite shower/WC, hair dryer, balcony, double bed and bunk bed, cable TV, radio, safe and free WiFi'),
  (2, 'BUSINESS','Business double room 20 – 25 m² for two persons with en suite bath/shower/WC, hair dryer, balcony, double bed, cable TV, radio, safe and free WiFi'),
  (3, 'STANDARD','Standard room 30 m² for two to four persons with en suite bath/shower/WC, hair dryer, balcony, double bed and bunk bed or sofa bed, cable TV, radio, safe and free WiFi'),
  (4, 'ECONOM','Economy single room 11 m² for one person with en suite shower/WC, hair dryer, cable TV, radio, safe and free WiFi');

insert into room_table (id, room_number, room_status, room_category_id, room_details_id)
  values
  (1, 20, 'OCCUPIED', 1, 1),
  (2, 15, 'OCCUPIED', 2, 2),
  (3, 10, 'VACANT', 3, 3),
  (4, 5, 'VACANT', 4, 4);

insert into order_table (id, order_number, room_category, amount_of_guests, date_of_check_in, date_of_check_out, approved, user_id, room_id)
values
  (1, 1, 'DELUXE', 4, '2020-08-01', '2020-08-06', true, 1, 1),
  (2, 2, 'BUSINESS', 2, '2020-08-03', '2020-08-09', true, 2, 2);