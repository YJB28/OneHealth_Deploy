package com.onehealth.pharmainventory.entity;

import jakarta.persistence.*;

@Entity
@Table
public class MedicineCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long categoryId;

	@Column
	private String images;

//	@Column(columnDefinition = "VARCHAR(255) COLLATE utf8_general_ci")
	@Column
	private String categoryName;

	@Column
	private Long subCategoryId;

	@Column
	private String healthCondition;

	@Column
	private Boolean flag;

	public MedicineCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicineCategory(Long categoryId, String images, String categoryName, Long subCategoryId,
			String healthCondition, Boolean flag) {
		super();
		this.categoryId = categoryId;
		this.images = images;
		this.categoryName = categoryName;
		this.subCategoryId = subCategoryId;
		this.healthCondition = healthCondition;
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "MedicineCategory [categoryId=" + categoryId + ", images=" + images + ", categoryName=" + categoryName
				+ ", subCategoryId=" + subCategoryId + ", healthCondition=" + healthCondition + ", flag=" + flag + "]";
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getHealthCondition() {
		return healthCondition;
	}

	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}