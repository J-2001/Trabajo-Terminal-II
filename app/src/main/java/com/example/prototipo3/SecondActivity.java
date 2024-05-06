package com.example.prototipo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tv = this.findViewById(R.id.second_tv);

        new Thread(() -> {
            try {
                Log.d("SecondActivity", "Obteniendo los tokens del servidor...");
                int n = requestDevicesInfo();
                runOnUiThread(() -> tv.setText(getString(R.string.second_tv_02, n)));
                int limit = 10;
                while (limit > 0) {
                    Thread.sleep(1000);
                    limit--;
                }
            } catch (Exception e) {
                Log.e("Error al obtener los tokens: ", e.toString());
            }
        }).start();
    }

    public int requestDevicesInfo() {
        try {
            URL url = new URL("https://trabajo-terminal-servidor.uc.r.appspot.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                urlConnection.setRequestProperty("Accion", "Info");
                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int nRead;
                while ((nRead = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, nRead);
                }
                String n = new String(baos.toByteArray(), StandardCharsets.UTF_8);
                return Integer.parseInt(n);
            } catch (Exception e) {
                throw new Exception(e.getCause());
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("Error - requestDevicesInfo(): ", e.toString());
            return 0;
        }
    }

    public String getTokens() {
        String tokens = "Tokens Registrados:\n";

        try {
            URL url = new URL("https://trabajo-terminal-servidor.uc.r.appspot.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                urlConnection.setRequestProperty("Accion", "Tokens");

                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                while ( is.read(buffer) != -1 ) {
                    baos.write(buffer);
                }
                tokens += baos.toString();
            }  finally {
                urlConnection.disconnect();
            }
        } catch (Exception e){
            Log.e("Error al leer y conectarse a la URL del servidor: ", e.toString());
        }

        return tokens;
    }
}