package com.example.ratheesh.war2.Company;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ratheesh.war2.R;
import com.example.ratheesh.war2.WAR.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Add_product extends AppCompatActivity {
    private EditText productname, productprice, productdesc;
    Spinner category;
    private Button addbutt;
    JSONObject jsonObject;
    JSONArray jsonArray4;
    JSONObject jsonObject3;
    ArrayList<String> catNam = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        final String cmpUname = getIntent().getStringExtra("Cmp_uname");

        productname = (EditText) findViewById(R.id.Product_name);
        productprice = (EditText) findViewById(R.id.Product_Price);
        productdesc = (EditText) findViewById(R.id.Product_desc);
        category = (Spinner) findViewById(R.id.spinner);
        addbutt = (Button) findViewById(R.id.Product_add);


        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/Cmp_ID.php").newBuilder();
            urlBuilder.addQueryParameter("Cmp_Uname", cmpUname);
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

                                    jsonArray4 = new JSONArray(data);
                                    jsonObject3 = jsonArray4.getJSONObject(0);
                                    Toast.makeText(getApplicationContext(), " "+ jsonObject3.getString("Cmp_ID") , Toast.LENGTH_SHORT).show();



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




        productname.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(productname, InputMethodManager.SHOW_IMPLICIT);



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
                                try {
                                    String data = response.body().string();

                                    JSONArray jsonArray = new JSONArray(data);

                                    catNam.clear();
                                    for(int i=0; i<jsonArray.length();i++){

                                        jsonObject = jsonArray.getJSONObject(i);
                                        catNam.add(jsonObject.getString("cat_Name"));


                                    }

                                    ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, catNam);
                                    category.setAdapter(adapter);
                                    Toast.makeText(getApplicationContext(),""+adapter.getCount(), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {

                                    AlertDialog alertDialog = new AlertDialog.Builder(Add_product.this).create();
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

        addbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////////////////////////////

                try {



                    if (productname.getText().toString().length() == 0) {
                        productname.setError("Enter Product name");

                    }
                    else if((productprice.getText().toString().length())== 0){
                        productprice.setError("Enter Price");

                    }

                    else if(productdesc.getText().toString().length() == 0){


                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        OkHttpClient client = new OkHttpClient();

                        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/add_product.php").newBuilder();
                        urlBuilder.addQueryParameter("Pr_name", productname.getText().toString());
                        urlBuilder.addQueryParameter("Pr_price", productprice.getText().toString());
                        urlBuilder.addQueryParameter("Pr_desc", productdesc.getText().toString());
                        urlBuilder.addQueryParameter("Pr_catname",category.getSelectedItem().toString());
                        urlBuilder.addQueryParameter("Cmp_ID", jsonObject3.getString("Cmp_ID"));

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
                                         Toast.makeText(getApplicationContext(), "Successfull added : " +productname, Toast.LENGTH_SHORT).show();





                                    }
                                });
                            }

                            ;
                        });

                    }

                    else {
                        productdesc.setError("Enter Description");


                            }





                }catch (Exception e) {
                    e.printStackTrace();
                }
                ////////////////////////////
            }
        });

    }

}
