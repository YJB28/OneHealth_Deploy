package com.onehealth.patientuser.repository;

import com.onehealth.patientuser.entity.PatientUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing Patient Users in the
 * database.
 *
 * This interface extends JpaRepository, which provides basic CRUD operations on
 * Patient Users. It allows the service layer to interact with the database for
 * creating, reading, updating, and deleting users.
 * 
 * Note: Make sure to import the required entities from the appropriate package.
 * 
 * @author Yogesh Baiskar
 * @version 1.0
 */
@Repository
public interface PatientUserRepository extends JpaRepository<PatientUser, Long> {

	/**
	 * Find a Patient User by their email ID and mobile number.
	 *
	 * @param emailId      The email ID of the user to search for.
	 * @param mobileNumber The mobile number of the user to search for.
	 * @return The Patient User with the given email ID and mobile number, or null
	 *         if not found.
	 */
	PatientUser findByEmailIdAndMobileNumber(String emailId, String mobileNumber);
}