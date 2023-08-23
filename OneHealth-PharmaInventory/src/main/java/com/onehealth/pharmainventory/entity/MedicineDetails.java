package com.onehealth.pharmainventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table
public class MedicineDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer medicineDetailsId;

	@OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "medicine_id", referencedColumnName = "medicineId")
	    @JsonIgnoreProperties("medicineDetails") // Add this line to break the loop
	    private Medicine medicine;

	@Column
	private String highlights;

	@Column
	private String description;

	@Column
	private String indications;

	@Column
	private String keyComponents;

	@Column
	private String directionForUse;

	@Column
	private String storage;

	@Column
	private String precautions;

	public MedicineDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicineDetails(Integer medicineDetailsId, Medicine medicine, String highlights, String description,
			String indications, String keyComponents, String directionForUse, String storage, String precautions) {
		super();
		this.medicineDetailsId = medicineDetailsId;
		this.medicine = medicine;
		this.highlights = highlights;
		this.description = description;
		this.indications = indications;
		this.keyComponents = keyComponents;
		this.directionForUse = directionForUse;
		this.storage = storage;
		this.precautions = precautions;
	}

	@Override
	public String toString() {
		return "MedicineDetails [medicineDetailsId=" + medicineDetailsId + ", medicine=" + medicine + ", highlights="
				+ highlights + ", description=" + description + ", indications=" + indications + ", keyComponents="
				+ keyComponents + ", directionForUse=" + directionForUse + ", storage=" + storage + ", precautions="
				+ precautions + "]";
	}

	public Integer getMedicineDetailsId() {
		return medicineDetailsId;
	}

	public void setMedicineDetailsId(Integer medicineDetailsId) {
		this.medicineDetailsId = medicineDetailsId;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public String getHighlights() {
		return highlights;
	}

	public void setHighlights(String highlights) {
		this.highlights = highlights;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIndications() {
		return indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
	}

	public String getKeyComponents() {
		return keyComponents;
	}

	public void setKeyComponents(String keyComponents) {
		this.keyComponents = keyComponents;
	}

	public String getDirectionForUse() {
		return directionForUse;
	}

	public void setDirectionForUse(String directionForUse) {
		this.directionForUse = directionForUse;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getPrecautions() {
		return precautions;
	}

	public void setPrecautions(String precautions) {
		this.precautions = precautions;
	}

}