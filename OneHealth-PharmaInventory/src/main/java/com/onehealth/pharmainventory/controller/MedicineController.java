package com.onehealth.pharmainventory.controller;

import com.onehealth.pharmainventory.entity.Medicine;
import com.onehealth.pharmainventory.entity.MedicineCategory;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.service.MedicineCategoryService;
import com.onehealth.pharmainventory.service.MedicineService;
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
public class MedicineController {

	private static final Logger logger = LoggerFactory.getLogger(MedicineController.class);

	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private MedicineCategoryService medicineCategoryService;

	/**
	 * Root endpoint for testing.
	 */
	@GetMapping("/")
	public String hello() {
		return "Hello From InventoryManagement";
	}

	/**
	 * Get all medicines.
	 *
	 * @return List of medicines along with HTTP status.
	 */
	@GetMapping("/medicines")
	public ResponseEntity<List<Medicine>> getAllMedicines() {
		logger.info("Received a GET request to fetch all medicines.");
		List<Medicine> medicines = medicineService.getAllMedicines();
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	/**
	 * Get a medicine by ID.
	 *
	 * @param medicineId The ID of the medicine to retrieve.
	 * @return The retrieved medicine along with HTTP status.
	 * @throws ProfileNotFoundException If the specified medicine is not found.
	 */
	@GetMapping("/medicines/{medicineId}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable Long medicineId) throws ProfileNotFoundException {
		logger.info("Received a GET request to fetch medicine with ID: {}", medicineId);
		Medicine medicine = medicineService.getMedicineById(medicineId);
		return new ResponseEntity<>(medicine, HttpStatus.OK);
	}

	/**
	 * Create a new medicine.
	 *
	 * @param medicine The medicine to create.
	 * @return A success message along with HTTP status.
	 */
	@PostMapping("/medicines")
	public ResponseEntity<String> createMedicine(@RequestBody Medicine medicine) {
	    logger.info("Received a POST request to create a new medicine.");

	    // Check if the categoryId exists in MedicineCategory
	    Optional<MedicineCategory> categoryOptional = medicineCategoryService.getCategoryById(medicine.getCategory().getCategoryId());
	    if (categoryOptional.isEmpty()) {
	        return new ResponseEntity<>("Category with the specified Category ID does not exist"+medicine.getCategory().getCategoryId(), HttpStatus.BAD_REQUEST);
	    }

		  // Check if a medicine with the same pharmaId and medicineName already exists
	    boolean medicineExists = medicineService.existsByPharmaIdAndMedicineNameIgnoreCase(
	            medicine.getPharmaId(), medicine.getMedicineName());

	    if (medicineExists) {
	        return new ResponseEntity<>("Medicine with the same Pharma ID and Medicine Name already exists",
	                HttpStatus.BAD_REQUEST);
	    }

	    medicineService.createMedicine(medicine);
	    return new ResponseEntity<>("Medicine Created Successfully", HttpStatus.CREATED);
	}


	/**
	 * Update an existing medicine.
	 *
	 * @param medicineId The ID of the medicine to update.
	 * @param medicine   The updated medicine details.
	 * @return A success message along with HTTP status.
	 * @throws ProfileNotFoundException If the specified medicine is not found.
	 */
	@PutMapping("/medicines/{medicineId}")
	public ResponseEntity<String> updateMedicine(@PathVariable Long medicineId, @RequestBody Medicine medicine)
			throws ProfileNotFoundException {
		logger.info("Received a PUT request to update medicine with ID: {}", medicineId);
		medicineService.updateMedicine(medicineId, medicine);
		return new ResponseEntity<>("Medicine Updated Successfully", HttpStatus.OK);
	}

	/**
	 * Delete a medicine by ID.
	 *
	 * @param medicineId The ID of the medicine to delete.
	 * @return A success message along with HTTP status.
	 * @throws ProfileNotFoundException If the specified medicine is not found.
	 */
	@DeleteMapping("/medicines/{medicineId}")
	public ResponseEntity<String> deleteMedicine(@PathVariable Long medicineId) throws ProfileNotFoundException {
		logger.info("Received a DELETE request to delete medicine with ID: {}", medicineId);
		medicineService.deleteMedicine(medicineId);
		return new ResponseEntity<>("Medicine Deleted Successfully", HttpStatus.OK);
	}

	/**
	 * Get medicines by medicine name.
	 *
	 * @param medicineName The name of the medicine.
	 * @return List of medicines with the specified name along with HTTP status.
	 */
	@GetMapping("/medicines/byName/{medicineName}")
	public ResponseEntity<List<Medicine>> getMedicinesByMedicineName(@PathVariable String medicineName) {
		logger.info("Received a GET request to fetch medicines by name: {}", medicineName);
		List<Medicine> medicines = medicineService.getMedicinesByMedicineName(medicineName);
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	/**
	 * Get medicines by category ID.
	 *
	 * @param categoryId The ID of the category.
	 * @return List of medicines with the specified category ID along with HTTP
	 *         status.
	 */
	@GetMapping("/medicines/byCategoryId/{categoryId}")
	public ResponseEntity<List<Medicine>> getMedicinesByCategoryId(@PathVariable Integer categoryId) {
		logger.info("Received a GET request to fetch medicines by category ID: {}", categoryId);
		List<Medicine> medicines = medicineService.getMedicinesByCategoryId(categoryId);
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	/**
	 * Get medicines by pharmacy ID.
	 *
	 * @param pharmaId The ID of the pharmacy.
	 * @return List of medicines with the specified pharmacy ID along with HTTP
	 *         status.
	 */
	@GetMapping("/medicines/byPharmaId/{pharmaId}")
	public ResponseEntity<List<Medicine>> getMedicinesByPharmaId(@PathVariable Long pharmaId) {
		logger.info("Received a GET request to fetch medicines by pharmacy ID: {}", pharmaId);
		List<Medicine> medicines = medicineService.getMedicinesByPharmaId(pharmaId);
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	/**
	 * Exception handler for ProfileNotFoundException.
	 *
	 * @param ex The exception that was thrown.
	 * @return Error message along with HTTP status 404 (Not Found).
	 */
	@ExceptionHandler(ProfileNotFoundException.class)
	public ResponseEntity<String> handleProfileNotFoundException(ProfileNotFoundException ex) {
		logger.error("ProfileNotFoundException: {}", ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
