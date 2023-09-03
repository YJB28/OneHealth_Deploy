package com.onehealth.patientuser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.onehealth.patientuser.DTO.ResponseMessage;
import com.onehealth.patientuser.entity.PatientUser;
import com.onehealth.patientuser.exception.DatabaseException;
import com.onehealth.patientuser.exception.ProfileNotFoundException;
import com.onehealth.patientuser.exception.RecordNotFoundException;
import com.onehealth.patientuser.service.PatientUserService;
import java.util.List;

/**
 * Controller class for handling Patient User related HTTP requests.
 * 
 * This class defines methods for saving, retrieving, updating, and deleting
 * Patient Users. It uses a service class, PatientUserService, to perform the
 * actual business logic. The controller maps HTTP endpoints to these methods,
 * allowing clients to interact with the application. Additionally, the class
 * includes logging statements to log important events for monitoring and
 * debugging.
 * 
 * Note: Make sure to import the required entities, exceptions, and services
 * from the appropriate packages.
 * 
 * @author Yogesh Baiskar
 * @version 1.0
 */
@RestController
@RequestMapping("/patientUser")
public class PatientUserController {

	@Autowired
	private PatientUserService patientUserService;

	private static final Logger logger = LoggerFactory.getLogger(PatientUserController.class);

	@GetMapping
	public String Hello() {
		logger.info("Received GET request at /patientUser");
		return "Hello From /patientUser";
	}

	/**
	 * Get all users.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<PatientUser>> getAllUsers() {
		logger.info("Received GET request at /patientUser/all");
		List<PatientUser> users = patientUserService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	/**
	 * Get a user by ID.
	 * 
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<PatientUser> getUserById(@PathVariable Long userId) throws  RecordNotFoundException {
		logger.info("Received GET request at /patientUser/{}", userId);
		PatientUser user = patientUserService.getUserById(userId);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("User Not Found");
		}
	}

	/**
	 * Create a new user.
	 * 
	 * @throws DatabaseException
	 * @throws RecordNotFoundException
	 */
	@PostMapping
	public ResponseEntity<ResponseMessage<PatientUser>> createUser(@RequestBody PatientUser user)
			throws DatabaseException {
		logger.info("Received POST request at /patientUser");
		// Check if the user already exists
		PatientUser userExist = patientUserService.getUserBymobileNumberandEmailId(user.getEmailId(),
				user.getMobileNumber());
		if (userExist != null) {
			logger.error("User Already Exists");
			throw new DatabaseException("User Already Exists");
		}

		PatientUser createdUser = patientUserService.createUser(user);
		ResponseMessage<PatientUser> responseMessage = new ResponseMessage<>("User Created Successfully", createdUser);
		return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
	}

	/**
	 * Update an existing user.
	 * 
	 * @throws RecordNotFoundException
	 */
	@PutMapping("/{userId}")
	public ResponseEntity<ResponseMessage<PatientUser>> updateUser(@PathVariable Long userId,
			@RequestBody PatientUser user) throws RecordNotFoundException {
		logger.info("Received PUT request at /patientUser/{}", userId);
		PatientUser updatedUser = patientUserService.updateUser(userId, user);
		if (updatedUser != null) {
			ResponseMessage<PatientUser> responseMessage = new ResponseMessage<>("User Updated Successfully",
					updatedUser);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("User Not Found");
		}
	}

	/**
	 * Delete a user by ID.
	 * 
	 * @throws RecordNotFoundException
	 */
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) throws RecordNotFoundException {
		logger.info("Received DELETE request at /patientUser/{}", userId);
		patientUserService.deleteUser(userId);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
}
