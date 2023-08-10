package com.onehealth.lifestyleandhistory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onehealth.lifestyleandhistory.DTO.ResponseMessage;
import com.onehealth.lifestyleandhistory.entity.MedicalHistory;
import com.onehealth.lifestyleandhistory.exception.DatabaseException;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;
import com.onehealth.lifestyleandhistory.service.MedicalHistoryService;

import java.util.List;

@RestController
@RequestMapping("/lifeStyleAndHistory/MedicalHistory")
/**
 * The MedicalHistoryController class handles HTTP requests related to medical history information.
 */
public class MedicalHistoryController {

    private static final Logger logger = LoggerFactory.getLogger(MedicalHistoryController.class);

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    /**
     * Handles a GET request to the root endpoint.
     * Returns a simple greeting message.
     *
     * @return A greeting message.
     */
    @GetMapping
    public String Hello() {
        logger.info("Received a GET request to root endpoint");
        return "Hello From /lifeStyleAndHistory/MedicalHistory ";
    }

    /**
     * Handles a GET request to retrieve all medical histories.
     *
     * @return A list of all medical histories along with an appropriate HTTP status.
     */
    @GetMapping("/all")
    public ResponseEntity<List<MedicalHistory>> getAllMedicalHistories() {
        logger.info("Received a GET request to retrieve all medical histories");

        List<MedicalHistory> medicalHistories = medicalHistoryService.getAllMedicalHistories();
        logger.info("Retrieved {} medical histories", medicalHistories.size());

        return new ResponseEntity<>(medicalHistories, HttpStatus.OK);
    }

    /**
     * Handles a GET request to retrieve a specific medical history by its patient ID.
     *
     * @param patientId The patient ID of the medical history to retrieve.
     * @return The retrieved medical history along with an appropriate HTTP status.
     * @throws RecordNotFoundException If the specified medical history is not found.
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<MedicalHistory> getMedicalHistoryByPatientId(@PathVariable Long patientId)
            throws RecordNotFoundException {
        logger.info("Received a GET request to retrieve medical history by patientId: {}", patientId);

        MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistoryByPatientId(patientId);
        logger.info("Retrieved medical history: {}", medicalHistory);

        return new ResponseEntity<>(medicalHistory, HttpStatus.OK);
    }

    /**
     * Handles a POST request to create a new medical history.
     *
     * @param medicalHistory The medical history details to be created.
     * @return A response message along with an appropriate HTTP status.
     * @throws DatabaseException If there is an issue with the database operation.
     */
    @PostMapping
    public ResponseEntity<ResponseMessage<MedicalHistory>> createMedicalHistory(@RequestBody MedicalHistory medicalHistory)
            throws DatabaseException {
        logger.info("Received a POST request to create a new medical history");

        MedicalHistory createdMedicalHistory = medicalHistoryService.createMedicalHistory(medicalHistory);
        ResponseMessage<MedicalHistory> responseMessage = new ResponseMessage<>("History Added Successfully",
                createdMedicalHistory);

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    /**
     * Handles a PUT request to update an existing medical history.
     *
     * @param patientId      The patient ID of the medical history to update.
     * @param medicalHistory The updated medical history details.
     * @return A response message along with an appropriate HTTP status.
     * @throws RecordNotFoundException If the specified medical history is not found.
     */
    @PutMapping("/patient/{patientId}")
    public ResponseEntity<ResponseMessage<MedicalHistory>> updateMedicalHistory(@PathVariable Long patientId,
            @RequestBody MedicalHistory medicalHistory) throws RecordNotFoundException {
        logger.info("Received a PUT request to update medical history with patientId: {}", patientId);

        MedicalHistory updatedMedicalHistory = medicalHistoryService.updateMedicalHistory(patientId, medicalHistory);
        ResponseMessage<MedicalHistory> responseMessage = new ResponseMessage<>("Update record successfully",
                updatedMedicalHistory);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /**
     * Handles a DELETE request to delete all medical histories associated with a specific patient ID and user ID.
     *
     * @param patientId The patient ID.
     * @param userId    The user ID.
     * @return A success message along with an appropriate HTTP status.
     * @throws RecordNotFoundException If no medical histories are found for the given patient ID and user ID.
     */
    @DeleteMapping("/patient/{patientId}/user/{userId}")
    public ResponseEntity<String> deleteMedicalHistoryByPatientIdAndUserId(@PathVariable Long patientId,
            @PathVariable Long userId) throws RecordNotFoundException {
        logger.info("Received a DELETE request to delete medical history with patientId and userId: {} {}", patientId, userId);

        medicalHistoryService.deleteMedicalHistoryByPatientIdAndUserId(patientId, userId);

        return new ResponseEntity<>("Delete All Record Successfully", HttpStatus.OK);
    }

    /**
     * Handles a DELETE request to delete a specific medical history by its patient ID.
     *
     * @param patientId The patient ID of the medical history to delete.
     * @return A success message along with an appropriate HTTP status.
     */
    @DeleteMapping("/patient/{patientId}")
    public ResponseEntity<String> deleteMedicalHistoryByPatientId(@PathVariable Long patientId) {
        logger.info("Received a DELETE request to delete medical history by patientId: {}", patientId);

        try {
            medicalHistoryService.deleteMedicalHistoryByPatientId(patientId);
            return new ResponseEntity<>("Medical History Deleted Successfully", HttpStatus.OK);
        } catch (RecordNotFoundException e) {
            logger.warn("Medical history not found with patientId: {}", patientId);
            return new ResponseEntity<>("Medical History not found with patientId: " + patientId, HttpStatus.NOT_FOUND);
        }
    }
}
