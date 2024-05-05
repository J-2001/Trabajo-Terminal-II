package com.example.prototipo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ThirdActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        coordinatorLayout = this.findViewById(R.id.thirdActivity);

        Button btn = this.findViewById(R.id.third_btn_01);
        btn.setOnClickListener(v -> new Thread(() -> {
            Log.d("ThirdActivity", "Reporte General...");
            //getGeneralReport();
            requestDataExtraction();
        }).start());
    }

    public void requestDataExtraction() {
        String response = "R: ";
        try {
            URL url = new URL("https://trabajo-terminal-servidor.uc.r.appspot.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                urlConnection.setRequestProperty("Accion", "Extract");

                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                while (is.read(buffer) != -1) {
                    baos.write(buffer);
                }
                response += baos.toString();
            } catch (Exception e){
                throw new Exception(e.getCause());
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("Error(01): ", e.toString());
        }

        Log.i("Pruebas(01): ", response);
    }

    public void getGeneralReport() {
        String response = "R: ";

        try {
            URL url = new URL("https://trabajo-terminal-servidor.uc.r.appspot.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                urlConnection.setRequestProperty("Accion",  "Reporte");

                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                while ( is.read(buffer) != -1 ) {
                    baos.write(buffer);
                }
                response += baos.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("Error al leer y conectarse a la URL del servidor", e.toString());
        }

        Snackbar snackbar = Snackbar.make(coordinatorLayout, response, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}