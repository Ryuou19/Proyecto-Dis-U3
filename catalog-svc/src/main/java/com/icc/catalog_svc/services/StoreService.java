package com.icc.catalog_svc.services;

import com.icc.catalog_svc.models.Store;
import com.icc.catalog_svc.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public List<Store> getStoresByCity(String city) {
        List<Store> stores = storeRepository.findByCity(city);
        return stores;
    }
}

