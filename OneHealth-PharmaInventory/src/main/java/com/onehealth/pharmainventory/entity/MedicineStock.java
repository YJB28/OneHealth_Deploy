package com.onehealth.pharmainventory.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
public class MedicineStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer medicineStockId;

	@Column
	private Integer medicineId;

	@Column
	private Integer pharmaId;

	@Column
	private Integer medicineUnits;

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

	public MedicineStock(Integer medicineStockId, Integer medicineId, Integer pharmaId, Integer medicineUnits,
			Date expDate, Date mfgDate, String batchNo, BigDecimal price, String packSize) {
		super();
		this.medicineStockId = medicineStockId;
		this.medicineId = medicineId;
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
		return "MedicineStock [medicineStockId=" + medicineStockId + ", medicineId=" + medicineId + ", pharmaId="
				+ pharmaId + ", medicineUnits=" + medicineUnits + ", expDate=" + expDate + ", mfgDate=" + mfgDate
				+ ", batchNo=" + batchNo + ", price=" + price + ", packSize=" + packSize + "]";
	}

	public Integer getMedicineStockId() {
		return medicineStockId;
	}

	public void setMedicineStockId(Integer medicineStockId) {
		this.medicineStockId = medicineStockId;
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public Integer getPharmaId() {
		return pharmaId;
	}

	public void setPharmaId(Integer pharmaId) {
		this.pharmaId = pharmaId;
	}

	public Integer getMedicineUnits() {
		return medicineUnits;
	}

	public void setMedicineUnits(Integer medicineUnits) {
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
