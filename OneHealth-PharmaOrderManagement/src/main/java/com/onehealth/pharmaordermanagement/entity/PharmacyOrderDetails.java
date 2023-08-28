package com.onehealth.pharmaordermanagement.entity;

import jakarta.persistence.*;

@Entity
@Table
public class PharmacyOrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long orderDetailsId;

	@Column(name = "medicine_id")
	private Long medicineId;

	@Column(name = "medicine_name")
	private String medicineName;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "total_product_price")
	private Double totalProductPrice;

	@Column(name = "pharma_id")
	private Long pharmaId;

	// Many-to-one relationship with PharmacyOrder
	@ManyToOne
	@JoinColumn(name = "order_id")
	private PharmacyOrder pharmacyOrder;

	public PharmacyOrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PharmacyOrderDetails(Long orderDetailsId, Long medicineId, String medicineName, Integer quantity,
			Double totalProductPrice, Long pharmaId, PharmacyOrder pharmacyOrder) {
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
		return "PharmacyOrderDetails [orderDetailsId=" + orderDetailsId + ", medicineId=" + medicineId
				+ ", medicineName=" + medicineName + ", quantity=" + quantity + ", totalProductPrice="
				+ totalProductPrice + ", pharmaId=" + pharmaId + ", pharmacyOrder=" + pharmacyOrder + "]";
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

	public PharmacyOrder getPharmacyOrder() {
		return pharmacyOrder;
	}

	public void setPharmacyOrder(PharmacyOrder pharmacyOrder) {
		this.pharmacyOrder = pharmacyOrder;
	}

}
