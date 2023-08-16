package com.onehealth.pharmainventory.service;

import com.onehealth.pharmainventory.entity.Medicine;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;

import java.util.List;

public interface MedicineService {
	
    List<Medicine> getAllMedicines();
    Medicine getMedicineById(Integer medicineId) throws ProfileNotFoundException;
    void createMedicine(Medicine medicine);
    void updateMedicine(Integer medicineId, Medicine medicine) throws ProfileNotFoundException;
    void deleteMedicine(Integer medicineId) throws ProfileNotFoundException;
	List<Medicine> getMedicinesByMedicineName(String medicineName);
	List<Medicine> getMedicinesByCategoryId(Integer categoryId);
}
