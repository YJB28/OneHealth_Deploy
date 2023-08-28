package com.onehealth.pharmaordermanagement.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table
public class PharmacyOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long orderId;

	@Column
	private Long cartId;

	@Column
	private Integer patientId;

	@Column
	private Double cartTotalPrice;

	@Column(name = "payment_mode", nullable = false)
	private String paymentMode;

	@Column(name = "payment_status", nullable = false)
	private String paymentStatus;

	@Column
	private String transactionId;

	@Column
	private String deliveryTrack;

	@Column
	private String deliveryAddress;

	@Column
	private Date orderedTime;

	@OneToMany(mappedBy = "pharmacyOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore // Add this annotation to prevent serialization of this property
	private List<PharmacyOrderDetails> orderDetailsList;

	public PharmacyOrder() {
		super();
		// TODO Auto-generated constructor stub
		this.orderedTime = new Date();
	}

	public PharmacyOrder(Long orderId, Long cartId, Integer patientId, Double cartTotalPrice, String paymentMode,
			String paymentStatus, String transactionId, String deliveryTrack, String deliveryAddress, Date orderedTime,
			List<PharmacyOrderDetails> orderDetailsList) {
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
		this.orderDetailsList = orderDetailsList;
	}

	@Override
	public String toString() {
		return "PharmacyOrder [orderId=" + orderId + ", cartId=" + cartId + ", patientId=" + patientId
				+ ", cartTotalPrice=" + cartTotalPrice + ", paymentMode=" + paymentMode + ", paymentStatus="
				+ paymentStatus + ", transactionId=" + transactionId + ", deliveryTrack=" + deliveryTrack
				+ ", deliveryAddress=" + deliveryAddress + ", orderedTime=" + orderedTime + ", orderDetailsList="
				+ orderDetailsList + "]";
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

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
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

	public List<PharmacyOrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<PharmacyOrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

}
