package com.example.ratheesh.war2.Branch;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ratheesh.war2.Company.Company_profile;
import com.example.ratheesh.war2.R;
import com.example.ratheesh.war2.Staff.Sales;

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

public class Branch_profil extends AppCompatActivity {

    Button staff,notification,profile,sale;
    String Cmp_id;
    String Br_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_profil);

        final String branchname= getIntent().getStringExtra("uname");


        staff = (Button) findViewById(R.id.staff);
        notification = (Button) findViewById(R.id.notification1);
        profile = (Button) findViewById(R.id.profile);
        sale = (Button) findViewById(R.id.sales);


        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/Br_ID.php").newBuilder();
            urlBuilder.addQueryParameter("Br_Uname", branchname);
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




        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "staff " , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Add_Staff.class);
                intent.putExtra("br_id", Br_id);
                intent.putExtra("cmp_id", Cmp_id);
                startActivity(intent);
            }
        });
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
                intent.putExtra("br_id", Br_id);
                intent.putExtra("cmp_id", Cmp_id);
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
