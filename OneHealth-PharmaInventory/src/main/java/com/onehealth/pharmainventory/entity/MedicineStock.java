package com.onehealth.pharmainventory.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class MedicineStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicineStockId;

	@OneToOne
	@JoinColumn(name = "medicine_id", nullable = false)
//	@JsonIgnore // Add this line
	private Medicine medicine;

	@Column
	private Long pharmaId;

	@Column
	private Long medicineUnits;

	@Column
	private Date expDate;

	@Column
	private Date mfgDate;

	@Column
	private String batchNo;

	@Column
	private BigDecimal price;

	@Column
	private String packSize;

	public MedicineStock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicineStock(Long medicineStockId, Medicine medicine, Long pharmaId, Long medicineUnits,
			Date expDate, Date mfgDate, String batchNo, BigDecimal price, String packSize) {
		super();
		this.medicineStockId = medicineStockId;
		this.medicine = medicine;
		this.pharmaId = pharmaId;
		this.medicineUnits = medicineUnits;
		this.expDate = expDate;
		this.mfgDate = mfgDate;
		this.batchNo = batchNo;
		this.price = price;
		this.packSize = packSize;
	}

	@Override
	public String toString() {
		return "MedicineStock [medicineStockId=" + medicineStockId + ", medicine=" + medicine + ", pharmaId=" + pharmaId
				+ ", medicineUnits=" + medicineUnits + ", expDate=" + expDate + ", mfgDate=" + mfgDate + ", batchNo="
				+ batchNo + ", price=" + price + ", packSize=" + packSize + "]";
	}

	public Long getMedicineStockId() {
		return medicineStockId;
	}

	public void setMedicineStockId(Long medicineStockId) {
		this.medicineStockId = medicineStockId;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Long getPharmaId() {
		return pharmaId;
	}

	public void setPharmaId(Long pharmaId) {
		this.pharmaId = pharmaId;
	}

	public Long getMedicineUnits() {
		return medicineUnits;
	}

	public void setMedicineUnits(Long medicineUnits) {
		this.medicineUnits = medicineUnits;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPackSize() {
		return packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	

}