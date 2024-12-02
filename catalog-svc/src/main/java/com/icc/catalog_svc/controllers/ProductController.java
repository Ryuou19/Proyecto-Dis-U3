package com.icc.catalog_svc.controllers;

import com.icc.catalog_svc.models.Product;
import com.icc.catalog_svc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProductsByStore(@RequestParam("storeId") Long storeId) {
        if (storeId == null) {
            throw new IllegalArgumentException("El parámetro storeId es obligatorio");
        }
        return productService.getProductsByStore(storeId);
    }


}
