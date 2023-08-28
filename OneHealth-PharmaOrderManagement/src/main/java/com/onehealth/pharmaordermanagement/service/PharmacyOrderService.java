package com.onehealth.pharmaordermanagement.service;

import java.util.List;
import java.util.Optional;

import com.onehealth.pharmaordermanagement.entity.PharmacyOrder;
import com.onehealth.pharmaordermanagement.entity.PharmacyOrderDetails;

public interface PharmacyOrderService {

	PharmacyOrder createPharmacyOrder(PharmacyOrder pharmacyOrder);

	void savePharmacyOrderDetails(List<PharmacyOrderDetails> orderDetailsList);

	Optional<PharmacyOrder> getPharmacyOrder(Long orderId);

	List<PharmacyOrder> getAllPharmacyOrders();

	Optional<PharmacyOrder> updatePharmacyOrder(Long orderId, PharmacyOrder pharmacyOrder);

	void deletePharmacyOrder(Long orderId);

	List<PharmacyOrder> getOrdersByCartId(Integer cartId);

	Optional<PharmacyOrder> getOrdersByDeliveryTrack(String deliveryTrack);

	Optional<List<PharmacyOrder>> getOrdersByPaymentMode(String paymentMode);

	Optional<PharmacyOrder> getOrdersByTransactionId(String transactionId);

	Optional<List<PharmacyOrder>> getOrdersByPatientId(Integer patientId);

	Optional<List<PharmacyOrder>> getOrdersByPaymentStatus(String paymentStatus);

	Optional<List<PharmacyOrderDetails>> getOrdersByPharmaId(Long pharmaId);

}
