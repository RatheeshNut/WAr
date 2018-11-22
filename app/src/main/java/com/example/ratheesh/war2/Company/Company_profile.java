package com.example.ratheesh.war2.Company;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ratheesh.war2.R;
import com.example.ratheesh.war2.WAR.Login;

public class Company_profile extends AppCompatActivity {
    private Button branch,notification,product,category,sales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);

        final String sessionUname= getIntent().getStringExtra("uname");

        branch = (Button) findViewById(R.id.branch);

        notification = (Button) findViewById(R.id.notification);
        product = (Button) findViewById(R.id.product);
        category = (Button) findViewById(R.id.category);
        sales = (Button) findViewById(R.id.sales);

        branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Add_Branch.class);
                intent.putExtra("uname", sessionUname);
                startActivity(intent);

            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Notification " , Toast.LENGTH_SHORT).show();
            }
        });
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "product " , Toast.LENGTH_SHORT).show();
            }
        });
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Category " , Toast.LENGTH_SHORT).show();
            }
        });
        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "sales " , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
