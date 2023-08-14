package com.oneHealth.pharmacyInventoryManagement.controller;

import com.oneHealth.pharmacyInventoryManagement.entity.Inventory;
import com.oneHealth.pharmacyInventoryManagement.exception.DatabaseException;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.exception.RecordNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
//@RequestMapping("/MedicalInventories")
@RequestMapping("/InventoryManagement")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/medicalInventories")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from /InventoryManagement/medicalInventories");
    }

    @GetMapping("/medicalInventories/all")
    public ResponseEntity<List<Inventory>> getAllInventories() throws DatabaseException {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/medicalInventories/{invId}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long invId) throws RecordNotFoundException {
        Inventory inventory = inventoryService.getInventoryById(invId);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PostMapping("/MedicalInventories/add")
    public ResponseEntity<String> createInventory(@RequestBody Inventory inventory) {
        inventoryService.createInventory(inventory);
        return new ResponseEntity<>("Inventory Created Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/medicalInventories/update/{invId}")
    public ResponseEntity<String> updateInventory(@PathVariable Long invId, @RequestBody Inventory inventory)
            throws ProfileNotFoundException {
        inventoryService.updateInventory(invId, inventory);
        return new ResponseEntity<>("Inventory Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/medicalInventories/delete/{invId}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long invId) throws  ProfileNotFoundException {
        inventoryService.deleteInventory(invId);
        return new ResponseEntity<>("Inventory Deleted Successfully", HttpStatus.OK);
    }
}
