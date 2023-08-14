package com.oneHealth.pharmacyInventoryManagement.serviceImplementation;
import com.oneHealth.pharmacyInventoryManagement.entity.ProductDetails;
import com.oneHealth.pharmacyInventoryManagement.exception.DatabaseException;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.repository.ProductDetailsRepository;
import com.oneHealth.pharmacyInventoryManagement.service.ProductDetailsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
	
	@Autowired
    private ProductDetailsRepository productDetailsRepository;


    @Override
    public ProductDetails createProductDetails(ProductDetails productDetails) {
        return productDetailsRepository.save(productDetails);
    }

    @Override
    public void updateProductDetails(long productDetailId, ProductDetails productDetails)
            throws ProfileNotFoundException {
        ProductDetails existingProductDetails = productDetailsRepository.findById(productDetailId)
                .orElseThrow(() -> new ProfileNotFoundException("Product Details not found with ID " + productDetailId));

        // Update the fields of the existing product details with the values from the input product details
        existingProductDetails.setHighlights(productDetails.getHighlights());
        existingProductDetails.setDescription(productDetails.getDescription());
        existingProductDetails.setIndications(productDetails.getIndications());
        existingProductDetails.setKeyComponents(productDetails.getKeyComponents());
        existingProductDetails.setDirectionForUse(productDetails.getDirectionForUse());
        existingProductDetails.setStorage(productDetails.getStorage());
        existingProductDetails.setPrecautions(productDetails.getPrecautions());

        // Save the updated product details
        productDetailsRepository.save(existingProductDetails);
    }

    @Override
    public void deleteProductDetails(long productDetailId) throws ProfileNotFoundException {
        ProductDetails existingProductDetails = productDetailsRepository.findById(productDetailId)
                .orElseThrow(() -> new ProfileNotFoundException("Product Details not found with ID " + productDetailId));

        productDetailsRepository.delete(existingProductDetails);
    }

    @Override
    public ProductDetails getProductDetailsById(long productDetailId) throws ProfileNotFoundException {
        return productDetailsRepository.findById(productDetailId)
                .orElseThrow(() -> new ProfileNotFoundException("Product Details not found with ID " + productDetailId));
    }

	@Override
	public List<ProductDetails> getAllProductDetails() throws DatabaseException  {
		List<ProductDetails> productDetails = productDetailsRepository.findAll();
		if(productDetails.isEmpty()) {
			throw new DatabaseException("No inventory records found");
		}
		return productDetails;
	}
	

	
}
