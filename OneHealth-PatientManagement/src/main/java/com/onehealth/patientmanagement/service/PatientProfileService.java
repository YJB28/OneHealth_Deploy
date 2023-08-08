package com.onehealth.patientmanagement.service;

import java.util.List;

import com.onehealth.patientmanagement.entity.PatientProfile;
import com.onehealth.patientmanagement.exception.ProfileNotFoundException;

public interface PatientProfileService {

    /**
     * Retrieves a list of PatientProfiles based on the given userId.
     *
     * @param userId The ID of the user for whom patient profiles need to be retrieved.
     * @return A list of PatientProfiles associated with the provided userId.
     * @throws ProfileNotFoundException If no patient profiles are found for the given userId.
     */
    List<PatientProfile> getAllPatientsByUserId(long userId) throws ProfileNotFoundException;

    /**
     * Retrieves a specific PatientProfile based on the provided patientId.
     *
     * @param patientId The ID of the patient profile to be retrieved.
     * @return The PatientProfile associated with the provided patientId.
     * @throws ProfileNotFoundException If the specified patient profile is not found.
     */
    PatientProfile getPatientProfileById(long patientId) throws ProfileNotFoundException;

    /**
     * Creates a new PatientProfile.
     *
     * @param patient The PatientProfile object containing the details of the new patient.
     * @return The newly created PatientProfile.
     */
    PatientProfile createPatientProfile(PatientProfile patient);

    /**
     * Updates an existing PatientProfile based on the provided patientId.
     *
     * @param patientId The ID of the patient profile to be updated.
     * @param patient   The updated PatientProfile object.
     * @return The updated PatientProfile.
     * @throws ProfileNotFoundException If the specified patient profile is not found.
     */
    PatientProfile updatePatientProfile(long patientId, PatientProfile patient) throws ProfileNotFoundException;

    /**
     * Deletes a specific PatientProfile based on the provided patientId.
     *
     * @param patientId The ID of the patient profile to be deleted.
     * @throws ProfileNotFoundException If the specified patient profile is not found.
     */
    void deletePatientProfile(long patientId) throws ProfileNotFoundException;
}
