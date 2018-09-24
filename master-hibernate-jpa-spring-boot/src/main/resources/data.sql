--CREATE TABLE person (
--	id integer not null,
--	name varchar(255) not null,
--	location varchar(255),
--	birth_date timestamp,
--	primary key(id)
--);


INSERT INTO person (id, name, location, birth_date) VALUES (1001, 'Phil', 'HZA', '1995-09-13 20:55:42.697');
INSERT INTO person (id, name, location, birth_date) VALUES (1002, 'Duke', 'HZA', '2018-09-17 20:55:42.697');
INSERT INTO person (id, name, location, birth_date) VALUES (1003, 'John', 'HZA', '2018-09-17 20:55:42.697');
INSERT INTO person (id, name, location, birth_date) VALUES (1004, 'Tom', 'HZA', '2018-09-17 20:55:42.697');
INSERT INTO person (id, name, location, birth_date) VALUES (1005, 'Tim', 'HZA', '2018-09-17 20:55:42.697');

INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1000, 'in28Minutes Beginner', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1001, 'in28Minutes Advanced', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1002, 'SpringframeworkGuru', sysdate
(), sysdate());

INSERT INTO student (id, name) VALUES (20001, 'Max');
INSERT INTO student (id, name) VALUES (20002, 'Tom');
INSERT INTO student (id, name) VALUES (20003, 'Duke');


INSERT INTO passport (id, number) VALUES (40001, '1337');
INSERT INTO passport (id, number) VALUES (40002, '42');
INSERT INTO passport (id, number) VALUES (40003, '0815');

INSERT INTO review (id, rating, description) VALUES (50001, '1', 'Nice');
INSERT INTO review (id, rating, description) VALUES (50002, '3', 'Awesome');
INSERT INTO review (id, rating, description) VALUES (50003, '5', 'Fantatstic');