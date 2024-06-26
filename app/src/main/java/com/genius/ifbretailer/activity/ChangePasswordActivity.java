package com.genius.ifbretailer.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.genius.ifbretailer.R;
import com.genius.ifbretailer.utility.AppController;
import com.genius.ifbretailer.utility.PrefManager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText etOldPassword, etNewPassword, etConfirmPassword;
    Button btnUpdate;
    ImageView imgBack,imgHome;
    AlertDialog alerDialog1;
    PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        onClick();
    }

    private void initView() {

        pref=new PrefManager(ChangePasswordActivity.this);
        etOldPassword = (EditText) findViewById(R.id.etOldPassword);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgHome=(ImageView)findViewById(R.id.imgHome);


    }

    private void onClick() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etOldPassword.getText().toString().length() > 0) {
                    if (!etOldPassword.getText().toString().equals(etNewPassword.getText().toString())) {
                        if (isValidPassword(etNewPassword.getText().toString())) {
                            if (etNewPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                               postFunction();

                            } else {
                                etConfirmPassword.requestFocus();
                                etConfirmPassword.setError("New password and Confirm password did not match");
                            }

                        } else {
                            etNewPassword.requestFocus();
                            etNewPassword.setError("Password should be contain onecharacyer and one numeric number and minimum length will be 6 and maximum length will be 12");
                        }

                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "Old password and New password can not be same", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(ChangePasswordActivity.this,"Please enter Old Password", Toast.LENGTH_LONG).show();
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChangePasswordActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,12}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


    private void postFunction() {

        byte[] data = new byte[0];
        try {
            data = etOldPassword.getText().toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\s+", "");;


        byte[] Confitmdata = new byte[0];
        try {
            Confitmdata = etNewPassword.getText().toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String Confirmbase64 = Base64.encodeToString(Confitmdata, Base64.DEFAULT).replaceAll("\\s+", "");;


        String surl = AppController.APIURL+"api/EmployeeChangedPassword?Code=" + pref.getUserCode() + "&password=" +base64+"&NewPassword="+Confirmbase64+"&SecurityCode=" + pref.getSecurityCode();
        Log.d("inputLogin", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Authenticating...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseLogin", response);
                        progressBar.dismiss();
                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                // Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();

                             successAlert();

                            } else {
                               Toast.makeText(ChangePasswordActivity.this,responseText,Toast.LENGTH_LONG).show();

                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Toast.makeText(LoginActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();
                //  Toast.makeText(LoginActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();

                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(ChangePasswordActivity.this);
        requestQueue.add(stringRequest);

    }


    private void successAlert() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ChangePasswordActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_success, null);
        dialogBuilder.setView(dialogView);
        Button btnOk = (Button) dialogView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerDialog1.dismiss();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        TextView tvSuccess = (TextView) dialogView.findViewById(R.id.tvSuccess);
        tvSuccess.setText("Your password has been changed successfully");


        alerDialog1 = dialogBuilder.create();
        alerDialog1.setCancelable(false);
        Window window = alerDialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alerDialog1.show();
    }
}
