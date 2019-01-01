CREATE FUNCTION inc(val integer) RETURNS integer AS $$
 	BEGIN
 		RETURN val + 1;
	END; $$
LANGUAGE PLPGSQL;