package com.onehealth.pharmainventory.service;

import com.onehealth.pharmainventory.entity.Medicine;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;

import java.util.List;

public interface MedicineService {

	/**
	 * Retrieves a list of all medicines.
	 *
	 * @return A list of all medicines.
	 */
	List<Medicine> getAllMedicines();

	/**
	 * Creates a new medicine.
	 *
	 * @param medicine The medicine to be created.
	 */
	void createMedicine(Medicine medicine);

	/**
	 * Updates an existing medicine.
	 *
	 * @param medicineId The ID of the medicine to be updated.
	 * @param medicine   The updated medicine object.
	 * @throws ProfileNotFoundException If the medicine with the specified ID is not
	 *                                  found.
	 */
	void updateMedicine(Long medicineId, Medicine medicine) throws ProfileNotFoundException;

	/**
	 * Deletes a medicine by its ID.
	 *
	 * @param medicineId The ID of the medicine to be deleted.
	 * @throws ProfileNotFoundException If the medicine with the specified ID is not
	 *                                  found.
	 */
	void deleteMedicine(Long medicineId) throws ProfileNotFoundException;

	/**
	 * Retrieves a list of medicines by their medicine name.
	 *
	 * @param medicineName The medicine name to search for.
	 * @return A list of medicines matching the specified medicine name.
	 */
	List<Medicine> getMedicinesByMedicineName(String medicineName);

	/**
	 * Retrieves a list of medicines by their category ID.
	 *
	 * @param categoryId The category ID to search for.
	 * @return A list of medicines in the specified category.
	 */
	List<Medicine> getMedicinesByCategoryId(Integer categoryId);

	/**
	 * Retrieves a medicine by its ID.
	 *
	 * @param medicineId The ID of the medicine to retrieve.
	 * @return The medicine with the specified ID.
	 * @throws ProfileNotFoundException If the medicine with the specified ID is not
	 *                                  found.
	 */
	Medicine getMedicineById(Long medicineId) throws ProfileNotFoundException;

	/**
	 * Retrieves a list of medicines by their pharmacy ID.
	 *
	 * @param pharmaId The pharmacy ID to search for.
	 * @return A list of medicines associated with the specified pharmacy.
	 */
	List<Medicine> getMedicinesByPharmaId(Long pharmaId);

	/**
	 * Checks if a medicine with the given pharmacy ID and medicine name
	 * (case-insensitive) exists.
	 *
	 * @param pharmaId     The pharmacy ID to check.
	 * @param medicineName The medicine name to check (case-insensitive).
	 * @return True if a matching medicine exists; false otherwise.
	 */
	boolean existsByPharmaIdAndMedicineNameIgnoreCase(Long pharmaId, String medicineName);
}
