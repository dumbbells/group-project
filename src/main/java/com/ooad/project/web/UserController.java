package com.ooad.project.web;

import com.ooad.project.domain.User;
import com.ooad.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/id/{userId}")
	public User getUserById(@PathVariable(value = "userId") Long userId) {
		return userRepository.findByUserId(userId);
	}

	@GetMapping("/userName/{userName}")
	public User getUserByUserName(@PathVariable(value = "userName") String userName) {
		return userRepository.findByUserName(userName);
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAllUsers();
	}

	@PostMapping
	public User addUser(@RequestBody User user) {
		userRepository.save(user);

		return user;
	}
}
