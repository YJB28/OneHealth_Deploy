package com.oneHealth.pharmacyInventoryManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ProductDetails {

    // ID field for the medicine details
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productDetailId;

    // Medicine associated with the details
    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;

    // Highlights of the medicine
    private String highlights;

    // Description of the medicine
    private String description;

    // Indications for using the medicine
    private String indications;

    // Key components or ingredients of the medicine
    private String keyComponents;

    // Directions for using the medicine
    private String directionForUse;

    // Storage instructions for the medicine
    private String storage;

    // Precautions and warnings for using the medicine
    private String precautions;

	public ProductDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDetails(long productDetailId, Product product, String highlights, String description,
			String indications, String keyComponents, String directionForUse, String storage, String precautions) {
		super();
		this.productDetailId = productDetailId;
		this.product = product;
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
		return "ProductDetails [productDetailId=" + productDetailId + ", product=" + product + ", highlights="
				+ highlights + ", description=" + description + ", indications=" + indications + ", keyComponents="
				+ keyComponents + ", directionForUse=" + directionForUse + ", storage=" + storage + ", precautions="
				+ precautions + "]";
	}

	public long getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(long productDetailId) {
		this.productDetailId = productDetailId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
