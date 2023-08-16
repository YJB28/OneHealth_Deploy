package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
