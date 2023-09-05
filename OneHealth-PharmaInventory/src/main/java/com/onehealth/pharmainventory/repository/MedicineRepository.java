package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

	// Find medicines by medicine name containing the provided name
	// (case-insensitive).
	List<Medicine> findByMedicineNameContainingIgnoreCase(String medicineName);

	// Find medicines by category ID.
	List<Medicine> findByCategory_CategoryId(Integer categoryId);

	// Find all medicines by pharmacy ID.
	List<Medicine> findAllByPharmaId(Long pharmaId);

	// Check if a medicine exists with the given pharmacy ID and medicine name
	// (case-insensitive).
	boolean existsByPharmaIdAndMedicineNameIgnoreCase(Long pharmaId, String medicineName);

//	boolean existsByMedicineId(Long medicineId);
}
