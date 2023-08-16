package com.onehealth.pharmainventory.service;

import java.util.List;


import com.onehealth.pharmainventory.entity.MedicineStock;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;

public interface MedicineStockService {
	
	
	MedicineStock getInventoryById(Integer medicineStockId) throws ProfileNotFoundException;
    void createInventory(MedicineStock inventory);
    void updateInventory(Integer medicineStockId, MedicineStock inventory) throws ProfileNotFoundException;
    void deleteInventory(Integer medicineStockId) throws ProfileNotFoundException;
	List<MedicineStock> getAllInventories();
}
