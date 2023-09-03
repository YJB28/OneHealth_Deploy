package com.onehealth.trackingorder.dto;

public class OrderDetailsDto {

	private Long orderDetailsId;
	private Long medicineId;
	private String medicineName;
	private Integer quantity;
	private Double totalProductPrice;
	private Long pharmaId;
	private PharmacyOrderDto pharmacyOrder;

	public OrderDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetailsDto(Long orderDetailsId, Long medicineId, String medicineName, Integer quantity,
			Double totalProductPrice, Long pharmaId, PharmacyOrderDto pharmacyOrder) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.quantity = quantity;
		this.totalProductPrice = totalProductPrice;
		this.pharmaId = pharmaId;
		this.pharmacyOrder = pharmacyOrder;
	}

	@Override
	public String toString() {
		return "OrderDetailsDto [orderDetailsId=" + orderDetailsId + ", medicineId=" + medicineId + ", medicineName="
				+ medicineName + ", quantity=" + quantity + ", totalProductPrice=" + totalProductPrice + ", pharmaId="
				+ pharmaId + ", pharmacyOrder=" + pharmacyOrder + "]";
	}

	public Long getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(Long orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(Double totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	public Long getPharmaId() {
		return pharmaId;
	}

	public void setPharmaId(Long pharmaId) {
		this.pharmaId = pharmaId;
	}

	public PharmacyOrderDto getPharmacyOrder() {
		return pharmacyOrder;
	}

	public void setPharmacyOrder(PharmacyOrderDto pharmacyOrder) {
		this.pharmacyOrder = pharmacyOrder;
	}

}
