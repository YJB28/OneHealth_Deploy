package com.onehealth.pharmainventory.entity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;

@Entity
@Table
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer medicineId;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private MedicineCategory category;

	@Column
	private String medicineName;

//	@Column
//	private String medicineImages;
	
	@Transient
    private List<MultipartFile> medicineImages; 

	@Column
	private Boolean medicineAvailability;

	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medicine(Integer medicineId, MedicineCategory category, String medicineName,
			List<MultipartFile> medicineImages, Boolean medicineAvailability) {
		super();
		this.medicineId = medicineId;
		this.category = category;
		this.medicineName = medicineName;
		this.medicineImages = medicineImages;
		this.medicineAvailability = medicineAvailability;
	}

	@Override
	public String toString() {
		return "Medicine [medicineId=" + medicineId + ", category=" + category + ", medicineName=" + medicineName
				+ ", medicineImages=" + medicineImages + ", medicineAvailability=" + medicineAvailability + "]";
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

	public List<MultipartFile> getMedicineImages() {
		return medicineImages;
	}

	public void setMedicineImages(List<MultipartFile> medicineImages) {
		this.medicineImages = medicineImages;
	}

	public Boolean getMedicineAvailability() {
		return medicineAvailability;
	}

	public void setMedicineAvailability(Boolean medicineAvailability) {
		this.medicineAvailability = medicineAvailability;
	}

	

}