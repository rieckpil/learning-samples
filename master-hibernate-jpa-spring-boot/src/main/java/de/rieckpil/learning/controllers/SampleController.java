package de.rieckpil.learning.controllers;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.rieckpil.learning.entity.Course;
import de.rieckpil.learning.entity.Passport;
import de.rieckpil.learning.repositories.CourseRepository;
import de.rieckpil.learning.repositories.PassportRepository;

@RestController
@RequestMapping("/helloworld")
public class SampleController {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	PassportRepository passportRepository;

	@GetMapping
	public String sayHello() {
		logger.info("Incoming request");
		return "Hi!";
	}

	@PostMapping
	public ResponseEntity<Void> uploadImage(@NotNull @RequestParam("file") MultipartFile multipartFile) {
		logger.info("File has size: {} and name {}", multipartFile.getSize(), multipartFile.getOriginalFilename());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllCourses() {
		return ResponseEntity.ok(courseRepository.findAll());
	}

	@GetMapping("/passports")
	public ResponseEntity<List<Passport>> getAllPassports() {
		return ResponseEntity.ok(passportRepository.findAll());
	}

}
