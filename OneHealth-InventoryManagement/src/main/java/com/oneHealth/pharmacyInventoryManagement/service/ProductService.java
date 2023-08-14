package com.oneHealth.pharmacyInventoryManagement.service;



import com.oneHealth.pharmacyInventoryManagement.entity.MedicineCategory;
import com.oneHealth.pharmacyInventoryManagement.entity.Product;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    void updateProduct(long productId, Product product) throws ProfileNotFoundException;

    void deleteProduct(long productId) throws ProfileNotFoundException;

    List<Product> getAllProducts();

    Product getProductById(long productId) throws ProfileNotFoundException;
}
