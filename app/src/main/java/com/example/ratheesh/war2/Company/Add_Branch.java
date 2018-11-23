package com.example.ratheesh.war2.Company;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.os.StrictMode;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.ratheesh.war2.Branch.Branch_profil;
import com.example.ratheesh.war2.R;
import com.example.ratheesh.war2.R;
import com.example.ratheesh.war2.WAR.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Add_Branch extends AppCompatActivity {

    private EditText brnchname, brnchaddrs, phoneno1, phno2, branchuser, branchpass,email,cnfpwd;
    private Button addbutt;
    String Cmp_id;
    String log_type = "Branch";
    JSONArray jsonArray1;
    JSONObject jsonObject1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__branch);

        String sessionUname= getIntent().getStringExtra("uname");

        brnchname = (EditText) findViewById(R.id.Branch_name);
        brnchaddrs = (EditText) findViewById(R.id.B_address);
        phoneno1 = (EditText) findViewById(R.id.Phone_number);
        email = (EditText) findViewById(R.id.br_email);
        phno2 = (EditText) findViewById(R.id.Phone_no2);
        branchuser = (EditText) findViewById(R.id.brnch_uname);
        branchpass = (EditText) findViewById(R.id.brnch_pass);
        cnfpwd = (EditText) findViewById(R.id.CnfPWD);
        addbutt = (Button) findViewById(R.id.butt_add);






        // get company Cmp_ID   //
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/Cmp_ID.php").newBuilder();
            urlBuilder.addQueryParameter("Cmp_Uname", sessionUname);
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
                                    JSONObject jsonObject3;


                                        jsonObject3 = jsonArray.getJSONObject(0);
                                        Cmp_id = jsonObject3.getString("Cmp_ID");


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

        Toast.makeText(getApplicationContext(), " "+ Cmp_id , Toast.LENGTH_SHORT).show();





        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/username.php").newBuilder();
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

                                    jsonArray1 = new JSONArray(data);


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
        //add button

        addbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Vemail = email.getText().toString();
                String validemail= "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
                Matcher matcherObj = Pattern.compile(validemail).matcher(Vemail);
                try {


                    if (brnchname.getText().toString().length() == 0) {
                        brnchname.setError("Enter name");

                    }
                    else if((phoneno1.getText().toString().length()) < 10 || (phoneno1.getText().toString().length() > 15)){
                        phoneno1.setError("Enter valid phone number");

                    }

                    else if(brnchaddrs.getText().toString().length() == 0){
                        brnchaddrs.setError("Enter address");

                    }
                    else if(branchuser.getText().toString().length() == 0){
                        branchuser.setError("Enter Username");

                    }
                    else if(branchpass.getText().toString().length()==0){
                        branchpass.setError("password does not match");

                    }
                    else if(branchpass.getText().toString().equals(cnfpwd.getText().toString())){
                        if(matcherObj.matches()){
                            int stoper = 0;
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                jsonObject1 = jsonArray1.getJSONObject(i);
                                if (jsonObject1.getString("log_Username").equals(brnchname.getText().toString())) {
                                    stoper = 1;
                                    break;

                                }
                            }
                            if (stoper == 1) {
                                brnchname.setError("Username already exists");
                            } else {

                                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                StrictMode.setThreadPolicy(policy);

                                OkHttpClient client = new OkHttpClient();

                                // add Cmp_ID foreign key

                                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/branch_add.php").newBuilder();
                                urlBuilder.addQueryParameter("Br_Name", brnchname.getText().toString());
                                urlBuilder.addQueryParameter("Br_Phn1", phoneno1.getText().toString());
                                urlBuilder.addQueryParameter("Br_Phn2", phno2.getText().toString());
                                urlBuilder.addQueryParameter("Br_Email", email.getText().toString());
                                urlBuilder.addQueryParameter("Br_Adrs", brnchaddrs.getText().toString());
                                urlBuilder.addQueryParameter("Br_Uname", branchuser.getText().toString());
                                urlBuilder.addQueryParameter("Br_Pwd", branchpass.getText().toString());
                                urlBuilder.addQueryParameter("Cmp_ID", Cmp_id);


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
////////////////////////////////////////////////////////

                                                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                                StrictMode.setThreadPolicy(policy);

                                                OkHttpClient client = new OkHttpClient();

                                                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/login.php").newBuilder();

                                                urlBuilder.addQueryParameter("log_Username", branchuser.getText().toString());
                                                urlBuilder.addQueryParameter("log_Pass", branchpass.getText().toString());
                                                urlBuilder.addQueryParameter("Log_Type", log_type);

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
                                                                Toast.makeText(getApplicationContext(), "Success ", Toast.LENGTH_SHORT).show();
                                                                Intent intent = new Intent(getApplicationContext(), Branch_profil.class);
                                                                intent.putExtra("uname", branchuser.getText().toString());
                                                                startActivity(intent);


                                                            }
                                                        });
                                                    }

                                                    ;
                                                });
                                                /////////////////////////////////////////


                                            }
                                        });
                                    }

                                    ;
                                });
                            }
                        }
                        else {
                            email.setError("invalid email");
                        }
                    } else {

                        cnfpwd.setError("password does not match");


                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });

    }
}
