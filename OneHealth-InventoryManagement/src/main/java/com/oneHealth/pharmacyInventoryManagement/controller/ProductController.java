package com.oneHealth.pharmacyInventoryManagement.controller;

import com.oneHealth.pharmacyInventoryManagement.entity.MedicineCategory;
import com.oneHealth.pharmacyInventoryManagement.entity.Product;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
//@RequestMapping("/api/products")
@RequestMapping("/InventoryManagement")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/inventoryProducts")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("Hello from /InventoryManagement/inventoryProducts/");
	}

	@GetMapping("/inventoryProducts/getAll")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/inventoryProducts/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) throws ProfileNotFoundException {
		Product product = productService.getProductById(productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping("/inventoryProducts/add")
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		productService.createProduct(product);
		return new ResponseEntity<>("Product Created Successfully", HttpStatus.CREATED);
	}

	@PutMapping("/inventoryProducts/update/{productId}")
	public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody Product product)
			throws ProfileNotFoundException {
		productService.updateProduct(productId, product);
		return new ResponseEntity<>("Product Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/inventoryProducts/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long productId) throws ProfileNotFoundException {
		productService.deleteProduct(productId);
		return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);
	}
}
