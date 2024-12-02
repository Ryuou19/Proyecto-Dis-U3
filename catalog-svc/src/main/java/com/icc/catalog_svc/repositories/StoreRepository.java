package com.icc.catalog_svc.repositories;

import com.icc.catalog_svc.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByCity(String city);
}

