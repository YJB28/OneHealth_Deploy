package com.onehealth.pharmainventory.serviceimpl;

import com.onehealth.pharmainventory.entity.MedicineDetails;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.repository.MedicineDetailsRepository;
import com.onehealth.pharmainventory.repository.MedicineRepository;
import com.onehealth.pharmainventory.service.MedicineDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineDetailsServiceImpl implements MedicineDetailsService {

	@Autowired
	private MedicineDetailsRepository medicineDetailsRepository;

	@Autowired
	private MedicineRepository medicineRepository;

	private static final Logger logger = LoggerFactory.getLogger(MedicineDetailsServiceImpl.class);

	/**
	 * Retrieves medicine details by its ID.
	 *
	 * @param medicineDetailsId The ID of the medicine details to retrieve.
	 * @return The medicine details.
	 * @throws ProfileNotFoundException If medicine details with the given ID are
	 *                                  not found.
	 */
	@Override
	public MedicineDetails getMedicineDetailsById(Long medicineDetailsId) throws ProfileNotFoundException {
		Optional<MedicineDetails> optionalMedicineDetails = medicineDetailsRepository.findById(medicineDetailsId);
		if (optionalMedicineDetails.isEmpty()) {
			throw new ProfileNotFoundException("Medicine details not found with ID: " + medicineDetailsId);
		}
		return optionalMedicineDetails.get();
	}

	/**
	 * Creates medicine details.
	 *
	 * @param medicineDetails The medicine details to create.
	 * @throws ProfileNotFoundException If the associated medicine doesn't exist.
	 */
	@Override
	public void createMedicineDetails(MedicineDetails medicineDetails) {
		Long medicineId = medicineDetails.getMedicine().getMedicineId();

		// Check if Medicine with the specified medicineId exists
		boolean medicineExists = medicineRepository.existsById(medicineId);

		if (!medicineExists) {
			throw new ProfileNotFoundException("Medicine not found with this Medicine ID: " + medicineId);
		}

		medicineDetailsRepository.save(medicineDetails);
	}

	/**
	 * Updates medicine details by its ID.
	 *
	 * @param medicineDetailsId The ID of the medicine details to update.
	 * @param medicineDetails   The updated medicine details.
	 * @throws ProfileNotFoundException If medicine details with the given ID are
	 *                                  not found.
	 */
	@Override
	public void updateMedicineDetails(Long medicineDetailsId, MedicineDetails medicineDetails)
			throws ProfileNotFoundException {
		if (!medicineDetailsRepository.existsById(medicineDetailsId)) {
			throw new ProfileNotFoundException("Medicine details not found with ID: " + medicineDetailsId);
		}
		medicineDetails.setMedicineDetailsId(medicineDetailsId);
		medicineDetails.setMedicine(medicineDetails.getMedicine());
		medicineDetails.setHighlights(medicineDetails.getHighlights());
		medicineDetails.setDescription(medicineDetails.getDescription());
		medicineDetails.setIndications(medicineDetails.getIndications());
		medicineDetails.setKeyComponents(medicineDetails.getKeyComponents());
		medicineDetails.setDirectionForUse(medicineDetails.getDirectionForUse());
		medicineDetails.setStorage(medicineDetails.getStorage());
		medicineDetails.setPrecautions(medicineDetails.getPrecautions());

		medicineDetailsRepository.save(medicineDetails);
	}

	/**
	 * Deletes medicine details by its ID.
	 *
	 * @param medicineDetailsId The ID of the medicine details to delete.
	 * @throws ProfileNotFoundException If medicine details with the given ID are
	 *                                  not found.
	 */
	@Override
	public void deleteMedicineDetails(Long medicineDetailsId) throws ProfileNotFoundException {
		logger.info("Deleting medicine details by ID: {}", medicineDetailsId);
		if (!medicineDetailsRepository.existsById(medicineDetailsId)) {
			throw new ProfileNotFoundException("Medicine details not found with ID: " + medicineDetailsId);
		}
		medicineDetailsRepository.deleteById(medicineDetailsId);
		logger.info("Medicine details deleted successfully.");
	}

	/**
	 * Retrieves medicine details by its associated medicine's ID.
	 *
	 * @param medicineId The ID of the associated medicine.
	 * @return The medicine details.
	 * @throws ProfileNotFoundException If medicine details for the given medicine
	 *                                  ID are not found.
	 */
	@Override
	public MedicineDetails getAllMedicineDetailsByMedicineId(Long medicineId) throws ProfileNotFoundException {
		Optional<MedicineDetails> optionalMedicineDetails = medicineDetailsRepository
				.findAllByMedicine_MedicineId(medicineId);
		if (optionalMedicineDetails.isEmpty()) {
			throw new ProfileNotFoundException("Medicine details not found with ID: " + medicineId);
		}
		return optionalMedicineDetails.get();
	}

	/**
	 * Retrieves all medicine details.
	 *
	 * @return List of medicine details.
	 */
	@Override
	public List<MedicineDetails> getAllMedicinesDetails() {
		return medicineDetailsRepository.findAll();
	}

	/**
	 * Retrieves all medicine details associated with a pharmacy.
	 *
	 * @param pharmaId The ID of the pharmacy.
	 * @return List of medicine details.
	 */
	@Override
	public List<MedicineDetails> getAllMedicinesDetailsByPharmaId(Long pharmaId) {
		return medicineDetailsRepository.findAllDetailsByMedicinePharmaId(pharmaId);
	}

	/**
	 * Checks if medicine details exist for a given medicine ID.
	 *
	 * @param medicineId The ID of the medicine.
	 * @return True if medicine details exist, false otherwise.
	 */
	@Override
	public boolean existsByMedicineId(Long medicineId) {
		return medicineDetailsRepository.existsByMedicine_MedicineId(medicineId);
	}
}
