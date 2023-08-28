package com.onehealth.pharmaordermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onehealth.pharmaordermanagement.entity.PharmacyOrder;
import com.onehealth.pharmaordermanagement.entity.PharmacyOrderDetails;
import com.onehealth.pharmaordermanagement.service.PharmacyOrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pharmacy-orders")
public class PharmacyOrderController {

	@Autowired
	private PharmacyOrderService pharmacyOrderService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/allOrders")
	public ResponseEntity<List<PharmacyOrder>> getAllPharmacyOrders() {
		List<PharmacyOrder> orders = pharmacyOrderService.getAllPharmacyOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

//	@PostMapping("/addOrder")
//	public ResponseEntity<?> createPharmacyOrder(@RequestBody PharmacyOrder pharmacyOrder) {
//
//		Optional<PharmacyOrder> createdOrder = pharmacyOrderService.createPharmacyOrder(pharmacyOrder);
//		
//		
//		if (createdOrder.isPresent()) {
//			return new ResponseEntity<>(createdOrder.get(), HttpStatus.CREATED);
//		}
//		return new ResponseEntity<>("Failed to create Pharmacy Order", HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@PostMapping("/addOrder")
	public ResponseEntity<?> createPharmacyOrder(@RequestBody PharmacyOrder pharmacyOrder) {

		// Fetch cart details using RestTemplate
		String cartApiUrl = "https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/api/medCarts/cart/"
				+ pharmacyOrder.getCartId();
		ResponseEntity<String> cartResponse = restTemplate.exchange(cartApiUrl, HttpMethod.GET, null, String.class);

		String cartDTO = cartResponse.getBody();

		System.out.println("cartDTO " + cartDTO);

		// Parse the cartDTO and create PharmacyOrderDetails
		List<PharmacyOrderDetails> orderDetailsList = parseCartDetails(cartDTO);

		// Create the PharmacyOrder
		PharmacyOrder createdOrder = pharmacyOrderService.createPharmacyOrder(pharmacyOrder);
//		Optional<PharmacyOrder> createdOrder = pharmacyOrderService.createPharmacyOrder(pharmacyOrder);

		// Set the created order for each order detail
		for (PharmacyOrderDetails orderDetail : orderDetailsList) {
			orderDetail.setPharmacyOrder(createdOrder);
		}

		// Save the PharmacyOrderDetails
		pharmacyOrderService.savePharmacyOrderDetails(orderDetailsList);

//		 http://localhost:8080/api/medCarts/clearCart/3
		System.out.println("cart removing.....for" + pharmacyOrder.getCartId());
		// Clear cart details using RestTemplate
		String cartClearApiUrl = "https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/api/medCarts/clearCart/"
				+ pharmacyOrder.getCartId();
		ResponseEntity<String> cartClearResponse = restTemplate.exchange(cartClearApiUrl, HttpMethod.DELETE, null,
				String.class);
		String cartclearMsg = cartClearResponse.getBody();
		System.out.println("cart removed....." + cartclearMsg);

		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	// Parse the cart details JSON and create PharmacyOrderDetails
	private List<PharmacyOrderDetails> parseCartDetails(String cartDTO) {
		List<PharmacyOrderDetails> orderDetailsList = new ArrayList<>();

		try {
			// Parse the JSON string
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode cartNode = objectMapper.readTree(cartDTO);

			// Extract the cart items array
			JsonNode cartItemsNode = cartNode.get("medicine_cart_items");

			// Iterate through the cart items
			if (cartItemsNode != null && cartItemsNode.isArray()) {
				for (JsonNode cartItemNode : cartItemsNode) {
					PharmacyOrderDetails orderDetail = new PharmacyOrderDetails();

					// Extract values from the cart item JSON
					orderDetail.setQuantity(cartItemNode.get("quantity").asInt());
					orderDetail.setMedicineId(cartItemNode.get("medicineId").asLong());
					orderDetail.setTotalProductPrice(cartItemNode.get("totalProductPrice").asDouble());
					orderDetail.setMedicineName(cartItemNode.get("medicineName").asText());
					orderDetail.setPharmaId(cartItemNode.get("pharmaId").asLong());

					// Add the order detail to the list
					orderDetailsList.add(orderDetail);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			// Handle parsing exception as needed
		}

		return orderDetailsList;
	}

	@GetMapping("/orderById/{orderId}")
	public ResponseEntity<?> getPharmacyOrder(@PathVariable Long orderId) {
		Optional<PharmacyOrder> order = pharmacyOrderService.getPharmacyOrder(orderId);
		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Pharmacy Order not found", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/updateOrderById/{orderId}")
	public ResponseEntity<?> updatePharmacyOrder(@PathVariable Long orderId, @RequestBody PharmacyOrder pharmacyOrder) {
		Optional<PharmacyOrder> updatedOrder = pharmacyOrderService.updatePharmacyOrder(orderId, pharmacyOrder);
		if (updatedOrder.isPresent()) {
			return new ResponseEntity<>("Pharmacy Order updated successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Pharmacy Order not found", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteOrderById/{orderId}")
	public ResponseEntity<?> deletePharmacyOrder(@PathVariable Long orderId) {
		try {
			pharmacyOrderService.deletePharmacyOrder(orderId);
			return new ResponseEntity<>("Pharmacy Order deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Pharmacy Order not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/byCartId/{cartId}")
	public ResponseEntity<List<PharmacyOrder>> getOrdersByCartId(@PathVariable Integer cartId) {
		List<PharmacyOrder> order = pharmacyOrderService.getOrdersByCartId(cartId);

		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@GetMapping("/byPatientId/{patientId}")
	public ResponseEntity<?> getOrdersByPatientId(@PathVariable Integer patientId) {
		Optional<List<PharmacyOrder>> order = pharmacyOrderService.getOrdersByPatientId(patientId);
		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Pharmacy Order not found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/byPharmaId/{pharmaId}")
	public ResponseEntity<?> getOrdersByPharmaId(@PathVariable Long pharmaId) {
		Optional<List<PharmacyOrderDetails>> order = pharmacyOrderService.getOrdersByPharmaId(pharmaId);
		System.out.println("order...."+ order);
		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Pharmacy Order not found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/byDeliveryTrack/{deliveryTrack}")
	public ResponseEntity<?> getOrdersByDeliveryTrack(@PathVariable String deliveryTrack) {
		Optional<PharmacyOrder> order = pharmacyOrderService.getOrdersByDeliveryTrack(deliveryTrack);
		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Pharmacy Order not found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/byPaymentMode/{paymentMode}")
	public ResponseEntity<?> getOrdersByPaymentMode(@PathVariable String paymentMode) {
		Optional<List<PharmacyOrder>> order = pharmacyOrderService.getOrdersByPaymentMode(paymentMode);
		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Pharmacy Order not found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/byPaymentStatus/{paymentStatus}")
	public ResponseEntity<?> getOrdersByPaymentStatus(@PathVariable String paymentStatus) {
		Optional<List<PharmacyOrder>> order = pharmacyOrderService.getOrdersByPaymentStatus(paymentStatus);
		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Pharmacy Order not found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/byTransactionId/{transactionId}")
	public ResponseEntity<?> getOrdersByTransactionId(@PathVariable String transactionId) {
		Optional<PharmacyOrder> order = pharmacyOrderService.getOrdersByTransactionId(transactionId);
		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Pharmacy Order not found", HttpStatus.NOT_FOUND);
	}
}
