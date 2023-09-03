package com.onehealth.patientuser.serviceImpl;

import com.onehealth.patientuser.DTO.PatientProfile;
import com.onehealth.patientuser.entity.PatientUser;
import com.onehealth.patientuser.exception.DatabaseException;
import com.onehealth.patientuser.exception.RecordNotFoundException;
import com.onehealth.patientuser.repository.PatientUserRepository;
import com.onehealth.patientuser.service.PatientUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service implementation for managing Patient Users.
 *
 * This class provides the actual implementation of the methods defined in the
 * PatientUserService interface. It interacts with the database through the
 * PatientUserRepository to perform CRUD operations on Patient Users. The
 * service layer encapsulates the business logic related to Patient Users.
 * 
 * Note: Make sure to import the required entities, exceptions, and repositories
 * from the appropriate packages.
 * 
 * @author Yogesh Baiskar
 * @version 1.0
 */
@Service
public class PatientUserServiceImpl implements PatientUserService {

	@Autowired
	private PatientUserRepository patientUserRepository;
	
	@Autowired
	RestTemplate restTemplate;


	/**
	 * Get a list of all Patient Users.
	 *
	 * @return A list of Patient Users.
	 */
	@Override
	public List<PatientUser> getAllUsers() {
		return patientUserRepository.findAll();
	}

	/**
	 * Get a Patient User by their ID.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The retrieved Patient User.
	 * @throws RecordNotFoundException If the user with the given ID is not found.
	 */
	@Override
	public PatientUser getUserById(Long userId) throws RecordNotFoundException {
		return patientUserRepository.findById(userId)
				.orElseThrow(() -> new RecordNotFoundException("User not found with ID: " + userId));
	}

	/**
	 * Create a new Patient User.
	 *
	 * @param user The Patient User object to be created.
	 * @return The created Patient User.
	 * @throws DatabaseException If there is an issue with the database while
	 *                           creating the user.
	 */
	@Override
	public PatientUser createUser(PatientUser user) {
		// Save the user to the database
		PatientUser patientUser = patientUserRepository.save(user);

		// Create a Patient profile using the saved user's details
		PatientProfile patientProfile = new PatientProfile();
		patientProfile.setUserId(patientUser.getUserId()); // Set the user ID
		patientProfile.setFirstName(patientUser.getFirstName());
		patientProfile.setLastName(patientUser.getLastName());
		patientProfile.setMobileNumber(patientUser.getMobileNumber());
		patientProfile.setEmailId(patientUser.getEmailId());
		// You can set other PatientProfile fields here using the user details you have

//		// Create a RestTemplate instance
//		RestTemplate restTemplate = new RestTemplate();

		// Set the URL for creating a Patient profile
		String createPatientUrl = "https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/patientProfile/add";

		// Create headers with JSON content type
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create the request entity with PatientProfile data and headers
		HttpEntity<PatientProfile> requestEntity = new HttpEntity<>(patientProfile, headers);

		// Send the POST request to create the Patient profile
		ResponseEntity<String> response = restTemplate.exchange(createPatientUrl, HttpMethod.POST, requestEntity,
				String.class);

		if (response.getStatusCode() == HttpStatus.CREATED) {
			// Patient profile created successfully, you can handle the response here
			String responseBody = response.getBody();
			System.out.println(("PatientProfile..."+ responseBody));
			// Log or process the response if needed
		} else {
			// Handle the case where the request to create the Patient profile failed
			// You can throw an exception or handle the error accordingly
			String responseBody = response.getBody();
			System.out.println(("PatientProfile...Not Created PAtient Service Down "+ responseBody));
		}

		return patientUser;
	}

	/**
	 * Update an existing Patient User.
	 *
	 * @param userId The ID of the user to be updated.
	 * @param user   The updated Patient User object.
	 * @return The updated Patient User.
	 * @throws RecordNotFoundException If the user with the given ID is not found.
	 */
	@Override
	public PatientUser updateUser(Long userId, PatientUser user) throws RecordNotFoundException {
		if (!patientUserRepository.existsById(userId)) {
			throw new RecordNotFoundException("User not found with ID: " + userId);
		}
		user.setUserId(userId);
		return patientUserRepository.save(user);
	}

	/**
	 * Delete a Patient User by their ID.
	 *
	 * @param userId The ID of the user to be deleted.
	 * @throws RecordNotFoundException If the user with the given ID is not found.
	 */
	@Override
	public void deleteUser(Long userId) throws RecordNotFoundException {
		if (!patientUserRepository.existsById(userId)) {
			throw new RecordNotFoundException("User not found with ID: " + userId);
		}
		patientUserRepository.deleteById(userId);
	}

	/**
	 * Get a Patient User by their email ID and mobile number.
	 *
	 * @param emailId      The email ID of the user.
	 * @param mobileNumber The mobile number of the user.
	 * @return The retrieved Patient User or null if not found.
	 */
	@Override
	public PatientUser getUserBymobileNumberandEmailId(String emailId, String mobileNumber) {
		return patientUserRepository.findByEmailIdAndMobileNumber(emailId, mobileNumber);
	}
}
