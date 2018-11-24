package com.example.ratheesh.war2.Staff;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ratheesh.war2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Sales extends AppCompatActivity {
    Spinner prodnme,notification;
    EditText name,price,catog,custnme,addrs,phno1,saledate,warrant,extwarr;
    Button saveb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        prodnme=(Spinner)findViewById(R.id.prdt_name);
        name=(EditText) findViewById(R.id.Sale_nme);
        price=(EditText) findViewById(R.id.Sale_price);
        catog=(EditText) findViewById(R.id.Sale_cat);
        custnme=(EditText) findViewById(R.id.sale_cust_name);
        addrs=(EditText) findViewById(R.id.sale_cust_addrs);
        phno1=(EditText) findViewById(R.id.Sale_cust_ph);
        saveb=(Button) findViewById(R.id.Add_sale);
        final Calendar myCalendar = Calendar.getInstance();
        saledate=findViewById(R.id.sale_date);
        warrant=findViewById(R.id.warr_date);
        extwarr=findViewById(R.id.extwrnt_date);

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

        

    }
}
