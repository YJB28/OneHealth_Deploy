package com.onehealth.pharmaordermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onehealth.pharmaordermanagement.entity.PharmacyOrder;
import com.onehealth.pharmaordermanagement.entity.PharmacyOrderDetails;

public interface pharmacyOrderDetailsRepository extends JpaRepository<PharmacyOrderDetails, Long> {

	Optional<List<PharmacyOrderDetails>> findByPharmaId(Long pharmaId);

//	Optional<List<PharmacyOrder>> findByPharmaId(String pharmaId);

}
