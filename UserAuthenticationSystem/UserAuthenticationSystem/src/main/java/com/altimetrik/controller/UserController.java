package com.altimetrik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.exception.RecordNotFoundException;
import com.altimetrik.model.UserEntity;
import com.altimetrik.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService service;

	@GetMapping
	public ResponseEntity<List<UserEntity>> getAllEmployees() {
		List<UserEntity> list = service.getAllUsers();
		return new ResponseEntity<List<UserEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id) throws RecordNotFoundException {
		UserEntity entity = service.getUserById(id);
		return new ResponseEntity<UserEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserEntity> createOrUpdateUser(@RequestBody UserEntity user) throws RecordNotFoundException {
		UserEntity updated = service.createOrUpdateUser(user);
		return new ResponseEntity<UserEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteUserById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteUserById(id);
		return HttpStatus.FORBIDDEN;
	}

}