package com.onehealth.pharmainventory.service;

import com.onehealth.pharmainventory.entity.MedicineCategory;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * The MedicineCategoryService interface defines methods for managing medicine
 * categories. Author: Yogesh Baiskar
 */
public interface MedicineCategoryService {

	/**
	 * Retrieves all medicine categories.
	 *
	 * @return A list of all medicine categories.
	 */
	List<MedicineCategory> getAllCategories();

	/**
	 * Retrieves a medicine category by its ID.
	 *
	 * @param categoryId The ID of the medicine category to retrieve.
	 * @return The retrieved medicine category.
	 * @throws ProfileNotFoundException If the specified category is not found.
	 */
	Optional<MedicineCategory> getCategoryById(Long categoryId) throws ProfileNotFoundException;


	/**
	 * Creates a new medicine category.
	 *
	 * @param category The medicine category to create.
	 * @return True if the category was created successfully, false otherwise.
	 */
	Boolean createCategory(MedicineCategory category);

	/**
	 * Updates an existing medicine category.
	 *
	 * @param categoryId The ID of the medicine category to update.
	 * @param category   The updated medicine category details.
	 * @throws ProfileNotFoundException If the specified category is not found.
	 */
	void updateCategory(Long categoryId, MedicineCategory category) throws ProfileNotFoundException;

	/**
	 * Deletes a medicine category by its ID.
	 *
	 * @param categoryId The ID of the medicine category to delete.
	 * @throws ProfileNotFoundException If the specified category is not found.
	 */
	void deleteCategory(Long categoryId) throws ProfileNotFoundException;

	/**
	 * Retrieves medicine categories by their sub-category ID.
	 *
	 * @param subCategoryId The ID of the sub-category to filter by.
	 * @return A list of medicine categories with the specified sub-category ID.
	 */
	List<MedicineCategory> getCategoriesBySubCategoryId(Long subCategoryId);

	/**
	 * Retrieves medicine categories by their flag.
	 *
	 * @param flag The flag to filter by.
	 * @return A list of medicine categories with the specified flag.
	 */
	List<MedicineCategory> getCategoriesByFlag(Boolean flag);

	/**
	 * Retrieves medicine categories by their category name.
	 *
	 * @param categoryName The name of the category to filter by.
	 * @return A list of medicine categories with the specified category name.
	 */
	List<MedicineCategory> getCategoriesByCategoryName(String categoryName);

	/**
	 * Retrieves medicine categories by their health condition.
	 *
	 * @param healthCondition The health condition to filter by.
	 * @return A list of medicine categories with the specified health condition.
	 */
	List<MedicineCategory> getCategoriesByHealthCondition(String healthCondition);

	/**
	 * Retrieves the top medicine categories with the most sub-categories.
	 *
	 * @return A list of top medicine categories.
	 */
	List<MedicineCategory> getTopCategories();

	/**
	 * Checks if a medicine category with the specified name exists.
	 *
	 * @param categoryName The name of the category to check.
	 * @return True if a category with the specified name exists, false otherwise.
	 */
	boolean existsByCategoryName(String categoryName);
}
