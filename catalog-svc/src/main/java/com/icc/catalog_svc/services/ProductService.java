package com.icc.catalog_svc.services;

import com.icc.catalog_svc.models.Product;
import com.icc.catalog_svc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByStore(Long storeId) {
        if (storeId == null) {
            throw new IllegalArgumentException("storeId no puede ser null");
        }
        return productRepository.findByStoreId(storeId);
    }

}
