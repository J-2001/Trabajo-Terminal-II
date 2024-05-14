package com.example.prototipo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.image.charts.ImageCharts;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdActivity extends AppCompatActivity {

    private final int textSize = 11;
    private String[] vsAppsNames;
    private String chartSize = "400x400";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        vsAppsNames = new String[]{getString(R.string.netflix), getString(R.string.disneyplus), getString(R.string.starplus), getString(R.string.primevideo), getString(R.string.max), getString(R.string.crunchyroll), getString(R.string.vix)};

        //textSize = (int) (11 * getResources().getDisplayMetrics().density + 0.5F);

        CoordinatorLayout coordinatorLayout = this.findViewById(R.id.thirdActivity);

        LinearLayout linearLayout = this.findViewById(R.id.third_linlay);

        ArrayList<TextView> textViews = new ArrayList<>();

        ArrayList<ImageView> imageViews = new ArrayList<>();

        Button btn = this.findViewById(R.id.third_btn_01);

        btn.setOnClickListener(v -> new Thread(() -> {
            Log.d("ThirdActivity", "Reporte General...");

            String[] all = getAllData().split("/");

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText(Arrays.toString(all));
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

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

            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nInfo: " + ainfo));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nData: " + adata));

            List<List<String>> info = new ArrayList<>();

            for (int i = 0; i < ainfo.size(); i++) {
                info.add(new ArrayList<>());
                String[] dinfo = ainfo.get(i).split(";");
                for (String s : dinfo) {
                    info.get(i).add(s.split(":")[1]);
                }
            }

            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nInfo: " + info));

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

            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nBateria: " + bateria));

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

            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nAnalizador: " + analizador));

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

            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEscaneo: " + escaneo));

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

            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella: " + huella));

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

            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nNo. Dispositivos Registrados: " + info.size()));

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
            Map<String, Integer> appsConsumo = new HashMap<>();
            for (String vsAppName : vsAppsNames) {
                appsConsumo.put(vsAppName, 0);
            }
            List<Map<String, List<Integer>>> vspu = new ArrayList<>();
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
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Veces Usada: " + totalVecesUsado));

            long totalTiempoVisualizado = ttv/(1000*60*60);
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Tiempo Visualizado: " + totalTiempoVisualizado + " h"));

            int totalEnergiaConsumida = tec;
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Energia Consumida: " + totalEnergiaConsumida + " uAh"));

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
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nHuella de Carbono Total Generada: " + totalHuellaCarbono + " gCO2e"));

            Map.Entry<String, Integer> appMasVista = Collections.max(appsVistas.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp de VS Mas Veces Vista: " + appMasVista.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nNo. de Veces Vista: " + appMasVista.getValue()));

            Map.Entry<String, Long> appMasUsada = Collections.max(appsUso.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp Mas Tiempo Usada: " + appMasUsada.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo Usada: " + appMasUsada.getValue()/(1000*60*60) + " h"));

            Map.Entry<String, Integer> appMayorConsumo = Collections.max(appsConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp que Consumió la Mayor Cantidad de Energía: " + appMayorConsumo.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida : " + appMayorConsumo.getValue() + " uAh"));

            Map.Entry<String, Float> appMayorCO2 = Collections.max(appsCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp que Generó una Mayor Huella de Carbono: " + appMayorCO2.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono: " + appMayorCO2.getValue() + " gCO2e"));

            try {
                File chart_01 = File.createTempFile("chart01_", ".png", getCacheDir());
                ImageCharts chart01 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Integer> vistas : appsVistas.entrySet()) {
                    d.add(String.valueOf(vistas.getValue()));
                    l.add(vistas.getKey());
                }
                //chart01.chd("a:" + String.join(",", d));
                chart01.chd("a:2.5,5,8.3").chl(String.join("|", new String[]{"2.5", "5", "8.3"})).chdl(String.join("|", l));
                URL url = new URL(chart01.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_01);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nNúmero de Visualizaciones por App:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_01));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(01): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 01!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_02 = File.createTempFile("chart02_", ".png", getCacheDir());
                ImageCharts chart02 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Long> uso : appsUso.entrySet()) {
                    d.add(String.valueOf(uso.getValue()));
                    l.add(uso.getKey());
                }
                //chart02.chd("a:" + String.join(",", d));
                chart02.chd("a:145,37,99").chl(String.join("|", new String[]{"145","37","99"})).chdl(String.join("|", l));
                URL url = new URL(chart02.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_02);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTiempo de Uso por App:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_02));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(02): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 02!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_03 = File.createTempFile("chart03_", ".png", getCacheDir());
                ImageCharts chart03 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Integer> consumo : appsConsumo.entrySet()) {
                    d.add(String.valueOf(consumo.getValue()));
                    l.add(consumo.getKey());
                }
                //chart03.chd("a:" + String.join(",", d));
                chart03.chd("a:1,2,3").chl(String.join("|", new String[]{"1","2","3"})).chdl(String.join("|", l));
                URL url = new URL(chart03.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_03);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nEnergía Consumida por App:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_03));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(03): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 03!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_04 = File.createTempFile("chart04_", ".png", getCacheDir());
                ImageCharts chart04 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Float> co2 : appsCO2.entrySet()) {
                    d.add(String.valueOf(co2.getValue()));
                    l.add(co2.getKey());
                }
                //chart04.chd("a:" + String.join(",", d));
                chart04.chd("a:99,98,99").chl(String.join("|", new String[]{"99","98","99"})).chdl(String.join("|", l));
                URL url = new URL(chart04.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_04);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nHuella de Carbono por App:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_04));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(04): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 04!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            if (fabricantesUsos.isEmpty()) {
                return;
            }
            Map.Entry<String, Integer> fabricanteMayorUso = Collections.max(fabricantesUsos.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nFabricante de los Dispositivos con Mayor Uso: " + fabricanteMayorUso.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nNo. de Veces Usada: " + fabricanteMayorUso.getValue()));

            Map.Entry<String, Long> fabricanteMayorVisualizacion = Collections.max(fabricantesVisualizacion.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nFabricante de los Dispositivos con Mayor Tiempo Visualizado: " + fabricanteMayorVisualizacion.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo Usada: " + fabricanteMayorVisualizacion.getValue()/(1000*60*60) + " h"));

            Map.Entry<String, Integer> fabricanteMayorConsumo = Collections.max(fabricantesConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nFabricante de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + fabricanteMayorConsumo.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida : " + fabricanteMayorConsumo.getValue() + " uAh"));

            Map.Entry<String, Float> fabricanteMayorCO2 = Collections.max(fabricantesCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nFabricante de los Dispositivos que Generaron una Mayor Huella de Carbono: " + fabricanteMayorCO2.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono: " + fabricanteMayorCO2.getValue() + " gCO2e"));

            try {
                File chart_05 = File.createTempFile("chart05_", ".png", getCacheDir());
                ImageCharts chart05 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Integer> usos : fabricantesUsos.entrySet()) {
                    d.add(String.valueOf(usos.getValue()));
                    l.add(usos.getKey());
                }
                chart05.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", l));
                URL url = new URL(chart05.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_05);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Veces Usada por Fabricante del Dispositivo:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_05));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(05): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 05!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_06 = File.createTempFile("chart06_", ".png", getCacheDir());
                ImageCharts chart06 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Long> visualizado : fabricantesVisualizacion.entrySet()) {
                    d.add(String.valueOf(visualizado.getValue()));
                    l.add(visualizado.getKey());
                }
                chart06.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", l));
                URL url = new URL(chart06.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_06);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Tiempo Visualizado por Fabricante del Dispositivo:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_06));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(06): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 06!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_07 = File.createTempFile("chart07_", ".png", getCacheDir());
                ImageCharts chart07 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Integer> consumo : fabricantesConsumo.entrySet()) {
                    d.add(String.valueOf(consumo.getValue()));
                    l.add(consumo.getKey());
                }
                chart07.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", l));
                URL url = new URL(chart07.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_07);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Energía Consumida por Fabricante del Dispositivo:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_07));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(07): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 07!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_08 = File.createTempFile("chart08_", ".png", getCacheDir());
                ImageCharts chart08 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Float> co2 : fabricantesCO2.entrySet()) {
                    d.add(String.valueOf(co2.getValue()));
                    l.add(co2.getKey());
                }
                chart08.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", l));
                URL url = new URL(chart08.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_08);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Huella de Carbono Generada por Fabricante del Dispositivo:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_08));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(08): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 08!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            Map.Entry<String, Integer> marcaMayorUso = Collections.max(marcasUsos.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nMarca de los dispositivos con Mayor Uso: " + marcaMayorUso.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nNo. de Veces Usada: " + marcaMayorUso.getValue()));

            Map.Entry<String, Long> marcaMayorVisualizacion = Collections.max(marcasVisualizacion.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nMarca de los dispositivos con Mayor Tiempo Visualizado: " + marcaMayorVisualizacion.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo Usada: " + marcaMayorVisualizacion.getValue()/(1000*60*60) + " h"));

            Map.Entry<String, Integer> marcaMayorConsumo = Collections.max(marcasConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nMarca de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + marcaMayorConsumo.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida : " + marcaMayorConsumo.getValue() + " uAh"));

            Map.Entry<String, Float> marcaMayorCO2 = Collections.max(marcasCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nMarca de los Dispositivos que Generaron una Mayor Huella de Carbono: " + marcaMayorCO2.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono: " + marcaMayorCO2.getValue() + " gCO2e"));

            try {
                File chart_09 = File.createTempFile("chart09_", ".png", getCacheDir());
                ImageCharts chart09 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Integer> usos : marcasUsos.entrySet()) {
                    d.add(String.valueOf(usos.getValue()));
                    l.add(usos.getKey());
                }
                chart09.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", l));
                URL url = new URL(chart09.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_09);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Veces Usada por Marca del Dispositivo:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_09));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(09): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 09!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_10 = File.createTempFile("chart10_", ".png", getCacheDir());
                ImageCharts chart10 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Long> visualizado : marcasVisualizacion.entrySet()) {
                    d.add(String.valueOf(visualizado.getValue()));
                    l.add(visualizado.getKey());
                }
                chart10.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", l));
                URL url = new URL(chart10.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_10);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Tiempo Visualizado por Marca del Dispositivo:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_10));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(10): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 10!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_11 = File.createTempFile("chart11_", ".png", getCacheDir());
                ImageCharts chart11 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Integer> consumo : marcasConsumo.entrySet()) {
                    d.add(String.valueOf(consumo.getValue()));
                    l.add(consumo.getKey());
                }
                chart11.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", l));
                URL url = new URL(chart11.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_11);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Energía Consumida por Marca del Dispositivo:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_11));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(11): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 11!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_12 = File.createTempFile("chart12_", ".png", getCacheDir());
                ImageCharts chart12 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Float> co2 : marcasCO2.entrySet()) {
                    d.add(String.valueOf(co2.getValue()));
                    l.add(co2.getKey());
                }
                chart12.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", l));
                URL url = new URL(chart12.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_12);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Huella de Carbono Generada por Marca del Dispositivo:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_12));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(12): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 12!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            Map.Entry<String, Integer> avMayorUso = Collections.max(avsUsos.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nVersión de Android de los dispositivos con Mayor Uso: " + avMayorUso.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nNo. de Veces Usada: " + avMayorUso.getValue()));

            Map.Entry<String, Long> avMayorVisualizacion = Collections.max(avsVisualizacion.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nVersión de Android de los dispositivos con Mayor Tiempo Visualizado: " + avMayorVisualizacion.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo Usada: " + avMayorVisualizacion.getValue()/(1000*60*60) + " h"));

            Map.Entry<String, Integer> avMayorConsumo = Collections.max(avsConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nVersión de Android de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + avMayorConsumo.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida : " + avMayorConsumo.getValue() + " uAh"));

            Map.Entry<String, Float> avMayorCO2 = Collections.max(avsCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nMarca de los Dispositivos que Generaron una Mayor Huella de Carbono: " + avMayorCO2.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono: " + avMayorCO2.getValue() + " gCO2e"));

            try {
                File chart_13 = File.createTempFile("chart13_", ".png", getCacheDir());
                ImageCharts chart13 = new ImageCharts().cht("p").chs(chartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                for (Map.Entry<String, Integer> usos : avsUsos.entrySet()) {
                    d.add(String.valueOf(usos.getValue()));
                    l.add(usos.getKey());
                }
                chart13.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", l));
                URL url = new URL(chart13.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_13);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Veces Usada por Versión de Android del Dispositivo:"));
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_13));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
            } catch (Exception e) {
                Log.e("Error(13): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 13!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            /*
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
/*
            runOnUiThread(() -> tv.append("\n\n\n - - - Usuarios: - - -"));
            runOnUiThread(() -> tv.append("\n\n\n - - - - - - - - - - - -"));

            for (int i = 0; i < info.size(); i++) {
                int u_totalVecesUsado = escaneo.get(i).size();
                runOnUiThread(() -> tv.append("\n\nTotal de Veces Usada: " + u_totalVecesUsado));

                long u_ttv = 0;
                int u_tec = 0;
                Map<String, Integer> u_appsVistas = new HashMap<>();
                for (String vsAppName : vsAppsNames) {
                    u_appsVistas.put(vsAppName, 0);
                }
                Map<String, Long> u_appsUso = new HashMap<>();
                for (String vsAppName : vsAppsNames) {
                    u_appsUso.put(vsAppName, 0L);
                }
                Map<String, Integer> u_appsConsumo = new HashMap<>();
                for (String vsAppName : vsAppsNames) {
                    u_appsConsumo.put(vsAppName, 0);
                }
                Map<String, List<Integer>> u_vs = new HashMap<>();
                for (String vsAppName : vsAppsNames) {
                    u_vs.put(vsAppName, new ArrayList<>());
                }
                int id = 1;
                for (List<Object> s : escaneo.get(i)) {
                    u_ttv += (Long) s.get(2);
                    u_tec += (Integer) s.get(3);
                    u_appsVistas.put((String) s.get(5), u_appsVistas.get((String) s.get(5)) + 1);
                    u_appsUso.put((String) s.get(5), u_appsUso.get((String) s.get(5)) + (Long) s.get(2));
                    u_appsConsumo.put((String) s.get(5), u_appsConsumo.get((String) s.get(5)) + (Integer) s.get(3));
                    u_vs.get((String) s.get(5)).add(id);
                    id++;
                }

                long u_totalTiempoVisualizado = u_ttv/(1000*60*60);
                runOnUiThread(() -> tv.append("\n\nTotal de Tiempo Visualizado: " + u_totalTiempoVisualizado + " h"));

                int u_totalEnergiaConsumida = u_tec;
                runOnUiThread(() -> tv.append("\n\nTotal de Energia Consumida: " + u_totalEnergiaConsumida + " uAh"));

                float u_thc = 0;
                Map<String, Float> u_appsCO2 = new HashMap<>();
                for (String vsAppName : vsAppsNames) {
                    u_appsCO2.put(vsAppName, 0F);
                }

                for (List<Object> h : huella.get(i)) {
                    u_thc += (Float) h.get(1);
                    for (String vs : u_vs.keySet()) {
                        if (u_vs.get(vs).contains((Integer) h.get(0))) {
                            u_appsCO2.put(vs, u_appsCO2.get(vs) + (Float) h.get(1));
                            break;
                        }
                    }
                }

                float u_totalHuellaCarbono = u_thc;
                runOnUiThread(() -> tv.append("\n\nHuella de Carbono Total Generada: " + u_totalHuellaCarbono + " gCO2e"));

                Map.Entry<String, Integer> u_appMasVista = Collections.max(u_appsVistas.entrySet(), Map.Entry.comparingByValue());
                runOnUiThread(() -> tv.append("\n\nApp de VS Mas Veces Vista: " + u_appMasVista.getKey()));
                runOnUiThread(() -> tv.append("\nNo. de Veces Vista: " + u_appMasVista.getValue()));

                Map.Entry<String, Long> u_appMasUsada = Collections.max(u_appsUso.entrySet(), Map.Entry.comparingByValue());
                runOnUiThread(() -> tv.append("\n\nApp Mas Tiempo Usada: " + u_appMasUsada.getKey()));
                runOnUiThread(() -> tv.append("\nTiempo Usada: " + u_appMasUsada.getValue()/(1000*60*60) + " h"));

                Map.Entry<String, Integer> u_appMayorConsumo = Collections.max(u_appsConsumo.entrySet(), Map.Entry.comparingByValue());
                runOnUiThread(() -> tv.append("\n\nApp que Consumió la Mayor Cantidad de Energía: " + u_appMayorConsumo.getKey()));
                runOnUiThread(() -> tv.append("\nEnergía Consumida : " + u_appMayorConsumo.getValue() + " uAh"));

                Map.Entry<String, Float> u_appMayorCO2 = Collections.max(u_appsCO2.entrySet(), Map.Entry.comparingByValue());
                runOnUiThread(() -> tv.append("\n\nApp que Generó una Mayor Huella de Carbono: " + u_appMayorCO2.getKey()));
                runOnUiThread(() -> tv.append("\nHuella de Carbono: " + u_appMayorCO2.getValue() + " gCO2e"));

                runOnUiThread(() -> tv.append("\n\nNúmero de Visualizaciones por app:"));
                for (Map.Entry<String, Integer> vistas : u_appsVistas.entrySet()) {
                    if (vistas.getValue() > 0) {
                        runOnUiThread(() -> tv.append("\n- " + vistas.getKey() + ": " + vistas.getValue()));
                    }
                }

                runOnUiThread(() -> tv.append("\n\nTiempo de Uso por app:"));
                for (Map.Entry<String, Long> uso : u_appsUso.entrySet()) {
                    if (uso.getValue() > 0) {
                        runOnUiThread(() -> tv.append("\n- " + uso.getKey() + ": " + uso.getValue()/(1000*60*60) + " h"));
                    }
                }

                runOnUiThread(() -> tv.append("\n\nEnergía Consumida por app:"));
                for (Map.Entry<String, Integer> consumo : u_appsConsumo.entrySet()) {
                    if (consumo.getValue() > 0) {
                        runOnUiThread(() -> tv.append("\n- " + consumo.getKey() + ": " + consumo.getValue() + " uAh"));
                    }
                }

                runOnUiThread(() -> tv.append("\n\nHuella de Carbono por app:"));
                for (Map.Entry<String, Float> co2 : u_appsCO2.entrySet()) {
                    if (co2.getValue() > 0) {
                        runOnUiThread(() -> tv.append("\n- " + co2.getKey() + ": " + co2.getValue() + " gCo2e"));
                    }
                }

                runOnUiThread(() -> tv.append("\n\n\n - - - - - - - - - - - -\n"));
            }*/
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