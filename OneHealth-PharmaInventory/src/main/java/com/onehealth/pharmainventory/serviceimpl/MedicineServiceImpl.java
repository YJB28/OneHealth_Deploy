package com.onehealth.pharmainventory.serviceimpl;

import com.onehealth.pharmainventory.entity.Medicine;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.repository.MedicineRepository;
import com.onehealth.pharmainventory.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MedicineServiceImpl implements MedicineService {

	private static final Logger logger = LoggerFactory.getLogger(MedicineServiceImpl.class);

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${apiGatewayUrl}")
	private String apiGatewayUrl;

	@Override
	public List<Medicine> getAllMedicines() {
		logger.info("Fetching all medicines.");
		return medicineRepository.findAll();
	}

	@Override
	public void createMedicine(Medicine medicine) {
		logger.info("Received a POST request to create a new medicine.");

		Long pharmaId = medicine.getPharmaId();

		try {
			// Check if the pharmacy with the specified pharmaId exists
			ResponseEntity<String> pharmacyResponse = restTemplate
					.getForEntity(apiGatewayUrl + "/pharmacies/" + pharmaId, String.class);

			if (pharmacyResponse.getStatusCode() != HttpStatus.OK) {
				throw new ProfileNotFoundException("Pharmacy not found with this pharma ID: " + pharmaId);
			}

			medicineRepository.save(medicine);
			logger.info("Medicine created successfully.");
		} catch (HttpClientErrorException.NotFound e) {
			// Handle the HttpClientErrorException.NotFound exception with a custom error
			// message
			logger.error("Pharmacy not found with ID: " + pharmaId);
			throw new ProfileNotFoundException("Pharmacy not found with ID: " + pharmaId);
		} catch (Exception e) {
			// Handle any other exceptions that might occur during the request or saving
			// process
			logger.error("An internal server error occurred while creating the medicine.", e);
			throw new ProfileNotFoundException(
					"An internal server error occurred in Pharma Microservice while creating the medicine(Pharma Not Exist)."
							+ pharmaId);
		}
	}

	@Override
	public void updateMedicine(Long medicineId, Medicine medicine) throws ProfileNotFoundException {
		logger.info("Received a PUT request to update a medicine with ID: {}", medicineId);
		if (!medicineRepository.existsById(medicineId)) {
			throw new ProfileNotFoundException("Medicine not found with ID: " + medicineId);
		}
		medicine.setMedicineId(medicineId);
		medicineRepository.save(medicine);
		logger.info("Medicine updated successfully.");
	}

	@Override
	public void deleteMedicine(Long medicineId) throws ProfileNotFoundException {
		logger.info("Received a DELETE request to delete a medicine with ID: {}", medicineId);
		if (!medicineRepository.existsById(medicineId)) {
			throw new ProfileNotFoundException("Medicine not found with ID: " + medicineId);
		}
		medicineRepository.deleteById(medicineId);
		logger.info("Medicine deleted successfully.");
	}

	@Override
	public List<Medicine> getMedicinesByMedicineName(String medicineName) {
		logger.info("Fetching medicines by name: {}", medicineName);
		return medicineRepository.findByMedicineNameContainingIgnoreCase(medicineName);
	}

	@Override
	public List<Medicine> getMedicinesByCategoryId(Integer categoryId) {
		logger.info("Fetching medicines by category ID: {}", categoryId);
		return medicineRepository.findByCategory_CategoryId(categoryId);
	}

	@Override
	public Medicine getMedicineById(Long medicineId) throws ProfileNotFoundException {
		logger.info("Fetching medicine by ID: {}", medicineId);
		Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
		if (optionalMedicine.isEmpty()) {
			throw new ProfileNotFoundException("Medicine not found with ID: " + medicineId);
		}
		return optionalMedicine.get();
	}

	@Override
	public List<Medicine> getMedicinesByPharmaId(Long pharmaId) {
		logger.info("Fetching medicines by pharmacy ID: {}", pharmaId);
		return medicineRepository.findAllByPharmaId(pharmaId);
	}

	@Override
	public boolean existsByPharmaIdAndMedicineNameIgnoreCase(Long pharmaId, String medicineName) {
		logger.info("Checking if medicine exists with pharmacy ID: {} and name: {}", pharmaId, medicineName);
		return medicineRepository.existsByPharmaIdAndMedicineNameIgnoreCase(pharmaId, medicineName);
	}
}
