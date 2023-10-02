package com.user.csvupload.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.user.csvupload.entities.User;
import com.user.csvupload.repositories.UserRepository;

@Service
public class UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public void processCSV(MultipartFile file) {
		if (file.isEmpty()) {
			throw new RuntimeException("Invalid File!!");
		}
		try (InputStream inputStream = file.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader reader = new BufferedReader(inputStreamReader)) {
			String line = null;
			while((line = reader.readLine())!=null) {
				String[] data = line.split(",");
				User user = new User(Long.parseLong(data[0]), data[1], Integer.parseInt(data[2]));
				createUser(user);
			}
		} catch (IOException e) {
			LOGGER.error("Failed to load data from csv file", e);
			throw new RuntimeException("Invalid File!!");
		}
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


}
