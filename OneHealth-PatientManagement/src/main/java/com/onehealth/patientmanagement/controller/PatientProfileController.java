package com.onehealth.patientmanagement.controller;

import com.onehealth.patientmanagement.entity.PatientProfile;
import com.onehealth.patientmanagement.exception.ProfileNotFoundException;
import com.onehealth.patientmanagement.service.PatientProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/patientProfile")
/**
 * The PatientProfileController class handles HTTP requests related to patient profiles.
 */
public class PatientProfileController {

    private static final Logger logger = LoggerFactory.getLogger(PatientProfileController.class);

    @Autowired
    private PatientProfileService patientProfileService;

    /**
     * Handles a GET request to the root endpoint.
     * Returns a simple greeting message.
     *
     * @return A greeting message.
     */
    @GetMapping
    public String Hello() {
        logger.info("Received a GET request to root endpoint");
        return "Hello From Patient";
    }

    /**
     * Handles a GET request to retrieve a specific patient profile by its ID.
     *
     * @param patientId The ID of the patient profile to retrieve.
     * @return The retrieved patient profile along with an appropriate HTTP status.
     * @throws ProfileNotFoundException If the specified patient profile is not found.
     */
    @GetMapping("/{patientId}")
    public ResponseEntity<PatientProfile> getPatientProfileById(@PathVariable long patientId) throws ProfileNotFoundException {
        logger.info("Received a GET request to retrieve patient profile by ID: {}", patientId);

        PatientProfile patientProfile = patientProfileService.getPatientProfileById(patientId);
        logger.info("Retrieved patient profile: {}", patientProfile);

        return new ResponseEntity<>(patientProfile, HttpStatus.OK);
    }

    /**
     * Handles a GET request to retrieve all patient profiles associated with a user ID.
     *
     * @param userId The ID of the user for whom patient profiles need to be retrieved.
     * @return A list of patient profiles along with an appropriate HTTP status.
     * @throws ProfileNotFoundException If no patient profiles are found for the given user ID.
     */
    @GetMapping("/allProfile/{userId}")
    public ResponseEntity<List<PatientProfile>> getAllPatientsByUserId(@PathVariable long userId) throws ProfileNotFoundException {
        logger.info("Received a GET request to retrieve all patient profiles for user ID: {}", userId);

        List<PatientProfile> patientProfiles = patientProfileService.getAllPatientsByUserId(userId);
        logger.info("Retrieved patient profiles: {}", patientProfiles);

        return new ResponseEntity<>(patientProfiles, HttpStatus.OK);
    }

    /**
     * Handles a POST request to create a new patient profile.
     *
     * @param patient The patient profile details to be created.
     * @return The created patient profile along with an appropriate HTTP status.
     */
    @PostMapping("/add")
    public ResponseEntity<String> createPatientProfile(@RequestBody PatientProfile patient) {
        logger.info("Received a POST request to create a new patient profile");

        patientProfileService.createPatientProfile(patient);

        return new ResponseEntity<>("Created Patient Profile", HttpStatus.CREATED);
    }

    /**
     * Handles a PUT request to update an existing patient profile.
     *
     * @param patientId The ID of the patient profile to be updated.
     * @param patient   The updated patient profile details.
     * @return The updated patient profile along with an appropriate HTTP status.
     * @throws ProfileNotFoundException If the specified patient profile is not found.
     */
    @PutMapping("/{patientId}")
    public ResponseEntity<PatientProfile> updatePatientProfile(@PathVariable long patientId,
                                                               @RequestBody PatientProfile patient) throws ProfileNotFoundException {
        logger.info("Received a PUT request to update patient profile with ID: {}", patientId);

        PatientProfile updatedPatientProfile = patientProfileService.updatePatientProfile(patientId, patient);
        logger.info("Updated patient profile: {}", updatedPatientProfile);

        return new ResponseEntity<>(updatedPatientProfile, HttpStatus.OK);
    }

    /**
     * Handles a DELETE request to delete a specific patient profile by its ID.
     *
     * @param patientId The ID of the patient profile to be deleted.
     * @return A success message along with an appropriate HTTP status.
     * @throws ProfileNotFoundException If the specified patient profile is not found.
     */
    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> deletePatientProfile(@PathVariable long patientId) throws ProfileNotFoundException {
        logger.info("Received a DELETE request to delete patient profile with ID: {}", patientId);

        patientProfileService.deletePatientProfile(patientId);

        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
