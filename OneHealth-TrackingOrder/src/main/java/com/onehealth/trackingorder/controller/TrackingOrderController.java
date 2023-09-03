package com.onehealth.trackingorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onehealth.trackingorder.entity.TrackingOrder;
import com.onehealth.trackingorder.service.TrackingOrderService;

import java.util.List;

@RestController
@RequestMapping("/tracking-orders")
public class TrackingOrderController {

	@Autowired
	private TrackingOrderService trackingOrderService;

	@GetMapping("/all")
	public ResponseEntity<List<TrackingOrder>> getAllTrackingOrders() {
		List<TrackingOrder> trackingOrders = trackingOrderService.getAllTrackingOrders();
		return new ResponseEntity<>(trackingOrders, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createTrackingOrder(@RequestBody TrackingOrder trackingOrder) {
		// Check if a TrackingOrder with the same consignment number already exists
		TrackingOrder existingTrackingOrder = trackingOrderService
				.checkedExistOrNot(trackingOrder.getConsignmentNumber());

		if (existingTrackingOrder != null) {
			// If it exists, return a response indicating that the consignment number is
			// already in use
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Consignment number already exists for " + trackingOrder.getConsignmentNumber());
		} else {
			// If it doesn't exist, proceed to create the new TrackingOrder

			TrackingOrder createdTrackingOrder = trackingOrderService.createTrackingOrder(trackingOrder);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdTrackingOrder);

		}
	}

	@GetMapping("/{trackingId}")
	public ResponseEntity<TrackingOrder> getTrackingOrderById(@PathVariable Long trackingId) {
		TrackingOrder trackingOrder = trackingOrderService.getTrackingOrderById(trackingId);
		return new ResponseEntity<>(trackingOrder, HttpStatus.OK);
	}

	@PutMapping("/{trackingId}")
	public ResponseEntity<TrackingOrder> updateTrackingOrder(@PathVariable Long trackingId,
			@RequestBody TrackingOrder trackingOrder) {
		TrackingOrder updatedTrackingOrder = trackingOrderService.updateTrackingOrder(trackingId, trackingOrder);
		return new ResponseEntity<>(updatedTrackingOrder, HttpStatus.OK);
	}

	@DeleteMapping("/{trackingId}")
	public ResponseEntity<String> deleteTrackingOrder(@PathVariable Long trackingId) {
		trackingOrderService.deleteTrackingOrder(trackingId);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/byConsignmentNumber/{consignmentNumber}")
	public ResponseEntity<TrackingOrder> getTrackingOrderByConsignmentNumber(@PathVariable String consignmentNumber) {
		TrackingOrder trackingOrder = trackingOrderService.getTrackingOrderByConsignmentNumber(consignmentNumber);
		return new ResponseEntity<>(trackingOrder, HttpStatus.OK);
	}

	@GetMapping("/byOrderId/{orderId}")
	public ResponseEntity<TrackingOrder> getTrackingOrderByOrderId(@PathVariable Long orderId) {
		TrackingOrder trackingOrder = trackingOrderService.getTrackingOrderByOrderId(orderId);
		return new ResponseEntity<>(trackingOrder, HttpStatus.OK);
	}
}