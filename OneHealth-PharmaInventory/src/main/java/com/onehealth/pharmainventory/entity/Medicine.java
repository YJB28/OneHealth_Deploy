package com.onehealth.pharmainventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer medicineId;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
//	@JsonIgnore
	private MedicineCategory category;

	@Column
	private String medicineName;

	@Column
	private String medicineImages;

//	@Transient
//    private List<MultipartFile> medicineImages; 

	@Column
	private Boolean medicineAvailability;

	@OneToOne(mappedBy = "medicine", fetch = FetchType.EAGER)
	private MedicineDetails medicineDetails;

	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medicine(Integer medicineId, MedicineCategory category, String medicineName, String medicineImages,
			Boolean medicineAvailability, MedicineDetails medicineDetails) {
		super();
		this.medicineId = medicineId;
		this.category = category;
		this.medicineName = medicineName;
		this.medicineImages = medicineImages;
		this.medicineAvailability = medicineAvailability;
		this.medicineDetails = medicineDetails;
	}

	@Override
	public String toString() {
		return "Medicine [medicineId=" + medicineId + ", category=" + category + ", medicineName=" + medicineName
				+ ", medicineImages=" + medicineImages + ", medicineAvailability=" + medicineAvailability
				+ ", medicineDetails=" + medicineDetails + "]";
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public MedicineCategory getCategory() {
		return category;
	}

	public void setCategory(MedicineCategory category) {
		this.category = category;
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

	public MedicineDetails getMedicineDetails() {
		return medicineDetails;
	}

	public void setMedicineDetails(MedicineDetails medicineDetails) {
		this.medicineDetails = medicineDetails;
	}

}