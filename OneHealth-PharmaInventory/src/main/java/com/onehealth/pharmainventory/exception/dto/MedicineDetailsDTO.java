package com.onehealth.pharmainventory.exception.dto;

public class MedicineDetailsDTO {

	private Integer medicineDetailsId;

	private String highlights;

	private String description;

	private String directionForUse;

	private String storage;

	private String precautions;

	private String medicineName;

	private String medicineImages;

	public MedicineDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicineDetailsDTO(Integer medicineDetailsId, String highlights, String description, String directionForUse,
			String storage, String precautions, String medicineName, String medicineImages, String categoryName) {
		this.medicineDetailsId = medicineDetailsId;
		this.highlights = highlights;
		this.description = description;
		this.directionForUse = directionForUse;
		this.storage = storage;
		this.precautions = precautions;
		this.medicineName = medicineName;
		this.medicineImages = medicineImages;
	}

	@Override
	public String toString() {
		return "MedicineDetailsDTO [medicineDetailsId=" + medicineDetailsId + ", highlights=" + highlights
				+ ", description=" + description + ", directionForUse=" + directionForUse + ", storage=" + storage
				+ ", precautions=" + precautions + ", medicineName=" + medicineName + ", medicineImages="
				+ medicineImages + ", categoryName=" + "]";
	}

	public Integer getMedicineDetailsId() {
		return medicineDetailsId;
	}

	public void setMedicineDetailsId(Integer medicineDetailsId) {
		this.medicineDetailsId = medicineDetailsId;
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

}
