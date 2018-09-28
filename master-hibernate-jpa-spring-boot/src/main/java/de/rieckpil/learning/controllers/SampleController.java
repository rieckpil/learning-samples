package de.rieckpil.learning.controllers;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/helloworld")
public class SampleController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
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

}
