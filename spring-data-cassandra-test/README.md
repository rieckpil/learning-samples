# CQL statements:

```
create keyspace dev with replication = {'class':'SimpleStrategy','replication_factor':1};

create table test (id int, firstname varchar, lastname varchar, code varchar, PRIMARY KEY(code, id)) WITH CLUSTERING ORDER BY (id ASC);

CREATE TABLE playlists ( 
    id uuid, song_order int, song_id uuid, title text, album text, artist text, 
    PRIMARY KEY (id, song_order)) WITH CLUSTERING ORDER BY (song_order ASC);

insert into test (id, firstname, lastname, code)
 values (3 ,'MAx','Mustermann','42');
 
 SELECT * FROM test WHERE id in (1, 10000) ORDER BY id ASC; 
 
 DELETE FROM test WHERE id > 0;
 
 TRUNCATE test;
 DROP TABLE test;
 
 SELECT * FROM test LIMIT 100;
 
 select count(*) FROM test;
 ```