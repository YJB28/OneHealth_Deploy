package com.oneHealth.pharmacyInventoryManagement.repository;

import com.oneHealth.pharmacyInventoryManagement.entity.MedicineCategory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineCategoryRepository extends JpaRepository<MedicineCategory, Long> {

	List<MedicineCategory> findByCategoryName(String categoryName);

    // Define any custom repository methods if needed

}
