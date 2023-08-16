package com.onehealth.pharmainventory.service;

import java.util.List;

import com.onehealth.pharmainventory.entity.MedicineDetails;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;

public interface MedicineDetailsService {
	
	
    MedicineDetails getMedicineDetailsById(Integer medicineDetailsId) throws ProfileNotFoundException;
    void createMedicineDetails(MedicineDetails medicineDetails);
    void updateMedicineDetails(Integer medicineDetailsId, MedicineDetails medicineDetails) throws ProfileNotFoundException;
    void deleteMedicineDetails(Integer medicineDetailsId) throws ProfileNotFoundException;
	List<MedicineDetails> getAllMedicineDetailsByMedicineId(Integer medicineId);
	List<MedicineDetails> getAllMedicinesDetails();
}
