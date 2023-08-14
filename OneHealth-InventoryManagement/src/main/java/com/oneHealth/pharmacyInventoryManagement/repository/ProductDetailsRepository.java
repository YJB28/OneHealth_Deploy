package com.oneHealth.pharmacyInventoryManagement.repository;

import com.oneHealth.pharmacyInventoryManagement.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

    // Define any custom repository methods if needed

}
