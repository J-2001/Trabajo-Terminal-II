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
import java.util.List;

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
                    bateria.get(i).get(j).add(Integer.valueOf(row[3]));
                    bateria.get(i).get(j).add(Integer.valueOf(row[4]));
                    bateria.get(i).get(j).add(Integer.valueOf(row[5]));
                }
            }

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