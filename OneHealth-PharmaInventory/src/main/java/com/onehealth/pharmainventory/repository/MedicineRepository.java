package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.Medicine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

	List<Medicine> findByMedicineNameContainingIgnoreCase(String medicineName);

	 List<Medicine> findByCategoryId(Integer categoryId);
}
