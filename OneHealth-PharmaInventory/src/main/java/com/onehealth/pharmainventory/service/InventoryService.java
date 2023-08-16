package com.onehealth.pharmainventory.service;

import java.util.List;

import com.onehealth.pharmainventory.entity.Inventory;
import com.onehealth.pharmainventory.exception.ProfileNotFoundException;

public interface InventoryService {
	
	
    Inventory getInventoryById(Integer inventoryId) throws ProfileNotFoundException;
    void createInventory(Inventory inventory);
    void updateInventory(Integer inventoryId, Inventory inventory) throws ProfileNotFoundException;
    void deleteInventory(Integer inventoryId) throws ProfileNotFoundException;
	List<Inventory> getAllInventories();
}
