package com.oneHealth.pharmacyInventoryManagement.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MedicineCategory {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "category")
    private Set<Product> product=new HashSet<Product>();
    
    private String categoryName;
    private int parentCategoryId;
    private boolean flag;

    private String categoryImagePath;

   
    private String healthCondition;


	public MedicineCategory() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MedicineCategory(long categoryId, Set<Product> product, String categoryName, int parentCategoryId,
			boolean flag, String categoryImagePath, String healthCondition) {
		super();
		this.categoryId = categoryId;
		this.product = product;
		this.categoryName = categoryName;
		this.parentCategoryId = parentCategoryId;
		this.flag = flag;
		this.categoryImagePath = categoryImagePath;
		this.healthCondition = healthCondition;
	}


	@Override
	public String toString() {
		return "MedicineCategory [categoryId=" + categoryId + ", product=" + product + ", categoryName=" + categoryName
				+ ", parentCategoryId=" + parentCategoryId + ", flag=" + flag + ", categoryImagePath="
				+ categoryImagePath + ", healthCondition=" + healthCondition + "]";
	}


	public long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}


	public Set<Product> getProduct() {
		return product;
	}


	public void setProduct(Set<Product> product) {
		this.product = product;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public int getParentCategoryId() {
		return parentCategoryId;
	}


	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public String getCategoryImagePath() {
		return categoryImagePath;
	}


	public void setCategoryImagePath(String categoryImagePath) {
		this.categoryImagePath = categoryImagePath;
	}


	public String getHealthCondition() {
		return healthCondition;
	}


	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}


	
 
   
    
}
