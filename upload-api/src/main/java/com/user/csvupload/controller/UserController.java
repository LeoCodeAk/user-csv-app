package com.user.csvupload.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.user.csvupload.entities.User;
import com.user.csvupload.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
		LOGGER.debug("Request to parse file {}", file);
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Please upload a valid file.");
		}
		if (!file.getContentType().equals("text/csv")) {
			return ResponseEntity.badRequest().body("Only CSV files are allowed.");
		}
		try {
			userService.processCSV(file);
			return ResponseEntity.ok().body("CSV file uploaded and processed successfully.");
		} catch (Exception e) {
			LOGGER.error("Exception occured while parsing data,", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@GetMapping
	public List<User> getAllUsers() {
		LOGGER.debug("Request to fetch all users");
		return userService.getAllUsers();
	}

}