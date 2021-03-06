DO
 $$
   DECLARE
      i int;  -- initial value is NULL
      t text DEFAULT 'Hello World!';
      d date DEFAULT CURRENT_DATE;
   BEGIN
      RAISE INFO 'Variables are: d = [%], t = [%], i = [%]', d, t, i;
   END
 $$;
 
 
DO $code$
 DECLARE
  i int  DEFAULT 10;
  t text DEFAULT 'Hello World!';
  j int  DEFAULT 100;
 BEGIN
    RAISE INFO 'Outer block: i = [%] t = [%] j = [%]', i, t, j;
    -- nested block
    DECLARE
      i text DEFAULT 'PostgreSQL is amazing!';
      t int  DEFAULT 20;
      j int  DEFAULT 999;
    BEGIN
    RAISE INFO 'Inner block i = [%] t = [%] j = [%]', i, t, j;
    END;

    RAISE INFO 'Outer block: i = [%] t = [%] j = [%]', i, t, j;
 END; $code$;
 
 
DO $code$
   DECLARE
      name text     := 'PL/pqSQL2';
      user_id text  := '1346XYZ';
      id   int;
   BEGIN
      INSERT INTO application_users( name, user_id )
      VALUES ( name, user_id )
      RETURNING application_users.id
      INTO id;

      RAISE INFO 'New user [%] has been inserted with primary key = %', name, id;

   END $code$;
   
   
DO $code$
 DECLARE
   pi CONSTANT real := 3.14;
   greeting_text CONSTANT text := 'Greetings ';
   people text[] := ARRAY[ 'Mr. Green', 'Mr. Red' ];
   current_person text;
 BEGIN
   FOREACH current_person IN ARRAY people LOOP
      RAISE INFO '% %', greeting_text, current_person;
      RAISE INFO 'Did you know that PI is %?', pi;
   END LOOP;
 END $code$;
 
 
DO $code$
 DECLARE
   name text := 'Duke2';
 BEGIN
    INSERT INTO application_users( name, user_id )
    VALUES ( name, 'UUID' );

  EXCEPTION
      WHEN unique_violation THEN
      	RAISE INFO 'ERROR: User [%] has already been inserted', name;
     WHEN others THEN
        NULL;
 END $code$;