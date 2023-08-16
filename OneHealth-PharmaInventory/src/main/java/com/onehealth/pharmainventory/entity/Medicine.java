package com.onehealth.pharmainventory.entity;


import jakarta.persistence.*;

@Entity
@Table
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicineId;

    @Column
    private Integer categoryId;

    @Column
    private String medicineName;

    @Column
    private String medicineImages;

    @Column
    private Boolean medicineAvailability;

	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medicine(Integer medicineId, Integer categoryId, String medicineName, String medicineImages,
			Boolean medicineAvailability) {
		super();
		this.medicineId = medicineId;
		this.categoryId = categoryId;
		this.medicineName = medicineName;
		this.medicineImages = medicineImages;
		this.medicineAvailability = medicineAvailability;
	}

	@Override
	public String toString() {
		return "Medicine [medicineId=" + medicineId + ", categoryId=" + categoryId + ", medicineName=" + medicineName
				+ ", medicineImages=" + medicineImages + ", medicineAvailability=" + medicineAvailability + "]";
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedicineImages() {
		return medicineImages;
	}

	public void setMedicineImages(String medicineImages) {
		this.medicineImages = medicineImages;
	}

	public Boolean getMedicineAvailability() {
		return medicineAvailability;
	}

	public void setMedicineAvailability(Boolean medicineAvailability) {
		this.medicineAvailability = medicineAvailability;
	}
    
    
    
}
