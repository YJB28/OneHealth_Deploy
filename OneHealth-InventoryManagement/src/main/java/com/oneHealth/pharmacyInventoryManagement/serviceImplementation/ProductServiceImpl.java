package com.oneHealth.pharmacyInventoryManagement.serviceImplementation;

import com.oneHealth.pharmacyInventoryManagement.entity.MedicineCategory;
import com.oneHealth.pharmacyInventoryManagement.entity.Product;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.repository.MedicineCategoryRepository;
import com.oneHealth.pharmacyInventoryManagement.repository.ProductRepository;
import com.oneHealth.pharmacyInventoryManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MedicineCategoryRepository medicineCategory;

	

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void updateProduct(long productId, Product product) throws ProfileNotFoundException {
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ProfileNotFoundException("Product not found with ID " + productId));

		// Update the fields of the existing product with the values from the input
		// product
		existingProduct.setCategory(product.getCategory());
		existingProduct.setMedName(product.getMedName());

		existingProduct.setMedImage(product.getMedImage());
		existingProduct.setMedAvailability(product.getMedAvailability());

		// Save the updated product
		productRepository.save(existingProduct);
	}

	@Override
	public void deleteProduct(long productId) throws ProfileNotFoundException {
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ProfileNotFoundException("Product not found with ID " + productId));

		productRepository.delete(existingProduct);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(long productId) throws ProfileNotFoundException {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ProfileNotFoundException("Product not found with ID " + productId));
	}

	
	
}
