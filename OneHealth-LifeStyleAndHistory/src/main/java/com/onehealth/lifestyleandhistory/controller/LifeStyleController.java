package com.onehealth.lifestyleandhistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onehealth.lifestyleandhistory.entity.LifeStyle;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;
import com.onehealth.lifestyleandhistory.service.LifeStyleService;

import java.util.List;

@RestController
@RequestMapping("/lifeStyleAndHistory/lifeStyle")
public class LifeStyleController {

	@Autowired
	private LifeStyleService lifestyleService;
	
	@GetMapping
	public String Hello() {
		return "Hello from /lifeStyleAndHistory/lifeStyle/";
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<LifeStyle>> getAllLifestyles() {
		List<LifeStyle> lifestyles = lifestyleService.getAllLifeStyles();
		return new ResponseEntity<>(lifestyles, HttpStatus.OK);
	}

	@GetMapping("/{lID}")
	public ResponseEntity<LifeStyle> getLifestyleBySNo(@PathVariable Long lID) throws RecordNotFoundException {
		LifeStyle lifestyle = lifestyleService.getLifestyleBylId(lID);
		return new ResponseEntity<>(lifestyle, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createLifestyle(@RequestBody LifeStyle lifestyle) {
		lifestyleService.createLifestyle(lifestyle);
		return new ResponseEntity<>(" LifeStyle Created Successfully", HttpStatus.CREATED);
	}

	@PutMapping("/{lID}")
	public ResponseEntity<String> updateLifestyle(@PathVariable Long lID, @RequestBody LifeStyle lifestyle)
			throws RecordNotFoundException {
		LifeStyle updatedLifestyle = lifestyleService.updateLifestyle(lID, lifestyle);
		return new ResponseEntity<>(" LifeStyle Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/{lID}")
	public ResponseEntity<String> deleteLifestyle(@PathVariable Long lID) throws RecordNotFoundException {
		lifestyleService.deleteLifestyle(lID);
		return new ResponseEntity<>(" LifeStyle Deleted Successfully",HttpStatus.OK);
	}
}
