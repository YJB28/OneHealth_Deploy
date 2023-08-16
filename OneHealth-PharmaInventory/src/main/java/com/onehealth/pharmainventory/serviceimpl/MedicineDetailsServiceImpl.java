package com.onehealth.pharmainventory.serviceimpl;

import com.onehealth.pharmainventory.entity.MedicineDetails;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.repository.MedicineDetailsRepository;
import com.onehealth.pharmainventory.service.MedicineDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineDetailsServiceImpl implements MedicineDetailsService {

    @Autowired
    private MedicineDetailsRepository medicineDetailsRepository;

    @Override
    public MedicineDetails getMedicineDetailsById(Integer medicineDetailsId) throws ProfileNotFoundException {
        Optional<MedicineDetails> optionalMedicineDetails = medicineDetailsRepository.findById(medicineDetailsId);
        if (optionalMedicineDetails.isEmpty()) {
            throw new ProfileNotFoundException("Medicine details not found with ID: " + medicineDetailsId);
        }
        return optionalMedicineDetails.get();
    }

    @Override
    public void createMedicineDetails(MedicineDetails medicineDetails) {
        medicineDetailsRepository.save(medicineDetails);
    }

    @Override
    public void updateMedicineDetails(Integer medicineDetailsId, MedicineDetails medicineDetails) throws ProfileNotFoundException {
        if (!medicineDetailsRepository.existsById(medicineDetailsId)) {
            throw new ProfileNotFoundException("Medicine details not found with ID: " + medicineDetailsId);
        }
        medicineDetails.setMedicineDetailsId(medicineDetailsId);
        medicineDetailsRepository.save(medicineDetails);
    }

    @Override
    public void deleteMedicineDetails(Integer medicineDetailsId) throws ProfileNotFoundException {
        if (!medicineDetailsRepository.existsById(medicineDetailsId)) {
            throw new ProfileNotFoundException("Medicine details not found with ID: " + medicineDetailsId);
        }
        medicineDetailsRepository.deleteById(medicineDetailsId);
    }

	@Override
	public List<MedicineDetails> getAllMedicineDetailsByMedicineId(Integer medicineId) {
		// TODO Auto-generated method stub
		return medicineDetailsRepository.findAllDetailsByMedicineId(medicineId);
	}

	@Override
	public List<MedicineDetails> getAllMedicinesDetails() {
		// TODO Auto-generated method stub
		return medicineDetailsRepository.findAll();
	}
}
