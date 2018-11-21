package com.example.ratheesh.war2.Company;


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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.ratheesh.war2.R;
import com.example.ratheesh.war2.R;

public class Add_Branch extends AppCompatActivity {

    private EditText brnchname, brnchaddrs, phoneno1, phno2, branchuser, branchpass,email,cnfpwd;
    private Button addbutt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__branch);

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
        //                      //
        //                      //
        //                      //
        //                      //
        //                      //
        //                      //
        //stored into a variable//



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
                    else if(!branchpass.getText().toString().equals(cnfpwd.getText().toString())){
                        if(matcherObj.matches()){

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
                            // urlBuilder.addQueryParameter("Cmp_ID", srting);


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

                                                AlertDialog alertDialog = new AlertDialog.Builder(Add_Branch.this).create();
                                                alertDialog.setTitle("Alert Insertion");
                                                alertDialog.setMessage(response.body().string());
                                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                alertDialog.show();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

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
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });

    }
}
