package com.onehealth.pharmainventory.controller;

import com.onehealth.pharmainventory.entity.MedicineDetails;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.service.MedicineDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/InventoryManagement")
public class MedicineDetailsController {

	@Autowired
	private MedicineDetailsService medicineDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(MedicineDetailsController.class);

	/**
	 * Get all medicine details.
	 *
	 * @return List of all medicine details.
	 */
	@GetMapping("/medicineDetails/all")
	public ResponseEntity<List<MedicineDetails>> getAllMedicineDetails() {
		logger.info("Fetching all medicine details.");
		List<MedicineDetails> medicineDetails = medicineDetailsService.getAllMedicinesDetails();
		return new ResponseEntity<>(medicineDetails, HttpStatus.OK);
	}

	/**
	 * Create a new medicine details.
	 *
	 * @param medicineDetails The medicine details to be created.
	 * @return A success message with HTTP status 201 (Created).
	 */
	@PostMapping("/medicineDetails")
	public ResponseEntity<String> createMedicineDetails(@RequestBody MedicineDetails medicineDetails) {
		logger.info("Received a POST request to create new medicine details.");

		// Check if MedicineDetails for the given Medicine ID already exist
		Long medicineId = medicineDetails.getMedicine().getMedicineId();
		boolean detailsExist = medicineDetailsService.existsByMedicineId(medicineId);

		if (detailsExist) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Medicine Details already exist for Medicine ID: " + medicineId);
		}

		// If MedicineDetails don't exist, proceed with creation
		medicineDetailsService.createMedicineDetails(medicineDetails);

		return new ResponseEntity<>("Medicine Details Created Successfully", HttpStatus.CREATED);
	}

	/**
	 * Get medicine details by its ID.
	 *
	 * @param medicineDetailsId The ID of the medicine details to retrieve.
	 * @return The requested medicine details or an error message with HTTP status
	 *         404 (Not Found).
	 * @throws ProfileNotFoundException If the medicine details are not found.
	 */
	@GetMapping("/medicineDetailsById/{medicineDetailsId}")
	public ResponseEntity<MedicineDetails> getMedicineDetailsById(@PathVariable Long medicineDetailsId)
			throws ProfileNotFoundException {
		logger.info("Fetching medicine details by ID: {}", medicineDetailsId);
		MedicineDetails medicineDetails = medicineDetailsService.getMedicineDetailsById(medicineDetailsId);
		return new ResponseEntity<>(medicineDetails, HttpStatus.OK);
	}

	/**
	 * Get medicine details by its associated medicine's ID.
	 *
	 * @param medicineId The ID of the associated medicine.
	 * @return The medicine details associated with the given medicine ID or an
	 *         error message with HTTP status 404 (Not Found).
	 */
	@GetMapping("/medicineDetailsByMedicineId/{medicineId}")
	public ResponseEntity<MedicineDetails> getMedicineDetailsByMedicineId(@PathVariable Long medicineId) throws ProfileNotFoundException{
		logger.info("Fetching medicine details by Medicine ID: {}", medicineId);
		MedicineDetails medicineDetails = medicineDetailsService.getAllMedicineDetailsByMedicineId(medicineId);
		return new ResponseEntity<>(medicineDetails, HttpStatus.OK);
	}

	/**
	 * Update medicine details by its ID.
	 *
	 * @param medicineDetailsId The ID of the medicine details to update.
	 * @param medicineDetails   The updated medicine details.
	 * @return A success message with HTTP status 200 (OK).
	 * @throws ProfileNotFoundException If the medicine details are not found.
	 */
	@PutMapping("/medicineDetails/{medicineDetailsId}")
	public ResponseEntity<String> updateMedicineDetails(@PathVariable Long medicineDetailsId,
			@RequestBody MedicineDetails medicineDetails) throws ProfileNotFoundException {
		logger.info("Updating medicine details by ID: {}", medicineDetailsId);
		medicineDetailsService.updateMedicineDetails(medicineDetailsId, medicineDetails);
		return new ResponseEntity<>("Medicine Details Updated Successfully", HttpStatus.OK);
	}

	/**
	 * Delete medicine details by its ID.
	 *
	 * @param medicineDetailsId The ID of the medicine details to delete.
	 * @return A success message with HTTP status 200 (OK).
	 * @throws ProfileNotFoundException If the medicine details are not found.
	 */
	@DeleteMapping("/medicineDetails/{medicineDetailsId}")
	public ResponseEntity<String> deleteMedicineDetails(@PathVariable Long medicineDetailsId)
			throws ProfileNotFoundException {
		logger.info("Deleting medicine details by ID: {}", medicineDetailsId);
		medicineDetailsService.deleteMedicineDetails(medicineDetailsId);
		return new ResponseEntity<>("Medicine Details Deleted Successfully", HttpStatus.OK);
	}

	/**
	 * Get all medicine details.
	 *
	 * @return List of all medicine details.
	 */
	@GetMapping("/allMedicineDetails/byPharmaId/{pharmaId}")
	public ResponseEntity<List<MedicineDetails>> getAllMedicineDetailsByPharmaId(@PathVariable Long pharmaId) {
		logger.info("Fetching all medicine details By Pharma Id.");
		List<MedicineDetails> medicineDetails = medicineDetailsService.getAllMedicinesDetailsByPharmaId(pharmaId);
		return new ResponseEntity<>(medicineDetails, HttpStatus.OK);
	}

	/**
	 * Exception handler for ProfileNotFoundException.
	 *
	 * @param ex The exception that was thrown.
	 * @return Error message along with HTTP status 404 (Not Found).
	 */
	@ExceptionHandler(ProfileNotFoundException.class)
	public ResponseEntity<String> handleProfileNotFoundException(ProfileNotFoundException ex) {
		logger.error("ProfileNotFoundException: {}", ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
