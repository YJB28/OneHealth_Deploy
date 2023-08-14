package com.oneHealth.pharmacyInventoryManagement.service;

import java.util.List;

import com.oneHealth.pharmacyInventoryManagement.entity.Inventory;
import com.oneHealth.pharmacyInventoryManagement.entity.ProductDetails;
import com.oneHealth.pharmacyInventoryManagement.exception.DatabaseException;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;

public interface ProductDetailsService {

    ProductDetails createProductDetails(ProductDetails productDetails);

    void updateProductDetails(long productDetailId, ProductDetails productDetails) throws ProfileNotFoundException;

    void deleteProductDetails(long productDetailId) throws ProfileNotFoundException;
    List<ProductDetails>getAllProductDetails() throws DatabaseException;

    ProductDetails getProductDetailsById(long productDetailId) throws ProfileNotFoundException;
}

