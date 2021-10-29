INSERT INTO user (username, password, name, account_non_expired, account_non_locked, credentials_non_expired, enabled) 
VALUES ('admin', '$2a$10$f/OzJpl3Cr6cTiDnQJgXoeekyOyZ9zLODoL9gocgLBY8rbFmYgmEO', 'Administrator', true, true, true, true); 
INSERT INTO user (username, password, name, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('kilsoo', '$2a$10$TAZ4On.HBwJuWlQTKT9jkufWdUYqd9y.F1QpdBYuqAiAoLmgTXTz6', 'Kilsoo Kang', true, true, true, true); 

INSERT INTO authority VALUES ('admin', 'ADMIN'); 
INSERT INTO authority VALUES ('kilsoo', 'ADMIN'); 
INSERT INTO authority VALUES ('kilsoo', 'USER');

INSERT INTO contents (id, title, director, year, genre) VALUES (1, 'Inception', 'Christopher Jonathan James Nolan', '2010', 'Thriller');
INSERT INTO contents (id, title, director, year, genre) VALUES (2, 'Pride & Prejudice', 'Joe Wright', '2005', 'Romance');
INSERT INTO contents (id, title, director, year, genre) VALUES (3, 'Logan', 'James Mangold', '2017', 'Action');
INSERT INTO contents (id, title, director, year, genre) VALUES (4, 'Inception', 'Christopher Jonathan James Nolan', '2010', 'Thriller');
INSERT INTO contents (id, title, director, year, genre) VALUES (6, 'Pride & Prejudice', 'Joe Wright', '2005', 'Romance');
INSERT INTO contents (id, title, director, year, genre) VALUES (7, 'Logan', 'James Mangold', '2017', 'Action');
INSERT INTO contents (id, title, director, year, genre) VALUES (8, 'Inception', 'Christopher Jonathan James Nolan', '2010', 'Thriller');
INSERT INTO contents (id, title, director, year, genre) VALUES (9, 'Pride & Prejudice', 'Joe Wright', '2005', 'Romance');
INSERT INTO contents (id, title, director, year, genre) VALUES (10, 'Logan', 'James Mangold', '2017', 'Action');
INSERT INTO contents (id, title, director, year, genre) VALUES (11, 'Inception', 'Christopher Jonathan James Nolan', '2010', 'Thriller');
INSERT INTO contents (id, title, director, year, genre) VALUES (12, 'Pride & Prejudice', 'Joe Wright', '2005', 'Romance');
INSERT INTO contents (id, title, director, year, genre) VALUES (13, 'Logan', 'James Mangold', '2017', 'Action');