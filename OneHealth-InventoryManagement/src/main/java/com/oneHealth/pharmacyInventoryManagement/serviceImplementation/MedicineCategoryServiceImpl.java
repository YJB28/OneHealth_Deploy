package com.oneHealth.pharmacyInventoryManagement.serviceImplementation;

import com.oneHealth.pharmacyInventoryManagement.entity.MedicineCategory;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.repository.MedicineCategoryRepository;
import com.oneHealth.pharmacyInventoryManagement.service.MedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineCategoryServiceImpl implements MedicineCategoryService {

	@Autowired
    private MedicineCategoryRepository categoryRepository;

   

    @Override
    public MedicineCategory createCategory(MedicineCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public void updateCategory(long categoryId, MedicineCategory category) throws ProfileNotFoundException {
        MedicineCategory existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ProfileNotFoundException("Category not found with ID " + categoryId));

        // Update the fields of the existing category with the values from the input category
        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setParentCategoryId(category.getParentCategoryId());
        existingCategory.setFlag(category.isFlag());
        existingCategory.setCategoryImagePath(category.getCategoryImagePath());
        existingCategory.setHealthCondition(category.getHealthCondition());

        // Save the updated category
        categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(long categoryId) throws ProfileNotFoundException {
        MedicineCategory existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ProfileNotFoundException("Category not found with ID " + categoryId));

        categoryRepository.delete(existingCategory);
    }

    @Override
    public List<MedicineCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public MedicineCategory getCategoryById(long categoryId) throws ProfileNotFoundException {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ProfileNotFoundException("Category not found with ID " + categoryId));
    }

	@Override
	public List<MedicineCategory> getCategoryByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return categoryRepository.findByCategoryName(categoryName);
	}
}
