package com.onehealth.patientmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onehealth.patientmanagement.entity.PatientProfile;

@Repository
/**
 * The PatientProfileRepository interface extends JpaRepository to provide the
 * necessary methods for performing CRUD (Create, Read, Update, Delete)
 * operations on the PatientProfile entity. It automatically generates the
 * implementation for the repository, allowing interactions with the database
 * and the PatientProfile table.
 */
public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {

	/**
	 * Retrieves a list of PatientProfiles based on the given userId.
	 *
	 * @param userId The ID of the user associated with the patient profiles.
	 * @return A list of PatientProfiles matching the provided userId.
	 */
	List<PatientProfile> findByUserId(long userId);

	void deleteAllProfileByUserId(long userId);

	boolean existsByUserId(long userId);

	// Method to retrieve a patient profile by userId, firstName, and lastName
	// (case-insensitive)
	PatientProfile findByUserIdAndFirstNameIgnoreCaseAndLastNameIgnoreCase(long userId, String firstName,
			String lastName);
}
