package com.onehealth.patientmanagement.serviceImpl;

import java.util.List;

import org.apache.hc.core5.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.onehealth.patientmanagement.entity.PatientProfile;
import com.onehealth.patientmanagement.exception.ProfileNotFoundException;
import com.onehealth.patientmanagement.repository.PatientProfileRepository;
import com.onehealth.patientmanagement.service.PatientProfileService;

import jakarta.transaction.Transactional;

@Service
/**
 * Implementation of the PatientProfileService interface. This class provides
 * methods for interacting with patient profiles in the database.
 */
public class PatientProfileServiceImpl implements PatientProfileService {

	private static final Logger logger = LoggerFactory.getLogger(PatientProfileServiceImpl.class);

	@Autowired
	private PatientProfileRepository patientProfileRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<PatientProfile> getAllPatientsByUserId(long userId) throws ProfileNotFoundException {
		logger.info("Fetching all patient profiles for user ID: {}", userId);

		List<PatientProfile> patientProfiles = patientProfileRepository.findByUserId(userId);

		if (patientProfiles.isEmpty()) {
			logger.warn("No patient profiles found for user ID: {}", userId);
			throw new ProfileNotFoundException("No profiles found for user ID: " + userId);
		}

		return patientProfiles;
	}

	@Override
	public PatientProfile getPatientProfileById(long patientId) throws ProfileNotFoundException {
		logger.info("Fetching patient profile by ID: {}", patientId);

		PatientProfile patientProfile = patientProfileRepository.findById(patientId).orElseThrow(() -> {
			logger.error("Profile not found for ID: {}", patientId);
			return new ProfileNotFoundException("Profile Does Not Exist for this patientId " + patientId);
		});

		return patientProfile;
	}

	@Override
	public PatientProfile createPatientProfile(PatientProfile patient) throws ProfileNotFoundException {
		logger.info("Creating new patient profile");

		try {
			// Check if the user with the specified userId exists
			ResponseEntity<String> userResponse = restTemplate.getForEntity(
					"https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/patientUser/{userId}",
					String.class, patient.getUserId());
			System.out.println(userResponse.getStatusCode().is2xxSuccessful());
			if (userResponse.getStatusCode().is2xxSuccessful()) {
				// User exists, proceed with saving the patient profile
				return patientProfileRepository.save(patient);
			} else if (userResponse.getStatusCode().is4xxClientError()) {
				// User does not exist, throw a ProfileNotFoundException
				System.out.println("inside...");
				throw new ProfileNotFoundException(
						"User with ID " + patient.getUserId() + " does not exist in the Patient User MicroService.");
			} else {
				// Handle other HTTP status codes as needed
				// For example, you can throw a different exception or return an error message
				throw new ProfileNotFoundException("Unexpected error occurred");
			}
		} catch (Exception ex) {
			// Handle the HttpClientErrorException, e.g., log the error or rethrow it
			throw new ProfileNotFoundException("Error while checking user existence (User Not exist) "+patient.getUserId());
		}
	}

	@Override
	public PatientProfile updatePatientProfile(long patientId, PatientProfile updatedPatientProfile)
			throws ProfileNotFoundException {
		logger.info("Updating patient profile with ID: {}", patientId);

		PatientProfile patientProfile = patientProfileRepository.findById(patientId).orElseThrow(() -> {
			logger.error("Profile not found for ID: {}", patientId);
			return new ProfileNotFoundException("Profile Does Not Exist");
		});

		// Update all fields of the patientProfile with the values from
		// updatedPatientProfile
		patientProfile.setFirstName(updatedPatientProfile.getFirstName());
		patientProfile.setLastName(updatedPatientProfile.getLastName());
		patientProfile.setMobileNumber(updatedPatientProfile.getMobileNumber());
		patientProfile.setAddress(updatedPatientProfile.getAddress());
		patientProfile.setPinCode(updatedPatientProfile.getPinCode());
		patientProfile.setCountry(updatedPatientProfile.getCountry());
		patientProfile.setCity(updatedPatientProfile.getCity());
		patientProfile.setGender(updatedPatientProfile.getGender());
		patientProfile.setAge(updatedPatientProfile.getAge());
		patientProfile.setDob(updatedPatientProfile.getDob());
		patientProfile.setBloodGroup(updatedPatientProfile.getBloodGroup());
		patientProfile.setHeight(updatedPatientProfile.getHeight());
		patientProfile.setWeight(updatedPatientProfile.getWeight());
		patientProfile.setMaritalStatus(updatedPatientProfile.getMaritalStatus());
		patientProfile.setEmailId(updatedPatientProfile.getEmailId());

		// Save the updated entity back to the repository
		patientProfile = patientProfileRepository.save(patientProfile);

		return patientProfile;
	}

	@Override
	public void deletePatientProfile(long patientId) throws ProfileNotFoundException {
		logger.info("Deleting patient profile with ID: {}", patientId);

		if (!patientProfileRepository.existsById(patientId)) {
			logger.error("Profile not found for ID: {}", patientId);
			throw new ProfileNotFoundException("Profile Does Not Exist: " + patientId);
		}

		patientProfileRepository.deleteById(patientId);
	}

	@Override
	public List<PatientProfile> getAllPatientsProfile() {
		// TODO Auto-generated method stub

		return patientProfileRepository.findAll();
	}

	@Transactional
	@Override
	public void deleteAllPatientProfileByUserId(long userId) throws ProfileNotFoundException {
		logger.info("Deleting All profiles with userId: {}", userId);

		// Check if any profiles exist for the given userId
		if (!patientProfileRepository.existsByUserId(userId)) {
			logger.error("Profiles not found for userID: {}", userId);
			throw new ProfileNotFoundException("Profiles Do Not Exist for User ID: " + userId);
		}

		// Assuming that patientProfileRepository has a method named
		// deleteAllProfileByUserId
		// which deletes all profiles with the given userId
		patientProfileRepository.deleteAllProfileByUserId(userId);
	}

	@Override
	public PatientProfile getPatientByUserIdAndNameIgnoreCase(long userId, String firstName, String lastName) {
		// TODO Auto-generated method stub
		return patientProfileRepository.findByUserIdAndFirstNameIgnoreCaseAndLastNameIgnoreCase(userId, firstName,
				lastName);
	}

}
