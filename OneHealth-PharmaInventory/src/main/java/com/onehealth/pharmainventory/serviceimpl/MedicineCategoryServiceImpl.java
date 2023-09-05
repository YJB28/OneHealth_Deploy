package com.onehealth.pharmainventory.serviceimpl;

import com.onehealth.pharmainventory.entity.MedicineCategory;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.repository.MedicineCategoryRepository;
import com.onehealth.pharmainventory.service.MedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The MedicineCategoryServiceImpl class implements the MedicineCategoryService
 * interface to provide functionality for managing medicine categories. Author:
 * Yogesh Baiskar
 */
@Service
public class MedicineCategoryServiceImpl implements MedicineCategoryService {

	private static final Logger logger = LoggerFactory.getLogger(MedicineCategoryServiceImpl.class);

	@Autowired
	private MedicineCategoryRepository categoryRepository;

	@Override
	public List<MedicineCategory> getAllCategories() {
		logger.info("Fetching all medicine categories");
		return categoryRepository.findAll();
	}

	@Override
	public Optional<MedicineCategory> getCategoryById(Long categoryId) throws ProfileNotFoundException {
		logger.info("Fetching medicine category by ID: {}", categoryId);

		return Optional.ofNullable(categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ProfileNotFoundException("Medicine category not found with this category ID: " + categoryId)));

	}

	@Override
	public Boolean createCategory(MedicineCategory category) {
		logger.info("Creating a new medicine category");

		if (category.getSubCategoryId() > 0) {
			// Check if the subCategoryId exists in the categoryId column
			boolean subCategoryExists = categoryRepository.existsById(category.getSubCategoryId());

			logger.info("Sub-category with ID {} exists: {}", category.getSubCategoryId(), subCategoryExists);

			if (!subCategoryExists) {
				logger.warn("Sub-category with ID {} does not exist. Category creation failed.",
						category.getSubCategoryId());
				return false;
			}
		}

		categoryRepository.save(category);
		logger.info("Medicine category created successfully");
		return true;
	}

	@Override
	public void updateCategory(Long categoryId, MedicineCategory category) throws ProfileNotFoundException {
		logger.info("Updating medicine category with ID: {}", categoryId);

		Optional<MedicineCategory> existingCategoryOptional = categoryRepository.findById(categoryId);
		if (existingCategoryOptional.isPresent()) {
			MedicineCategory existingCategory = existingCategoryOptional.get();
			existingCategory.setCategoryName(category.getCategoryName());
			existingCategory.setImages(category.getImages());
			existingCategory.setSubCategoryId(category.getSubCategoryId());
			existingCategory.setHealthCondition(category.getHealthCondition());
			existingCategory.setFlag(category.getFlag());
			categoryRepository.save(existingCategory);
			logger.info("Medicine category updated successfully");
		} else {
			logger.warn("Medicine category with ID {} not found. Update failed.", categoryId);
			throw new ProfileNotFoundException("Medicine category not found with ID: " + categoryId);
		}
	}

	@Override
	public void deleteCategory(Long categoryId) throws ProfileNotFoundException {
		logger.info("Deleting medicine category with ID: {}", categoryId);

		Optional<MedicineCategory> categoryOptional = categoryRepository.findById(categoryId);
		if (categoryOptional.isPresent()) {
			categoryRepository.deleteById(categoryId);
			logger.info("Medicine category deleted successfully");
		} else {
			logger.warn("Medicine category with ID {} not found. Deletion failed.", categoryId);
			throw new ProfileNotFoundException("Medicine category not found with ID: " + categoryId);
		}
	}

	@Override
	public List<MedicineCategory> getCategoriesBySubCategoryId(Long subCategoryId) {
		logger.info("Fetching medicine categories by sub-category ID: {}", subCategoryId);
		return categoryRepository.findBySubCategoryId(subCategoryId);
	}

	@Override
	public List<MedicineCategory> getCategoriesByFlag(Boolean flag) {
		logger.info("Fetching medicine categories by flag: {}", flag);
		return categoryRepository.findByFlag(flag);
	}

	@Override
	public List<MedicineCategory> getCategoriesByCategoryName(String categoryName) {
		logger.info("Fetching medicine categories by category name: {}", categoryName);
		return categoryRepository.findByCategoryName(categoryName);
	}

	@Override
	public List<MedicineCategory> getCategoriesByHealthCondition(String healthCondition) {
		logger.info("Fetching medicine categories by health condition: {}", healthCondition);
		return categoryRepository.findByHealthCondition(healthCondition);
	}

	@Override
	public boolean existsByCategoryName(String categoryName) {
		logger.info("Checking if medicine category with name {} exists", categoryName);
		return categoryRepository.existsByCategoryNameIgnoreCase(categoryName);
	}

	@Override
	public List<MedicineCategory> getTopCategories() {
		logger.info("Fetching top medicine categories with sub-category count");
		return categoryRepository.findTopCategoriesWithSubCategoryCount();
	}
}
