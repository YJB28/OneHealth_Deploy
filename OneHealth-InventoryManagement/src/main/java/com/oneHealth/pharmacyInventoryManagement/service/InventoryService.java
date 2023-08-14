package com.oneHealth.pharmacyInventoryManagement.service;

import java.util.List;

import com.oneHealth.pharmacyInventoryManagement.entity.Inventory;
import com.oneHealth.pharmacyInventoryManagement.exception.DatabaseException;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.exception.RecordNotFoundException;

public interface InventoryService {

    Inventory createInventory(Inventory inventory);

    void updateInventory(long invId, Inventory inventory) throws ProfileNotFoundException;

    void deleteInventory(long invId) throws ProfileNotFoundException;

    List<Inventory>getAllInventories() throws DatabaseException;
    Inventory getInventoryById(long invId) throws RecordNotFoundException;
}
