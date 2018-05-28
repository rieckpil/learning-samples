INSERT INTO Passport (id, passport_id) VALUES (1, 'XYZ111');
INSERT INTO Passport (id, passport_id) VALUES (2, 'XYZ222');
INSERT INTO Passport (id, passport_id) VALUES (3, 'XYZ333');
INSERT INTO Passport (id, passport_id) VALUES (4, 'XYZ444');
INSERT INTO Passport (id, passport_id) VALUES (5, 'XYZ555');

INSERT INTO Person (id, last_name, first_name, passport_id) VALUES (1, 'Riecks', 'Philip', 1);
INSERT INTO Person (id, last_name, first_name, passport_id) VALUES (2, 'John', 'Doe', 2);
INSERT INTO Person (id, last_name, first_name, passport_id) VALUES (3, 'Foo', 'Bar', 3);
INSERT INTO Person (id, last_name, first_name, passport_id) VALUES (4, 'Einstein', 'Albert', 4);
INSERT INTO Person (id, last_name, first_name, passport_id) VALUES (5, 'Touring', 'Alan', 5);

INSERT INTO Address (id, city, street, house_number, person_id) VALUES (1, 'Herzogenaurach', 'Hauptstraße', 14, 1);
INSERT INTO Address (id, city, street, house_number, person_id) VALUES (2, 'Mannheim', 'Kloppenheimerstraße', 27, 1);
INSERT INTO Address (id, city, street, house_number, person_id) VALUES (3, 'Berlin', 'Kadewe', 12, 2);
INSERT INTO Address (id, city, street, house_number, person_id) VALUES (4, 'Köln', 'Donauufer', 81, 3);
INSERT INTO Address (id, city, street, house_number, person_id) VALUES (5, 'Mannheim', 'An den Planken', 2, 4);