package com.onehealth.trackingorder.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.onehealth.trackingorder.dto.DestinationDetailsDto;
import com.onehealth.trackingorder.dto.OrderDetailsDto;
import com.onehealth.trackingorder.entity.TrackingOrder;
import com.onehealth.trackingorder.exception.TrackingOrderNotFoundException;
import com.onehealth.trackingorder.repository.TrackingOrderRepository;
import com.onehealth.trackingorder.service.TrackingOrderService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class TrackingOrderServiceImpl implements TrackingOrderService {

	@Autowired
	private TrackingOrderRepository trackingOrderRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<TrackingOrder> getAllTrackingOrders() {
		return trackingOrderRepository.findAll();
	}

	public TrackingOrder createTrackingOrder(TrackingOrder trackingOrder) {
		// Implement the logic to create a tracking order
		// You can generate the tracking number and set the created_at timestamp here

		System.out.println("orderId " + trackingOrder.getOrderId());
		System.out.println("trackingOrder " + trackingOrder);

		// Define the type of the response entity as a list of OrderDetailsDto
		ParameterizedTypeReference<List<OrderDetailsDto>> responseType = new ParameterizedTypeReference<List<OrderDetailsDto>>() {
		};

		// Use exchange to get a list of OrderDetailsDto
		ResponseEntity<List<OrderDetailsDto>> response = restTemplate.exchange(
				"https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/pharmacy-orders/orderDetails/byOrderId/"
						+ trackingOrder.getOrderId(),
				HttpMethod.GET, null, responseType);

		// Get the list of OrderDetailsDto from the response
		List<OrderDetailsDto> orderDetailsList = response.getBody();
		System.out.println("orderDetailsList");
//		System.out.println("orderDetailsList");
		System.out.println(orderDetailsList.get(0));
		// For demonstration, assuming you want the first item from the list
		Long patientId = orderDetailsList.get(0).getPharmacyOrder().getPatientId();
		if (orderDetailsList != null && !orderDetailsList.isEmpty()) {
			OrderDetailsDto orderDetails = orderDetailsList.get(0);
			// Now you can use orderDetails as needed
			System.out.println(orderDetails.getPharmacyOrder().getPaymentMode());
			trackingOrder.setPaymentMode(orderDetails.getPharmacyOrder().getPaymentMode());
			trackingOrder.setDestinationLocation(orderDetails.getPharmacyOrder().getDeliveryAddress());

			patientId = orderDetails.getPharmacyOrder().getPatientId();
		}

		ResponseEntity<DestinationDetailsDto> responsePatient = restTemplate.exchange(
				"https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/patientProfile/" + patientId,
				HttpMethod.GET, null, DestinationDetailsDto.class);

		System.out.println("responsePatient " + responsePatient);
		System.out.println("responsePatient " + responsePatient.getBody().getPinCode());
		trackingOrder.setDestinationPinCode(responsePatient.getBody().getPinCode());

//		TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
//		Calendar istCalendar = Calendar.getInstance(istTimeZone);
//
//		Date currentDateAndTimeInIST = istCalendar.getTime();
//		System.out.println(currentDateAndTimeInIST);
//		trackingOrder.setCreatedAt(currentDateAndTimeInIST);
		trackingOrder.setCreatedAt(new Date());

		return trackingOrderRepository.save(trackingOrder);
	}

	@Override
	public TrackingOrder getTrackingOrderById(Long trackingId) {
		// Implement the logic to retrieve a tracking order by its trackingId from the
		// repository
		// Return the tracking order or throw a custom exception if not found

		Optional<TrackingOrder> optionalTrackingOrder = trackingOrderRepository.findById(trackingId);

		if (optionalTrackingOrder.isPresent()) {
			return optionalTrackingOrder.get();
		} else {
			throw new TrackingOrderNotFoundException("Tracking order not found with ID: " + trackingId);
		}
	}

	@Override
	public TrackingOrder updateTrackingOrder(Long trackingId, TrackingOrder trackingOrder1) {
		// Implement the logic to update a tracking order by its trackingId with the
		// data from the request
		// Save the updated tracking order to the repository and return it

		Optional<TrackingOrder> optionalTrackingOrder = trackingOrderRepository.findById(trackingId);

		if (optionalTrackingOrder.isPresent()) {
			TrackingOrder order = optionalTrackingOrder.get();

			// Update the fields with data from the requestDto
			// For example:

			order.setCurrentLocation(trackingOrder1.getCurrentLocation());
			order.setCurrentPinCode(trackingOrder1.getCurrentPinCode());
			order.setNextLocation(trackingOrder1.getNextLocation());
			order.setNextPinCode(trackingOrder1.getNextPinCode());
			order.setCurrentStatus(trackingOrder1.getCurrentStatus());

			// Save the updated tracking order
			return trackingOrderRepository.save(order);
		} else {
			throw new TrackingOrderNotFoundException("Tracking order not found with ID: " + trackingId);
		}
	}

	@Override
	public void deleteTrackingOrder(Long trackingId) {
		// Implement the logic to delete a tracking order by its trackingId from the
		// repository
		// Throw a custom exception if the tracking order is not found

		Optional<TrackingOrder> optionalTrackingOrder = trackingOrderRepository.findById(trackingId);

		if (optionalTrackingOrder.isPresent()) {
			trackingOrderRepository.deleteById(trackingId);
		} else {
			throw new TrackingOrderNotFoundException("Tracking order not found with ID: " + trackingId);
		}
	}

	@Override
	public TrackingOrder getTrackingOrderByConsignmentNumber(String consignmentNumber) {
		// Implement the logic to retrieve a tracking order by its consignmentNumber
		// from the repository
		// Return the tracking order or throw a custom exception if not found

		TrackingOrder trackingOrder = trackingOrderRepository.findByConsignmentNumber(consignmentNumber);

		if (trackingOrder != null) {
			return trackingOrder;
		} else {
			throw new TrackingOrderNotFoundException(
					"Tracking order not found with consignment number: " + consignmentNumber);
		}
	}

	@Override
	public TrackingOrder getTrackingOrderByOrderId(Long orderId) {
		// Implement the logic to retrieve a tracking order by its orderId from the
		// repository
		// Return the tracking order or throw a custom exception if not found

		TrackingOrder trackingOrder = trackingOrderRepository.findByOrderId(orderId);

		if (trackingOrder != null) {
			return trackingOrder;
		} else {
			throw new TrackingOrderNotFoundException("Tracking order not found with order ID: " + orderId);
		}
	}

	@Override
	public TrackingOrder checkedExistOrNot(String consignmentNumber) {
		// TODO Auto-generated method stub
		return trackingOrderRepository.findByConsignmentNumber(consignmentNumber);
	}

}
