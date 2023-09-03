package com.onehealth.patientuser.service;

import com.onehealth.patientuser.entity.PatientUser;
import com.onehealth.patientuser.exception.DatabaseException;
import com.onehealth.patientuser.exception.RecordNotFoundException;

import java.util.List;

/**
 * Service interface for managing Patient Users.
 * 
 * This interface defines methods for performing CRUD operations on Patient
 * Users. It is implemented by the PatientUserServiceImpl class, which provides
 * the actual implementation of these methods. The service layer acts as an
 * intermediary between the controller and the repository, encapsulating the
 * business logic.
 * 
 * Note: Make sure to import the required entities and exceptions from the
 * appropriate packages.
 * 
 * @author Yogesh Baiskar
 * @version 1.0
 */
public interface PatientUserService {

	/**
	 * Get a list of all Patient Users.
	 *
	 * @return A list of Patient Users.
	 */
	List<PatientUser> getAllUsers();

	/**
	 * Get a Patient User by their ID.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The retrieved Patient User.
	 * @throws RecordNotFoundException If the user with the given ID is not found.
	 */
	PatientUser getUserById(Long userId) throws RecordNotFoundException;

	/**
	 * Create a new Patient User.
	 *
	 * @param user The Patient User object to be created.
	 * @return The created Patient User.
	 * @throws DatabaseException If there is an issue with the database while
	 *                           creating the user.
	 */
	PatientUser createUser(PatientUser user) throws DatabaseException;

	/**
	 * Update an existing Patient User.
	 *
	 * @param userId The ID of the user to be updated.
	 * @param user   The updated Patient User object.
	 * @return The updated Patient User.
	 * @throws RecordNotFoundException If the user with the given ID is not found.
	 */
	PatientUser updateUser(Long userId, PatientUser user) throws RecordNotFoundException;

	/**
	 * Delete a Patient User by their ID.
	 *
	 * @param userId The ID of the user to be deleted.
	 * @throws RecordNotFoundException If the user with the given ID is not found.
	 */
	void deleteUser(Long userId) throws RecordNotFoundException;

	/**
	 * Get a Patient User by their email ID and mobile number.
	 *
	 * @param emailId      The email ID of the user.
	 * @param mobileNumber The mobile number of the user.
	 * @return The retrieved Patient User or null if not found.
	 */
	PatientUser getUserBymobileNumberandEmailId(String emailId, String mobileNumber);
}
