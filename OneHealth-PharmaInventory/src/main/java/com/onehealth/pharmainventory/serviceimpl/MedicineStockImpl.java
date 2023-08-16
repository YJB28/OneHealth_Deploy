package com.onehealth.pharmainventory.serviceimpl;

import com.onehealth.pharmainventory.entity.MedicineStock;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.repository.MedicineStockRepository;
import com.onehealth.pharmainventory.service.MedicineStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineStockImpl implements MedicineStockService {

	@Autowired
	private MedicineStockRepository medicineStockRepository;

	@Override
	public MedicineStock getInventoryById(Integer medicineStockId) throws ProfileNotFoundException {
		Optional<MedicineStock> optionalInventory = medicineStockRepository.findById(medicineStockId);
		if (optionalInventory.isEmpty()) {
			throw new ProfileNotFoundException("Inventory not found with ID: " + medicineStockId);
		}
		return optionalInventory.get();
	}

	@Override
	public void createInventory(MedicineStock inventory) {
		medicineStockRepository.save(inventory);
	}

	@Override
	public void updateInventory(Integer medicineStockId, MedicineStock inventory) throws ProfileNotFoundException {
		if (!medicineStockRepository.existsById(medicineStockId)) {
			throw new ProfileNotFoundException("Inventory not found with ID: " + medicineStockId);
		}
		inventory.setMedicineStockId(medicineStockId);
		medicineStockRepository.save(inventory);
	}

	@Override
	public void deleteInventory(Integer medicineStockId) throws ProfileNotFoundException {
		if (!medicineStockRepository.existsById(medicineStockId)) {
			throw new ProfileNotFoundException("Inventory not found with ID: " + medicineStockId);
		}
		medicineStockRepository.deleteById(medicineStockId);
	}

	@Override
	public List<MedicineStock> getAllInventories() {
		// TODO Auto-generated method stub
		return medicineStockRepository.findAll();
	}
}
