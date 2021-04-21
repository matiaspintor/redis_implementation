package com.implementation.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.implementation.redis.dto.UserDTO;
import com.implementation.redis.service.UserService;

@RestController()
@RequestMapping(path = { "/user" })
public class UserController {

	@Autowired()
	private UserService userService;

	@GetMapping()
	public ResponseEntity<List<UserDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.userService.findAllUser());
	}

	@GetMapping(params = {"emailUser"})
	public ResponseEntity<UserDTO> findByEmail(@RequestParam("emailUser") String emailUser) {
		return ResponseEntity.status(HttpStatus.OK).body(this.userService.findByEmailUser(emailUser));

	}

	@PostMapping()
	public ResponseEntity<UserDTO> newUser(@RequestBody() UserDTO userDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.newUser(userDTO));
	}
	
	@GetMapping(params = {"from", "to"})
	public ResponseEntity<List<UserDTO>> findAllUserRangeBrith(@RequestParam("from") String from, @RequestParam("to") String to){
		return ResponseEntity.status(HttpStatus.OK).body(this.userService.findAllByRangeBirth(from, to));
	}
	
	@DeleteMapping(params = {"emailUser"})
	public ResponseEntity<String> deleteUser(@RequestParam("emailUser") String emailUser,  UserDTO userDTO){
		return ResponseEntity.status(HttpStatus.OK).body(this.userService.deleteUserByUsername(userDTO.getEmailUser()));
	}
}
