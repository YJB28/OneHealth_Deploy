package com.onehealth.lifestyleandhistory.service;

import com.onehealth.lifestyleandhistory.entity.LifeStyle;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;

import java.util.List;

/**
 * The LifeStyleService interface defines operations for managing lifestyle-related data.
 * This interface provides methods for retrieving, creating, updating, and deleting lifestyle records.
 * Author: Yogesh
 */
public interface LifeStyleService {

    /**
     * Retrieves a list of all LifeStyle records.
     *
     * @return List of LifeStyle records.
     */
    List<LifeStyle> getAllLifeStyles();

    /**
     * Retrieves a specific LifeStyle record by its patient ID.
     *
     * @param patientId The unique ID of the patient associated with the LifeStyle record.
     * @return The retrieved LifeStyle record.
     * @throws RecordNotFoundException If the record is not found.
     */
    LifeStyle getLifestyleByPatientId(Long patientId) throws RecordNotFoundException;

    /**
     * Creates a new LifeStyle record.
     *
     * @param lifestyle The LifeStyle object to be created.
     * @return The created LifeStyle record.
     */
    LifeStyle createLifestyle(LifeStyle lifestyle);

    /**
     * Updates an existing LifeStyle record.
     *
     * @param patientId The unique ID of the patient associated with the LifeStyle record.
     * @param lifestyle The updated LifeStyle object.
     * @return The updated LifeStyle record.
     * @throws RecordNotFoundException If the record is not found.
     */
    LifeStyle updateLifestyleByPatientId(Long patientId, LifeStyle lifestyle) throws RecordNotFoundException;

    /**
     * Deletes a LifeStyle record by its patient ID.
     *
     * @param patientId The unique ID of the patient associated with the LifeStyle record.
     * @throws RecordNotFoundException If the record is not found.
     */
    void deleteLifestyleByPatientId(Long patientId) throws RecordNotFoundException;
}
