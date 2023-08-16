package com.onehealth.pharmainventory.controller;

import com.onehealth.pharmainventory.entity.MedicineStock;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.service.MedicineStockService;

import java.util.List;

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
	public ResponseEntity<MedicineStock> getInventoryById(@PathVariable Integer medicineStockId)
			throws ProfileNotFoundException {
		MedicineStock medicineStock = medicineStockService.getInventoryById(medicineStockId);
		return new ResponseEntity<>(medicineStock, HttpStatus.OK);
	}

	@PostMapping("/medicineStock")
	public ResponseEntity<String> createInventory(@RequestBody MedicineStock inventory) {
		medicineStockService.createInventory(inventory);
		return new ResponseEntity<>("Inventory Created Successfully", HttpStatus.CREATED);
	}

	@PutMapping("/medicineStock/{medicineStockId}")
	public ResponseEntity<String> updateInventory(@PathVariable Integer medicineStockId,
			@RequestBody MedicineStock inventory) throws ProfileNotFoundException {
		medicineStockService.updateInventory(medicineStockId, inventory);
		return new ResponseEntity<>("Inventory Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/medicineStock/{medicineStockId}")
	public ResponseEntity<String> deleteInventory(@PathVariable Integer medicineStockId)
			throws ProfileNotFoundException {
		medicineStockService.deleteInventory(medicineStockId);
		return new ResponseEntity<>("Inventory Deleted Successfully", HttpStatus.OK);
	}
}
