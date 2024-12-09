package com.example.myapplication;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = findViewById(R.id.web1);
        WebSettings myWebSettings = myWebView.getSettings();

        // Configuración de WebView
        myWebSettings.setJavaScriptEnabled(true);
        myWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        myWebSettings.setAllowFileAccess(true);
        myWebSettings.setDomStorageEnabled(true);
        myWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // Habilitar depuración para WebView (opcional, solo para pruebas)
        WebView.setWebContentsDebuggingEnabled(true);

        // Configuración del cliente WebView
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        // Cargar la URL de Angular
        myWebView.loadUrl("http://10.0.2.2:4200"); // Para el emulador
        // myWebView.loadUrl("http://192.168.x.x:4200"); // Para dispositivos físicos
    }
}
