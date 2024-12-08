package com.example.apkproyecto.network;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {
    @GET("/api/data") // Endpoint del backend
    Call<List<MyEntity>> getData(); // Cambia MyEntity según tu modelo de datos
}
