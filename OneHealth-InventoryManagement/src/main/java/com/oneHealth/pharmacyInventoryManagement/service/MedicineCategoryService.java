package com.oneHealth.pharmacyInventoryManagement.service;

import com.oneHealth.pharmacyInventoryManagement.entity.MedicineCategory;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;

import java.util.List;

public interface MedicineCategoryService {

    MedicineCategory createCategory(MedicineCategory category);

    void updateCategory(long categoryId, MedicineCategory category) throws ProfileNotFoundException;

    void deleteCategory(long categoryId) throws ProfileNotFoundException;

    List<MedicineCategory> getAllCategories();

    MedicineCategory getCategoryById(long categoryId) throws ProfileNotFoundException;

	List<MedicineCategory> getCategoryByCategoryName(String categoryName);
}
