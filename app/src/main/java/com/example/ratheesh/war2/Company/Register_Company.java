package com.example.ratheesh.war2.Company;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.ratheesh.war2.WAR.Login;
import com.example.ratheesh.war2.WAR.MyAdapter;
import com.example.ratheesh.war2.R;

public class Register_Company extends AppCompatActivity {


    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    EditText cmpName, phon1, phon2, email, addres, usernam, pwd, cnfpwd;
    Button regstr;
    Spinner categoy;
    RecyclerView catlist;
    ArrayList<String> catNam = new ArrayList<>();
    ArrayList<String> catId = new ArrayList<>();
    ArrayList<String> cardviewList = new ArrayList<>();
    ArrayList<String> cardcatID = new ArrayList<>();
    String log_type = "Company";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__company);

        regstr = (Button) findViewById(R.id.register);
        cmpName = (EditText) findViewById(R.id.Cmp_Name);
        phon1 = (EditText) findViewById(R.id.phone1);
        phon2 = (EditText) findViewById(R.id.phone2);
        email = (EditText) findViewById(R.id.email);
        addres = (EditText) findViewById(R.id.address);
        usernam = (EditText) findViewById(R.id.username);
        pwd = (EditText) findViewById(R.id.password);
        cnfpwd = (EditText) findViewById(R.id.confirmpwd);
        categoy = (Spinner) findViewById(R.id.spinner);
        catlist = (RecyclerView) findViewById(R.id.cat_list);


        cmpName.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(cmpName, InputMethodManager.SHOW_IMPLICIT);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        catlist.setHasFixedSize(true);
        cmpName.setSelection(0);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        catlist.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/category_read.php").newBuilder();

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
                                //txtInfo.setText(response.body().string());
                                catNam.clear();
                                catId.clear();
                                try {
                                    String data = response.body().string();

                                    JSONArray jsonArray = new JSONArray(data);
                                    JSONObject jsonObject;
                                    for(int i=0; i<jsonArray.length();i++){

                                        jsonObject = jsonArray.getJSONObject(i);
                                        catNam.add(jsonObject.getString("cat_Name"));
                                        catId.add(jsonObject.getString("cat_ID"));

                                    }

                                    ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, catNam);
                                    categoy.setAdapter(adapter);

                                } catch (JSONException e) {

                                    AlertDialog alertDialog = new AlertDialog.Builder(Register_Company.this).create();
                                    alertDialog.setTitle("Alert Category");
                                    alertDialog.setMessage(e.getMessage());
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {

                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
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
        catNam.clear();
        catId.clear();
        categoy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // Notify the selected item text

                cardviewList.add(selectedItemText);
                cardcatID.add(position,selectedItemText);
                mAdapter = new MyAdapter(cardviewList);
                catlist.setAdapter(mAdapter);

                Toast.makeText(getApplicationContext(), "Selected : " + cardcatID, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        regstr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String Vemail = email.getText().toString();
                String validemail= "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
                Matcher matcherObj = Pattern.compile(validemail).matcher(Vemail);
                try {

                    String str = "";
                    for (int i = 0; i < cardcatID.size(); i++) {
                        str += cardcatID.get(i) + "\n";
                    }

                    if (cmpName.getText().toString().length() == 0) {
                        cmpName.setError("Enter name");

                    }
                    else if((phon1.getText().toString().length()) < 10 || (phon1.getText().toString().length() > 15)){
                        phon1.setError("Enter valid phone number");

                    }

                    else if(addres.getText().toString().length() == 0){
                        addres.setError("Enter address");

                    }
                    else if(usernam.getText().toString().length() == 0){
                        usernam.setError("Enter Username");

                    }
                    else if(pwd.getText().toString().length()==0){
                        cnfpwd.setError("password does not match");

                    }
                    else if(pwd.getText().toString().equals(cnfpwd.getText().toString())){

                        if(matcherObj.matches()){

                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            OkHttpClient client = new OkHttpClient();

                            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/company_register.php").newBuilder();
                            urlBuilder.addQueryParameter("Cmp_Name", cmpName.getText().toString());
                            urlBuilder.addQueryParameter("Cmp_Phn1", phon1.getText().toString());
                            urlBuilder.addQueryParameter("Cmp_Phn2", phon2.getText().toString());
                            urlBuilder.addQueryParameter("Cmp_Email", email.getText().toString());
                            urlBuilder.addQueryParameter("Cmp_Addrs", addres.getText().toString());
                            urlBuilder.addQueryParameter("Cmp_Cat", str.toString());
                            urlBuilder.addQueryParameter("Cmp_Uname", usernam.getText().toString());
                            urlBuilder.addQueryParameter("Cmp_Pwd", pwd.getText().toString());

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

                                                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                                StrictMode.setThreadPolicy(policy);

                                                OkHttpClient client = new OkHttpClient();

                                                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/login.php").newBuilder();

                                                urlBuilder.addQueryParameter("log_Username", usernam.getText().toString());
                                                urlBuilder.addQueryParameter("log_Pass", pwd.getText().toString());
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

                                                                    Intent intent = new Intent(getBaseContext(), Login.class);
                                                                    intent.putExtra("Company", log_type);
                                                                    intent.putExtra("uname", usernam.getText().toString());
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
                        else {
                            email.setError("invalid email");
                        }



                    } else {

                        cnfpwd.setError("password does not match");


                    }
                  /*  else{
                        email.setError("Enter Username");
                        return;

                    }*/
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

}



