-- Insert Cities
INSERT INTO city (id, name) VALUES (1, 'Delhi');
INSERT INTO city (id, name) VALUES (2, 'Mumbai');

-- Insert Movies
INSERT INTO movie (title, language, genre, duration_in_minutes) VALUES ('Inception', 'English', 'Sci-Fi', 148);
INSERT INTO movie (title, language, genre, duration_in_minutes) VALUES ('Interstellar', 'English', 'Sci-Fi', 169);
INSERT INTO movie (title, language, genre, duration_in_minutes) VALUES ('The Dark Knight', 'English', 'Action', 152);
INSERT INTO movie (title, language, genre, duration_in_minutes) VALUES ('Dune', 'English', 'Sci-Fi', 155);

-- Insert Theatres
INSERT INTO theatre (id, name, address, city_id) VALUES (1, 'PVR Cinemas', 'Andheri West', 1);
INSERT INTO theatre (id, name, address, city_id) VALUES (2, 'INOX', 'Nariman Point', 2);
INSERT INTO theatre (id, name, address, city_id) VALUES (3, 'PVR Cinemas', 'Dwarka', 1);
INSERT INTO theatre (id, name, address, city_id) VALUES (4, 'INOX', 'Connaught Place', 1);

-- Insert Physical Seats for Theatre 1 (PVR Cinemas)
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (1, 1, 'A1', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (2, 1, 'A2', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (3, 1, 'A3', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (4, 1, 'B1', 'PREMIUM');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (5, 1, 'B2', 'PREMIUM');

-- Insert Physical Seats for Theatre 2 (INOX)
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (6, 2, 'A1', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (7, 2, 'A2', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (8, 2, 'A3', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (9, 2, 'B1', 'PREMIUM');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (10, 2, 'B2', 'PREMIUM');

-- Insert Physical Seats for Theatre 3 (PVR Cinemas)
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (11, 3, 'A1', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (12, 3, 'A2', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (13, 3, 'A3', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (14, 3, 'B1', 'PREMIUM');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (15, 3, 'B2', 'PREMIUM');

-- Insert Physical Seats for Theatre 4 (INOX)
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (16, 4, 'A1', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (17, 4, 'A2', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (18, 4, 'A3', 'STANDARD');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (19, 4, 'B1', 'PREMIUM');
INSERT INTO seat (id, theatre_id, seat_number, seat_type) VALUES (20, 4, 'B2', 'PREMIUM');

-- Insert Shows
-- Show 1
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (1, 1, CURRENT_DATE, '09:00:00', '11:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (1, 1, CURRENT_DATE, '12:00:00', '14:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (1, 1, CURRENT_DATE, '15:00:00', '17:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (1, 1, CURRENT_DATE, '18:00:00', '20:30:00');
-- Show 2
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (2, 2, CURRENT_DATE, '09:00:00', '11:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (2, 2, CURRENT_DATE, '12:00:00', '14:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (2, 2, CURRENT_DATE, '15:00:00', '17:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (2, 2, CURRENT_DATE, '18:00:00', '20:30:00');
-- Show 3
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (3, 3, CURRENT_DATE, '09:00:00', '11:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (3, 3, CURRENT_DATE, '12:00:00', '14:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (3, 3, CURRENT_DATE, '15:00:00', '17:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (3, 3, CURRENT_DATE, '18:00:00', '20:30:00');
-- Show 4
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (4, 1, CURRENT_DATE, '09:00:00', '11:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (4, 2, CURRENT_DATE, '12:00:00', '14:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (4, 3, CURRENT_DATE, '15:00:00', '17:30:00');
INSERT INTO movie_show (movie_id, theatre_id, show_date, start_time, end_time) VALUES (4, 3, CURRENT_DATE, '18:00:00', '20:30:00');

--Seats for show 1
INSERT INTO show_seat (id, show_id, status, price) VALUES (1, 1, 'AVAILABLE', 300);
INSERT INTO show_seat (id, show_id, status, price) VALUES (2, 1, 'AVAILABLE', 300);
INSERT INTO show_seat (id, show_id, status, price) VALUES (3, 1, 'AVAILABLE', 300);
INSERT INTO show_seat (id, show_id, status, price) VALUES (4, 1, 'AVAILABLE', 300);
INSERT INTO show_seat (id, show_id, status, price) VALUES (5, 1, 'AVAILABLE', 300);
INSERT INTO show_seat (id, show_id, status, price) VALUES (6, 1, 'AVAILABLE', 500);
INSERT INTO show_seat (id, show_id, status, price) VALUES (7, 1, 'AVAILABLE', 500);

--Seats for show 2
INSERT INTO show_seat (id, show_id, status, price) VALUES (8, 2, 'AVAILABLE', 300);
INSERT INTO show_seat (id, show_id, status, price) VALUES (9, 2, 'AVAILABLE', 300);
INSERT INTO show_seat (id, show_id, status, price) VALUES (10, 2, 'AVAILABLE', 300);
INSERT INTO show_seat (id, show_id, status, price) VALUES (11, 2, 'AVAILABLE', 500);
INSERT INTO show_seat (id, show_id, status, price) VALUES (12, 2, 'AVAILABLE', 500);
