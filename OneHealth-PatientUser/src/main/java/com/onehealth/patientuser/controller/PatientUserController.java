package com.onehealth.patientuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onehealth.patientuser.DTO.ResponseMessage;
import com.onehealth.patientuser.entity.PatientUser;
import com.onehealth.patientuser.exception.DatabaseException;
import com.onehealth.patientuser.exception.RecordNotFoundException;
import com.onehealth.patientuser.service.PatientUserService;

import java.util.List;

@RestController
@RequestMapping("/patientUser")
public class PatientUserController {

	@Autowired
	private PatientUserService patientUserService;

	@GetMapping("/")
	public String Hello() {
		
		System.out.println("555555555");
		return "Hello From /patientUser22 ";
	}

	@GetMapping("/all")
	public ResponseEntity<List<PatientUser>> getAllUsers() {
		List<PatientUser> users = patientUserService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<PatientUser> getUserById(@PathVariable Long userId) throws RecordNotFoundException {
		PatientUser user = patientUserService.getUserById(userId);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<ResponseMessage<PatientUser>> createUser(@RequestBody PatientUser user)
			throws DatabaseException {
		PatientUser createdUser = patientUserService.createUser(user);

		ResponseMessage<PatientUser> responseMessage = new ResponseMessage<>("User Created Successfully", createdUser);
		return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<ResponseMessage<PatientUser>> updateUser(@PathVariable Long userId,
			@RequestBody PatientUser user) throws RecordNotFoundException {
		PatientUser updatedUser = patientUserService.updateUser(userId, user);
		if (updatedUser != null) {
			ResponseMessage<PatientUser> responseMessage = new ResponseMessage<>("User Updated Successfully",
					updatedUser);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) throws RecordNotFoundException {
		patientUserService.deleteUser(userId);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
}
