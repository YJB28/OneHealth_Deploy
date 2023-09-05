package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.MedicineStock;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineStockRepository extends JpaRepository<MedicineStock, Long> {

	List<MedicineStock> findByMedicine_MedicineIdAndPharmaId(Long medicineId, Long pharmaId);

	Optional<MedicineStock> findByMedicine_MedicineId(Long medicineId);

	List<MedicineStock> findByPharmaId(Long pharmaId);

//	boolean existsByMedicineId(Long medicineId);

	boolean existsByMedicine_MedicineId(Long medicineId);

//	List<MedicineStock> findByMedicineIdAndPharmaId(Integer medicineId, Integer pharmaId);
//
//	List<MedicineStock> findByMedicineId(Integer medicineId);
//
//	List<MedicineStock> findByPharmaId(Integer pharmaId);
}