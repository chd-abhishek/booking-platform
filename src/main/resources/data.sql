insert into account (type, id) values ('REGULAR', 101);
insert into account (type, id) values ('PREMIUM', 102);

insert into address (area, city, pincode, state, id) values ('Amanora', 'Pune', '411028', 'MH', '201');
insert into address (area, city, pincode, state, id) values ('Magarpatta', 'Pune', '411029', 'MH', '202');
insert into address (area, city, pincode, state, id) values ('Phoenix', 'Pune', '411030', 'MH', '203');
insert into address (area, city, pincode, state, id) values ('Aundh', 'Pune', '411031', 'MH', '204');

insert into preference (preference, id) values ('ROMCOM', 301);
insert into preference (preference, id) values ('HORROR', 302);

insert into users (account_id, address_id, name, preference_id, type, user_id, id) values (101, 201, 'Abhi' ,301, 'CUSTOMER', 'Abhi1', 401);
insert into users (account_id, address_id, name, preference_id, type, user_id, id) values (102, 202, 'Raj' ,302, 'CUSTOMER', 'Raj1', 402);

insert into rating (entity, ratings, id) values ('MOVIE','THREE' , 501);
insert into rating (entity, ratings, id) values ('MOVIE','FOUR' , 502);
insert into rating (entity, ratings, id) values ('CINEMA','THREE' , 503);
insert into rating (entity, ratings, id) values ('CINEMA','FOUR' , 504);

insert into movie (language, name, rating_id, type, id) values ('ENGLISH', 'MOVIE1', 501, 'ROMCOM', 601);
insert into movie (language, name, rating_id, type, id) values ('HINDI', 'MOVIE2', 502, 'HORROR', 602);


insert into cinema (address_id, rating_id, type, name, id) values (203, 503, 'MULTIPLEX', 'Seasons', 701);
insert into cinema (address_id, rating_id, type, name, id) values (204, 504, 'MULTIPLEX', 'Phoenix', 702);

insert into hall (cinema_id, hall_id, num_of_rows, num_of_seats, screen_size, id) values (701, 'Hall1', 10, 100, 'MEDIUM', 801);
insert into hall (cinema_id, hall_id, num_of_rows, num_of_seats, screen_size, id) values (701, 'Hall1', 20, 500, 'LARGE', 802);
insert into hall (cinema_id, hall_id, num_of_rows, num_of_seats, screen_size, id) values (702, 'Hall3', 10, 100, 'MEDIUM', 803);
insert into hall (cinema_id, hall_id, num_of_rows, num_of_seats, screen_size, id) values (702, 'Hall4', 20, 500, 'LARGE', 804);


insert into show (duration, hall_id, movie_id, seat_booked, show_time, total_seats, id) values (2, 801, 601, 10, '2023-05-10 13.00.00', 100, 901);
insert into show (duration, hall_id, movie_id, seat_booked, show_time, total_seats, id) values (2, 801, 602, 20, '2023-05-10 15.00.00', 500, 902);
insert into show (duration, hall_id, movie_id, seat_booked, show_time, total_seats, id) values (2, 802, 601, 10, '2023-05-10 13.00.00', 100, 903);
insert into show (duration, hall_id, movie_id, seat_booked, show_time, total_seats, id) values (2, 802, 602, 20, '2023-05-10 15.00.00', 500, 904);


insert into seat (seat_num, seat_status, show_id, id) values (5, 'BOOKED', 901, 1001);
insert into seat (seat_num, seat_status, show_id, id) values (10, 'BOOKED', 901, 1002);
insert into seat (seat_num, seat_status, show_id, id) values (15, 'BOOKED', 902, 1003);
insert into seat (seat_num, seat_status, show_id, id) values (25, 'BOOKED', 902, 1004);

insert into payment (amount, coupon_id, method, status, id) values (500, null, 'CREDIT_CARD', 'DONE', 1101);