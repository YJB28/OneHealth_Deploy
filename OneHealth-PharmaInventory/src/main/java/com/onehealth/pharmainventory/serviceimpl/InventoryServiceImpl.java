package com.onehealth.pharmainventory.serviceimpl;

import com.onehealth.pharmainventory.entity.Inventory;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;
import com.onehealth.pharmainventory.repository.InventoryRepository;
import com.onehealth.pharmainventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory getInventoryById(Integer inventoryId) throws ProfileNotFoundException {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
        if (optionalInventory.isEmpty()) {
            throw new ProfileNotFoundException("Inventory not found with ID: " + inventoryId);
        }
        return optionalInventory.get();
    }

    @Override
    public void createInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Override
    public void updateInventory(Integer inventoryId, Inventory inventory) throws ProfileNotFoundException {
        if (!inventoryRepository.existsById(inventoryId)) {
            throw new ProfileNotFoundException("Inventory not found with ID: " + inventoryId);
        }
        inventory.setInventoryId(inventoryId);
        inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(Integer inventoryId) throws ProfileNotFoundException {
        if (!inventoryRepository.existsById(inventoryId)) {
            throw new ProfileNotFoundException("Inventory not found with ID: " + inventoryId);
        }
        inventoryRepository.deleteById(inventoryId);
    }

	@Override
	public List<Inventory> getAllInventories() {
		// TODO Auto-generated method stub
		return inventoryRepository.findAll();
	}
}
