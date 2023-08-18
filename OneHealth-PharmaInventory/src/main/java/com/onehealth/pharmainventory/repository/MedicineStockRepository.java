package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.MedicineStock;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineStockRepository extends JpaRepository<MedicineStock, Integer> {

	List<MedicineStock> findByMedicineIdAndPharmaId(Integer medicineId, Integer pharmaId);

	List<MedicineStock> findByMedicineId(Integer medicineId);

	List<MedicineStock> findByPharmaId(Integer pharmaId);
}
