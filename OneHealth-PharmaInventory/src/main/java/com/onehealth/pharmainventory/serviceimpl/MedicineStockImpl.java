package com.onehealth.pharmainventory.serviceimpl;

import com.onehealth.pharmainventory.entity.MedicineStock;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.repository.MedicineRepository;
import com.onehealth.pharmainventory.repository.MedicineStockRepository;
import com.onehealth.pharmainventory.service.MedicineStockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineStockImpl implements MedicineStockService {

	@Autowired
	private MedicineStockRepository medicineStockRepository;

	private static final Logger logger = LoggerFactory.getLogger(MedicineStockImpl.class);

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${apiGatewayUrl}")
	private String apiGatewayUrl;

	@Override
	public MedicineStock getInventoryById(Long medicineStockId) throws ProfileNotFoundException {
		Optional<MedicineStock> optionalInventory = medicineStockRepository.findById(medicineStockId);
		if (optionalInventory.isEmpty()) {
			throw new ProfileNotFoundException("Inventory not found with ID: " + medicineStockId);
		}
		return optionalInventory.get();
	}

	@Override
	public void createInventory(MedicineStock inventory) {
		logger.info("Received a POST request to create a new medicine.");

		Long pharmaId = inventory.getPharmaId();

		try {
			// Check if the pharmacy with the specified pharmaId exists
			ResponseEntity<String> pharmacyResponse = restTemplate
					.getForEntity(apiGatewayUrl + "/pharmacies/" + pharmaId, String.class);

			if (pharmacyResponse.getStatusCode() != HttpStatus.OK) {
				throw new ProfileNotFoundException("Pharmacy not found with this pharma ID: " + pharmaId);
			}

			medicineStockRepository.save(inventory);
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
	public void updateInventory(Long medicineStockId, MedicineStock inventory) throws ProfileNotFoundException {
		if (!medicineStockRepository.existsById(medicineStockId)) {
			throw new ProfileNotFoundException("Inventory not found with ID: " + medicineStockId);
		}
		inventory.setMedicineStockId(medicineStockId);
		inventory.setBatchNo(inventory.getBatchNo());
		inventory.setExpDate(inventory.getExpDate());
		inventory.setMedicineUnits(inventory.getMedicineUnits());
		inventory.setMfgDate(inventory.getMfgDate());
		inventory.setPackSize(inventory.getPackSize());
		inventory.setPrice(inventory.getPrice());

		medicineStockRepository.save(inventory);
	}

	@Override
	public void deleteInventory(Long medicineStockId) throws ProfileNotFoundException {
		if (!medicineStockRepository.existsById(medicineStockId)) {
			throw new ProfileNotFoundException("Inventory not found with ID: " + medicineStockId);
		}
		medicineStockRepository.deleteById(medicineStockId);
	}

	@Override
	public List<MedicineStock> getAllInventories() {
		// TODO Auto-generated method stub
		return medicineStockRepository.findAll();
	}

	@Override
	public List<MedicineStock> getMedicineStockByMedicineAndPharmaId(Long medicineId, Long pharmaId) {
		return medicineStockRepository.findByMedicine_MedicineIdAndPharmaId(medicineId, pharmaId);
	}

	@Override
	public MedicineStock getMedicineStockByMedicineId(Long medicineId) {
		Optional<MedicineStock> optionalInventory = medicineStockRepository.findByMedicine_MedicineId(medicineId);
		if (optionalInventory.isEmpty()) {
			throw new ProfileNotFoundException("Inventory not found with ID: " + medicineId);
		}
		return optionalInventory.get();
	}

	@Override
	public List<MedicineStock> getMedicineStockByPharmaId(Long pharmaId) {
		return medicineStockRepository.findByPharmaId(pharmaId);
	}

	@Override
	public boolean existsByMedicineId(Long medicineId) {
		// TODO Auto-generated method stub
		return medicineStockRepository.existsByMedicine_MedicineId(medicineId);
	}

	@Override
	public boolean existsByMedicineIdInMedicineSerice(Long medicineId) {
		// TODO Auto-generated method stub
		System.out.print(medicineId);
		return medicineRepository.existsById(medicineId);
	}
}
