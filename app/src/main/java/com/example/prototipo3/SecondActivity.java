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

        TextView tv = this.findViewById(R.id.second_tv_01);

        new Thread(() -> {
            try {
                Log.d("SecondActivity", "Obteniendo los tokens del servidor...");
                String tokens = getTokens();
                runOnUiThread(() -> tv.setText(tokens));

                Log.d("SecondActivity", "Tokens obtenidos!");
            } catch (Exception e) {
                Log.e("Error al obtener los tokens: ", e.toString());
            }
        }).start();
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