package de.rieckpil.learning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.rieckpil.learning.entity.Course;

public interface SpringCourseRepository extends JpaRepository<Course, Long> {

}
