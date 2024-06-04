package com.example.prototipo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.image.charts.ImageCharts;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ThirdActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Map<String, String> videoStreaming = new HashMap<>();
        videoStreaming.put(getString(R.string.netflix), getString(R.color.netflix).substring(3));
        videoStreaming.put(getString(R.string.disneyplus), getString(R.color.disneyplus).substring(3));
        videoStreaming.put(getString(R.string.starplus), getString(R.color.starplus).substring(3));
        videoStreaming.put(getString(R.string.primevideo), getString(R.color.primevideo).substring(3));
        videoStreaming.put(getString(R.string.max), getString(R.color.max).substring(3));
        videoStreaming.put(getString(R.string.crunchyroll), getString(R.color.crunchyroll).substring(3));
        videoStreaming.put(getString(R.string.vix), getString(R.color.vix).substring(3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM", Locale.forLanguageTag("es-MX"));

        String pieChartSize = "400x350";
        String scatterChartSize = "500x500";

        int width = 612;
        int height = 792;
        int titleTextSize = 17;
        int textSize = 14;
        float titleSpaceWidth = titleTextSize * 0.6F;
        int titleLineSpacing = 24;
        int textLineSpacing = 19;
        int leftMargin = 9;

        //textSize = (int) (11 * getResources().getDisplayMetrics().density + 0.5F);

        CoordinatorLayout coordinatorLayout = this.findViewById(R.id.thirdActivity);

        LinearLayout linearLayout = this.findViewById(R.id.third_linlay);

        ArrayList<TextView> textViews = new ArrayList<>();

        ArrayList<ImageView> imageViews = new ArrayList<>();

        Button btn = this.findViewById(R.id.third_btn_01);

        btn.setOnClickListener(v -> new Thread(() -> {
            Log.d("ThirdActivity", "Reporte General...");

            runOnUiThread(() -> btn.setVisibility(View.GONE));

            String[] all = getAllData().split("/");

            String[] allInfo = all[0].split("_");
            String[] allData = all[1].split("_");

            List<String> ainfo = new ArrayList<>();
            List<String> adata = new ArrayList<>();

            for (int i = 0; i < allData.length; i++) {
                if (!allData[i].equals("0") && !allData[i].contains("::")) {
                    ainfo.add(allInfo[i]);
                    adata.add(allData[i]);
                }
            }

            List<List<String>> info = new ArrayList<>();

            for (int i = 0; i < ainfo.size(); i++) {
                info.add(new ArrayList<>());
                String[] dinfo = ainfo.get(i).split(";");
                for (String s : dinfo) {
                    info.get(i).add(s.split(":")[1]);
                }
            }

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

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("Escaneo: " + escaneo);
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

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

            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nManufacturers: " + manufacturers));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nBrands: " + brands));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nVersions: " + versions));

            PdfDocument pdfDocument = new PdfDocument();

            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(width, height, 15 + 4 * info.size() + 1).create(); // Carta (612x792)

            Paint titles = new Paint();
            titles.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));
            titles.setTextSize(titleTextSize);
            titles.setColor(getColor(R.color.black));

            Paint text = new Paint();
            text.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            text.setTextSize(textSize);
            text.setColor(getColor(R.color.black));

            Paint images = new Paint();

            final boolean[] open = {true};

            PdfDocument.Page page = pdfDocument.startPage(pageInfo);

            Canvas canvas = page.getCanvas();

            String s = "Instituto Politécnico Nacional";

            float x = (width - s.length() * titleSpaceWidth) / 2;
            int y = titleLineSpacing;

            canvas.drawText(s, x, y, titles);
            s = "Escuela superior de Cómputo";
            x = (width - s.length() * titleSpaceWidth) / 2;
            y += titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            s = "Reporte General Final";
            x = (width - s.length() * titleSpaceWidth) / 2;
            y += titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            s = "Trabajo Terminal No. 2024-a065";
            x = (width - s.length() * titleSpaceWidth) / 2;
            y += titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            s = "Consumo de energía en aplicaciones de Video Streaming";
            x = (width - s.length() * titleSpaceWidth) / 2;
            y += titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            s = "Integrantes:";
            x = (width - s.length() * titleSpaceWidth) / 2;
            y += titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            s = "Ríos alonso Juan Jose";
            x = (width - s.length() * titleSpaceWidth) / 2;
            y += titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            s = "Roldan Gómez Juan";
            x = (width - s.length() * titleSpaceWidth) / 2;
            y += titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            s = "Salazar Gómez andres";
            x = (width - s.length() * titleSpaceWidth) / 2;
            y += titleLineSpacing;
            canvas.drawText(s, x, y, titles);

            pdfDocument.finishPage(page);

            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nNo. Dispositivos Registrados: " + info.size()));
            page = pdfDocument.startPage(pageInfo);
            canvas = page.getCanvas();
            s = "Datos Generales";
            x = width / 2 - s.length() * titleTextSize / 4;
            y = titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            x = leftMargin;
            y += titleLineSpacing;
            canvas.drawText("No. Dispositivos Registrados: " + info.size(), x, y, text);

            int tvu = 0;
            long ttv = 0;
            int tec = 0;
            Map<String, Integer> appsVistas = new HashMap<>();
            for (String vsAppName : videoStreaming.keySet()) {
                appsVistas.put(vsAppName, 0);
            }
            Map<String, Long> appsUso = new HashMap<>();
            for (String vsAppName : videoStreaming.keySet()) {
                appsUso.put(vsAppName, 0L);
            }
            Map<String, Integer> appsConsumo = new HashMap<>();
            for (String vsAppName : videoStreaming.keySet()) {
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
                for (String vsAppName : videoStreaming.keySet()) {
                    vspu.get(i).put(vsAppName, new ArrayList<>());
                }
                fabricantesUsos.put(info.get(i).get(0), fabricantesUsos.get(info.get(i).get(0)) + escaneo.get(i).size());
                marcasUsos.put(info.get(i).get(1), marcasUsos.get(info.get(i).get(1)) + escaneo.get(i).size());
                avsUsos.put(info.get(i).get(3), avsUsos.get(info.get(i).get(3)) + escaneo.get(i).size());
                int id = 1;
                for (List<Object> scan : escaneo.get(i)) {
                    ttv += (Long) scan.get(2);
                    tec += (Integer) scan.get(3);
                    appsVistas.put((String) scan.get(5), appsVistas.get((String) scan.get(5)) + 1);
                    appsUso.put((String) scan.get(5), appsUso.get((String) scan.get(5)) + (Long) scan.get(2));
                    appsConsumo.put((String) scan.get(5), appsConsumo.get((String) scan.get(5)) + (Integer) scan.get(3));
                    vspu.get(i).get((String) scan.get(5)).add(id);
                    fabricantesVisualizacion.put(info.get(i).get(0), fabricantesVisualizacion.get(info.get(i).get(0)) + (Long) scan.get(2));
                    fabricantesConsumo.put(info.get(i).get(0), fabricantesConsumo.get(info.get(i).get(0)) + (Integer) scan.get(3));
                    marcasVisualizacion.put(info.get(i).get(1), marcasVisualizacion.get(info.get(i).get(1)) + (Long) scan.get(2));
                    marcasConsumo.put(info.get(i).get(1), marcasConsumo.get(info.get(i).get(1)) + (Integer) scan.get(3));
                    avsVisualizacion.put(info.get(i).get(3), avsVisualizacion.get(info.get(i).get(3)) + (Long) scan.get(2));
                    avsConsumo.put(info.get(i).get(3), avsConsumo.get(info.get(i).get(3)) + (Integer) scan.get(3));
                    id++;
                }
            }

            int totalVecesUsado = tvu;
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Veces Usada: " + totalVecesUsado));
            y += textLineSpacing;
            canvas.drawText("Total de Veces Usada: " + totalVecesUsado, x, y, text);

            double totalTiempoVisualizado = ttv/(1000.0*60*60);
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Tiempo Visualizado: " + getString(R.string.round_one_decimal_place, totalTiempoVisualizado) + " h"));
            y += textLineSpacing;
            canvas.drawText("Total de Tiempo Visualizado: " + getString(R.string.round_one_decimal_place, totalTiempoVisualizado) + " h", x, y, text);

            int totalEnergiaConsumida = tec;
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Energia Consumida: " + totalEnergiaConsumida + " uAh"));
            y += textLineSpacing;
            canvas.drawText("Total de Energia Consumida: " + totalEnergiaConsumida + " uAh", x, y, text);

            float thc = 0;
            Map<String, Float> appsCO2 = new HashMap<>();
            for (String vsAppName : videoStreaming.keySet()) {
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
                    marcasCO2.put(info.get(i).get(1), marcasCO2.get(info.get(i).get(1)) + (Float) h.get(1));
                    avsCO2.put(info.get(i).get(3), avsCO2.get(info.get(i).get(3)) + (Float) h.get(1));
                    for (String vs : vspu.get(i).keySet()) {
                        if (vspu.get(i).get(vs).contains((Integer) h.get(0))) {
                            appsCO2.put(vs, appsCO2.get(vs) + (Float) h.get(1));
                            break;
                        }
                    }
                }
            }

            float totalHuellaCarbono = thc;
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nHuella de Carbono Total Generada: " + getString(R.string.round_four_decimal_places, totalHuellaCarbono) + " gCO2e"));
            y += textLineSpacing;
            canvas.drawText("Huella de Carbono Total Generada: " + getString(R.string.round_four_decimal_places, totalHuellaCarbono) + " gCO2e", x, y, text);
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nQue es Equivalente a:"));
            y += textLineSpacing;
            canvas.drawText("Que es Equivalente a:", x, y, text);
            float lgc = thc / 2347.69814F;
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n" + getString(R.string.round_four_decimal_places, lgc) + " Litros de Gasolina Consumidos"));
            y += textLineSpacing;
            canvas.drawText(getString(R.string.round_four_decimal_places, lgc) + " Litros de Gasolina Consumidos", x, y, text);
            float krv = lgc * 9.73577129F;
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n" + getString(R.string.round_four_decimal_places, krv) + " Kilómetros Recorridos por un Vehículo de Pasajeros (4 llantas y 2 ejes)"));
            y += textLineSpacing;
            canvas.drawText(getString(R.string.round_four_decimal_places, krv) + " Kilómetros Recorridos por un Vehículo de Pasajeros (4 llantas y 2 ejes)", x, y, text);
            float kgu = thc / 3031.35524F;
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n" + getString(R.string.round_four_decimal_places, kgu) + " Kilogramos de Gas Usados para Cocinar"));
            y += textLineSpacing;
            canvas.drawText(getString(R.string.round_four_decimal_places, kgu) + " Kilogramos de Gas Usados para Cocinar", x, y, text);
            float kcq = thc / 1.96814494F;
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n" + getString(R.string.round_four_decimal_places, kcq) + " Kilogramos de Carbón Quemados"));
            y += textLineSpacing;
            canvas.drawText(getString(R.string.round_four_decimal_places, kcq) + " Kilogramos de Carbón Quemados", x, y, text);

            Map<Integer, Integer> horas = new HashMap<>();

            for (List<List<Object>> bat : bateria) {
                Map<Integer, Integer> hours = new HashMap<>();
                for (List<Object> b : bat) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date((Long) b.get(4)));
                    if (hours.containsKey(calendar.get(Calendar.HOUR_OF_DAY))) {
                        hours.put(calendar.get(Calendar.HOUR_OF_DAY), hours.get(calendar.get(Calendar.HOUR_OF_DAY)) + 1);
                    } else {
                        hours.put(calendar.get(Calendar.HOUR_OF_DAY), 1);
                    }
                }
                int rushHour = Collections.max(hours.entrySet(), Map.Entry.comparingByValue()).getKey();
                if (horas.containsKey(rushHour)) {
                    horas.put(rushHour, horas.get(rushHour) + 1);
                } else {
                    horas.put(rushHour, 1);
                }
            }

            int horaPico = Collections.max(horas.entrySet(), Map.Entry.comparingByValue()).getKey();
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nHora Pico: " + horaPico + ":00"));
            y += textLineSpacing;
            canvas.drawText("Hora Pico: " + horaPico + ":00", x, y, text);

            pdfDocument.finishPage(page);

            Map.Entry<String, Integer> appMasVista = Collections.max(appsVistas.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp de VS Mas Veces Vista: " + appMasVista.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nNo. de Veces Vista: " + appMasVista.getValue()));
            page = pdfDocument.startPage(pageInfo);
            canvas = page.getCanvas();
            s = "Datos sobre las aplicaciones de VideoStreaming";
            x = width / 2 - s.length() * titleTextSize / 4;
            y = titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            x = leftMargin;
            y += titleLineSpacing;
            canvas.drawText("App de VS Mas Veces Vista: " + appMasVista.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("No. de Veces Vista: " + appMasVista.getValue(), x, y, text);

            Map.Entry<String, Long> appMasUsada = Collections.max(appsUso.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp Mas Tiempo Usada: " + appMasUsada.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo Usada: " + getString(R.string.round_one_decimal_place, appMasUsada.getValue()/(1000.0*60*60)) + " h"));
            y += textLineSpacing;
            canvas.drawText("App Mas Tiempo Usada: " + appMasUsada.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Tiempo Usada: " + getString(R.string.round_one_decimal_place, appMasUsada.getValue()/(1000.0*60*60)) + " h", x, y, text);

            Map.Entry<String, Integer> appMayorConsumo = Collections.max(appsConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp que Consumió la Mayor Cantidad de Energía: " + appMayorConsumo.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida : " + appMayorConsumo.getValue() + " uAh"));
            y += textLineSpacing;
            canvas.drawText("App que Consumió la Mayor Cantidad de Energía: " + appMayorConsumo.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Energía Consumida : " + appMayorConsumo.getValue() + " uAh", x, y, text);

            Map.Entry<String, Float> appMayorCO2 = Collections.max(appsCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp que Generó una Mayor Huella de Carbono: " + appMayorCO2.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono: " + getString(R.string.round_one_decimal_place, appMayorCO2.getValue()) + " gCO2e"));
            y += textLineSpacing;
            canvas.drawText("App que Generó una Mayor Huella de Carbono: " + appMayorCO2.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Huella de Carbono: " + getString(R.string.round_one_decimal_place, appMayorCO2.getValue()) + " gCO2e", x, y, text);
            pdfDocument.finishPage(page);

            try {
                File chart_01 = File.createTempFile("chart01_", ".png", getCacheDir());
                ImageCharts chart01 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                List<String> co = new ArrayList<>();
                for (Map.Entry<String, Integer> vistas : appsVistas.entrySet()) {
                    if (vistas.getValue() == 0) {
                        continue;
                    }
                    d.add(String.valueOf(vistas.getValue()));
                    dl.add(vistas.getKey());
                    co.add(videoStreaming.get(vistas.getKey()));
                }
                chart01.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", dl)).chdlp("b").chlps("color,FFFFFF").chco(String.join("|", co));
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
                page = pdfDocument.startPage(pageInfo);
                canvas = page.getCanvas();
                y = titleLineSpacing;
                canvas.drawText("Número de Visualizaciones por App:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                //imageViews.get(imageViews.size()-1).setAdjustViewBounds(true);
                //imageViews.get(imageViews.size()-1).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_01));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_01.getAbsolutePath()), x, y, images);
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
                ImageCharts chart02 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                List<String> co = new ArrayList<>();
                for (Map.Entry<String, Long> uso : appsUso.entrySet()) {
                    if (uso.getValue() == 0) {
                        continue;
                    }
                    d.add(String.valueOf(uso.getValue()));
                    l.add(getString(R.string.round_one_decimal_place, uso.getValue()/(1000.0*60*60)));
                    dl.add(uso.getKey());
                    co.add(videoStreaming.get(uso.getKey()));
                }
                chart02.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b").chlps("color,FFFFFF").chco(String.join("|", co));
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
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo de Uso por App:"));
                x = leftMargin;
                y += Integer.parseInt(pieChartSize.split("x")[1]) + textLineSpacing;
                canvas.drawText("Tiempo de Uso por App:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_02));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_02.getAbsolutePath()), x, y, images);
                pdfDocument.finishPage(page);
            } catch (Exception e) {
                Log.e("Error(02): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 02!!!"));
                pdfDocument.finishPage(page);
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_03 = File.createTempFile("chart03_", ".png", getCacheDir());
                ImageCharts chart03 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                List<String> co = new ArrayList<>();
                for (Map.Entry<String, Integer> consumo : appsConsumo.entrySet()) {
                    if (consumo.getValue() == 0) {
                        continue;
                    }
                    d.add(String.valueOf(consumo.getValue()));
                    l.add(largeValueFormatter(consumo.getValue()));
                    dl.add(consumo.getKey());
                    co.add(videoStreaming.get(consumo.getKey()));
                }
                chart03.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b").chco(String.join("|", co)).chlps("color,FFFFFF");
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
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida por App:"));
                page = pdfDocument.startPage(pageInfo);
                canvas = page.getCanvas();
                x = leftMargin;
                y = titleLineSpacing;
                canvas.drawText("Tiempo de Uso por App:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_03));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_03.getAbsolutePath()), x, y, images);
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
                ImageCharts chart04 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                List<String> co = new ArrayList<>();
                for (Map.Entry<String, Float> co2 : appsCO2.entrySet()) {
                    if (co2.getValue() == 0) {
                        continue;
                    }
                    d.add(String.valueOf(co2.getValue()));
                    l.add(getString(R.string.round_four_decimal_places, co2.getValue()));
                    dl.add(co2.getKey());
                    co.add(videoStreaming.get(co2.getKey()));
                }
                chart04.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b").chco(String.join("|", co)).chlps("color,FFFFFF");
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
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono por App:"));
                x = leftMargin;
                y += Integer.parseInt(pieChartSize.split("x")[1]) + textLineSpacing;
                canvas.drawText("Huella de Carbono por App:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_04));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_04.getAbsolutePath()), x, y, images);
                pdfDocument.finishPage(page);
            } catch (Exception e) {
                Log.e("Error(04): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 04!!!"));
                pdfDocument.finishPage(page);
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            /*if (fabricantesUsos.isEmpty()) {
                return;
            }*/
            Map.Entry<String, Integer> fabricanteMayorUso = Collections.max(fabricantesUsos.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nFabricante de los Dispositivos con Mayor Uso: " + fabricanteMayorUso.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nNo. de Veces Usada: " + fabricanteMayorUso.getValue()));
            page = pdfDocument.startPage(pageInfo);
            canvas = page.getCanvas();
            s = "Datos Sobre los Fabricantes de los dispositivos Registrados";
            x = width / 2 - s.length() * titleTextSize / 4;
            y = titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            x = leftMargin;
            y += textLineSpacing;
            canvas.drawText("Fabricante de los Dispositivos con Mayor Uso: " + fabricanteMayorUso.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("No. de Veces Usada: " + fabricanteMayorUso.getValue(), x, y, text);

            Map.Entry<String, Long> fabricanteMayorVisualizacion = Collections.max(fabricantesVisualizacion.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nFabricante de los Dispositivos con Mayor Tiempo Visualizado: " + fabricanteMayorVisualizacion.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo Usada: " + getString(R.string.round_one_decimal_place, fabricanteMayorVisualizacion.getValue()/(1000.0*60*60)) + " h"));
            y += textLineSpacing;
            canvas.drawText("Fabricante de los Dispositivos con Mayor Tiempo Visualizado: " + fabricanteMayorVisualizacion.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Tiempo Usada: " + getString(R.string.round_one_decimal_place, fabricanteMayorVisualizacion.getValue()/(1000.0*60*60)) + " h", x, y, text);

            Map.Entry<String, Integer> fabricanteMayorConsumo = Collections.max(fabricantesConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nFabricante de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + fabricanteMayorConsumo.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida : " + fabricanteMayorConsumo.getValue() + " uAh"));
            y += textLineSpacing;
            canvas.drawText("Fabricante de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + fabricanteMayorConsumo.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Energía Consumida : " + fabricanteMayorConsumo.getValue() + " uAh", x, y, text);

            Map.Entry<String, Float> fabricanteMayorCO2 = Collections.max(fabricantesCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nFabricante de los Dispositivos que Generaron una Mayor Huella de Carbono: " + fabricanteMayorCO2.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono: " + getString(R.string.round_four_decimal_places, fabricanteMayorCO2.getValue()) + " gCO2e"));
            y += textLineSpacing;
            canvas.drawText("Fabricante de los Dispositivos que Generaron una Mayor Huella de Carbono: " + fabricanteMayorCO2.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Huella de Carbono: " + getString(R.string.round_four_decimal_places, fabricanteMayorCO2.getValue()) + " gCO2e", x, y, text);
            pdfDocument.finishPage(page);

            try {
                File chart_05 = File.createTempFile("chart05_", ".png", getCacheDir());
                ImageCharts chart05 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Integer> usos : fabricantesUsos.entrySet()) {
                    d.add(String.valueOf(usos.getValue()));
                    dl.add(usos.getKey());
                }
                chart05.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", dl)).chdlp("b");
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
                page = pdfDocument.startPage(pageInfo);
                canvas = page.getCanvas();
                y = titleLineSpacing;
                canvas.drawText("Total de Veces Usada por Fabricante del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_05));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_05.getAbsolutePath()), x, y, images);
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
                ImageCharts chart06 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Long> visualizado : fabricantesVisualizacion.entrySet()) {
                    d.add(String.valueOf(visualizado.getValue()));
                    l.add(getString(R.string.round_one_decimal_place, visualizado.getValue()/(1000.0*60*60)));
                    dl.add(visualizado.getKey());
                }
                chart06.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b");
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
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTotal de Tiempo Visualizado por Fabricante del Dispositivo:"));
                x = leftMargin;
                y += Integer.parseInt(pieChartSize.split("x")[1]) + textLineSpacing;
                canvas.drawText("Total de Tiempo Visualizado por Fabricante del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_06));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_06.getAbsolutePath()), x, y, images);
                pdfDocument.finishPage(page);
            } catch (Exception e) {
                Log.e("Error(06): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 06!!!"));
                pdfDocument.finishPage(page);
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_07 = File.createTempFile("chart07_", ".png", getCacheDir());
                ImageCharts chart07 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Integer> consumo : fabricantesConsumo.entrySet()) {
                    d.add(String.valueOf(consumo.getValue()));
                    l.add(largeValueFormatter(consumo.getValue()));
                    dl.add(consumo.getKey());
                }
                chart07.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b");
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
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTotal de Energía Consumida por Fabricante del Dispositivo:"));
                page = pdfDocument.startPage(pageInfo);
                canvas = page.getCanvas();
                x = leftMargin;
                y = titleLineSpacing;
                canvas.drawText("Total de Energía Consumida por Fabricante del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_07));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_07.getAbsolutePath()), x, y, images);
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
                ImageCharts chart08 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Float> co2 : fabricantesCO2.entrySet()) {
                    d.add(String.valueOf(co2.getValue()));
                    l.add(getString(R.string.round_four_decimal_places, co2.getValue()));
                    dl.add(co2.getKey());
                }
                chart08.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", d)).chdlp("b");
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
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTotal de Huella de Carbono Generada por Fabricante del Dispositivo:"));
                x = leftMargin;
                y += Integer.parseInt(pieChartSize.split("x")[1]) + textLineSpacing;
                canvas.drawText("Total de Huella de Carbono Generada por Fabricante del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_08));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += titleLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_08.getAbsolutePath()), x, y, images);
                pdfDocument.finishPage(page);
            } catch (Exception e) {
                Log.e("Error(08): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 08!!!"));
                pdfDocument.finishPage(page);
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            Map.Entry<String, Integer> marcaMayorUso = Collections.max(marcasUsos.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nMarca de los dispositivos con Mayor Uso: " + marcaMayorUso.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nNo. de Veces Usada: " + marcaMayorUso.getValue()));
            page = pdfDocument.startPage(pageInfo);
            canvas = page.getCanvas();
            s = "Datos Sobre las Marcas de los dispositivos Registrados";
            x = width / 2 - s.length() * titleTextSize / 4;
            y = titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            x = leftMargin;
            y += textLineSpacing;
            canvas.drawText("Marca de los dispositivos con Mayor Uso: " + marcaMayorUso.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("No. de Veces Usada: " + marcaMayorUso.getValue(), x, y, text);

            Map.Entry<String, Long> marcaMayorVisualizacion = Collections.max(marcasVisualizacion.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nMarca de los dispositivos con Mayor Tiempo Visualizado: " + marcaMayorVisualizacion.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo Usada: " + getString(R.string.round_one_decimal_place, marcaMayorVisualizacion.getValue()/(1000.0*60*60)) + " h"));
            y += textLineSpacing;
            canvas.drawText("Marca de los dispositivos con Mayor Tiempo Visualizado: " + marcaMayorVisualizacion.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Tiempo Usada: " + getString(R.string.round_one_decimal_place, marcaMayorVisualizacion.getValue()/(1000.0*60*60)) + " h", x, y, text);

            Map.Entry<String, Integer> marcaMayorConsumo = Collections.max(marcasConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nMarca de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + marcaMayorConsumo.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida : " + marcaMayorConsumo.getValue() + " uAh"));
            y += textLineSpacing;
            canvas.drawText("Marca de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + marcaMayorConsumo.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Energía Consumida : " + marcaMayorConsumo.getValue() + " uAh", x, y, text);

            Map.Entry<String, Float> marcaMayorCO2 = Collections.max(marcasCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nMarca de los Dispositivos que Generaron una Mayor Huella de Carbono: " + marcaMayorCO2.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono: " + getString(R.string.round_one_decimal_place, marcaMayorCO2.getValue()) + " gCO2e"));
            y += textLineSpacing;
            canvas.drawText("Marca de los Dispositivos que Generaron una Mayor Huella de Carbono: " + marcaMayorCO2.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Huella de Carbono: " + getString(R.string.round_one_decimal_place, marcaMayorCO2.getValue()) + " gCO2e", x, y, text);
            pdfDocument.finishPage(page);

            try {
                File chart_09 = File.createTempFile("chart09_", ".png", getCacheDir());
                ImageCharts chart09 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Integer> usos : marcasUsos.entrySet()) {
                    d.add(String.valueOf(usos.getValue()));
                    dl.add(usos.getKey());
                }
                chart09.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", dl)).chdlp("b");
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
                page = pdfDocument.startPage(pageInfo);
                canvas = page.getCanvas();
                y = titleLineSpacing;
                canvas.drawText("Total de Veces Usada por Marca del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_09));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_09.getAbsolutePath()), x, y, images);
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
                ImageCharts chart10 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Long> visualizado : marcasVisualizacion.entrySet()) {
                    d.add(String.valueOf(visualizado.getValue()));
                    l.add(getString(R.string.round_one_decimal_place, visualizado.getValue()/(1000.0*60*60)));
                    dl.add(visualizado.getKey());
                }
                chart10.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b");
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
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTotal de Tiempo Visualizado por Marca del Dispositivo:"));
                x = leftMargin;
                y += Integer.parseInt(pieChartSize.split("x")[1]) + textLineSpacing;
                canvas.drawText("Total de Tiempo Visualizado por Marca del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_10));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_10.getAbsolutePath()), x, y, images);
                pdfDocument.finishPage(page);
            } catch (Exception e) {
                Log.e("Error(10): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 10!!!"));
                pdfDocument.finishPage(page);
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_11 = File.createTempFile("chart11_", ".png", getCacheDir());
                ImageCharts chart11 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Integer> consumo : marcasConsumo.entrySet()) {
                    d.add(String.valueOf(consumo.getValue()));
                    l.add(largeValueFormatter(consumo.getValue()));
                    dl.add(consumo.getKey());
                }
                chart11.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b");
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
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTotal de Energía Consumida por Marca del Dispositivo:"));
                page = pdfDocument.startPage(pageInfo);
                canvas = page.getCanvas();
                x = leftMargin;
                y = titleLineSpacing;
                canvas.drawText("Total de Energía Consumida por Marca del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_11));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_11.getAbsolutePath()), x, y, images);
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
                ImageCharts chart12 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Float> co2 : marcasCO2.entrySet()) {
                    d.add(String.valueOf(co2.getValue()));
                    l.add(getString(R.string.round_four_decimal_places, co2.getValue()));
                    dl.add(co2.getKey());
                }
                chart12.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b");
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
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTotal de Huella de Carbono Generada por Marca del Dispositivo:"));
                x = leftMargin;
                y += Integer.parseInt(pieChartSize.split("x")[1]) + textLineSpacing;
                canvas.drawText("Total de Huella de Carbono Generada por Marca del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_12));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_12.getAbsolutePath()), x, y, images);
                pdfDocument.finishPage(page);
            } catch (Exception e) {
                Log.e("Error(12): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 12!!!"));
                pdfDocument.finishPage(page);
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            Map.Entry<String, Integer> avMayorUso = Collections.max(avsUsos.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nVersión de Android de los dispositivos con Mayor Uso: " + avMayorUso.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nNo. de Veces Usada: " + avMayorUso.getValue()));
            page = pdfDocument.startPage(pageInfo);
            canvas = page.getCanvas();
            s = "Datos Sobre la Versión de android de los dispositivos Registrados";
            x = width / 2 - s.length() * titleTextSize / 4;
            y = titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            x = leftMargin;
            y += textLineSpacing;
            canvas.drawText("Versión de Android de los dispositivos con Mayor Uso: " + avMayorUso.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("No. de Veces Usada: " + avMayorUso.getValue(), x, y, text);

            Map.Entry<String, Long> avMayorVisualizacion = Collections.max(avsVisualizacion.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nVersión de Android de los dispositivos con Mayor Tiempo Visualizado: " + avMayorVisualizacion.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo Usada: " + getString(R.string.round_one_decimal_place, avMayorVisualizacion.getValue()/(1000.0*60*60)) + " h"));
            y += textLineSpacing;
            canvas.drawText("Versión de Android de los dispositivos con Mayor Tiempo Visualizado: " + avMayorVisualizacion.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Tiempo Usada: " + getString(R.string.round_one_decimal_place, avMayorVisualizacion.getValue()/(1000.0*60*60)) + " h", x, y, text);

            Map.Entry<String, Integer> avMayorConsumo = Collections.max(avsConsumo.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nVersión de Android de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + avMayorConsumo.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida : " + avMayorConsumo.getValue() + " uAh"));
            y += textLineSpacing;
            canvas.drawText("Versión de Android de los Dispositivos que Consumieron la Mayor Cantidad de Energía: " + avMayorConsumo.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Energía Consumida : " + avMayorConsumo.getValue() + " uAh", x, y, text);

            Map.Entry<String, Float> avMayorCO2 = Collections.max(avsCO2.entrySet(), Map.Entry.comparingByValue());
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nVersión de Android de los Dispositivos que Generaron una Mayor Huella de Carbono: " + avMayorCO2.getKey()));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono: " + getString(R.string.round_four_decimal_places, avMayorCO2.getValue()) + " gCO2e"));
            y += textLineSpacing;
            canvas.drawText("Versión de Android de los Dispositivos que Generaron una Mayor Huella de Carbono: " + avMayorCO2.getKey(), x, y, text);
            y += textLineSpacing;
            canvas.drawText("Huella de Carbono: " + getString(R.string.round_four_decimal_places, avMayorCO2.getValue()) + " gCO2e", x, y, text);
            pdfDocument.finishPage(page);

            try {
                File chart_13 = File.createTempFile("chart13_", ".png", getCacheDir());
                ImageCharts chart13 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Integer> usos : avsUsos.entrySet()) {
                    d.add(String.valueOf(usos.getValue()));
                    dl.add(usos.getKey());
                }
                chart13.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", dl)).chdlp("b");
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
                page = pdfDocument.startPage(pageInfo);
                canvas = page.getCanvas();
                y = titleLineSpacing;
                canvas.drawText("Total de Veces Usada por Versión de Android del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_13));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_13.getAbsolutePath()), x, y, images);
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

            try {
                File chart_14 = File.createTempFile("chart14_", ".png", getCacheDir());
                ImageCharts chart14 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Long> visualizado : avsVisualizacion.entrySet()) {
                    d.add(String.valueOf(visualizado.getValue()));
                    l.add(getString(R.string.round_one_decimal_place, visualizado.getValue()/(1000.0*60*60)));
                    dl.add(visualizado.getKey());
                }
                chart14.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b");
                URL url = new URL(chart14.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_14);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTotal de Tiempo Visualizado por Versión de Android del Dispositivo:"));
                x = leftMargin;
                y += Integer.parseInt(pieChartSize.split("x")[1]) + textLineSpacing;
                canvas.drawText("Total de Tiempo Visualizado por Versión de Android del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_14));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_14.getAbsolutePath()), x, y, images);
                pdfDocument.finishPage(page);
            } catch (Exception e) {
                Log.e("Error(14): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 14!!!"));
                pdfDocument.finishPage(page);
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_15 = File.createTempFile("chart15_", ".png", getCacheDir());
                ImageCharts chart15 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Integer> consumo : avsConsumo.entrySet()) {
                    d.add(String.valueOf(consumo.getValue()));
                    l.add(largeValueFormatter(consumo.getValue()));
                    dl.add(consumo.getKey());
                }
                chart15.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b");
                URL url = new URL(chart15.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_15);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTotal de Energía Consumida por Versión de Android del Dispositivo:"));
                page = pdfDocument.startPage(pageInfo);
                canvas = page.getCanvas();
                x = leftMargin;
                y = titleLineSpacing;
                canvas.drawText("Total de Energía Consumida por Versión de Android del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_15));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_15.getAbsolutePath()), x, y, images);
            } catch (Exception e) {
                Log.e("Error(15): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 15!!!"));
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

            try {
                File chart_16 = File.createTempFile("chart16_", ".png", getCacheDir());
                ImageCharts chart16 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                List<String> d = new ArrayList<>();
                List<String> l = new ArrayList<>();
                List<String> dl = new ArrayList<>();
                for (Map.Entry<String, Float> co2 : avsCO2.entrySet()) {
                    d.add(String.valueOf(co2.getValue()));
                    l.add(getString(R.string.round_four_decimal_places, co2.getValue()));
                    dl.add(co2.getKey());
                }
                chart16.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b");
                URL url = new URL(chart16.toURL());
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(chart_16);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = is.read(b)) != -1) {
                    os.write(b, 0, nRead);
                }
                is.close();
                os.close();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTotal de Huella de Carbono Generada por Versión de Android del Dispositivo:"));
                x = leftMargin;
                y += Integer.parseInt(pieChartSize.split("x")[1]) + textLineSpacing;
                canvas.drawText("Total de Huella de Carbono Generada por Versión de Android del Dispositivo:", x, y, text);
                imageViews.add(new ImageView(this));
                imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(chart_16));
                runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                y += textLineSpacing;
                canvas.drawBitmap(BitmapFactory.decodeFile(chart_16.getAbsolutePath()), x, y, images);
                pdfDocument.finishPage(page);
            } catch (Exception e) {
                Log.e("Error(16): ", e.toString());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart 16!!!"));
                pdfDocument.finishPage(page);
            }

            textViews.add(new TextView(this));
            textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.get(textViews.size()-1).setTextSize(textSize);
            textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
            textViews.get(textViews.size()-1).setText("\n\n - - - Usuarios: - - -");
            runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));
            runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\n\n - - - - - - - - - - - -\n"));

            page = pdfDocument.startPage(pageInfo);
            canvas = page.getCanvas();
            s = "Usuarios";
            x = width / 2 - s.length() * titleTextSize / 4;
            y = titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            pdfDocument.finishPage(page);

            for (int i = 0; i < info.size(); i++) {
                int no = i;
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nUsuario No. " + (no+1)));
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nFabricante del Dispositivo: " + info.get(no).get(0)));
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nMarca del Dispositivo: " + info.get(no).get(1)));
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nModelo del Dispositivo: " + info.get(no).get(2)));
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nVersión de Android del Dispositivo: " + info.get(no).get(3)));
                page = pdfDocument.startPage(pageInfo);
                canvas = page.getCanvas();
                s = "Usuario No. " + (no + 1);
                x = width / 2 - s.length() * titleTextSize / 4;
                y = titleLineSpacing;
                canvas.drawText(s, x, y, titles);
                x = leftMargin;
                y += textLineSpacing;
                canvas.drawText("Fabricante del Dispositivo: " + info.get(no).get(0), x, y, text);
                y += textLineSpacing;
                canvas.drawText("Marca del Dispositivo: " + info.get(no).get(1), x, y, text);
                y += textLineSpacing;
                canvas.drawText("Modelo del Dispositivo: " + info.get(no).get(2), x, y, text);
                y += textLineSpacing;
                canvas.drawText("Versión de Android del Dispositivo: " + info.get(no).get(3), x, y, text);

                int u_totalVecesUsado = escaneo.get(i).size();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Veces Usada: " + u_totalVecesUsado));
                y += textLineSpacing;
                canvas.drawText("Total de Veces Usada: " + u_totalVecesUsado, x, y, text);

                long u_ttv = 0;
                int u_tec = 0;
                Map<String, Integer> u_appsVistas = new HashMap<>();
                for (String vsAppName : videoStreaming.keySet()) {
                    u_appsVistas.put(vsAppName, 0);
                }
                Map<String, Long> u_appsUso = new HashMap<>();
                for (String vsAppName : videoStreaming.keySet()) {
                    u_appsUso.put(vsAppName, 0L);
                }
                Map<String, Integer> u_appsConsumo = new HashMap<>();
                for (String vsAppName : videoStreaming.keySet()) {
                    u_appsConsumo.put(vsAppName, 0);
                }
                Map<String, List<Integer>> u_vs = new HashMap<>();
                for (String vsAppName : videoStreaming.keySet()) {
                    u_vs.put(vsAppName, new ArrayList<>());
                }
                int id = 1;
                for (List<Object> scan : escaneo.get(i)) {
                    u_ttv += (Long) scan.get(2);
                    u_tec += (Integer) scan.get(3);
                    u_appsVistas.put((String) scan.get(5), u_appsVistas.get((String) scan.get(5)) + 1);
                    u_appsUso.put((String) scan.get(5), u_appsUso.get((String) scan.get(5)) + (Long) scan.get(2));
                    u_appsConsumo.put((String) scan.get(5), u_appsConsumo.get((String) scan.get(5)) + (Integer) scan.get(3));
                    u_vs.get((String) scan.get(5)).add(id);
                    id++;
                }

                double u_totalTiempoVisualizado = u_ttv/(1000.0*60*60);
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Tiempo Visualizado: " + getString(R.string.round_one_decimal_place, u_totalTiempoVisualizado) + " h"));
                y += textLineSpacing;
                canvas.drawText("Total de Tiempo Visualizado: " + getString(R.string.round_one_decimal_place, u_totalTiempoVisualizado) + " h", x, y, text);

                int u_totalEnergiaConsumida = u_tec;
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nTotal de Energia Consumida: " + u_totalEnergiaConsumida + " uAh"));
                y += textLineSpacing;
                canvas.drawText("Total de Energia Consumida: " + u_totalEnergiaConsumida + " uAh", x, y, text);

                float u_thc = 0;
                Map<String, Float> u_appsCO2 = new HashMap<>();
                for (String vsAppName : videoStreaming.keySet()) {
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
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nHuella de Carbono Total Generada: " + getString(R.string.round_four_decimal_places, u_totalHuellaCarbono) + " gCO2e"));
                y += textLineSpacing;
                canvas.drawText("Huella de Carbono Total Generada: " + getString(R.string.round_four_decimal_places, u_totalHuellaCarbono) + " gCO2e", x, y, text);

                List<Long> dx = new ArrayList<>();
                List<Integer> dy = new ArrayList<>();
                Map<Integer, Integer> u_horas = new HashMap<>();

                for (List<Object> row : bateria.get(i)) {
                    dx.add((Long) row.get(4));
                    dy.add((Integer) row.get(0));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date((Long) row.get(4)));
                    if (u_horas.containsKey(calendar.get(Calendar.HOUR_OF_DAY))) {
                        u_horas.put(calendar.get(Calendar.HOUR_OF_DAY), u_horas.get(calendar.get(Calendar.HOUR_OF_DAY)) + 1);
                    } else {
                        u_horas.put(calendar.get(Calendar.HOUR_OF_DAY), 1);
                    }
                }

                int u_horaPico = Collections.max(u_horas.entrySet(), Map.Entry.comparingByValue()).getKey();
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nHora Pico: " + u_horaPico + ":00"));
                y += textLineSpacing;
                canvas.drawText("Hora Pico: " + u_horaPico + ":00", x, y, text);

                Map.Entry<String, Integer> u_appMasVista = Collections.max(u_appsVistas.entrySet(), Map.Entry.comparingByValue());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp de VS Mas Veces Vista: " + u_appMasVista.getKey()));
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nNo. de Veces Vista: " + u_appMasVista.getValue()));
                y += textLineSpacing;
                canvas.drawText("App de VS Mas Veces Vista: " + u_appMasVista.getKey(), x, y, text);
                y += textLineSpacing;
                canvas.drawText("No. de Veces Vista: " + u_appMasVista.getValue(), x, y, text);

                Map.Entry<String, Long> u_appMasUsada = Collections.max(u_appsUso.entrySet(), Map.Entry.comparingByValue());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp Mas Tiempo Usada: " + u_appMasUsada.getKey()));
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo Usada: " + getString(R.string.round_one_decimal_place, u_appMasUsada.getValue()/(1000.0*60*60)) + " h"));
                y += textLineSpacing;
                canvas.drawText("App Mas Tiempo Usada: " + u_appMasUsada.getKey(), x, y, text);
                y += textLineSpacing;
                canvas.drawText("Tiempo Usada: " + getString(R.string.round_one_decimal_place, u_appMasUsada.getValue()/(1000.0*60*60)) + " h", x, y, text);

                Map.Entry<String, Integer> u_appMayorConsumo = Collections.max(u_appsConsumo.entrySet(), Map.Entry.comparingByValue());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp que Consumió la Mayor Cantidad de Energía: " + u_appMayorConsumo.getKey()));
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida : " + u_appMayorConsumo.getValue() + " uAh"));
                y += textLineSpacing;
                canvas.drawText("App que Consumió la Mayor Cantidad de Energía: " + u_appMayorConsumo.getKey(), x, y, text);
                y += textLineSpacing;
                canvas.drawText("Energía Consumida : " + u_appMayorConsumo.getValue() + " uAh", x, y, text);

                Map.Entry<String, Float> u_appMayorCO2 = Collections.max(u_appsCO2.entrySet(), Map.Entry.comparingByValue());
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nApp que Generó una Mayor Huella de Carbono: " + u_appMayorCO2.getKey()));
                runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono: " + getString(R.string.round_four_decimal_places, u_appMayorCO2.getValue()) + " gCO2e"));
                y += textLineSpacing;
                canvas.drawText("App que Generó una Mayor Huella de Carbono: " + u_appMayorCO2.getKey(), x, y, text);
                y += textLineSpacing;
                canvas.drawText("Huella de Carbono: " + getString(R.string.round_four_decimal_places, u_appMayorCO2.getValue()) + " gCO2e", x, y, text);
                pdfDocument.finishPage(page);

                try {
                    File u_chart_01 = File.createTempFile("u_chart01_", ".png", getCacheDir());
                    ImageCharts u_chart01 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                    List<String> d = new ArrayList<>();
                    List<String> dl = new ArrayList<>();
                    List<String> co = new ArrayList<>();
                    for (Map.Entry<String, Integer> vistas : u_appsVistas.entrySet()) {
                        if (vistas.getValue() == 0) {
                            continue;
                        }
                        d.add(String.valueOf(vistas.getValue()));
                        dl.add(vistas.getKey());
                        co.add(videoStreaming.get(vistas.getKey()));
                    }
                    u_chart01.chd("a:" + String.join(",", d)).chl(String.join("|", d)).chdl(String.join("|", dl)).chdlp("b").chco(String.join("|", co)).chlps("color,FFFFFF");
                    URL url = new URL(u_chart01.toURL());
                    InputStream is = url.openStream();
                    OutputStream os = new FileOutputStream(u_chart_01);
                    byte[] b = new byte[1024];
                    int nRead;
                    while ((nRead = is.read(b)) != -1) {
                        os.write(b, 0, nRead);
                    }
                    is.close();
                    os.close();
                    runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nNúmero de Visualizaciones por app:"));
                    page = pdfDocument.startPage(pageInfo);
                    canvas = page.getCanvas();
                    y = titleLineSpacing;
                    canvas.drawText("Número de Visualizaciones por app:", x, y, text);
                    imageViews.add(new ImageView(this));
                    imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(u_chart_01));
                    runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                    x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                    y += textLineSpacing;
                    canvas.drawBitmap(BitmapFactory.decodeFile(u_chart_01.getAbsolutePath()), x, y, images);
                } catch (Exception e) {
                    Log.e("Error(u_01): ", e.toString());
                    runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart u_01!!!"));
                }

                textViews.add(new TextView(this));
                textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textViews.get(textViews.size()-1).setTextSize(textSize);
                textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
                textViews.get(textViews.size()-1).setText("");
                runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

                try {
                    File u_chart_02 = File.createTempFile("u_chart02_", ".png", getCacheDir());
                    ImageCharts u_chart02 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                    List<String> d = new ArrayList<>();
                    List<String> l = new ArrayList<>();
                    List<String> dl = new ArrayList<>();
                    List<String> co = new ArrayList<>();
                    for (Map.Entry<String, Long> uso : u_appsUso.entrySet()) {
                        if (uso.getValue() == 0) {
                            continue;
                        }
                        d.add(String.valueOf(uso.getValue()));
                        l.add(getString(R.string.round_one_decimal_place, uso.getValue()/(1000.0*60*60)));
                        dl.add(uso.getKey());
                        co.add(videoStreaming.get(uso.getKey()));
                    }
                    u_chart02.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b").chco(String.join("|", co)).chlps("color,FFFFFF");
                    URL url = new URL(u_chart02.toURL());
                    InputStream is = url.openStream();
                    OutputStream os = new FileOutputStream(u_chart_02);
                    byte[] b = new byte[1024];
                    int nRead;
                    while ((nRead = is.read(b)) != -1) {
                        os.write(b, 0, nRead);
                    }
                    is.close();
                    os.close();
                    runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nTiempo de Uso por app:"));
                    x = leftMargin;
                    y += Integer.parseInt(pieChartSize.split("x")[1]) + textLineSpacing;
                    canvas.drawText("Tiempo de Uso por app:", x, y, text);
                    imageViews.add(new ImageView(this));
                    imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(u_chart_02));
                    runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                    x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                    y += textLineSpacing;
                    canvas.drawBitmap(BitmapFactory.decodeFile(u_chart_02.getAbsolutePath()), x, y, images);
                    pdfDocument.finishPage(page);
                } catch (Exception e) {
                    Log.e("Error(u_02): ", e.toString());
                    runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart u_02!!!"));
                    pdfDocument.finishPage(page);
                }

                textViews.add(new TextView(this));
                textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textViews.get(textViews.size()-1).setTextSize(textSize);
                textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
                textViews.get(textViews.size()-1).setText("");
                runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

                try {
                    File u_chart_03 = File.createTempFile("u_chart03_", ".png", getCacheDir());
                    ImageCharts u_chart03 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                    List<String> d = new ArrayList<>();
                    List<String> l = new ArrayList<>();
                    List<String> dl = new ArrayList<>();
                    List<String> co = new ArrayList<>();
                    for (Map.Entry<String, Integer> consumo : u_appsConsumo.entrySet()) {
                        if (consumo.getValue() == 0) {
                            continue;
                        }
                        d.add(String.valueOf(consumo.getValue()));
                        l.add(largeValueFormatter(consumo.getValue()));
                        dl.add(consumo.getKey());
                        co.add(videoStreaming.get(consumo.getKey()));
                    }
                    u_chart03.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b").chco(String.join("|", co)).chlps("color,FFFFFF");
                    URL url = new URL(u_chart03.toURL());
                    InputStream is = url.openStream();
                    OutputStream os = new FileOutputStream(u_chart_03);
                    byte[] b = new byte[1024];
                    int nRead;
                    while ((nRead = is.read(b)) != -1) {
                        os.write(b, 0, nRead);
                    }
                    is.close();
                    os.close();
                    runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nEnergía Consumida por app:"));
                    page = pdfDocument.startPage(pageInfo);
                    canvas = page.getCanvas();
                    x = leftMargin;
                    y = titleLineSpacing;
                    canvas.drawText("Energía Consumida por app:", x, y, text);
                    imageViews.add(new ImageView(this));
                    imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(u_chart_03));
                    runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                    x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                    y += textLineSpacing;
                    canvas.drawBitmap(BitmapFactory.decodeFile(u_chart_03.getAbsolutePath()), x, y, images);
                } catch (Exception e) {
                    Log.e("Error(u_03): ", e.toString());
                    runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart u_03!!!"));
                }

                textViews.add(new TextView(this));
                textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textViews.get(textViews.size()-1).setTextSize(textSize);
                textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
                textViews.get(textViews.size()-1).setText("");
                runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

                try {
                    File u_chart_04 = File.createTempFile("u_chart04_", ".png", getCacheDir());
                    ImageCharts u_chart04 = new ImageCharts().cht("p").chs(pieChartSize).chof(".png");
                    List<String> d = new ArrayList<>();
                    List<String> l = new ArrayList<>();
                    List<String> dl = new ArrayList<>();
                    List<String> co = new ArrayList<>();
                    for (Map.Entry<String, Float> co2 : u_appsCO2.entrySet()) {
                        if (co2.getValue() == 0) {
                            continue;
                        }
                        d.add(String.valueOf(co2.getValue()));
                        l.add(getString(R.string.round_four_decimal_places, co2.getValue()));
                        dl.add(co2.getKey());
                        co.add(videoStreaming.get(co2.getKey()));
                    }
                    u_chart04.chd("a:" + String.join(",", d)).chl(String.join("|", l)).chdl(String.join("|", dl)).chdlp("b").chco(String.join("|", co)).chlps("color,FFFFFF");
                    URL url = new URL(u_chart04.toURL());
                    InputStream is = url.openStream();
                    OutputStream os = new FileOutputStream(u_chart_04);
                    byte[] b = new byte[1024];
                    int nRead;
                    while ((nRead = is.read(b)) != -1) {
                        os.write(b, 0, nRead);
                    }
                    is.close();
                    os.close();
                    runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nHuella de Carbono por app:"));
                    x = leftMargin;
                    y += Integer.parseInt(pieChartSize.split("x")[1]) + textLineSpacing;
                    canvas.drawText("Huella de Carbono por app:", x, y, text);
                    imageViews.add(new ImageView(this));
                    imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(u_chart_04));
                    runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                    x = width / 2 - Integer.parseInt(pieChartSize.split("x")[0]) / 2;
                    y += textLineSpacing;
                    canvas.drawBitmap(BitmapFactory.decodeFile(u_chart_04.getAbsolutePath()), x, y, images);
                    pdfDocument.finishPage(page);
                } catch (Exception e) {
                    Log.e("Error(u_04): ", e.toString());
                    runOnUiThread(() -> textViews.get(textViews.size()-1).append("\n\nError al Generar el Chart u_04!!!"));
                    pdfDocument.finishPage(page);
                }

                textViews.add(new TextView(this));
                textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textViews.get(textViews.size()-1).setTextSize(textSize);
                textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
                textViews.get(textViews.size()-1).setText("");
                runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));

                try {
                    File u_chart_05 = File.createTempFile("u_chart05_", ".png", getCacheDir());
                    ImageCharts u_chart05 = new ImageCharts().cht("lxy").chs(scatterChartSize).chof(".png");
                    long startx = dx.get(0);
                    long endx = dx.get(dx.size()-1);
                    long xlimit = endx - startx;
                    int starty = Collections.min(dy);
                    int endy = Collections.max(dy);
                    int ylimit = endy - starty;
                    List<String> xl = new ArrayList<>();
                    for (long l = 0; l <= xlimit; l += Math.round(xlimit/5.0)) {
                        xl.add(sdf.format(new Date(startx + l)).substring(0, 6));
                    }
                    List<String> yl = new ArrayList<>();
                    for (int j = 0; j <= ylimit; j += Math.round(ylimit/7F)) {
                        yl.add(String.valueOf(starty + j));
                    }
                    List<Integer> dxn = new ArrayList<>();
                    for (Long l : dx) {
                        int t = 4094 - Math.round((endx - l) * 4094F / xlimit);
                        dxn.add(t);
                    }
                    int startyn = 0;
                    int endyn = 0;
                    List<Integer> dyn = new ArrayList<>();
                    for (Integer j : dy) {
                        int t = 4094 - Math.round((endy - j) * 4094F / ylimit);
                        dyn.add(t);
                        if (j == starty) {
                            startyn = t;
                        }
                        if (j == endy) {
                            endyn = t;
                        }
                    }
                    u_chart05.chd("e:" + extendedEncodingFormat(dxn) + "," + extendedEncodingFormat(dyn)).chxr("0," + startyn + "," + endyn + "|1," + dxn.get(0) + "," + dxn.get(dxn.size()-1));
                    u_chart05.chxt("y,x").chxl("0:|" + String.join("|", yl) + "|1:|" + String.join("|", xl)).chls("2").chm("o,FF0000,0,-1,4.0");
                    URL url = new URL(u_chart05.toURL());
                    InputStream is = url.openStream();
                    OutputStream os = new FileOutputStream(u_chart_05);
                    byte[] b = new byte[1024];
                    int nRead;
                    while ((nRead = is.read(b)) != -1) {
                        os.write(b, 0, nRead);
                    }
                    is.close();
                    os.close();
                    runOnUiThread(() -> textViews.get(textViews.size()-1).append("\nComportamiento de la Batería:"));
                    page = pdfDocument.startPage(pageInfo);
                    canvas = page.getCanvas();
                    x = leftMargin;
                    y = titleLineSpacing;
                    canvas.drawText("Comportamiento de la Batería:", x, y, text);
                    imageViews.add(new ImageView(this));
                    imageViews.get(imageViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    imageViews.get(imageViews.size()-1).setImageURI(Uri.fromFile(u_chart_05));
                    runOnUiThread(() -> linearLayout.addView(imageViews.get(imageViews.size()-1)));
                    x = width / 2 - Integer.valueOf(scatterChartSize.split("x")[0]) / 2;
                    y += textLineSpacing;
                    canvas.drawBitmap(BitmapFactory.decodeFile(u_chart_05.getAbsolutePath()), x, y, images);
                    pdfDocument.finishPage(page);
                } catch (Exception e) {
                    Log.e("Error(u_05): ", e.toString());
                }

                textViews.add(new TextView(this));
                textViews.get(textViews.size()-1).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textViews.get(textViews.size()-1).setTextSize(textSize);
                textViews.get(textViews.size()-1).setGravity(Gravity.CENTER);
                textViews.get(textViews.size()-1).setText("\n\n - - - - - - - - - - - -\n");
                runOnUiThread(() -> linearLayout.addView(textViews.get(textViews.size()-1)));
            }

            page = pdfDocument.startPage(pageInfo);
            canvas = page.getCanvas();
            s = "Conclusiones";
            x = width / 2 - s.length() / 2;
            y += titleLineSpacing;
            canvas.drawText(s, x, y, titles);
            x = leftMargin;
            y += textLineSpacing;
            canvas.drawText("...", x, y, text);
            pdfDocument.finishPage(page);

            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setTextSize(11);
            button.setText("Descargar PDF!");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        File pdf = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), "Reporte.pdf");
                        if (open[0]) {
                            pdfDocument.writeTo(new FileOutputStream(pdf));
                            pdfDocument.close();
                            open[0] = false;
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "PDF Guardado en Descargas!", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", pdf), "application/pdf");
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.e("Error al Generar el PDF!", e.toString());
                    }
                }
            });
            runOnUiThread(() -> linearLayout.addView(button));

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

    public String largeValueFormatter(int value) {
        String[] suffix = {"", "k", "m", "b", "t"};
        DecimalFormat format = new DecimalFormat("###E00");
        String r = format.format(value);
        int combined = Integer.valueOf(r.substring(r.length()-2));
        r = r.replaceAll("E[0-9][0-9]", suffix[combined/3]);
        while (r.length() > 5 || r.matches("[0-9]+\\.[a-z]")) {
            r = r.substring(0, r.length()-2) + r.charAt(r.length()-1);
        }
        return r;
    }

    public String extendedEncodingFormat(List<Integer> points) {
        StringBuilder eef = new StringBuilder();
        for (int point : points) {
            if (point < 1664) {
                eef.append((char)(point / 64 + 65));
                int p = point % 64;
                if (p < 26) {
                    eef.append((char)(p + 65));
                } else if (p < 52) {
                    eef.append((char)(p + 71));
                } else if (p < 62) {
                    eef.append((char)(p - 4));
                } else if (p == 62) {
                    eef.append("-");
                } else {
                    eef.append(".");
                }
            } else if (point < 3328) {
                eef.append((char)(point / 64 + 71));
                int p = point % 64;
                if (p < 26) {
                    eef.append((char)(p + 65));
                } else if (p < 52) {
                    eef.append((char)(p + 71));
                } else if (p < 62) {
                    eef.append((char)(p - 4));
                } else if (p == 62) {
                    eef.append("-");
                } else {
                    eef.append(".");
                }
            } else if (point < 3968) {
                eef.append((char)(point / 64 - 4));
                int p = point % 64;
                if (p < 26) {
                    eef.append((char)(p + 65));
                } else if (p < 52) {
                    eef.append((char)(p + 71));
                } else if (p < 62) {
                    eef.append((char)(p - 4));
                } else if (p == 62) {
                    eef.append("-");
                } else {
                    eef.append(".");
                }
            } else if (point < 4032) {
                eef.append("-");
                int p = point - 3968;
                if (p < 26) {
                    eef.append((char)(p + 65));
                } else if (p < 52) {
                    eef.append((char)(p + 71));
                } else if (p < 62) {
                    eef.append((char)(p - 4));
                } else if (p == 62) {
                    eef.append("-");
                } else {
                    eef.append(".");
                }
            } else {
                eef.append(".");
                int p = point - 4032;
                if (p < 26) {
                    eef.append((char)(p + 65));
                } else if (p < 52) {
                    eef.append((char)(p + 71));
                } else if (p < 62) {
                    eef.append((char)(p - 4));
                } else {
                    eef.append("-");
                }
            }
        }
        return eef.toString();
    }

}