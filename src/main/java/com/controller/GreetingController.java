package com.controller;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.example.restservice.Greeting;
import com.example.restservice.domain.User;
import com.example.restservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GreetingController {
	@Autowired
	private UserRepo userRepo;

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping
	public String main(Map<String, Object> model) {
		Iterable<User> users = userRepo.findAll();

		model.put("some", "Hello!");
		model.put("users", users);

		return "main";
	}

	@PostMapping
	public String add(@RequestParam String nickname, @RequestParam double balance, Map<String, Object> model) {
		User user = new User(nickname, balance);

		userRepo.save(user);

		Iterable<User> users = userRepo.findAll();

		model.put("some", "Hello, Post!");
		model.put("users", users);

		return "main";
	}
}
