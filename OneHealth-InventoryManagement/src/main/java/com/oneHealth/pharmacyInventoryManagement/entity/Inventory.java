package com.oneHealth.pharmacyInventoryManagement.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

import java.sql.Date;
import java.util.Set;
@Entity
@Table(name = "Inventory")
public class Inventory {

   

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invId;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "inventory")
    private Set<Product> product;
    private long pharmaId;
    private long productId;
    
    private long medUnits;
         
    private Date manufacturingDate;
    private Date expiryDate;
    private int batchNo;

    private String packSize;
    private long price;
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Inventory(long invId, Set<Product> product, long pharmaId, long medId, long medUnits, Date manufacturingDate,
			Date expiryDate, int batchNo, String packSize, long price) {
		super();
		this.invId = invId;
		this.product = product;
		this.pharmaId = pharmaId;
		this.productId = medId;
		this.medUnits = medUnits;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
		this.batchNo = batchNo;
		this.packSize = packSize;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Inventory [invId=" + invId + ", product=" + product + ", pharmaId=" + pharmaId + ", productId=" + productId
				+ ", medUnits=" + medUnits + ", manufacturingDate=" + manufacturingDate + ", expiryDate=" + expiryDate
				+ ", batchNo=" + batchNo + ", packSize=" + packSize + ", price=" + price + "]";
	}
	public long getInvId() {
		return invId;
	}
	public void setInvId(long invId) {
		this.invId = invId;
	}
	public Set<Product> getProduct() {
		return product;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	public long getPharmaId() {
		return pharmaId;
	}
	public void setPharmaId(long pharmaId) {
		this.pharmaId = pharmaId;
	}
	public long getMedId() {
		return productId;
	}
	public void setMedId(long medId) {
		this.productId = medId;
	}
	public long getMedUnits() {
		return medUnits;
	}
	public void setMedUnits(long medUnits) {
		this.medUnits = medUnits;
	}
	public Date getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public String getPackSize() {
		return packSize;
	}
	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}

	
	
   
}
