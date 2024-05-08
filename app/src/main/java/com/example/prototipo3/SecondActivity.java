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

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tv = this.findViewById(R.id.second_tv);

        new Thread(() -> {
            try {
                Log.d("SecondActivity", "Obteniendo los datos del servidor...");
                String devicesInfo = getDevicesInfo();
                runOnUiThread(() -> tv.setText(devicesInfo));
            } catch (Exception e) {
                Log.e("Error al verificar los usuarios registrados:", e.toString());
            }
        }).start();
    }

    public String getDevicesInfo() {
        try {
            URL url = new URL("https://trabajo-terminal-servidor.uc.r.appspot.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int nRead;
                while ((nRead = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, nRead);
                }
                String info = baos.toString();

                return info;
            } catch (Exception e) {
                throw new Exception(e.getCause());
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("getDevicesInfo()", e.toString());
            return "Error al obtener la informacion del servidor!";
        }
    }
}