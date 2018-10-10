package de.rieckpil.learning;

import java.util.List;

import com.datastax.driver.core.PagingState;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;

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

  @Autowired
  Session           session;

  public void alternativeWay() {

    Statement st = new SimpleStatement("SELECT * FROM user WHERE ID < 8000 ALLOW FILTERING");
    st.setFetchSize(5);

    ResultSet resultSet = session.execute(st);

    PagingState pagingState = resultSet.getExecutionInfo().getPagingState();

    printData(resultSet);


    System.out.println("String based: " + pagingState.toString());

    System.out.println("---------------------------");

    PagingState pagingState2 = PagingState.fromString(pagingState.toString());

    st.setPagingState(pagingState2);

    ResultSet resultSet2 = session.execute(st);

    printData(resultSet2);

    System.out.println("String based: " + resultSet2.getExecutionInfo().getPagingState().toString());

    System.out.println("---------------------------");


  }


  private void printData(ResultSet resultSet) {
    int remaining = resultSet.getAvailableWithoutFetching();

    System.out.println("Remaining: " + remaining);

    for (Row row : resultSet) {
      System.out.println(row);
      if (--remaining == 0) {
        break;
      }
    }

  }

  private void doFoo() {
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
