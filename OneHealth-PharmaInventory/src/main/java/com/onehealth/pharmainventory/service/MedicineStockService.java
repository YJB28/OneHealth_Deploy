package com.onehealth.pharmainventory.service;

import java.util.List;
import java.util.Optional;

import com.onehealth.pharmainventory.entity.MedicineStock;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;

public interface MedicineStockService {

	MedicineStock getInventoryById(Long medicineStockId) throws ProfileNotFoundException;

	void createInventory(MedicineStock inventory);

	void updateInventory(Long medicineStockId, MedicineStock inventory) throws ProfileNotFoundException;

	void deleteInventory(Long medicineStockId) throws ProfileNotFoundException;

	List<MedicineStock> getAllInventories();

	List<MedicineStock> getMedicineStockByMedicineAndPharmaId(Long medicineId, Long pharmaId);

	MedicineStock getMedicineStockByMedicineId(Long medicineId);

	List<MedicineStock> getMedicineStockByPharmaId(Long pharmaId);

	boolean existsByMedicineId(Long medicineId);

	boolean existsByMedicineIdInMedicineSerice(Long medicineId);
}