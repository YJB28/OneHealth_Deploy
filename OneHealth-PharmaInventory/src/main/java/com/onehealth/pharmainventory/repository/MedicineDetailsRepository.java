package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.MedicineDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineDetailsRepository extends JpaRepository<MedicineDetails, Integer> {

	List<MedicineDetails> findAllDetailsByMedicineId(Integer medicineId);
}
