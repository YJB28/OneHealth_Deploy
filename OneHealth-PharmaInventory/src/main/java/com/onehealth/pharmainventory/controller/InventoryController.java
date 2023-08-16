package com.onehealth.pharmainventory.controller;

import com.onehealth.pharmainventory.entity.Inventory;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.service.InventoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/InventoryManagement")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping("/inventory/all")
	public ResponseEntity<List<Inventory>> getAllInventory() {
		
		List<Inventory> inventories = inventoryService.getAllInventories();
		return new ResponseEntity<>(inventories, HttpStatus.OK);
	}
	
	@GetMapping("/inventory/{inventoryId}")
	public ResponseEntity<Inventory> getInventoryById(@PathVariable Integer inventoryId) throws ProfileNotFoundException {
		Inventory inventory = inventoryService.getInventoryById(inventoryId);
		return new ResponseEntity<>(inventory, HttpStatus.OK);
	}

	@PostMapping("/inventory")
	public ResponseEntity<String> createInventory(@RequestBody Inventory inventory) {
		inventoryService.createInventory(inventory);
		return new ResponseEntity<>("Inventory Created Successfully", HttpStatus.CREATED);
	}

	@PutMapping("/inventory/{inventoryId}")
	public ResponseEntity<String> updateInventory(@PathVariable Integer inventoryId, @RequestBody Inventory inventory)
			throws ProfileNotFoundException {
		inventoryService.updateInventory(inventoryId, inventory);
		return new ResponseEntity<>("Inventory Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/inventory/{inventoryId}")
	public ResponseEntity<String> deleteInventory(@PathVariable Integer inventoryId) throws ProfileNotFoundException {
		inventoryService.deleteInventory(inventoryId);
		return new ResponseEntity<>("Inventory Deleted Successfully", HttpStatus.OK);
	}
}
