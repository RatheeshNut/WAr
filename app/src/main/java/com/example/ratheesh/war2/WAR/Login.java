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



    JSONArray jsonArray;
    JSONObject jsonObject;

    EditText usrname,pass;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String sessionUname= getIntent().getStringExtra("uname");

        register = (Button) findViewById(R.id.register);
        usrname = (EditText) findViewById(R.id.usrname);
        pass = (EditText) findViewById(R.id.pass);
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

                                     jsonArray = new JSONArray(data);


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
                    int inde =0;
                for(int i=0; i<jsonArray.length();i++){

                        jsonObject = jsonArray.getJSONObject(i);
                        if ((jsonObject.getString("log_Username").equals(usrname.getText().toString())) && (jsonObject.getString("log_Pass").equals(pass.getText().toString()))){

                               break;
                            }

                        else{
                            pass.setError("Invalid username or password");

                        }

                    }
                    if (jsonObject.getString("Log_Type").equals("Company")){
                      //  Toast.makeText(getApplicationContext(), "Logging " , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Company_profile.class);
                        intent.putExtra("uname", usrname.getText().toString());
                        startActivity(intent);

                    }
                    else if(jsonObject.getString("Log_Type").equals("Branch")){
                        Intent intent = new Intent(getApplicationContext(), Branch_profil.class);
                        intent.putExtra("uname", usrname.getText().toString());
                        startActivity(intent);

                    }
                    else if(jsonObject.getString("Log_Type").equals("Staff")){
                        Intent intent = new Intent(getApplicationContext(), Staff_profile.class);
                        intent.putExtra("uname", usrname.getText().toString());
                        startActivity(intent);

                    }

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
