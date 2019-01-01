CREATE FUNCTION trigger_function() RETURNS trigger AS
$BODY$
BEGIN
	IF NEW.id > 1 THEN
 		INSERT INTO application_users(id, name, user_id) VALUES(NEW.id + 1000, 'Trigger','Trigger');
 	END IF;
 RETURN NEW;
END;
$BODY$
LANGUAGE PLPGSQL;

CREATE TRIGGER trigger_function
  BEFORE UPDATE
  ON application_users
  FOR EACH ROW
  EXECUTE PROCEDURE trigger_function();

INSERT INTO application_users (id, name, user_id) VALUES (1337, 'Manual', 'Manual');
UPDATE application_users SET name = 'Updated' WHERE id = 1337;