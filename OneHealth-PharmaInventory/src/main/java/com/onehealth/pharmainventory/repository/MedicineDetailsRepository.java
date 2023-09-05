package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.MedicineDetails;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineDetailsRepository extends JpaRepository<MedicineDetails, Long> {

	// Retrieves all medicine details by the associated medicine's ID.
	List<MedicineDetails> findAllDetailsByMedicineMedicineId(Long medicineId);

	// Retrieves all medicine details by the associated medicine's ID using
	// Optional.
	Optional<MedicineDetails> findAllByMedicine_MedicineId(Long medicineId);

	// Retrieves all medicine details associated with a pharmacy.
	List<MedicineDetails> findAllDetailsByMedicinePharmaId(Long pharmaId);

	// Checks if medicine details exist for a given medicine ID.
	boolean existsByMedicine_MedicineId(Long medicineId);
}
