package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.MedicineStock;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineStockRepository extends JpaRepository<MedicineStock, Long> {

	// Find medicine stock by both medicine ID and pharmacy ID
	List<MedicineStock> findByMedicine_MedicineIdAndPharmaId(Long medicineId, Long pharmaId);

	// Find medicine stock by medicine ID
	Optional<MedicineStock> findByMedicine_MedicineId(Long medicineId);

	// Find medicine stock by pharmacy ID
	List<MedicineStock> findByPharmaId(Long pharmaId);

	// Check if medicine with the specified ID exists in any medicine stock
	boolean existsByMedicine_MedicineId(Long medicineId);
}
