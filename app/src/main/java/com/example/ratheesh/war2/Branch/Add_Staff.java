package com.example.ratheesh.war2.Branch;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ratheesh.war2.R;
import com.example.ratheesh.war2.WAR.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Add_Staff extends AppCompatActivity {
    EditText Staff_name, staffno, staff_email, staff_usernam, staff_pwd, staff_cnfpwd;
    Button add_staff;
    JSONArray jsonArray1;
    JSONObject jsonObject1;
    String log_type = "Staff";
    String Cmp_id;
    String Br_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__staff);
        final String session1= getIntent().getStringExtra("br_id");
        final String session2= getIntent().getStringExtra("cmp_id");

       // final String Cmp_id= getIntent().getStringExtra("cmp_id");
       // Toast.makeText(getApplicationContext(), "Selected : " + Br_id, Toast.LENGTH_SHORT).show();
      //  Toast.makeText(getApplicationContext(), "Selected : " + Cmp_id, Toast.LENGTH_SHORT).show();
        add_staff = (Button) findViewById(R.id.addstaff);
        Staff_name = (EditText) findViewById(R.id.staff_name);
        staffno = (EditText) findViewById(R.id.staff_phn_no);
        staff_email = (EditText) findViewById(R.id.email_staff);
        staff_usernam = (EditText) findViewById(R.id.staff_username);
        staff_pwd = (EditText) findViewById(R.id.staff_passwrd);
        staff_cnfpwd = (EditText) findViewById(R.id.staff_comfipasswrd);

        Staff_name.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(Staff_name, InputMethodManager.SHOW_IMPLICIT);



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






        add_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Vemail = staff_email.getText().toString();
                String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
                Matcher matcherObj = Pattern.compile(validemail).matcher(Vemail);
                try {


                    if (Staff_name.getText().toString().length() == 0) {
                        Staff_name.setError("Enter name");

                    } else if ((staffno.getText().toString().length()) < 10 || (staffno.getText().toString().length() > 15)) {
                        staffno.setError("Enter valid phone number");

                    } else if (staff_usernam.getText().toString().length() == 0) {
                        staff_usernam.setError("Enter Username");

                    } else if (staff_pwd.getText().toString().length() == 0) {
                        staff_pwd.setError("password does not match");

                    } else if (staff_cnfpwd.getText().toString().equals(staff_cnfpwd.getText().toString())) {

                        if (matcherObj.matches()) {
                            int stoper = 0;
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                jsonObject1 = jsonArray1.getJSONObject(i);
                                if (jsonObject1.getString("log_Username").equals(staff_usernam.getText().toString())) {
                                    stoper = 1;
                                    break;

                                }
                            }
                            if (stoper == 1) {
                                staff_usernam.setError("Username already exists");
                            } else {

                                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                StrictMode.setThreadPolicy(policy);

                                OkHttpClient client = new OkHttpClient();

                                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/staff_add.php").newBuilder();
                                urlBuilder.addQueryParameter("Sf_Name", Staff_name.getText().toString());
                                urlBuilder.addQueryParameter("Sf_phone", staffno.getText().toString());
                                urlBuilder.addQueryParameter("Sf_email", staff_email.getText().toString());
                                urlBuilder.addQueryParameter("Sf_uname", staff_usernam.getText().toString());
                                urlBuilder.addQueryParameter("Sf_pass", staff_pwd.getText().toString());
                                urlBuilder.addQueryParameter("Cmp_ID",session2);
                                urlBuilder.addQueryParameter("Br_ID", session1);


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
                                                //  Toast.makeText(getApplicationContext(), "Selected : " + response.body().toString(), Toast.LENGTH_SHORT).show();
                                                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                                StrictMode.setThreadPolicy(policy);

                                                OkHttpClient client = new OkHttpClient();

                                                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/login.php").newBuilder();

                                                urlBuilder.addQueryParameter("log_Username", staff_usernam.getText().toString());
                                                urlBuilder.addQueryParameter("log_Pass", staff_pwd.getText().toString());
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
                                                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                                                intent.putExtra("uname", staff_usernam.getText().toString());
                                                                startActivity(intent);


                                                            }
                                                        });
                                                    }

                                                    ;
                                                });


                                            }
                                        });
                                    }

                                    ;
                                });

                            }
                        } else {
                            staff_email.setError("invalid email");
                        }


                    } else {

                        staff_cnfpwd.setError("password does not match");


                    }
                  /*  else{
                        email.setError("Enter Username");
                        return;

                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        }
}

