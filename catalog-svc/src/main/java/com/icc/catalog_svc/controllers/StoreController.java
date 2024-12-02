package com.icc.catalog_svc.controllers;

import com.icc.catalog_svc.models.Store;
import com.icc.catalog_svc.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    public List<Store> getStoresByCity(@RequestParam("city") String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("El parámetro 'city' es obligatorio y no puede estar vacío");
        }
        List<Store> stores = storeService.getStoresByCity(city);
        System.out.println("Stores returned to client: " + stores); // Depuración
        return stores;
    }
}
