package com.onehealth.pharmainventory.serviceimpl;

import com.onehealth.pharmainventory.entity.Medicine;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.repository.MedicineRepository;
import com.onehealth.pharmainventory.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public List<Medicine> getAllMedicines() {
		return medicineRepository.findAll();
	}

	@Override
	public Medicine getMedicineById(Integer medicineId) throws ProfileNotFoundException {
		Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
		if (optionalMedicine.isEmpty()) {
			throw new ProfileNotFoundException("Medicine not found with ID: " + medicineId);
		}
		return optionalMedicine.get();
	}

	@Override
	public void createMedicine(Medicine medicine) {
		medicineRepository.save(medicine);
	}

	@Override
	public void updateMedicine(Integer medicineId, Medicine medicine) throws ProfileNotFoundException {
		if (!medicineRepository.existsById(medicineId)) {
			throw new ProfileNotFoundException("Medicine not found with ID: " + medicineId);
		}
		medicine.setMedicineId(medicineId);
		medicineRepository.save(medicine);
	}

	@Override
	public void deleteMedicine(Integer medicineId) throws ProfileNotFoundException {
		if (!medicineRepository.existsById(medicineId)) {
			throw new ProfileNotFoundException("Medicine not found with ID: " + medicineId);
		}
		medicineRepository.deleteById(medicineId);
	}

	@Override
	public List<Medicine> getMedicinesByMedicineName(String medicineName) {
		return medicineRepository.findByMedicineNameContainingIgnoreCase(medicineName);
	}

	@Override
	public List<Medicine> getMedicinesByCategoryId(Integer categoryId) {
		return medicineRepository.findByCategoryId(categoryId);
	}
}
