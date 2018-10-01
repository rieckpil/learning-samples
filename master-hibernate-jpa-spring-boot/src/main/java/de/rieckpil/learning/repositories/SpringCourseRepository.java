package de.rieckpil.learning.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.rieckpil.learning.entity.Course;

public interface SpringCourseRepository extends JpaRepository<Course, Long> {

	@Query("SELECT c FROM Course c WHERE name like '%28%'")
	List<Course> courseWith28InName();

	@Query(value = "SELECT * FROM course_details c WHERE name LIKE '%28%'", nativeQuery = true)
	List<Course> courseWith28InNameUsingNativeQuery();

	@Query(name = "query_get_28_in_name")
	List<Course> courseWith28InNameUsingNamedQuery();

}
