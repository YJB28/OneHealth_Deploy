package com.onehealth.lifestyleandhistory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onehealth.lifestyleandhistory.entity.LifeStyle;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;
import com.onehealth.lifestyleandhistory.service.LifeStyleService;

import java.util.List;

/**
 * The LifeStyleController class handles HTTP requests related to lifestyle
 * information. Author: Yogesh Baiskar
 */
//@CrossOrigin("*")
@RestController
@RequestMapping("/lifeStyleAndHistory/lifeStyle")
public class LifeStyleController {

	private static final Logger logger = LoggerFactory.getLogger(LifeStyleController.class);

	@Autowired
	private LifeStyleService lifestyleService;

	/**
	 * Handles a GET request to the root endpoint. Returns a simple greeting
	 * message.
	 *
	 * @return A greeting message.
	 */
	@GetMapping
	public String Hello() {
		logger.info("Received a GET request to root endpoint");
		return "Hello from /lifeStyleAndHistory/lifeStyle/";
	}

	/**
	 * Handles a GET request to retrieve all lifestyles.
	 *
	 * @return A list of all lifestyles along with an appropriate HTTP status.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<LifeStyle>> getAllLifestyles() {
		logger.info("Received a GET request to retrieve all lifestyles");

		List<LifeStyle> lifestyles = lifestyleService.getAllLifeStyles();
		logger.info("Retrieved {} lifestyles", lifestyles.size());

		return new ResponseEntity<>(lifestyles, HttpStatus.OK);
	}

	/**
	 * Handles a GET request to retrieve a specific lifestyle by its serial number.
	 *
	 * @param lID The serial number of the lifestyle to retrieve.
	 * @return The retrieved lifestyle along with an appropriate HTTP status.
	 * @throws RecordNotFoundException If the specified lifestyle is not found.
	 */
	@GetMapping("/byPatientId/{patientId}")
	public ResponseEntity<LifeStyle> getLifestyleByPatientId(@PathVariable Long patientId)
			throws RecordNotFoundException {
		logger.info("Received a GET request to retrieve lifestyle by patientId : {}", patientId);

		LifeStyle lifestyle = lifestyleService.getLifestyleByPatientId(patientId);
		logger.info("Retrieved lifestyle: {}", lifestyle);

		return new ResponseEntity<>(lifestyle, HttpStatus.OK);
	}

	@GetMapping("/AllbyUserId/{userId}")
	public ResponseEntity<List<LifeStyle>> getAllLifestyleByUserId(@PathVariable Long userId)
			throws RecordNotFoundException {
		logger.info("Received a GET request to retrieve lifestyle by patientId : {}", userId);

		List<LifeStyle> lifestyle = lifestyleService.getAllLifestyleByUserId(userId);
		logger.info("Retrieved lifestyle: {}", lifestyle);

		return new ResponseEntity<>(lifestyle, HttpStatus.OK);
	}

	/**
	 * Handles a POST request to create a new lifestyle.
	 *
	 * @param lifestyle The lifestyle details to be created.
	 * @return A success message along with an appropriate HTTP status.
	 */
	@PostMapping
	public ResponseEntity<String> createLifestyle(@RequestBody LifeStyle lifestyle) {
		logger.info("Received a POST request to create a new lifestyle");

		// Check if a lifestyle record already exists for the given patientId and userId
		boolean lifestyleExists = lifestyleService.existsByPatientIdAndUserId(lifestyle.getPatientId(), lifestyle.getUserId());
		System.out.println(lifestyleExists);
		if (lifestyleExists) {
		    return ResponseEntity.badRequest().body("A lifestyle record already exists for this patientId and userId");
		}

		// Create the lifestyle record since it doesn't exist and patientId is valid
		lifestyleService.createLifestyle(lifestyle);

		return new ResponseEntity<>("LifeStyle Added Successfully", HttpStatus.CREATED);
	}

	/**
	 * Handles a PUT request to update an existing lifestyle.
	 *
	 * @param lID       The serial number of the lifestyle to update.
	 * @param lifestyle The updated lifestyle details.
	 * @return A success message along with an appropriate HTTP status.
	 * @throws RecordNotFoundException If the specified lifestyle is not found.
	 */
	@PutMapping("/updateByPatientId/{patientId}")
	public ResponseEntity<String> updateLifestyleByPatientId(@PathVariable Long patientId,
			@RequestBody LifeStyle lifestyle) throws RecordNotFoundException {
		logger.info("Received a PUT request to update lifestyle with patientId : {}", patientId);

		lifestyleService.updateLifestyleByPatientId(patientId, lifestyle);

		return new ResponseEntity<>(" LifeStyle Updated Successfully", HttpStatus.OK);
	}

	/**
	 * Handles a DELETE request to delete a specific lifestyle by its serial number.
	 *
	 * @param lID The serial number of the lifestyle to delete.
	 * @return A success message along with an appropriate HTTP status.
	 * @throws RecordNotFoundException If the specified lifestyle is not found.
	 */
	@DeleteMapping("/deleteByPatientId/{patientId}")
	public ResponseEntity<String> deleteLifestyle(@PathVariable Long patientId) {
		try {
			lifestyleService.deleteLifestyleByPatientId(patientId);
			return new ResponseEntity<>("LifeStyle Deleted Successfully", HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity<>("LifeStyle not found with patientId: " + patientId, HttpStatus.NOT_FOUND);
		}
	}

}
