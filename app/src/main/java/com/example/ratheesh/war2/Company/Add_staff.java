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

public class Add_staff extends AppCompatActivity {

    private EditText staffname, stafphoneno1,  staffemail, staffuser,stafpass,cnfpwd;
    private Button addStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        staffname = (EditText) findViewById(R.id.stf_nme);
        stafphoneno1 = (EditText) findViewById(R.id.phn_no);
        staffemail = (EditText) findViewById(R.id.email);
        staffuser = (EditText) findViewById(R.id.username);
        stafpass = (EditText) findViewById(R.id.passwrd);
        cnfpwd = (EditText) findViewById(R.id.cnfpasswrd);
        addStaff = (Button) findViewById(R.id.addstaff);

        // get Br_ID,  Cmp_ID   //
        //                      //
        //                      //
        //                      //
        //                      //
        //                      //
        //                      //
        //stored into a variable//

        addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Vemail = staffemail.getText().toString();
                String validemail= "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
                Matcher matcherObj = Pattern.compile(validemail).matcher(Vemail);
                try {


                    if (staffname.getText().toString().length() == 0) {
                        staffname.setError("Enter name");

                    }
                    else if((stafphoneno1.getText().toString().length()) < 10 || (stafphoneno1.getText().toString().length() > 15)){
                        stafphoneno1.setError("Enter valid phone number");

                    }

                    else if(staffuser.getText().toString().length() == 0){
                        staffuser.setError("Enter Username");

                    }
                    else if(stafpass.getText().toString().length()==0){
                        stafpass.setError("password does not match");

                    }
                    else if(stafpass.getText().toString().equals(cnfpwd.getText().toString())){
                        if(matcherObj.matches()){

                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            OkHttpClient client = new OkHttpClient();

                            // add Br_ID, Cmp_ID foreign key

                            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/16mca021/WAR/staff_add.php").newBuilder();
                            urlBuilder.addQueryParameter("Sf_Name", staffname.getText().toString());
                            urlBuilder.addQueryParameter("Sf_phone", stafphoneno1.getText().toString());
                            urlBuilder.addQueryParameter("Sf_email", staffemail.getText().toString());
                            urlBuilder.addQueryParameter("Sf_uname", staffuser.getText().toString());
                            urlBuilder.addQueryParameter("Sf_pass", stafpass.getText().toString());

                            // urlBuilder.addQueryParameter("Cmp_ID", srting);
                            // urlBuilder.addQueryParameter("Br_ID", srting);

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

                                                AlertDialog alertDialog = new AlertDialog.Builder(Add_staff.this).create();
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
                            staffemail.setError("invalid email");
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
