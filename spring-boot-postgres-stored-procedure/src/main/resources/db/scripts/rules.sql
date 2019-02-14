CREATE OR REPLACE RULE r_avoid_delete_users
AS ON DELETE TO application_users
DO INSTEAD NOTHING;

CREATE OR REPLACE RULE r_archive_tuples_on_deletion
 AS ON DELETE TO application_users
 DO ALSO
 INSERT INTO application_users_archived
 SELECT OLD.*;