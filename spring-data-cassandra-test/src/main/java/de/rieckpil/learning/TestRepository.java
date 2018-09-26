package de.rieckpil.learning;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface TestRepository extends CassandraRepository<Test, Long> {

}
