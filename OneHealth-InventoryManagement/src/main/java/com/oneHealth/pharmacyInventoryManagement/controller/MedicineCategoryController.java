package com.oneHealth.pharmacyInventoryManagement.controller;

import com.oneHealth.pharmacyInventoryManagement.entity.MedicineCategory;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.service.MedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
//@RequestMapping("/medicineCategories")
@RequestMapping("/InventoryManagement")
public class MedicineCategoryController {

    @Autowired
    private MedicineCategoryService categoryService;

    @GetMapping("/medicineCategories")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from /InventoryManagement/medicineCategories/");
    }

    @GetMapping("/medicineCategories/getAll")
    public ResponseEntity<List<MedicineCategory>> getAllCategories() {
        List<MedicineCategory> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/medicineCategories/{categoryId}")
    public ResponseEntity<MedicineCategory> getCategoryById(@PathVariable Long categoryId) throws ProfileNotFoundException {
        MedicineCategory category = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    
    @GetMapping("/medicineCategories/categoryByName/{categoryName}")
    public ResponseEntity<List<MedicineCategory>> getCategoryByCategoryName(@PathVariable String categoryName) throws ProfileNotFoundException {
    	List<MedicineCategory> categories = categoryService.getCategoryByCategoryName(categoryName);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/medicineCategories/add")
    public ResponseEntity<String> createCategory(@RequestBody MedicineCategory category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Created Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/medicineCategories/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody MedicineCategory category)
            throws ProfileNotFoundException {
        categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>("Category Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/medicineCategories/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) throws ProfileNotFoundException {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category Deleted Successfully", HttpStatus.OK);
    }
}
