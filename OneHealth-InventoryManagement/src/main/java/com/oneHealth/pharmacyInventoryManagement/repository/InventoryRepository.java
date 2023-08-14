package com.oneHealth.pharmacyInventoryManagement.repository;

import com.oneHealth.pharmacyInventoryManagement.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // Define any custom repository methods if needed

}
