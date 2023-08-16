package com.onehealth.pharmainventory.controller;


import com.onehealth.pharmainventory.entity.MedicineDetails;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.service.MedicineDetailsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/InventoryManagement")
public class MedicineDetailsController {

	@Autowired
	private MedicineDetailsService medicineDetailsService;
	
	@GetMapping("/medicineDetails/all")
	public ResponseEntity<List<MedicineDetails>> getAllMedicineDetails() {
		
		List<MedicineDetails> medicineDetails = medicineDetailsService.getAllMedicinesDetails();
		return new ResponseEntity<>(medicineDetails, HttpStatus.OK);
	}
	
	@GetMapping("/medicineDetailsById/{medicineDetailsId}")
	public ResponseEntity<MedicineDetails> getMedicineDetailsById(@PathVariable Integer medicineDetailsId)
			throws ProfileNotFoundException {
		MedicineDetails medicineDetails = medicineDetailsService.getMedicineDetailsById(medicineDetailsId);
		return new ResponseEntity<>(medicineDetails, HttpStatus.OK);
	}

	@GetMapping("/medicineDetailsByMedicineId/{medicineId}")
	public ResponseEntity<List<MedicineDetails>> getMedicineDetailsByMedicineId(@PathVariable Integer medicineId) {
		
		List<MedicineDetails> medicineDetails = medicineDetailsService.getAllMedicineDetailsByMedicineId(medicineId);
		return new ResponseEntity<>(medicineDetails, HttpStatus.OK);
	}

	@PostMapping("/medicineDetails")
	public ResponseEntity<String> createMedicineDetails(@RequestBody MedicineDetails medicineDetails) {
		medicineDetailsService.createMedicineDetails(medicineDetails);
		return new ResponseEntity<>("Medicine Details Created Successfully", HttpStatus.CREATED);
	}

	@PutMapping("/medicineDetails/{medicineDetailsId}")
	public ResponseEntity<String> updateMedicineDetails(@PathVariable Integer medicineDetailsId,
			@RequestBody MedicineDetails medicineDetails) throws ProfileNotFoundException {
		medicineDetailsService.updateMedicineDetails(medicineDetailsId, medicineDetails);
		return new ResponseEntity<>("Medicine Details Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/medicineDetails/{medicineDetailsId}")
	public ResponseEntity<String> deleteMedicineDetails(@PathVariable Integer medicineDetailsId)
			throws ProfileNotFoundException {
		medicineDetailsService.deleteMedicineDetails(medicineDetailsId);
		return new ResponseEntity<>("Medicine Details Deleted Successfully", HttpStatus.OK);
	}
}
