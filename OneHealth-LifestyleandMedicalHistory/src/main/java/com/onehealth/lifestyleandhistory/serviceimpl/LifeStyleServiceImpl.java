package com.onehealth.lifestyleandhistory.serviceimpl;

import com.onehealth.lifestyleandhistory.entity.LifeStyle;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;
import com.onehealth.lifestyleandhistory.repository.LifeStyleRepository;
import com.onehealth.lifestyleandhistory.service.LifeStyleService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the LifeStyleService interface providing operations for
 * managing lifestyle-related data. Author: Yogesh Baiskar
 */
@Service
public class LifeStyleServiceImpl implements LifeStyleService {

	@Autowired
	private LifeStyleRepository lifeStyleRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${apiGatewayUrl}") // Set the URL in your properties or configuration
	private String apiGatewayUrl;

	@Override
	public List<LifeStyle> getAllLifeStyles() {
		return lifeStyleRepository.findAll();
	}

	@Override
	public LifeStyle getLifestyleByPatientId(Long patientId) throws RecordNotFoundException {
		return lifeStyleRepository.findByPatientId(patientId)
				.orElseThrow(() -> new RecordNotFoundException("Lifestyle not found with patientId: " + patientId));
	}

	@Override
	public ResponseEntity<?> createLifestyle(LifeStyle lifestyle) {
		System.out.println(apiGatewayUrl);

		// Now, check if the patientId exists in the patientProfile microservice
		ResponseEntity<String> patientProfileResponse = restTemplate
				.getForEntity(apiGatewayUrl + "/patientProfile/byPatientId/" + lifestyle.getPatientId(), String.class);

		System.out.println(patientProfileResponse);
		if (patientProfileResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
			return ResponseEntity.badRequest().body("Patient with the specified patientId does not exist");
		} else if (patientProfileResponse.getStatusCode() != HttpStatus.OK) {
			// Handle other error statuses here, if needed
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
		}

		LifeStyle savedLifestyle = lifeStyleRepository.save(lifestyle);
		return ResponseEntity.ok(savedLifestyle);
	}

	@Override
	public LifeStyle updateLifestyleByPatientId(Long patientId, LifeStyle lifestyle) throws RecordNotFoundException {
		LifeStyle existingLifestyle = lifeStyleRepository.findByPatientId(patientId)
				.orElseThrow(() -> new RecordNotFoundException("Lifestyle not found with patientId: " + patientId));

		existingLifestyle.setSmoke(lifestyle.getSmoke());
		existingLifestyle.setAlcohol(lifestyle.getAlcohol());
		existingLifestyle.setExercise(lifestyle.getExercise());
		existingLifestyle.setFoodPreferences(lifestyle.getFoodPreferences());
		existingLifestyle.setOccupation(lifestyle.getOccupation());

		return lifeStyleRepository.save(existingLifestyle);
	}

	@Override
	@Transactional
	public void deleteLifestyleByPatientId(Long patientId) throws RecordNotFoundException {
		if (!lifeStyleRepository.existsByPatientId(patientId)) {
			throw new RecordNotFoundException("Lifestyle not found with patientId: " + patientId);
		}
		lifeStyleRepository.deleteByPatientId(patientId);
	}

	@Override
	public List<LifeStyle> getAllLifestyleByUserId(Long userId) {
		// TODO Auto-generated method stub
		return lifeStyleRepository.findAllByUserId(userId);
	}

	@Override
	public boolean existsByPatientIdAndUserId(Long patientId, Long userId) {
	    // Use your lifeStyleRepository to check if a record exists for the given patientId and userId
	    return lifeStyleRepository.existsByPatientIdAndUserId(patientId, userId);
	}

}
