package com.example.ratheesh.war2.Staff;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratheesh.war2.Company.Register_Company;
import com.example.ratheesh.war2.R;
import com.example.ratheesh.war2.WAR.Login;
import com.example.ratheesh.war2.WAR.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Sales extends AppCompatActivity {
    Spinner prodnme,notification;
    TextView name,price,catog;
    EditText custnme,addrs,phno1,saledate,warrant,extwarr;
    Button saveb;
    ArrayList<String> prdt = new ArrayList<>();
    ArrayList<String> remain = new ArrayList<>();
    JSONArray jsonArray6,jsonArray7;
    JSONObject jsonObject,jsonObject7;
    GregorianCalendar cal = new GregorianCalendar();
    Date remainder;
    String sf_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        prodnme=(Spinner)findViewById(R.id.prdt_name);
        name=(TextView) findViewById(R.id.Sale_nme);
        price=(TextView) findViewById(R.id.Sale_price);
        catog=(TextView) findViewById(R.id.Sale_cat);
        custnme=(EditText) findViewById(R.id.sale_cust_name);
        addrs=(EditText) findViewById(R.id.sale_cust_addrs);
        phno1=(EditText) findViewById(R.id.Sale_cust_ph);
        saveb=(Button) findViewById(R.id.Add_sale);
        final Calendar myCalendar = Calendar.getInstance();
        saledate=findViewById(R.id.sale_date);
        warrant=findViewById(R.id.warr_date);
        extwarr=findViewById(R.id.extwrnt_date);
        notification=(Spinner)findViewById(R.id.remains);

        final String Br_id= getIntent().getStringExtra("br_id");
        final String Cmp_id= getIntent().getStringExtra("cmp_id");


        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/Sf_ID.php").newBuilder();
            urlBuilder.addQueryParameter("Cmp_ID", Cmp_id);
            urlBuilder.addQueryParameter("Br_ID", Br_id);
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

                                    jsonArray7 = new JSONArray(data);
                                    jsonObject7 = jsonArray7.getJSONObject(0);
                                    Toast.makeText(getApplicationContext(), " "+ jsonObject7.getString("Sf_ID") , Toast.LENGTH_SHORT).show();
                                    sf_id= jsonObject7.getString("Sf_ID");



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



        remain.add("Before one week");
        remain.add("Before one month");
        remain.add("Before six month");
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, remain);
        notification.setAdapter(adapter);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    sdf = new SimpleDateFormat(myFormat, Locale.US);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    saledate.setText(sdf.format(myCalendar.getTime()));
                }

            }

        };

        //////////////




        saledate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Sales.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        warrant.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Sales.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        extwarr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Sales.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/product_read.php").newBuilder();
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

                            try {
                                //txtInfo.setText(response.body().string());
                                try {
                                    String data = response.body().string();

                                    jsonArray6 = new JSONArray(data);

                                    prdt.clear();
                                    for(int i=0; i<jsonArray6.length();i++){

                                        jsonObject = jsonArray6.getJSONObject(i);
                                        prdt.add(jsonObject.getString("Pr_name"));
                                        //catId.add(jsonObject.getString("cat_ID"));

                                    }

                                    ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, prdt);
                                    prodnme.setAdapter(adapter);
                                    Toast.makeText(getApplicationContext(),""+adapter.getCount(), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {

                                    AlertDialog alertDialog = new AlertDialog.Builder(Sales.this).create();
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


        prodnme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                try {
                    jsonObject = jsonArray6.getJSONObject(position);
                    name.setText(jsonObject.getString("Pr_name"));
                    price.setText(jsonObject.getString("Pr_price"));
                    catog.setText(jsonObject.getString("Pr_catname"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }





                // Toast.makeText(getApplicationContext(), "Selected : " + cardcatID, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        notification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                int d=0;
                if(selectedItemText.equals("Before one week"))
                     d = -7;
                if(selectedItemText.equals("Before one month"))
                    d = -30;
                if(selectedItemText.equals("Before six month"))
                    d = -60;

                cal.setTime((Date) date);
                cal.add(Calendar.DATE, -d);

            remainder =cal.getTime();


                // Toast.makeText(getApplicationContext(), "Selected : " + cardcatID, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

            ////////////////



        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //////////////////////
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                OkHttpClient client = new OkHttpClient();

                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/sales.php").newBuilder();
                urlBuilder.addQueryParameter("Sale_prdname", prodnme.getSelectedItem().toString());
                urlBuilder.addQueryParameter("Sale_prdprice", price.getText().toString());
                urlBuilder.addQueryParameter("Sale_cat", catog.getText().toString());
                urlBuilder.addQueryParameter("Sale_custname", custnme.getText().toString());
                urlBuilder.addQueryParameter("Sale_custadds", addrs.getText().toString());
                urlBuilder.addQueryParameter("Sale_custphn", phno1.getText().toString());
                urlBuilder.addQueryParameter("Sale_date", saledate.getText().toString());
                urlBuilder.addQueryParameter("Sale_warr", warrant.getText().toString());
                urlBuilder.addQueryParameter("Sale_ext", extwarr.getText().toString());
                urlBuilder.addQueryParameter("Sale_remanid", remainder.toString());
                urlBuilder.addQueryParameter("Cmp_ID",Cmp_id);
                urlBuilder.addQueryParameter("Br_ID", Br_id);
                urlBuilder.addQueryParameter("sf_ID", sf_id);


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



                            }
                        });
                    }

                    ;
                });
                //////////////////////

            }
        });

    }
}
