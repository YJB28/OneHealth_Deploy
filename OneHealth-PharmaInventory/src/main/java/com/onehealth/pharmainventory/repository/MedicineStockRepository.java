package com.onehealth.pharmainventory.repository;

import com.onehealth.pharmainventory.entity.MedicineStock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineStockRepository extends JpaRepository<MedicineStock, Integer> {
}
