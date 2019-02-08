CREATE TABLE application_users (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255) UNIQUE,
	user_id VARCHAR(255)
);



CREATE FUNCTION inc(val integer) RETURNS integer AS $$
 	BEGIN
 		RETURN val + 1;
	END; $$
LANGUAGE PLPGSQL;