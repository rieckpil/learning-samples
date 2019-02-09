WITH only_major_users AS (SELECT * FROM application_users WHERE id > 1000 )
SELECT name FROM only_major_users;