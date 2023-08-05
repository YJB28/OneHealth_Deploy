package com.onehealth.lifestyleandhistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onehealth.lifestyleandhistory.DTO.ResponseMessage;
import com.onehealth.lifestyleandhistory.entity.MedicalHistory;
import com.onehealth.lifestyleandhistory.exception.DatabaseException;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;
import com.onehealth.lifestyleandhistory.service.MedicalHistoryService;

import java.util.List;

@RestController
@RequestMapping("/lifeStyleAndHistory/MedicalHistory")
public class MedicalHistoryController {

	@Autowired
	private MedicalHistoryService medicalHistoryService;

	@GetMapping
	public String Hello() {

		return "Hello From /lifeStyleAndHistory/MedicalHistory ";
	}

	@GetMapping("/all")
	public ResponseEntity<List<MedicalHistory>> getAllMedicalHistories() {
		List<MedicalHistory> medicalHistories = medicalHistoryService.getAllMedicalHistories();
		return new ResponseEntity<>(medicalHistories, HttpStatus.OK);
	}

	@GetMapping("/{recordId}")
	public ResponseEntity<MedicalHistory> getMedicalHistoryByRecordId(@PathVariable Long recordId)
			throws RecordNotFoundException {
		MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistoryByRecordId(recordId);
		return new ResponseEntity<>(medicalHistory, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResponseMessage<MedicalHistory>> createMedicalHistory(@RequestBody MedicalHistory medicalHistory) throws DatabaseException{
		MedicalHistory createdMedicalHistory = medicalHistoryService.createMedicalHistory(medicalHistory);
		
		ResponseMessage<MedicalHistory> responseMessage = new ResponseMessage<>("History Added Successfully",
				createdMedicalHistory);
		return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
		
	}

	@PutMapping("/{recordId}")
	public ResponseEntity<ResponseMessage<MedicalHistory>> updateMedicalHistory(@PathVariable Long recordId,
			@RequestBody MedicalHistory medicalHistory) throws RecordNotFoundException {
		MedicalHistory updatedMedicalHistory = medicalHistoryService.updateMedicalHistory(recordId, medicalHistory);
		ResponseMessage<MedicalHistory> responseMessage = new ResponseMessage<>("Update record successfully",
				updatedMedicalHistory);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	@DeleteMapping("/{recordId}")
	public ResponseEntity<String> deleteMedicalHistoryByRecordId(@PathVariable Long recordId)
			throws RecordNotFoundException {
		medicalHistoryService.deleteMedicalHistoryByRecordId(recordId);
		return new ResponseEntity<>("Delete Record Successfully",HttpStatus.OK);
	}

	@DeleteMapping("/patient/{patientId}/user/{userId}")
	public ResponseEntity<String> deleteMedicalHistoryByPatientIdAndUserId(@PathVariable Long patientId,
			@PathVariable Long userId) throws RecordNotFoundException {
		medicalHistoryService.deleteMedicalHistoryByPatientIdAndUserId(patientId, userId);
		return new ResponseEntity<>("Delete All Record Successfully",HttpStatus.OK);
	}
}
