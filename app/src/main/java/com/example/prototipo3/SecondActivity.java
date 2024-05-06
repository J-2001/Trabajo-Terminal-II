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
                Log.d("SecondActivity", "Obteniendo los tokens del servidor...");
                int n = requestDevicesInfo();
                runOnUiThread(() -> tv.setText(getString(R.string.second_tv_02, n)));
                int limit = 10;
                boolean nonzero = false;
                while (limit > 0) {
                    Thread.sleep(1000);
                    int m = checkCollectedInfo();
                    runOnUiThread(() -> tv.setText(getString(R.string.second_tv_03, m, n)));
                    if (m > 0) {
                        nonzero = true;
                    }
                    limit--;
                }
                if (nonzero) {
                    String info = getInfo();
                    runOnUiThread(() -> tv.setText(info));
                } else {
                    runOnUiThread(() -> tv.setText(getString(R.string.second_tv_04)));
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
                return Integer.parseInt(baos.toString());
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

    public int checkCollectedInfo() {
        try {
            URL url = new URL("https://trabajo-terminal-servidor.uc.r.appspot.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                urlConnection.setRequestProperty("Accion", "CInfo");
                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int nRead;
                while ((nRead = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, nRead);
                }
                return Integer.parseInt(baos.toString());
            } catch (Exception e) {
                throw new Exception(e.getCause());
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("Error - checkCollectedInfo(): ", e.toString());
            return 0;
        }
    }

    public String getInfo() {
        try {
            URL url = new URL("https://trabajo-terminal-servidor.uc.r.appspot.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                urlConnection.setRequestProperty("Accion", "GInfo");
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
            Log.e("Error - getInfo(): ", e.toString());
            return "Error!";
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