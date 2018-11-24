package com.example.ratheesh.war2.WAR;




import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;

import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.os.StrictMode;

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


import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.ratheesh.war2.Branch.Branch_profil;
import com.example.ratheesh.war2.Company.Company_profile;
import com.example.ratheesh.war2.Company.Register_Company;
import com.example.ratheesh.war2.R;
import com.example.ratheesh.war2.Staff.Staff_profile;

public class Login extends AppCompatActivity {



    JSONArray jsonArray12;
    JSONObject jsonObject12;

    EditText usrname,pass;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String sessionUname= getIntent().getStringExtra("uname");

        register = (Button) findViewById(R.id.register);
        usrname = (EditText) findViewById(R.id.usrname1);
        pass = (EditText) findViewById(R.id.pas1);
        login = (Button) findViewById(R.id.login);



            usrname.setText(sessionUname);

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/signin.php").newBuilder();
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

                                     jsonArray12 = new JSONArray(data);

                                    Toast.makeText(getApplicationContext(), "Logging "+jsonArray12.length() , Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {

                                    Toast.makeText(getApplicationContext(), "network Error ", Toast.LENGTH_SHORT).show();
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






        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int i;
                for(i=0; i<jsonArray12.length();i++) {

                    jsonObject12 = jsonArray12.getJSONObject(i);
                    if ((jsonObject12.getString("log_Username").equals(usrname.getText().toString())) && (jsonObject12.getString("log_Pass").equals(pass.getText().toString())) && (jsonObject12.getString("Log_Type").equals("Company"))) {
                        Toast.makeText(getApplicationContext(), "Logging " + jsonObject12.getString("log_Username"), Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(getApplicationContext(), Company_profile.class);
                        intent1.putExtra("uname", usrname.getText().toString());
                        startActivity(intent1);
                    } else if ((jsonObject12.getString("log_Username").equals(usrname.getText().toString())) && (jsonObject12.getString("log_Pass").equals(pass.getText().toString())) && (jsonObject12.getString("Log_Type").equals("Branch"))) {
                        Toast.makeText(getApplicationContext(), "Logging " + jsonObject12.getString("log_Username"), Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(getApplicationContext(), Branch_profil.class);
                        intent2.putExtra("bruname",  usrname.getText().toString());
                        startActivity(intent2);
                    } else if ((jsonObject12.getString("log_Username").equals(usrname.getText().toString())) && (jsonObject12.getString("log_Pass").equals(pass.getText().toString())) && (jsonObject12.getString("Log_Type").equals("Staff"))) {
                        Toast.makeText(getApplicationContext(), "Logging " + jsonObject12.getString("log_Username"), Toast.LENGTH_LONG).show();
                        Intent intent3 = new Intent(getApplicationContext(), Staff_profile.class);
                        intent3.putExtra("suname",  usrname.getText().toString());
                        startActivity(intent3);

                    }


                }

                        pass.setError("Invalid username or password");


                    }catch (JSONException e) {
                    e.printStackTrace();
                    }

            }
        });





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Register_Company.class);
                startActivity(intent);
            }
        });

    }
}
