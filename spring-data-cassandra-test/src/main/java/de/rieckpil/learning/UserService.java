package de.rieckpil.learning;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import static org.springframework.data.cassandra.core.query.Criteria.where;
import static org.springframework.data.cassandra.core.query.Query.query;

@Service
public class UserService {

  @Autowired
  UserRepository    userRepository;

  @Autowired
  CassandraTemplate cassandraTemplate;

  public void alternativeWay() {
    Slice<User> originSlice = userRepository.findAll(CassandraPageRequest.first(100));
    List<User> resultList2 = cassandraTemplate.select(query(where("id").in(1, 100_000)).limit(1000).withAllowFiltering(), User.class);
    Slice<User> resultList = userRepository.findAll(CassandraPageRequest.first(500));
    for (int i = 1; i < 1000; i++) {
      if (resultList.hasNext()) {
        resultList = userRepository.findAll(resultList.nextPageable());
      } else {
        break;
      }
    }
  }

}
