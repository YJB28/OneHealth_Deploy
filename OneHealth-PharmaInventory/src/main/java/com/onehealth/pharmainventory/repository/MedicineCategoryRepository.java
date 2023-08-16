package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.MedicineCategory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineCategoryRepository extends JpaRepository<MedicineCategory, Integer> {

	List<MedicineCategory> findBySubCategoryId(Integer subCategoryId);

	List<MedicineCategory> findByFlag(Boolean flag);

	List<MedicineCategory> findByCategoryName(String categoryName);

	List<MedicineCategory> findByHealthCondition(String healthCondition);
	// Add custom queries if needed
}
