package com.oneHealth.pharmacyInventoryManagement.repository;

import com.oneHealth.pharmacyInventoryManagement.entity.MedicineCategory;
import com.oneHealth.pharmacyInventoryManagement.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	

	

	

    // Define any custom repository methods if needed

}

