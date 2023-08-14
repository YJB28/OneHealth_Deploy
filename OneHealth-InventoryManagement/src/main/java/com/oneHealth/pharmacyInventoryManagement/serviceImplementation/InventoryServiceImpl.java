package com.oneHealth.pharmacyInventoryManagement.serviceImplementation;

import com.oneHealth.pharmacyInventoryManagement.entity.Inventory;
import com.oneHealth.pharmacyInventoryManagement.exception.DatabaseException;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.exception.RecordNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.repository.InventoryRepository;
import com.oneHealth.pharmacyInventoryManagement.service.InventoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
    private InventoryRepository inventoryRepository;
    
   
    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    
    @Override
    public void updateInventory(long invId, Inventory inventory) throws ProfileNotFoundException {
        Inventory existingInventory = inventoryRepository.findById(invId)
                .orElseThrow(() -> new ProfileNotFoundException("Inventory not found with ID " + invId));

        // Update the fields of the existing inventory with the values from the input inventory
        existingInventory.setPharmaId(inventory.getPharmaId());
        existingInventory.setMedUnits(inventory.getMedUnits());
        existingInventory.setManufacturingDate(inventory.getManufacturingDate());
        existingInventory.setPackSize(inventory.getPackSize());

        // Save the updated inventory
         inventoryRepository.save(existingInventory);
    }


    @Override
    public void deleteInventory(long invId) throws ProfileNotFoundException {
        Inventory existingInventory = inventoryRepository.findById(invId)
        		.orElseThrow(() -> new ProfileNotFoundException("Inventory not found with Id" + invId));
        inventoryRepository.delete(existingInventory);
    }

    @Override
    public Inventory getInventoryById(long invId)  throws RecordNotFoundException {
        return inventoryRepository.findById(invId)
        		.orElseThrow(() -> new RecordNotFoundException("Inventory not found with ID " + invId));
    }

	@Override
	public List<Inventory> getAllInventories() throws DatabaseException {
		List<Inventory> inventories = inventoryRepository.findAll();
		if(inventories.isEmpty()) {
			throw new DatabaseException("No inventory records found");
		}
		return inventories;
	}
}
