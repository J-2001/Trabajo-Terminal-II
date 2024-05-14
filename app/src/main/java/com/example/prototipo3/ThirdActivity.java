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

    private String[] vsAppsNames;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        vsAppsNames = new String[]{getString(R.string.netflix), getString(R.string.disneyplus), getString(R.string.starplus), getString(R.string.primevideo), getString(R.string.max), getString(R.string.crunchyroll), getString(R.string.vix)};

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
                if (!allData[i].equals("0") && !allData[i].endsWith("::")) {
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

            List<String> manufacturers = new ArrayList<>();
            List<String> brands = new ArrayList<>();
            List<String> versions = new ArrayList<>();
            for (List<String> inf : info) {
                if (!manufacturers.contains(inf.get(0))) {
                    manufacturers.add(inf.get(0));
                }
                if (!brands.contains(inf.get(1))) {
                    brands.add(inf.get(1));
                }
                if (!versions.contains(inf.get(3))) {
                    versions.add(inf.get(3));
                }
            }

            runOnUiThread(() -> tv.append("\n\nNo. Dispositivos Registrados: " + info.size()));

            int tvu = 0;
            long ttv = 0;
            int tec = 0;
            Map<String, Integer> appsVistas = new HashMap<>();
            for (String vsAppName : vsAppsNames) {
                appsVistas.put(vsAppName, 0);
            }
            Map<String, Long> appsUso = new HashMap<>();
            for (String vsAppName : vsAppsNames) {
                appsUso.put(vsAppName, 0L);
            }
            List<Map<String, List<Integer>>> vspu = new ArrayList<>();
            Map<String, Integer> appsConsumo = new HashMap<>();
            for (String vsAppName : vsAppsNames) {
                appsConsumo.put(vsAppName, 0);
            }
            Map<String, Integer> fabricantesUsos = new HashMap<>();
            for (String manufacturer : manufacturers) {
                fabricantesUsos.put(manufacturer, 0);
            }
            Map<String, Long> fabricantesVisualizacion = new HashMap<>();
            for (String manufacturer : manufacturers) {
                fabricantesVisualizacion.put(manufacturer, 0L);
            }
            Map<String, Integer> fabricantesConsumo = new HashMap<>();
            for (String manufacturer : manufacturers) {
                fabricantesConsumo.put(manufacturer, 0);
            }
            Map<String, Integer> marcasUsos = new HashMap<>();
            for (String brand : brands) {
                marcasUsos.put(brand, 0);
            }
            Map<String, Long> marcasVisualizacion = new HashMap<>();
            for (String brand : brands) {
                marcasVisualizacion.put(brand, 0L);
            }
            Map<String, Integer> marcasConsumo = new HashMap<>();
            for (String brand : brands) {
                marcasConsumo.put(brand, 0);
            }
            Map<String, Integer> avsUsos = new HashMap<>();
            for (String version : versions) {
                avsUsos.put(version, 0);
            }
            Map<String, Long> avsVisualizacion = new HashMap<>();
            for (String version : versions) {
                avsVisualizacion.put(version, 0L);
            }
            Map<String, Integer> avsConsumo = new HashMap<>();
            for (String version : versions) {
                avsConsumo.put(version, 0);
            }

            for (int i = 0; i < escaneo.size(); i++) {
                tvu += escaneo.get(i).size();
                vspu.add(new HashMap<>());
                for (String vsAppName : vsAppsNames) {
                    vspu.get(i).put(vsAppName, new ArrayList<>());
                }
                fabricantesUsos.put(info.get(i).get(0), fabricantesUsos.get(info.get(i).get(0)) + escaneo.get(i).size());
                marcasUsos.put(info.get(i).get(1), marcasUsos.get(info.get(i).get(1)) + escaneo.get(i).size());
                avsUsos.put(info.get(i).get(3), avsUsos.get(info.get(i).get(3)) + escaneo.get(i).size());
                int id = 1;
                for (List<Object> s : escaneo.get(i)) {
                    ttv += (Long) s.get(2);
                    tec += (Integer) s.get(3);
                    appsVistas.put((String) s.get(5), appsVistas.get((String) s.get(5)) + 1);
                    appsUso.put((String) s.get(5), appsUso.get((String) s.get(5)) + (Long) s.get(2));
                    appsConsumo.put((String) s.get(5), appsConsumo.get((String) s.get(5)) + (Integer) s.get(3));
                    vspu.get(i).get((String) s.get(5)).add(id);
                    fabricantesVisualizacion.put(info.get(i).get(0), fabricantesVisualizacion.get(info.get(i).get(0)) + (Long) s.get(2));
                    fabricantesConsumo.put(info.get(i).get(0), fabricantesConsumo.get(info.get(i).get(0)) + (Integer) s.get(3));
                    marcasVisualizacion.put(info.get(i).get(1), marcasVisualizacion.get(info.get(i).get(1)) + (Long) s.get(2));
                    marcasConsumo.put(info.get(i).get(1), marcasConsumo.get(info.get(i).get(1)) + (Integer) s.get(3));
                    avsVisualizacion.put(info.get(i).get(3), avsVisualizacion.get(info.get(i).get(3)) + (Long) s.get(2));
                    avsConsumo.put(info.get(i).get(3), avsConsumo.get(info.get(i).get(3)) + (Integer) s.get(3));
                    id++;
                }
            }

            int totalVecesUsado = tvu;
            runOnUiThread(() -> tv.append("\n\nTotal de Veces Usada: " + totalVecesUsado));

            long totalTiempoVisualizado = ttv/(1000*60*60);
            runOnUiThread(() -> tv.append("\n\nTotal de Tiempo Visualizado: " + totalTiempoVisualizado + " h"));

            int totalEnergiaConsumida = tec;
            runOnUiThread(() -> tv.append("\n\nTotal de Energia Consumida: " + totalEnergiaConsumida + " uAh"));

            float thc = 0;
            Map<String, Float> appsCO2 = new HashMap<>();
            for (String vsAppName : vsAppsNames) {
                appsCO2.put(vsAppName, 0F);
            }
            Map<String, Float> fabricantesCO2 = new HashMap<>();
            for (String manufacturer : manufacturers) {
                fabricantesCO2.put(manufacturer, 0F);
            }
            Map<String, Float> marcasCO2 = new HashMap<>();
            for (String brand : brands) {
                marcasCO2.put(brand, 0F);
            }
            Map<String, Float> avsCO2 = new HashMap<>();
            for (String version : versions) {
                avsCO2.put(version, 0F);
            }

            for (int i = 0; i < huella.size(); i++) {
                for (List<Object> h : huella.get(i)) {
                    thc += (Float) h.get(1);
                    fabricantesCO2.put(info.get(i).get(0), fabricantesCO2.get(info.get(i).get(0)) + (Float) h.get(1));
                    marcasCO2.put(info.get(i).get(1), marcasCO2.get(info.get(i).get(0)) + (Float) h.get(1));
                    avsCO2.put(info.get(i).get(3), avsCO2.get(info.get(i).get(0)) + (Float) h.get(1));
                    for (String vs : vspu.get(i).keySet()) {
                        if (vspu.get(i).get(vs).contains((Integer) h.get(0))) {
                            appsCO2.put(vs, appsCO2.get(vs) + (Float) h.get(1));
                            break;
                        }
                    }
                }
            }

            float totalHuellaCarbono = thc;
            runOnUiThread(() -> tv.append("\n\nHuella de Carbono Total Generada: " + totalHuellaCarbono + " gCO2e"));

            Map.Entry<String, Integer> appMasVista = Collections.max(appsVistas.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nApp de VS Mas Veces Vista: " + appMasVista.getKey()));
            runOnUiThread(() -> tv.append("\nNo. de Veces Vista: " + appMasVista.getValue()));

            Map.Entry<String, Long> appMasUsada = Collections.max(appsUso.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nApp Mas Tiempo Usada: " + appMasUsada.getKey()));
            runOnUiThread(() -> tv.append("\nTiempo Usada: " + appMasUsada.getValue()/(1000*60*60) + " h"));

            Map.Entry<String, Integer> appMayorConsumo = Collections.max(appsConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nApp que Consumió la Mayor Cantidad de Energía: " + appMayorConsumo.getKey()));
            runOnUiThread(() -> tv.append("\nEnergía Consumida : " + appMayorConsumo.getValue() + " uAh"));

            Map.Entry<String, Float> appMayorCO2 = Collections.max(appsCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nApp que Generó una Mayor Huella de Carbono: " + appMayorCO2.getKey()));
            runOnUiThread(() -> tv.append("\nHuella de Carbono: " + appMayorCO2.getValue() + "gCO2e"));

            runOnUiThread(() -> tv.append("\n\nNúmero de Visualizaciones por app:"));
            for (Map.Entry<String, Integer> vistas : appsVistas.entrySet()) {
                if (vistas.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + vistas.getKey() + ": " + vistas.getValue()));
                }
            }

            runOnUiThread(() -> tv.append("\n\nTiempo de Uso por app:"));
            for (Map.Entry<String, Long> uso : appsUso.entrySet()) {
                if (uso.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + uso.getKey() + ": " + uso.getValue()/(1000*60*60) + " h"));
                }
            }

            runOnUiThread(() -> tv.append("\n\nEnergía Consumida por app:"));
            for (Map.Entry<String, Integer> consumo : appsConsumo.entrySet()) {
                if (consumo.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + consumo.getKey() + ": " + consumo.getValue() + " uAh"));
                }
            }

            runOnUiThread(() -> tv.append("\n\nHuella de Carbono por app:"));
            for (Map.Entry<String, Float> co2 : appsCO2.entrySet()) {
                if (co2.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + co2.getKey() + ": " + co2.getValue() + " gCo2e"));
                }
            }

            if (fabricantesUsos.isEmpty()) {
                return;
            }
            Map.Entry<String, Integer> fabricanteMayorUso = Collections.max(fabricantesUsos.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nFabricante de los Dispositivos con Mayor Uso: " + fabricanteMayorUso.getKey()));
            runOnUiThread(() -> tv.append("\nNo. de Veces Usada: " + fabricanteMayorUso.getValue()));

            Map.Entry<String, Long> fabricanteMayorVisualizacion = Collections.max(fabricantesVisualizacion.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nFabricante de los Dispositivos con Mayor Tiempo Visualizado: " + fabricanteMayorVisualizacion.getKey()));
            runOnUiThread(() -> tv.append("\nTiempo Usada: " + fabricanteMayorVisualizacion.getValue()/(1000*60*60) + " h"));

            Map.Entry<String, Integer> fabricanteMayorConsumo = Collections.max(fabricantesConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nFabricante de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + fabricanteMayorConsumo.getKey()));
            runOnUiThread(() -> tv.append("\nEnergía Consumida : " + fabricanteMayorConsumo.getValue() + " uAh"));

            Map.Entry<String, Float> fabricanteMayorCO2 = Collections.max(fabricantesCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nFabricante de los Dispositivos que Generaron una Mayor Huella de Carbono: " + fabricanteMayorCO2.getKey()));
            runOnUiThread(() -> tv.append("\nHuella de Carbono: " + fabricanteMayorCO2.getValue() + "gCO2e"));

            runOnUiThread(() -> tv.append("\n\nTotal de Veces Usada por Fabricante del Dispositivo:"));
            for (Map.Entry<String, Integer> usos : fabricantesUsos.entrySet()) {
                if (usos.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + usos.getKey() + ": " + usos.getValue()));
                }
            }

            runOnUiThread(() -> tv.append("\n\nTotal de Tiempo Visualizado por Fabricante del Dispositivo:"));
            for (Map.Entry<String, Long> visualizado : fabricantesVisualizacion.entrySet()) {
                if (visualizado.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + visualizado.getKey() + ": " + visualizado.getValue()/(1000*60*60) + " h"));
                }
            }

            runOnUiThread(() -> tv.append("\n\nTotal de Energía Consumida por Fabricante del Dispositivo:"));
            for (Map.Entry<String, Integer> consumo : fabricantesConsumo.entrySet()) {
                if (consumo.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + consumo.getKey() + ": " + consumo.getValue() + " uAh"));
                }
            }

            runOnUiThread(() -> tv.append("\n\nTotal de Huella de Carbono Generada por Fabricante del Dispositivo:"));
            for (Map.Entry<String, Float> co2 : fabricantesCO2.entrySet()) {
                if (co2.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + co2.getKey() + ": " + co2.getValue() + " gCo2e"));
                }
            }

            Map.Entry<String, Integer> marcaMayorUso = Collections.max(marcasUsos.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nMarca de los dispositivos con Mayor Uso: " + marcaMayorUso.getKey()));
            runOnUiThread(() -> tv.append("\nNo. de Veces Usada: " + marcaMayorUso.getValue()));

            Map.Entry<String, Long> marcaMayorVisualizacion = Collections.max(marcasVisualizacion.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nMarca de los dispositivos con Mayor Tiempo Visualizado: " + marcaMayorVisualizacion.getKey()));
            runOnUiThread(() -> tv.append("\nTiempo Usada: " + marcaMayorVisualizacion.getValue()/(1000*60*60) + " h"));

            Map.Entry<String, Integer> marcaMayorConsumo = Collections.max(marcasConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nMarca de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + marcaMayorConsumo.getKey()));
            runOnUiThread(() -> tv.append("\nEnergía Consumida : " + marcaMayorConsumo.getValue() + " uAh"));

            Map.Entry<String, Float> marcaMayorCO2 = Collections.max(marcasCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nMarca de los Dispositivos que Generaron una Mayor Huella de Carbono: " + marcaMayorCO2.getKey()));
            runOnUiThread(() -> tv.append("\nHuella de Carbono: " + marcaMayorCO2.getValue() + "gCO2e"));

            runOnUiThread(() -> tv.append("\n\nTotal de Veces Usada por Marca del Dispositivo:"));
            for (Map.Entry<String, Integer> usos : marcasUsos.entrySet()) {
                if (usos.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + usos.getKey() + ": " + usos.getValue()));
                }
            }

            runOnUiThread(() -> tv.append("\n\nTotal de Tiempo Visualizado por Marca del Dispositivo:"));
            for (Map.Entry<String, Long> visualizado : marcasVisualizacion.entrySet()) {
                if (visualizado.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + visualizado.getKey() + ": " + visualizado.getValue()/(1000*60*60) + " h"));
                }
            }

            runOnUiThread(() -> tv.append("\n\nTotal de Energía Consumida por Marca del Dispositivo:"));
            for (Map.Entry<String, Integer> consumo : marcasConsumo.entrySet()) {
                if (consumo.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + consumo.getKey() + ": " + consumo.getValue() + " uAh"));
                }
            }

            runOnUiThread(() -> tv.append("\n\nTotal de Huella de Carbono Generada por Marca del Dispositivo:"));
            for (Map.Entry<String, Float> co2 : marcasCO2.entrySet()) {
                if (co2.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + co2.getKey() + ": " + co2.getValue() + " gCo2e"));
                }
            }

            Map.Entry<String, Integer> avMayorUso = Collections.max(avsUsos.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nVersión de Android de los dispositivos con Mayor Uso: " + avMayorUso.getKey()));
            runOnUiThread(() -> tv.append("\nNo. de Veces Usada: " + avMayorUso.getValue()));

            Map.Entry<String, Long> avMayorVisualizacion = Collections.max(avsVisualizacion.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nVersión de Android de los dispositivos con Mayor Tiempo Visualizado: " + avMayorVisualizacion.getKey()));
            runOnUiThread(() -> tv.append("\nTiempo Usada: " + avMayorVisualizacion.getValue()/(1000*60*60) + " h"));

            Map.Entry<String, Integer> avMayorConsumo = Collections.max(avsConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nVersión de Android de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + avMayorConsumo.getKey()));
            runOnUiThread(() -> tv.append("\nEnergía Consumida : " + avMayorConsumo.getValue() + " uAh"));

            Map.Entry<String, Float> avMayorCO2 = Collections.max(avsCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> tv.append("\n\nMarca de los Dispositivos que Generaron una Mayor Huella de Carbono: " + avMayorCO2.getKey()));
            runOnUiThread(() -> tv.append("\nHuella de Carbono: " + avMayorCO2.getValue() + "gCO2e"));

            runOnUiThread(() -> tv.append("\n\nTotal de Veces Usada por Versión de Android del Dispositivo:"));
            for (Map.Entry<String, Integer> usos : avsUsos.entrySet()) {
                if (usos.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + usos.getKey() + ": " + usos.getValue()));
                }
            }

            runOnUiThread(() -> tv.append("\n\nTotal de Tiempo Visualizado por Versión de Android del Dispositivo:"));
            for (Map.Entry<String, Long> visualizado : avsVisualizacion.entrySet()) {
                if (visualizado.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + visualizado.getKey() + ": " + visualizado.getValue()/(1000*60*60) + " h"));
                }
            }

            runOnUiThread(() -> tv.append("\n\nTotal de Energía Consumida por Versión de Android del Dispositivo:"));
            for (Map.Entry<String, Integer> consumo : avsConsumo.entrySet()) {
                if (consumo.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + consumo.getKey() + ": " + consumo.getValue() + " uAh"));
                }
            }

            runOnUiThread(() -> tv.append("\n\nTotal de Huella de Carbono Generada por Versión de Android del Dispositivo:"));
            for (Map.Entry<String, Float> co2 : avsCO2.entrySet()) {
                if (co2.getValue() > 0) {
                    runOnUiThread(() -> tv.append("\n- " + co2.getKey() + ": " + co2.getValue() + " gCo2e"));
                }
            }

            // Imprimir info por usuario:
            

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