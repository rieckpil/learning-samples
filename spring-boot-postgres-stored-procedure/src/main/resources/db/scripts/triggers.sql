CREATE OR REPLACE FUNCTION helloWorld()
	RETURNS TRIGGER AS $$
 	BEGIN
 		RAISE INFO 'Hello World!';
 		RETURN NEW;
	END; $$
LANGUAGE PLPGSQL;

DROP TRIGGER IF EXISTS say_hello ON application_users;

CREATE TRIGGER say_hello BEFORE INSERT ON application_users FOR EACH ROW EXECUTE PROCEDURE helloWorld();

ALTER TABLE application_users DISABLE TRIGGER say_hello;
ALTER TABLE application_users ENABLE TRIGGER say_hello;
ALTER TABLE application_users ENABLE TRIGGER ALL;

WHEN(NEW.* IS DISTINCT FROM OLD.* )


-- Event trigger
  
 DROP TRIGGER IF EXISTS etr_notifier;

CREATE OR REPLACE FUNCTION f_etr_notifier()
RETURNS EVENT_TRIGGER AS $code$
  DECLARE
    event_tuple record;
  BEGIN
    FOR event_tuple IN SELECT *
                       FROM pg_event_trigger_ddl_commands()  LOOP
       RAISE DEBUG 'Event fired by command [%] on [%]',
                   event_tuple.command_tag,    -- statement
                   event_tuple.object_identity;
    END LOOP;

    FOR event_tuple IN SELECT *
                       FROM pg_event_trigger_dropped_objects()  LOOP
      RAISE DEBUG 'Dropped object [%] of type [%]',
                       event_tuple.object_identity,
                       event_tuple.object_type;
    END LOOP;
  END $code$ LANGUAGE plpgsql;

  
 CREATE EVENT TRIGGER
 etr_notifier ON sql_drop
 EXECUTE PROCEDURE f_etr_notifier();
         