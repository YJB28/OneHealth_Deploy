package com.onehealth.trackingorder.dto;

import java.util.Date;

public class PharmacyOrderDto {

	private Long orderId;
	private Long cartId;
	private Long patientId;
	private Double cartTotalPrice;
	private String paymentMode;
	private String paymentStatus;
	private String transactionId;
	private String deliveryTrack;
	private String deliveryAddress;
	private Date orderedTime;

	public PharmacyOrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PharmacyOrderDto(Long orderId, Long cartId, Long patientId, Double cartTotalPrice, String paymentMode,
			String paymentStatus, String transactionId, String deliveryTrack, String deliveryAddress,
			Date orderedTime) {
		super();
		this.orderId = orderId;
		this.cartId = cartId;
		this.patientId = patientId;
		this.cartTotalPrice = cartTotalPrice;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
		this.transactionId = transactionId;
		this.deliveryTrack = deliveryTrack;
		this.deliveryAddress = deliveryAddress;
		this.orderedTime = orderedTime;
	}

	@Override
	public String toString() {
		return "PharmacyOrderDto [orderId=" + orderId + ", cartId=" + cartId + ", patientId=" + patientId
				+ ", cartTotalPrice=" + cartTotalPrice + ", paymentMode=" + paymentMode + ", paymentStatus="
				+ paymentStatus + ", transactionId=" + transactionId + ", deliveryTrack=" + deliveryTrack
				+ ", deliveryAddress=" + deliveryAddress + ", orderedTime=" + orderedTime + "]";
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Double getCartTotalPrice() {
		return cartTotalPrice;
	}

	public void setCartTotalPrice(Double cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getDeliveryTrack() {
		return deliveryTrack;
	}

	public void setDeliveryTrack(String deliveryTrack) {
		this.deliveryTrack = deliveryTrack;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getOrderedTime() {
		return orderedTime;
	}

	public void setOrderedTime(Date orderedTime) {
		this.orderedTime = orderedTime;
	}

}
