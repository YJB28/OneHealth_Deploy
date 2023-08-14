package com.oneHealth.pharmacyInventoryManagement.entity;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Product {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Inventory inventory;

  
    @ManyToOne(fetch=FetchType.LAZY )
    private MedicineCategory category;
    
    @OneToOne(mappedBy = "product")
    private  ProductDetails productDetails;

   
    private String medName;

  
   

  
    private Set<String> medImage;

    
    private Boolean medAvailability;


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Product(long productId, Inventory inventory, MedicineCategory category, ProductDetails productDetails,
			String medName, Set<String> medImage, Boolean medAvailability) {
		super();
		this.productId = productId;
		this.inventory = inventory;
		this.category = category;
		this.productDetails = productDetails;
		this.medName = medName;
		this.medImage = medImage;
		this.medAvailability = medAvailability;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", inventory=" + inventory + ", category=" + category
				+ ", productDetails=" + productDetails + ", medName=" + medName + ", medImage=" + medImage
				+ ", medAvailability=" + medAvailability + "]";
	}


	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	public Inventory getInventory() {
		return inventory;
	}


	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}


	public MedicineCategory getCategory() {
		return category;
	}


	public void setCategory(MedicineCategory category) {
		this.category = category;
	}


	public ProductDetails getProductDetails() {
		return productDetails;
	}


	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}


	public String getMedName() {
		return medName;
	}


	public void setMedName(String medName) {
		this.medName = medName;
	}


	public Set<String> getMedImage() {
		return medImage;
	}


	public void setMedImage(Set<String> medImage) {
		this.medImage = medImage;
	}


	public Boolean getMedAvailability() {
		return medAvailability;
	}


	public void setMedAvailability(Boolean medAvailability) {
		this.medAvailability = medAvailability;
	}

  
    


   
    

   
   
}
