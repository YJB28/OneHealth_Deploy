package com.onehealth.pharmainventory.controller;

import com.onehealth.pharmainventory.entity.MedicineStock;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.service.MedicineService;
import com.onehealth.pharmainventory.service.MedicineStockService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/InventoryManagement")
public class MedicineStockController {

	@Autowired
	private MedicineStockService medicineStockService;

	@GetMapping("/medicineStock/all")
	public ResponseEntity<List<MedicineStock>> getAllInventory() {

		List<MedicineStock> inventories = medicineStockService.getAllInventories();
		return new ResponseEntity<>(inventories, HttpStatus.OK);
	}

	@GetMapping("/medicineStock/{medicineStockId}")
	public ResponseEntity<MedicineStock> getInventoryById(@PathVariable Long medicineStockId)
			throws ProfileNotFoundException {
		MedicineStock medicineStock = medicineStockService.getInventoryById(medicineStockId);
		return new ResponseEntity<>(medicineStock, HttpStatus.OK);
	}

	@PostMapping("/medicineStock")
	public ResponseEntity<String> createInventory(@RequestBody MedicineStock inventory) {
		
		// Check if a medicine with the same pharmaId and medicineName already exists
	    boolean medicineExistsInMedicineEntity = medicineStockService.existsByMedicineIdInMedicineSerice(
	    		inventory.getMedicine().getMedicineId());

	    if (!medicineExistsInMedicineEntity) {
	        return new ResponseEntity<>("Medicine with the Medicine Id Not exist",
	                HttpStatus.BAD_REQUEST);
	    }
		
		
		// Check if a medicine with the same pharmaId and medicineName already exists
	    boolean medicineExists = medicineStockService.existsByMedicineId(
	    		inventory.getMedicine().getMedicineId());

	    if (medicineExists) {
	        return new ResponseEntity<>("Medicine with the Medicine Id already added in stock",
	                HttpStatus.BAD_REQUEST);
	    }

	    medicineStockService.createInventory(inventory);
	    return new ResponseEntity<>("Inventory Created Successfully", HttpStatus.CREATED);
		
		
		
	}

	@PutMapping("/medicineStock/{medicineStockId}")
	public ResponseEntity<String> updateInventory(@PathVariable Long medicineStockId,
			@RequestBody MedicineStock inventory) throws ProfileNotFoundException {
		medicineStockService.updateInventory(medicineStockId, inventory);
		return new ResponseEntity<>("Inventory Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/medicineStock/{medicineStockId}")
	public ResponseEntity<String> deleteInventory(@PathVariable Long medicineStockId) throws ProfileNotFoundException {
		medicineStockService.deleteInventory(medicineStockId);
		return new ResponseEntity<>("Inventory Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/medicineStock/byMedicineAndPharmaID/medicineID/{medicineId}/pharmaID/{pharmaId}")
	public ResponseEntity<List<MedicineStock>> getMedicineStockByMedicineAndPharma(@PathVariable Long medicineId,
			@PathVariable Long pharmaId) throws ProfileNotFoundException {
		List<MedicineStock> medicineStock = medicineStockService.getMedicineStockByMedicineAndPharmaId(medicineId,
				pharmaId);
		return new ResponseEntity<>(medicineStock, HttpStatus.OK);
	}

	@GetMapping("/medicineStock/byMedicineID/{medicineId}")
	public ResponseEntity<MedicineStock> getMedicineStockByMedicine(@PathVariable Long medicineId)
			throws ProfileNotFoundException {
		MedicineStock medicineStock = medicineStockService.getMedicineStockByMedicineId(medicineId);
		return new ResponseEntity<>(medicineStock, HttpStatus.OK);
	}

	@GetMapping("/medicineStock/byPharmaId/{pharmaId}")
	public ResponseEntity<List<MedicineStock>> getMedicineStockByPharma(@PathVariable Long pharmaId)
			throws ProfileNotFoundException {
		List<MedicineStock> medicineStock = medicineStockService.getMedicineStockByPharmaId(pharmaId);

		return new ResponseEntity<>(medicineStock, HttpStatus.OK);
	}

	/**
	 * Exception handler for ProfileNotFoundException.
	 *
	 * @param ex The exception that was thrown.
	 * @return Error message along with HTTP status 404 (Not Found).
	 */
	@ExceptionHandler(ProfileNotFoundException.class)
	public ResponseEntity<String> handleProfileNotFoundException(ProfileNotFoundException ex) {
//		logger.error("ProfileNotFoundException: {}", ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}