package de.rieckpil.learning;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Validated
public class UsersResource {

  @Autowired
  UserRepository userRepository;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers(@Positive @RequestParam(defaultValue = "1", name = "page") int requiredPage,
      @Max(value = 1000) @Positive @RequestParam(defaultValue = "500", name = "size") int size) {

    Slice<User> resultList = userRepository.findAll(CassandraPageRequest.first(size));

    int currentPage = 1;

    while (resultList.hasNext() && currentPage <= requiredPage) {
      System.out.println("Current Page Number: " + currentPage);
      resultList = userRepository.findAll(resultList.nextPageable());
      currentPage++;
    }

    return ResponseEntity.ok(resultList.getContent());
  }
}
