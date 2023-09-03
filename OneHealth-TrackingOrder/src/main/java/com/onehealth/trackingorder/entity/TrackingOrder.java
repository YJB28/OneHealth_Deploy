package com.onehealth.trackingorder.entity;

import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name = "tracking_order")
public class TrackingOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tracking_id")
	private Long trackingId;

	@Column(name = "consignment_number", nullable = false, updatable = false)
	private String consignmentNumber;

	@Column(name = "order_id", updatable = false)
	private Long orderId;

	@Column(name = "current_location", nullable = false)
	private String currentLocation;

	@Column(name = "current_pin_code", nullable = false)
	private Long currentPinCode;

	@Column(name = "next_location", nullable = false)
	private String nextLocation;

	@Column(name = "next_pin_code", nullable = false)
	private Long nextPinCode;

	@Column(name = "destination_location", nullable = false)
	private String destinationLocation;

	@Column(name = "destination_pin_code", nullable = false)
	private Long destinationPinCode;

	@Column(name = "current_status", nullable = false)
	private String currentStatus;

	@Column(name = "payment_mode", nullable = false, updatable = false)
	private String paymentMode;

	@Column(name = "created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public TrackingOrder() {
		super();
//		this.createdAt = new Date();

		// Set the updatedAt field with the Date object
		this.updatedAt = new Date();
	}

	public TrackingOrder(Long trackingId, String consignmentNumber, Long orderId, String currentLocation,
			Long currentPinCode, String nextLocation, Long nextPinCode, String destinationLocation,
			Long destinationPinCode, String currentStatus, String paymentMode, Date createdAt, Date updatedAt) {
		super();
		this.trackingId = trackingId;
		this.consignmentNumber = consignmentNumber;
		this.orderId = orderId;
		this.currentLocation = currentLocation;
		this.currentPinCode = currentPinCode;
		this.nextLocation = nextLocation;
		this.nextPinCode = nextPinCode;
		this.destinationLocation = destinationLocation;
		this.destinationPinCode = destinationPinCode;
		this.currentStatus = currentStatus;
		this.paymentMode = paymentMode;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "TrackingOrder [trackingId=" + trackingId + ", consignmentNumber=" + consignmentNumber + ", orderId="
				+ orderId + ", currentLocation=" + currentLocation + ", currentPinCode=" + currentPinCode
				+ ", nextLocation=" + nextLocation + ", nextPinCode=" + nextPinCode + ", destinationLocation="
				+ destinationLocation + ", destinationPinCode=" + destinationPinCode + ", currentStatus="
				+ currentStatus + ", paymentMode=" + paymentMode + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

	public Long getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(Long trackingId) {
		this.trackingId = trackingId;
	}

	public String getConsignmentNumber() {
		return consignmentNumber;
	}

	public void setConsignmentNumber(String consignmentNumber) {
		this.consignmentNumber = consignmentNumber;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Long getCurrentPinCode() {
		return currentPinCode;
	}

	public void setCurrentPinCode(Long currentPinCode) {
		this.currentPinCode = currentPinCode;
	}

	public String getNextLocation() {
		return nextLocation;
	}

	public void setNextLocation(String nextLocation) {
		this.nextLocation = nextLocation;
	}

	public Long getNextPinCode() {
		return nextPinCode;
	}

	public void setNextPinCode(Long nextPinCode) {
		this.nextPinCode = nextPinCode;
	}

	public String getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public Long getDestinationPinCode() {
		return destinationPinCode;
	}

	public void setDestinationPinCode(Long destinationPinCode) {
		this.destinationPinCode = destinationPinCode;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
