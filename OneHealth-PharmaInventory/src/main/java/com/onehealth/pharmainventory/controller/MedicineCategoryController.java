package com.onehealth.pharmainventory.controller;

import com.onehealth.pharmainventory.entity.MedicineCategory;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.service.MedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/InventoryManagement")
public class MedicineCategoryController {

	@Autowired
	private MedicineCategoryService categoryService;

	@GetMapping("/medicineCategories")
	public ResponseEntity<List<MedicineCategory>> getAllCategories() {
		List<MedicineCategory> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("/medicineCategories/{categoryId}")
	public ResponseEntity<MedicineCategory> getCategoryById(@PathVariable Integer categoryId)
			throws ProfileNotFoundException {
		MedicineCategory category = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@PostMapping("/medicineCategories")
	public ResponseEntity<String> createCategory(@RequestBody MedicineCategory category) {
		categoryService.createCategory(category);
		return new ResponseEntity<>("Category Created Successfully", HttpStatus.CREATED);
	}

	@PutMapping("/medicineCategories/{categoryId}")
	public ResponseEntity<String> updateCategory(@PathVariable Integer categoryId,
			@RequestBody MedicineCategory category) throws ProfileNotFoundException {
		categoryService.updateCategory(categoryId, category);
		return new ResponseEntity<>("Category Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/medicineCategories/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) throws ProfileNotFoundException {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>("Category Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/medicineCategories/bySubCategoryId/{subCategoryId}")
	public ResponseEntity<List<MedicineCategory>> getCategoriesBySubCategoryId(@PathVariable Integer subCategoryId) {
		List<MedicineCategory> categories = categoryService.getCategoriesBySubCategoryId(subCategoryId);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("/medicineCategories/byFlag/{flag}")
	public ResponseEntity<List<MedicineCategory>> getCategoriesByFlag(@PathVariable Boolean flag) {
		List<MedicineCategory> categories = categoryService.getCategoriesByFlag(flag);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("/medicineCategories/byCategoryName/{categoryName}")
	public ResponseEntity<List<MedicineCategory>> getCategoriesByCategoryName(@PathVariable String categoryName) {
		List<MedicineCategory> categories = categoryService.getCategoriesByCategoryName(categoryName);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("/medicineCategories/byHealthCondition/{healthCondition}")
	public ResponseEntity<List<MedicineCategory>> getCategoriesByHealthCondition(@PathVariable String healthCondition) {
		List<MedicineCategory> categories = categoryService.getCategoriesByHealthCondition(healthCondition);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
}
