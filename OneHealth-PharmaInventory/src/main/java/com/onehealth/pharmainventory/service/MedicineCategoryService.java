package com.onehealth.pharmainventory.service;

import com.onehealth.pharmainventory.entity.MedicineCategory;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;

import java.util.List;

public interface MedicineCategoryService {

    List<MedicineCategory> getAllCategories();

    MedicineCategory getCategoryById(Integer categoryId) throws ProfileNotFoundException;

    void createCategory(MedicineCategory category);

    void updateCategory(Integer categoryId, MedicineCategory category) throws ProfileNotFoundException;

    void deleteCategory(Integer categoryId) throws ProfileNotFoundException;

	List<MedicineCategory> getCategoriesBySubCategoryId(Integer subCategoryId);

	List<MedicineCategory> getCategoriesByFlag(Boolean flag);

	List<MedicineCategory> getCategoriesByCategoryName(String categoryName);

	List<MedicineCategory> getCategoriesByHealthCondition(String healthCondition);
}
