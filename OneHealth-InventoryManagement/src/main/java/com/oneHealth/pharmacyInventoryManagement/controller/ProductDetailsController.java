package com.oneHealth.pharmacyInventoryManagement.controller;

import com.oneHealth.pharmacyInventoryManagement.entity.ProductDetails;
import com.oneHealth.pharmacyInventoryManagement.exception.ProfileNotFoundException;
import com.oneHealth.pharmacyInventoryManagement.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/productDetails")
@RequestMapping("/InventoryManagement")
public class ProductDetailsController {

    @Autowired
    private ProductDetailsService productDetailsService;

    @GetMapping("/productDetails")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from /inventoryProducts/productDetails/");
    }

    @GetMapping("/productDetails/all")
    public ResponseEntity<List<ProductDetails>> getAllProductDetails() throws Exception {
        List<ProductDetails> productDetailsList = productDetailsService.getAllProductDetails();
        return new ResponseEntity<>(productDetailsList, HttpStatus.OK);
    }

    @GetMapping("/productDetails/{productDetailId}")
    public ResponseEntity<ProductDetails> getProductDetailsById(@PathVariable Long productDetailId) throws ProfileNotFoundException {
        ProductDetails productDetails = productDetailsService.getProductDetailsById(productDetailId);
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }

    @PostMapping("/productDetails/add")
    public ResponseEntity<String> createProductDetails(@RequestBody ProductDetails productDetails) {
        productDetailsService.createProductDetails(productDetails);
        return new ResponseEntity<>("Product Details Created Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/productDetails/update/{productDetailId}")
    public ResponseEntity<String> updateProductDetails(@PathVariable Long productDetailId,
                                                       @RequestBody ProductDetails productDetails) throws ProfileNotFoundException {
        productDetailsService.updateProductDetails(productDetailId, productDetails);
        return new ResponseEntity<>("Product Details Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/productDetails/delete/{productDetailId}")
    public ResponseEntity<String> deleteProductDetails(@PathVariable Long productDetailId) throws ProfileNotFoundException {
        productDetailsService.deleteProductDetails(productDetailId);
        return new ResponseEntity<>("Product Details Deleted Successfully", HttpStatus.OK);
    }
}
