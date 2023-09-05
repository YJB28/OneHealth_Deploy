package com.onehealth.pharmainventory.controller;

import com.onehealth.pharmainventory.entity.MedicineCategory;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.service.MedicineCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/InventoryManagement")
/**
 * The MedicineCategoryController class handles HTTP requests related to
 * medicine categories. Author: Yogesh Baiskar
 */
public class MedicineCategoryController {

	// Logger for this class
	private static final Logger logger = LoggerFactory.getLogger(MedicineCategoryController.class);

	@Autowired
	private MedicineCategoryService categoryService;

	/**
	 * Handles a GET request to retrieve all medicine categories.
	 *
	 * @return A list of all medicine categories along with an appropriate HTTP
	 *         status.
	 */
	@GetMapping("/medicineCategories")
	public ResponseEntity<List<MedicineCategory>> getAllCategories() {
		logger.info("Received a GET request to retrieve all medicine categories");
		List<MedicineCategory> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	/**
	 * Handles a GET request to retrieve a specific medicine category by its ID.
	 *
	 * @param categoryId The ID of the medicine category to retrieve.
	 * @return The retrieved medicine category along with an appropriate HTTP
	 *         status.
	 * @throws ProfileNotFoundException If the specified medicine category is not
	 *                                  found.
	 */
	@GetMapping("/medicineCategories/{categoryId}")
	public ResponseEntity<Optional<MedicineCategory>> getCategoryById(@PathVariable Long categoryId)
			throws ProfileNotFoundException {
		logger.info("Received a GET request to retrieve medicine category by ID: {}", categoryId);
		Optional<MedicineCategory> category = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	/**
	 * Handles a POST request to create a new medicine category.
	 *
	 * @param category The medicine category details to be created.
	 * @return A success message along with an appropriate HTTP status.
	 */
	@PostMapping("/medicineCategories")
	public ResponseEntity<String> createCategory(@RequestBody MedicineCategory category) {
		logger.info("Received a POST request to create a new medicine category");

		// Check if the category already exists
		System.out.println("Category Name to Check: " + category.getCategoryName());
		boolean categoryExists = categoryService.existsByCategoryName(category.getCategoryName());
		System.out.println("categoryExists " + categoryExists);
		if (categoryExists) {
			return ResponseEntity.badRequest().body("Category with the specified Name already exists");
		}

		// Create the category since it doesn't exist
		Boolean subExist = categoryService.createCategory(category);
		if (!subExist) {

			return new ResponseEntity<>(
					"First create Parent Category(Parent) with the specified SubCategoryId (SubcateId must be Parent first) ",
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Category Created Successfully", HttpStatus.CREATED);
	}

	/**
	 * Handles a PUT request to update an existing medicine category.
	 *
	 * @param categoryId The ID of the medicine category to update.
	 * @param category   The updated medicine category details.
	 * @return A success message along with an appropriate HTTP status.
	 * @throws ProfileNotFoundException If the specified medicine category is not
	 *                                  found.
	 */
	@PutMapping("/medicineCategories/{categoryId}")
	public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody MedicineCategory category)
			throws ProfileNotFoundException {
		logger.info("Received a PUT request to update medicine category with ID: {}", categoryId);
		categoryService.updateCategory(categoryId, category);
		return new ResponseEntity<>("Category Updated Successfully", HttpStatus.OK);
	}

	/**
	 * Handles a DELETE request to delete a specific medicine category by its ID.
	 *
	 * @param categoryId The ID of the medicine category to delete.
	 * @return A success message along with an appropriate HTTP status.
	 * @throws ProfileNotFoundException If the specified medicine category is not
	 *                                  found.
	 */
	@DeleteMapping("/medicineCategories/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) throws ProfileNotFoundException {
		logger.info("Received a DELETE request to delete medicine category with ID: {}", categoryId);
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>("Category Deleted Successfully", HttpStatus.OK);
	}

	/**
	 * Handles a GET request to retrieve medicine categories by sub-category ID.
	 *
	 * @param subCategoryId The ID of the sub-category.
	 * @return A list of medicine categories belonging to the specified
	 *         sub-category.
	 */
	@GetMapping("/medicineCategories/bySubCategoryId/{subCategoryId}")
	public ResponseEntity<List<MedicineCategory>> getCategoriesBySubCategoryId(@PathVariable Long subCategoryId) {
		logger.info("Received a GET request to retrieve medicine categories by sub-category ID: {}", subCategoryId);
		List<MedicineCategory> categories = categoryService.getCategoriesBySubCategoryId(subCategoryId);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	/**
	 * Handles a GET request to retrieve medicine categories by flag.
	 *
	 * @param flag The flag indicating whether the category is active.
	 * @return A list of medicine categories based on the flag value.
	 */
	@GetMapping("/medicineCategories/byFlag/{flag}")
	public ResponseEntity<List<MedicineCategory>> getCategoriesByFlag(@PathVariable Boolean flag) {
		logger.info("Received a GET request to retrieve medicine categories by flag: {}", flag);
		List<MedicineCategory> categories = categoryService.getCategoriesByFlag(flag);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	/**
	 * Handles a GET request to retrieve medicine categories by category name.
	 *
	 * @param categoryName The name of the medicine category.
	 * @return A list of medicine categories matching the specified category name.
	 */
	@GetMapping("/medicineCategories/byCategoryName/{categoryName}")
	public ResponseEntity<List<MedicineCategory>> getCategoriesByCategoryName(@PathVariable String categoryName) {
		logger.info("Received a GET request to retrieve medicine categories by category name: {}", categoryName);
		List<MedicineCategory> categories = categoryService.getCategoriesByCategoryName(categoryName);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	/**
	 * Handles a GET request to retrieve medicine categories by health condition.
	 *
	 * @param healthCondition The health condition associated with the category.
	 * @return A list of medicine categories based on the specified health
	 *         condition.
	 */
	@GetMapping("/medicineCategories/byHealthCondition/{healthCondition}")
	public ResponseEntity<List<MedicineCategory>> getCategoriesByHealthCondition(@PathVariable String healthCondition) {
		logger.info("Received a GET request to retrieve medicine categories by health condition: {}", healthCondition);
		List<MedicineCategory> categories = categoryService.getCategoriesByHealthCondition(healthCondition);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	/**
	 * Handles a GET request to retrieve top-level medicine categories.
	 *
	 * @return A list of top-level medicine categories.
	 */
	@GetMapping("/medicineCategories/topCategories")
	public ResponseEntity<List<MedicineCategory>> getTopCategories() {
		logger.info("Received a GET request to retrieve top-level medicine categories");
		List<MedicineCategory> categories = categoryService.getTopCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
}
