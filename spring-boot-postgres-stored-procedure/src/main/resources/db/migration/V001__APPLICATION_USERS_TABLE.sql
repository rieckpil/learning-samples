CREATE TABLE application_users (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255) UNIQUE,
	user_id VARCHAR(255)
);