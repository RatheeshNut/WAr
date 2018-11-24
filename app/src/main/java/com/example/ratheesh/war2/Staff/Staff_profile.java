package com.example.ratheesh.war2.Staff;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ratheesh.war2.Branch.Add_Staff;
import com.example.ratheesh.war2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Staff_profile extends AppCompatActivity {
    Button notification,profile,sale;
    String Cmp_id;
    String Br_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_profile);

        final String sf_name= getIntent().getStringExtra("uname");

        notification = (Button) findViewById(R.id.snotification1);
        profile = (Button) findViewById(R.id.sprofile);
        sale = (Button) findViewById(R.id.ssales);


        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/Sf_ID.php").newBuilder();
            urlBuilder.addQueryParameter("Sf_uname", sf_name);
            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {

                                try {
                                    String data = response.body().string();

                                    JSONArray jsonArray = new JSONArray(data);
                                    JSONObject jsonObject;


                                    jsonObject = jsonArray.getJSONObject(0);
                                    Br_id = jsonObject.getString("Br_ID");
                                    Cmp_id = jsonObject.getString("Cmp_ID");


                                } catch (JSONException e) {

                                    Toast.makeText(getApplicationContext(), "Network Error " , Toast.LENGTH_SHORT).show();
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

                ;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Notification " , Toast.LENGTH_SHORT).show();
            }
        });
        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sales " , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Sales.class);
                intent.putExtra("cmp_id",Cmp_id);
                intent.putExtra("br_id",Br_id);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "profile " , Toast.LENGTH_SHORT).show();
            }
        });


    }
}
