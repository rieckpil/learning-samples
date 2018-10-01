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
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1003, 'Not really interesting', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1004, 'Boring course 1', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1005, 'Boring course 2', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1006, 'Boring course 3', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1007, 'Boring course 4', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1008, 'Boring course 5', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1009, 'Boring course 6', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1010, 'Boring course 7', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1011, 'Boring course 8', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1012, 'Boring course 9', sysdate
(), sysdate());
INSERT INTO course_details (id, name, last_updated_date, creation_date) VALUES (1013, 'Boring course 10', sysdate
(), sysdate());

INSERT INTO passport (id, number) VALUES (40001, '1337');
INSERT INTO passport (id, number) VALUES (40002, '421234');
INSERT INTO passport (id, number) VALUES (40003, '08154321');

INSERT INTO student (id, name, passport_id) VALUES (20001, 'Max', 40001);
INSERT INTO student (id, name, passport_id) VALUES (20002, 'Tom', 40002);
INSERT INTO student (id, name, passport_id) VALUES (20003, 'Duke', 40003);

INSERT INTO review (id, rating, description, course_id) VALUES (50001, '1', 'Nice', 1000);
INSERT INTO review (id, rating, description, course_id) VALUES (50002, '3', 'Awesome', 1000);
INSERT INTO review (id, rating, description, course_id) VALUES (50003, '5', 'Fantatstic', 1001);

INSERT INTO student_course (student_id, course_id) VALUES (20001, 1000);
INSERT INTO student_course (student_id, course_id) VALUES (20001, 1001);
INSERT INTO student_course (student_id, course_id) VALUES (20001, 1002);
INSERT INTO student_course (student_id, course_id) VALUES (20002, 1000);