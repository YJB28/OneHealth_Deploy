package com.onehealth.pharmaordermanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.onehealth.pharmaordermanagement.entity.PharmacyOrder;
import com.onehealth.pharmaordermanagement.entity.PharmacyOrderDetails;
import com.onehealth.pharmaordermanagement.repository.PharmacyOrderRepository;
import com.onehealth.pharmaordermanagement.repository.pharmacyOrderDetailsRepository;
import com.onehealth.pharmaordermanagement.service.PharmacyOrderService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PharmacyOrderServiceImpl implements PharmacyOrderService {

	@Autowired
	private PharmacyOrderRepository pharmacyOrderRepository;

	@Autowired
	private pharmacyOrderDetailsRepository pharmacyOrderDetailsRepository;

	@Override
	public PharmacyOrder createPharmacyOrder(PharmacyOrder pharmacyOrder) {
		return pharmacyOrderRepository.save(pharmacyOrder);
	}

	@Override
	public void savePharmacyOrderDetails(List<PharmacyOrderDetails> orderDetailsList) {
		pharmacyOrderDetailsRepository.saveAll(orderDetailsList);
	}

	@Override
	public Optional<PharmacyOrder> getPharmacyOrder(Long orderId) {
		return pharmacyOrderRepository.findById(orderId);
	}

	@Override
	public List<PharmacyOrder> getAllPharmacyOrders() {
		return pharmacyOrderRepository.findAll();
	}

	@Override
	public Optional<PharmacyOrder> updatePharmacyOrder(Long orderId, PharmacyOrder pharmacyOrder) {
		if (pharmacyOrderRepository.existsById(orderId)) {
			pharmacyOrder.setOrderId(orderId);
			return Optional.ofNullable(pharmacyOrderRepository.save(pharmacyOrder));
		}
		return Optional.empty();
	}

	@Override
	public void deletePharmacyOrder(Long orderId) {
		pharmacyOrderRepository.deleteById(orderId);
	}

	@Override
	public List<PharmacyOrder> getOrdersByCartId(Integer cartId) {
		return pharmacyOrderRepository.findByCartId(cartId);
	}

	@Override
	public Optional<List<PharmacyOrder>> getOrdersByPatientId(Integer patientId) {
		return pharmacyOrderRepository.findByPatientId(patientId);
	}

//	@Override
//	public Optional<List<PharmacyOrder>> getOrdersByPharmaId(String pharmaId) {
//		return pharmacyOrderDetailsRepository.findByPharmaId(pharmaId);
//	}

	@Override
	public Optional<PharmacyOrder> getOrdersByDeliveryTrack(String deliveryTrack) {
		return pharmacyOrderRepository.findByDeliveryTrack(deliveryTrack);
	}

	@Override
	public Optional<List<PharmacyOrder>> getOrdersByPaymentMode(String paymentMode) {
		return pharmacyOrderRepository.findByPaymentMode(paymentMode);
	}

	@Override
	public Optional<PharmacyOrder> getOrdersByTransactionId(String transactionId) {
		return pharmacyOrderRepository.findByTransactionId(transactionId);
	}

	@Override
	public Optional<List<PharmacyOrder>> getOrdersByPaymentStatus(String paymentStatus) {
		// TODO Auto-generated method stub
		return pharmacyOrderRepository.findByPaymentStatus(paymentStatus);
	}

	@Override
	public Optional<List<PharmacyOrderDetails>> getDetailsByPharmaId(Long pharmaId) {
		return pharmacyOrderDetailsRepository.findByPharmaId(pharmaId);
	}

	@Override
	public Optional<List<PharmacyOrderDetails>> getOrderDetailsByOrderId(Long orderId) {
		return pharmacyOrderDetailsRepository.findByPharmacyOrder_OrderId(orderId);
	}

}
