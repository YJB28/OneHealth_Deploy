package com.onehealth.pharmainventory.service;

import java.util.List;

import com.onehealth.pharmainventory.entity.MedicineDetails;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public interface MedicineDetailsService {

	/**
	 * Get medicine details by ID.
	 *
	 * @param medicineDetailsId The ID of the medicine details to retrieve.
	 * @return The medicine details with the specified ID.
	 * @throws ProfileNotFoundException If medicine details are not found with the
	 *                                  given ID.
	 */
	MedicineDetails getMedicineDetailsById(Long medicineDetailsId) throws ProfileNotFoundException;

	/**
	 * Create medicine details.
	 *
	 * @param medicineDetails The medicine details to create.
	 */
	void createMedicineDetails(MedicineDetails medicineDetails);

	/**
	 * Update medicine details by ID.
	 *
	 * @param medicineDetailsId The ID of the medicine details to update.
	 * @param medicineDetails   The updated medicine details.
	 * @throws ProfileNotFoundException If medicine details are not found with the
	 *                                  given ID.
	 */
	void updateMedicineDetails(Long medicineDetailsId, MedicineDetails medicineDetails) throws ProfileNotFoundException;

	/**
	 * Delete medicine details by ID.
	 *
	 * @param medicineDetailsId The ID of the medicine details to delete.
	 * @throws ProfileNotFoundException If medicine details are not found with the
	 *                                  given ID.
	 */
	void deleteMedicineDetails(Long medicineDetailsId) throws ProfileNotFoundException;

	/**
	 * Get all medicine details for a specific medicine.
	 *
	 * @param medicineId The ID of the medicine.
	 * @return The medicine details for the specified medicine.
	 */
	MedicineDetails getAllMedicineDetailsByMedicineId(Long medicineId) throws ProfileNotFoundException;

	/**
	 * Get a list of all medicine details.
	 *
	 * @return A list of all medicine details.
	 */
	List<MedicineDetails> getAllMedicinesDetails();

	/**
	 * Get all medicine details for a specific pharmacy.
	 *
	 * @param pharmaId The ID of the pharmacy.
	 * @return A list of medicine details for the specified pharmacy.
	 */
	List<MedicineDetails> getAllMedicinesDetailsByPharmaId(Long pharmaId);

	/**
	 * Check if medicine details exist for a specific medicine.
	 *
	 * @param medicineId The ID of the medicine.
	 * @return {@code true} if medicine details exist, {@code false} otherwise.
	 */
	boolean existsByMedicineId(Long medicineId);
}

// Add a logger to your service implementation class for logging
