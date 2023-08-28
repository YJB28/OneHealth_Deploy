package com.onehealth.pharmaordermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onehealth.pharmaordermanagement.entity.PharmacyOrder;

public interface PharmacyOrderRepository extends JpaRepository<PharmacyOrder, Long> {

	List<PharmacyOrder> findByCartId(Integer cartId);

	Optional<List<PharmacyOrder>> findByPatientId(Integer patientId);
//
//	Optional<List<PharmacyOrder>> findByPharmaId(String pharmaId);

	Optional<List<PharmacyOrder>> findByPaymentMode(String paymentMode);

	Optional<PharmacyOrder> findByDeliveryTrack(String deliveryTrack);

	Optional<PharmacyOrder> findByTransactionId(String transactionId);

	Optional<List<PharmacyOrder>> findByPaymentStatus(String paymentStatus);

//	Optional<List<PharmacyOrder>> findByPharmaId(Long pharmaId);

}
