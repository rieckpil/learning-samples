package de.rieckpil.learning;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.cassandra.core.query.Criteria.where;
import static org.springframework.data.cassandra.core.query.Query.query;

@RestController
@RequestMapping("/test")
public class TestResource {

  @Autowired
  TestRepository    testRepository;

  @Autowired
  CassandraTemplate cassandraTemplate;

  @GetMapping
  public ResponseEntity<List<Test>> getAllTests(@RequestParam(defaultValue = "392", name = "page") int page, @RequestParam(defaultValue = "500", name = "size") int size) {

    Slice<Test> originSlice = testRepository.findAll(CassandraPageRequest.first(size));

    List<Test> resultList = cassandraTemplate.select(query(where("id").in(1, 100_000)).limit(1000).sort(Sort.by("id").ascending()).withAllowFiltering(), Test.class);

    return ResponseEntity.ok(resultList);
  }

  private void alternativeWay() {
    Slice<Test> resultList = testRepository.findAll(CassandraPageRequest.first(500));
    for (int i = 1; i < 1000; i++) {
      if (resultList.hasNext()) {
        resultList = testRepository.findAll(resultList.nextPageable());
      } else {
        break;
      }
    }
  }
}
