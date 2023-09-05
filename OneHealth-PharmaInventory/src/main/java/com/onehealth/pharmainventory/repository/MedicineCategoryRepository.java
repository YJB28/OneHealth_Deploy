package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.MedicineCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The MedicineCategoryRepository interface provides data access operations for
 * the MedicineCategory entity. Author: Yogesh Baiskar
 */
public interface MedicineCategoryRepository extends JpaRepository<MedicineCategory, Long> {

	/**
	 * Retrieve a list of medicine categories by sub-category ID.
	 *
	 * @param subCategoryId The sub-category ID to search for.
	 * @return A list of medicine categories matching the sub-category ID.
	 */
	List<MedicineCategory> findBySubCategoryId(Long subCategoryId);

	/**
	 * Retrieve a list of medicine categories by flag.
	 *
	 * @param flag The flag to search for.
	 * @return A list of medicine categories matching the flag.
	 */
	List<MedicineCategory> findByFlag(Boolean flag);

	/**
	 * Retrieve a list of medicine categories by category name.
	 *
	 * @param categoryName The category name to search for.
	 * @return A list of medicine categories matching the category name.
	 */
	List<MedicineCategory> findByCategoryName(String categoryName);

	/**
	 * Retrieve a list of medicine categories by health condition.
	 *
	 * @param healthCondition The health condition to search for.
	 * @return A list of medicine categories matching the health condition.
	 */
	List<MedicineCategory> findByHealthCondition(String healthCondition);

	/**
	 * Check if a medicine category with the specified name exists
	 * (case-insensitive).
	 *
	 * @param categoryName The category name to check.
	 * @return True if a medicine category with the specified name exists;
	 *         otherwise, false.
	 */
	boolean existsByCategoryNameIgnoreCase(String categoryName);

	/**
	 * Custom query to retrieve the top medicine categories with sub-category count.
	 *
	 * @return A list of top medicine categories ordered by sub-category count in
	 *         descending order.
	 */
	@Query(value = "SELECT c.*, COUNT(s.sub_category_id) AS sub_category_count " + "FROM medicine_category c "
			+ "LEFT JOIN medicine_category s ON c.category_id = s.sub_category_id " + "WHERE c.flag = true "
			+ "GROUP BY c.category_id " + "ORDER BY sub_category_count DESC", nativeQuery = true)
	List<MedicineCategory> findTopCategoriesWithSubCategoryCount();
}
