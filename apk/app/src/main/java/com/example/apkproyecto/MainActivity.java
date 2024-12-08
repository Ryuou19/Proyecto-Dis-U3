package com.example.apkproyecto;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.apkproyecto.network.ApiService;
import com.example.apkproyecto.network.RetrofitClient;
import com.example.apkproyecto.models.Catalog;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración del WebView existente
        myWebView = findViewById(R.id.web1);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.loadUrl("http://localhost:4200");
        myWebView.setWebViewClient(new WebViewClient());

        // Consumir datos desde el backend
        fetchProductsFromBackend(1L); // Cambia "1L" por el ID de la tienda que desees
    }

    private void fetchProductsFromBackend(Long storeId) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

        apiService.getProductsByStore(storeId).enqueue(new Callback<List<Catalog>>() {
            @Override
            public void onResponse(Call<List<Catalog>> call, Response<List<Catalog>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Catalog> products = response.body();
                    Toast.makeText(MainActivity.this, "Productos obtenidos: " + products.size(), Toast.LENGTH_SHORT).show();
                    // Aquí puedes procesar los datos (mostrar en RecyclerView, por ejemplo)
                } else {
                    Toast.makeText(MainActivity.this, "Error al obtener productos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Catalog>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}