package com.onehealth.pharmainventory.controller;

import com.onehealth.pharmainventory.entity.MedicineStock;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.service.MedicineService;
import com.onehealth.pharmainventory.service.MedicineStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Import the Logger
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/InventoryManagement")
public class MedicineStockController {

	private static final Logger logger = LoggerFactory.getLogger(MedicineStockController.class); // Create a logger

	@Autowired
	private MedicineStockService medicineStockService;

	// Retrieve all inventories
	@GetMapping("/medicineStock/all")
	public ResponseEntity<List<MedicineStock>> getAllInventory() {
		logger.info("Request received: getAllInventory");
		List<MedicineStock> inventories = medicineStockService.getAllInventories();
		logger.info("Response sent: getAllInventory");
		return new ResponseEntity<>(inventories, HttpStatus.OK);
	}

	// Retrieve inventory by ID
	@GetMapping("/medicineStock/{medicineStockId}")
	public ResponseEntity<MedicineStock> getInventoryById(@PathVariable Long medicineStockId)
			throws ProfileNotFoundException {
		logger.info("Request received: getInventoryById");
		MedicineStock medicineStock = medicineStockService.getInventoryById(medicineStockId);
		logger.info("Response sent: getInventoryById");
		return new ResponseEntity<>(medicineStock, HttpStatus.OK);
	}

	// Create an inventory
	@PostMapping("/medicineStock")
	public ResponseEntity<String> createInventory(@RequestBody MedicineStock inventory) {
		logger.info("Request received: createInventory");
		try {
			// Check if a medicine with the same pharmaId and medicineName already exists
			boolean medicineExistsInMedicineEntity = medicineStockService
					.existsByMedicineIdInMedicineSerice(inventory.getMedicine().getMedicineId());

			if (!medicineExistsInMedicineEntity) {
				return new ResponseEntity<>("Medicine with the Medicine Id Not exist", HttpStatus.BAD_REQUEST);
			}

			// Check if a medicine with the same pharmaId and medicineName already exists
			boolean medicineExists = medicineStockService.existsByMedicineId(inventory.getMedicine().getMedicineId());

			if (medicineExists) {
				return new ResponseEntity<>("Medicine with the Medicine Id already added in stock",
						HttpStatus.BAD_REQUEST);
			}

			medicineStockService.createInventory(inventory);
			logger.info("Response sent: createInventory");
			return new ResponseEntity<>("Inventory Created Successfully", HttpStatus.CREATED);
		} catch (Exception ex) {
			logger.error("Error in createInventory: {}", ex.getMessage());
			return new ResponseEntity<>("Error Creating Inventory", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update an inventory
	@PutMapping("/medicineStock/{medicineStockId}")
	public ResponseEntity<String> updateInventory(@PathVariable Long medicineStockId,
			@RequestBody MedicineStock inventory) throws ProfileNotFoundException {
		logger.info("Request received: updateInventory");
		try {
			medicineStockService.updateInventory(medicineStockId, inventory);
			logger.info("Response sent: updateInventory");
			return new ResponseEntity<>("Inventory Updated Successfully", HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error in updateInventory: {}", ex.getMessage());
			return new ResponseEntity<>("Error Updating Inventory", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete an inventory
	@DeleteMapping("/medicineStock/{medicineStockId}")
	public ResponseEntity<String> deleteInventory(@PathVariable Long medicineStockId) throws ProfileNotFoundException {
		logger.info("Request received: deleteInventory");
		try {
			medicineStockService.deleteInventory(medicineStockId);
			logger.info("Response sent: deleteInventory");
			return new ResponseEntity<>("Inventory Deleted Successfully", HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error in deleteInventory: {}", ex.getMessage());
			return new ResponseEntity<>("Error Deleting Inventory", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Retrieve medicine stock by medicine ID and pharma ID
	@GetMapping("/medicineStock/byMedicineAndPharmaID/medicineID/{medicineId}/pharmaID/{pharmaId}")
	public ResponseEntity<List<MedicineStock>> getMedicineStockByMedicineAndPharma(@PathVariable Long medicineId,
			@PathVariable Long pharmaId) throws ProfileNotFoundException {
		logger.info("Request received: getMedicineStockByMedicineAndPharma");
		List<MedicineStock> medicineStock = medicineStockService.getMedicineStockByMedicineAndPharmaId(medicineId,
				pharmaId);
		logger.info("Response sent: getMedicineStockByMedicineAndPharma");
		return new ResponseEntity<>(medicineStock, HttpStatus.OK);
	}

	// Retrieve medicine stock by medicine ID
	@GetMapping("/medicineStock/byMedicineID/{medicineId}")
	public ResponseEntity<MedicineStock> getMedicineStockByMedicine(@PathVariable Long medicineId)
			throws ProfileNotFoundException {
		logger.info("Request received: getMedicineStockByMedicine");
		MedicineStock medicineStock = medicineStockService.getMedicineStockByMedicineId(medicineId);
		logger.info("Response sent: getMedicineStockByMedicine");
		return new ResponseEntity<>(medicineStock, HttpStatus.OK);
	}

	// Retrieve medicine stock by pharma ID
	@GetMapping("/medicineStock/byPharmaId/{pharmaId}")
	public ResponseEntity<List<MedicineStock>> getMedicineStockByPharma(@PathVariable Long pharmaId)
			throws ProfileNotFoundException {
		logger.info("Request received: getMedicineStockByPharma");
		List<MedicineStock> medicineStock = medicineStockService.getMedicineStockByPharmaId(pharmaId);
		logger.info("Response sent: getMedicineStockByPharma");
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
		logger.error("ProfileNotFoundException: {}", ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
