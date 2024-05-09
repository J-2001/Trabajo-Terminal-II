package com.example.prototipo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        coordinatorLayout = this.findViewById(R.id.thirdActivity);

        TextView tv = this.findViewById(R.id.third_tv_01);

        Button btn = this.findViewById(R.id.third_btn_01);

        btn.setOnClickListener(v -> new Thread(() -> {
            Log.d("ThirdActivity", "Reporte General...");

            String[] all = getAllData().split("/");

            runOnUiThread(() -> tv.append(Arrays.toString(all)));

            String[] allInfo = all[0].split("_");
            String[] allData = all[1].split("_");

            List<String> ainfo = new ArrayList<>();
            List<String> adata = new ArrayList<>();

            for (int i = 0; i < allData.length; i++) {
                if (!allData[i].equals("0") && !allData[i].equals(":::")) {
                    ainfo.add(allInfo[i]);
                    adata.add(allData[i]);
                }
            }

            runOnUiThread(() -> tv.append("\nInfo: " + ainfo));
            runOnUiThread(() -> tv.append("\nData: " + adata));

            List<List<String>> info = new ArrayList<>();

            for (int i = 0; i < ainfo.size(); i++) {
                info.add(new ArrayList<>());
                String[] dinfo = ainfo.get(i).split(";");
                for (String s : dinfo) {
                    info.get(i).add(s.split(":")[1]);
                }
            }

            runOnUiThread(() -> tv.append("\nInfo: " + info));

            List<List<String>> data = new ArrayList<>();

            for (int i = 0; i < adata.size(); i++) {
                data.add(new ArrayList<>());
                String[] dbs = adata.get(i).split(":");
                for (String s : dbs) {
                    data.get(i).add(s);
                }
            }

            List<List<List<Object>>> bateria = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                bateria.add(new ArrayList<>());
                String[] rows = data.get(i).get(0).split(";");
                for (int j = 0; j < rows.length; j++) {
                    bateria.get(i).add(new ArrayList<>());
                    String[] row = rows[j].split(",");
                    bateria.get(i).get(j).add(Integer.valueOf(row[0]));
                    bateria.get(i).get(j).add(Integer.valueOf(row[1]));
                    bateria.get(i).get(j).add(Integer.valueOf(row[2]));
                    bateria.get(i).get(j).add(Float.valueOf(row[3]));
                    bateria.get(i).get(j).add(Long.valueOf(row[4]));
                }
            }

            runOnUiThread(() -> tv.append("\nBateria: " + bateria));

            List<List<List<Object>>> analizador = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                analizador.add(new ArrayList<>());
                String[] rows = data.get(i).get(1).split(";");
                for (int j = 0; j < rows.length; j++) {
                    analizador.get(i).add(new ArrayList<>());
                    String[] row = rows[j].split(",");
                    analizador.get(i).get(j).add(Integer.valueOf(row[0]));
                    analizador.get(i).get(j).add(Integer.valueOf(row[1]));
                    analizador.get(i).get(j).add(Integer.valueOf(row[2]));
                    analizador.get(i).get(j).add(Float.valueOf(row[3]));
                    analizador.get(i).get(j).add(Float.valueOf(row[4]));
                    analizador.get(i).get(j).add(Float.valueOf(row[5]));
                    analizador.get(i).get(j).add(Float.valueOf(row[6]));
                    analizador.get(i).get(j).add(Boolean.valueOf(row[7]));
                }
            }

            runOnUiThread(() -> tv.append("\nAnalizador: " + analizador));

            List<List<List<Object>>> escaneo = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                escaneo.add(new ArrayList<>());
                String[] rows = data.get(i).get(2).split(";");
                for (int j = 0; j < rows.length; j++) {
                    escaneo.get(i).add(new ArrayList<>());
                    String[] row = rows[j].split(",");
                    escaneo.get(i).get(j).add(Integer.valueOf(row[0]));
                    escaneo.get(i).get(j).add(Integer.valueOf(row[1]));
                    escaneo.get(i).get(j).add(Long.valueOf(row[2]));
                    escaneo.get(i).get(j).add(Integer.valueOf(row[3]));
                    escaneo.get(i).get(j).add(Float.valueOf(row[4]));
                    escaneo.get(i).get(j).add(String.valueOf(row[5]));
                }
            }

            runOnUiThread(() -> tv.append("\nEscaneo: " + escaneo));

            List<List<List<Object>>> huella = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                huella.add(new ArrayList<>());
                String[] rows = data.get(i).get(3).split(";");
                for (int j = 0; j < rows.length; j++) {
                    huella.get(i).add(new ArrayList<>());
                    String[] row = rows[j].split(",");
                    huella.get(i).get(j).add(Integer.valueOf(row[0]));
                    huella.get(i).get(j).add(Float.valueOf(row[1]));
                }
            }

            runOnUiThread(() -> tv.append("\nHuella: " + huella));

            runOnUiThread(() -> tv.append("\n\nNo. Dispositivos Registrados: " + info.size()));

            float thc = 0;

            for (List<List<Object>> footprint : huella) {
                for (List<Object> h : footprint) {
                    thc += (Float) h.get(1);
                }
            }

            float totalHuellaCarbono = thc;
            runOnUiThread(() -> tv.append("\n\nHuella de Carbono Total Generada: " + totalHuellaCarbono + " gCO2e"));

            int tec = 0;
            long ttv = 0;
            Map<String, Integer> appsVistas = new HashMap<>();
            appsVistas.put(getString(R.string.netflix), 0);
            appsVistas.put(getString(R.string.disneyplus), 0);
            appsVistas.put(getString(R.string.starplus), 0);
            appsVistas.put(getString(R.string.primevideo), 0);
            appsVistas.put(getString(R.string.max), 0);
            appsVistas.put(getString(R.string.crunchyroll), 0);
            appsVistas.put(getString(R.string.vix), 0);
            Map<String, Long> appsUso = new HashMap<>();
            appsUso.put(getString(R.string.netflix), 0L);
            appsUso.put(getString(R.string.disneyplus), 0L);
            appsUso.put(getString(R.string.starplus), 0L);
            appsUso.put(getString(R.string.primevideo), 0L);
            appsUso.put(getString(R.string.max), 0L);
            appsUso.put(getString(R.string.crunchyroll), 0L);
            appsUso.put(getString(R.string.vix), 0L);

            for (List<List<Object>> scan : escaneo) {
                for (List<Object> s : scan) {
                    tec += (Integer) s.get(3);
                    ttv += (Long) s.get(2);
                    appsVistas.put((String) s.get(5), appsVistas.get((String) s.get(5)) + 1);
                    appsUso.put((String) s.get(5), appsUso.get((String) s.get(5)) + (Long) s.get(2));
                }
            }

            int totalEnergiaConsumida = tec;
            runOnUiThread(() -> tv.append("\n\nTotal de Energia Consumida: " + totalEnergiaConsumida + " uAh"));

            long totalTiempoVisualizado = ttv/(1000*60*60);
            runOnUiThread(() -> tv.append("\n\nTotal de Tiempo Visualizado: " + totalTiempoVisualizado + " h"));

            Map.Entry<String, Integer> appMasVista = Collections.max(appsVistas.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nApp de VS Mas Visualizada: " + appMasVista.getKey()));
            runOnUiThread(() -> tv.append("\nNo. de Veces Vista: " + appMasVista.getValue()));

            Map.Entry<String, Long> appMasUsada = Collections.max(appsUso.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nApp Mas Tiempo Usada: " + appMasUsada.getKey()));
            runOnUiThread(() -> tv.append("\nTiempo Usada: " + appMasUsada.getValue()/(1000*60*60) + " h"));

            //App que mas enegia y huella de carbono genera

            runOnUiThread(() -> tv.append("\n\n"));

        }).start());
    }

    public String getAllData() {
        try {
            URL url = new URL("https://trabajo-terminal-servidor.uc.r.appspot.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                urlConnection.setRequestProperty("Accion", "All");

                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int nRead;
                while ((nRead = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, nRead);
                }

                return baos.toString();
            } catch (Exception e) {
                throw new Exception(e.getCause());
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("getAllData()", e.toString());
            return "0";
        }
    }
}