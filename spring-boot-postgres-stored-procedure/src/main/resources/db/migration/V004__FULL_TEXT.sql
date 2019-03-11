CREATE TABLE comments (
	id BIGSERIAL PRIMARY KEY,
	content TSVECTOR,
	commented_at TIMESTAMP
);