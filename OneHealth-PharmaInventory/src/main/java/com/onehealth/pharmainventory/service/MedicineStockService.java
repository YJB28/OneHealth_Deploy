package com.onehealth.pharmainventory.service;

import java.util.List;
import java.util.Optional;

import com.onehealth.pharmainventory.entity.MedicineStock;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;

public interface MedicineStockService {

	// Get a medicine inventory by its ID
	MedicineStock getInventoryById(Long medicineStockId) throws ProfileNotFoundException;

	// Create a new medicine inventory
	void createInventory(MedicineStock inventory);

	// Update an existing medicine inventory by ID
	void updateInventory(Long medicineStockId, MedicineStock inventory) throws ProfileNotFoundException;

	// Delete a medicine inventory by its ID
	void deleteInventory(Long medicineStockId) throws ProfileNotFoundException;

	// Get a list of all medicine inventories
	List<MedicineStock> getAllInventories();

	// Get medicine stock by medicine ID and pharma ID
	List<MedicineStock> getMedicineStockByMedicineAndPharmaId(Long medicineId, Long pharmaId);

	// Get medicine stock by medicine ID
	MedicineStock getMedicineStockByMedicineId(Long medicineId);

	// Get medicine stock by pharma ID
	List<MedicineStock> getMedicineStockByPharmaId(Long pharmaId);

	// Check if a medicine inventory exists by its medicine ID
	boolean existsByMedicineId(Long medicineId);

	// Check if a medicine inventory exists in the MedicineService by its medicine
	// ID
	boolean existsByMedicineIdInMedicineSerice(Long medicineId);
}
