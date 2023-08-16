package com.onehealth.pharmainventory.controller;

import com.onehealth.pharmainventory.entity.Medicine;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/InventoryManagement")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	
	@GetMapping("/")
	public String hello() {
		return "Hello From InventoryManagement";
	}
	
	@GetMapping("/medicines")
	public ResponseEntity<List<Medicine>> getAllMedicines() {
		List<Medicine> medicines = medicineService.getAllMedicines();
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	@GetMapping("/medicines/{medicineId}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable Integer medicineId) throws ProfileNotFoundException {
		Medicine medicine = medicineService.getMedicineById(medicineId);
		return new ResponseEntity<>(medicine, HttpStatus.OK);
	}

	@PostMapping("/medicines")
	public ResponseEntity<String> createMedicine(@RequestBody Medicine medicine) {
		medicineService.createMedicine(medicine);
		return new ResponseEntity<>("Medicine Created Successfully", HttpStatus.CREATED);
	}

	@PutMapping("/medicines/{medicineId}")
	public ResponseEntity<String> updateMedicine(@PathVariable Integer medicineId, @RequestBody Medicine medicine)
			throws ProfileNotFoundException {
		medicineService.updateMedicine(medicineId, medicine);
		return new ResponseEntity<>("Medicine Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/medicines/{medicineId}")
	public ResponseEntity<String> deleteMedicine(@PathVariable Integer medicineId) throws ProfileNotFoundException {
		medicineService.deleteMedicine(medicineId);
		return new ResponseEntity<>("Medicine Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/medicines/byName/{medicineName}")
	public ResponseEntity<List<Medicine>> getMedicinesByMedicineName(@PathVariable String medicineName) {
		List<Medicine> medicines = medicineService.getMedicinesByMedicineName(medicineName);
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	@GetMapping("/medicines/byCategoryId/{categoryId}")
	public ResponseEntity<List<Medicine>> getMedicinesByCategoryId(@PathVariable Integer categoryId) {
		List<Medicine> medicines = medicineService.getMedicinesByCategoryId(categoryId);
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}
}
