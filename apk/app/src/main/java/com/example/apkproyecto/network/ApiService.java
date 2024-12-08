package com.example.apkproyecto.network;

import com.example.apkproyecto.models.Catalog;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    // Endpoint para obtener productos por tienda
    @GET("/product")
    Call<List<Catalog>> getProductsByStore(@Query("storeId") Long storeId);
}
