CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    published TIMESTAMP,
    isbn VARCHAR(255),
    price NUMERIC
);