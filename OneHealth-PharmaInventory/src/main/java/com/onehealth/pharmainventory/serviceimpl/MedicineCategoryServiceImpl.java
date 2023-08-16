package com.onehealth.pharmainventory.serviceimpl;

import com.onehealth.pharmainventory.entity.MedicineCategory;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.repository.MedicineCategoryRepository;
import com.onehealth.pharmainventory.service.MedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineCategoryServiceImpl implements MedicineCategoryService {

	@Autowired
	private MedicineCategoryRepository categoryRepository;

	@Override
	public List<MedicineCategory> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public MedicineCategory getCategoryById(Integer categoryId) throws ProfileNotFoundException {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ProfileNotFoundException("Medicine category not found with ID: " + categoryId));
	}

	@Override
	public void createCategory(MedicineCategory category) {
		categoryRepository.save(category);
	}

	@Override
	public void updateCategory(Integer categoryId, MedicineCategory category) throws ProfileNotFoundException {
		Optional<MedicineCategory> existingCategoryOptional = categoryRepository.findById(categoryId);
		if (existingCategoryOptional.isPresent()) {
			MedicineCategory existingCategory = existingCategoryOptional.get();
			existingCategory.setCategoryName(category.getCategoryName());
			existingCategory.setImages(category.getImages());
			existingCategory.setSubCategoryId(category.getSubCategoryId());
			existingCategory.setHealthCondition(category.getHealthCondition());
			existingCategory.setFlag(category.getFlag());
			categoryRepository.save(existingCategory);
		} else {
			throw new ProfileNotFoundException("Medicine category not found with ID: " + categoryId);
		}
	}

	@Override
	public void deleteCategory(Integer categoryId) throws ProfileNotFoundException {
		Optional<MedicineCategory> categoryOptional = categoryRepository.findById(categoryId);
		if (categoryOptional.isPresent()) {
			categoryRepository.deleteById(categoryId);
		} else {
			throw new ProfileNotFoundException("Medicine category not found with ID: " + categoryId);
		}
	}

	@Override
	public List<MedicineCategory> getCategoriesBySubCategoryId(Integer subCategoryId) {
		return categoryRepository.findBySubCategoryId(subCategoryId);
	}

	@Override
	public List<MedicineCategory> getCategoriesByFlag(Boolean flag) {
		return categoryRepository.findByFlag(flag);
	}

	@Override
	public List<MedicineCategory> getCategoriesByCategoryName(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}

	@Override
	public List<MedicineCategory> getCategoriesByHealthCondition(String healthCondition) {
		return categoryRepository.findByHealthCondition(healthCondition);
	}

}
