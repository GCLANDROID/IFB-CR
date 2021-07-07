package com.genius.ifbretailer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.ifbretailer.R;
import com.genius.ifbretailer.utility.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class SaleDashboardActivity extends AppCompatActivity {
    LinearLayout llManage,llReport,llDownload,llCompetitorSales;
    ImageView imgBack,imHome;
    PrefManager prefManager;
    String version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_dashboard);
        initView();
        onClick();
    }

    private void initView(){
        prefManager=new PrefManager(SaleDashboardActivity.this);
        llManage=(LinearLayout)findViewById(R.id.llManage);
        llReport=(LinearLayout)findViewById(R.id.llReport);
        llDownload=(LinearLayout)findViewById(R.id.llDownload);
        llCompetitorSales=(LinearLayout)findViewById(R.id.llCompetitorSales);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imHome=(ImageView)findViewById(R.id.imgHome);

        try {
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;

            Log.d("sddk", version);
            Log.d("sdkl", String.valueOf(version));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void onClick(){
        llManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loginFunction();
            }
        });

        llReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SaleDashboardActivity.this,ConsolidateSalesReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        llDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SaleDashboardActivity.this,SalesReportDownldActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        llCompetitorSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SaleDashboardActivity.this,CompetitorSaleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });




        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SaleDashboardActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void loginFunction() {
        byte[] data = new byte[0];
        try {
            data = prefManager.getPassword().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\s+", "");;

        String surl = "http://111.93.182.173/IFBiOSApi/api/GCLAuthenticateWithEncryption?LoginID=" + prefManager.getMasterId() + "&password=" +base64+"&IMEI=1122&SecurityCode=" +prefManager.getSecurityCode() + "&DeviceID=1233&DeviceType="+version;
        Log.d("inputLogin", surl);
        final ProgressDialog progressDialog=new ProgressDialog(SaleDashboardActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseLogin", response);
                        progressDialog.dismiss();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                               checkBersion();



                            } else {
                                Intent intent=new Intent(SaleDashboardActivity.this,LoginActivity.class);
                                startActivity(intent);
                                fileList();

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
                Intent intent=new Intent(SaleDashboardActivity.this,LoginActivity.class);
                startActivity(intent);
                fileList();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SaleDashboardActivity.this);
        requestQueue.add(stringRequest);

    }


    private void checkBersion() {
        String surl = "http://111.93.182.173/IFBiOSApi/api/ApkVersionChecking";
        final ProgressDialog progressDialog=new ProgressDialog(SaleDashboardActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseLeave", response);
                        progressDialog.dismiss();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            JSONArray responseData = job1.optJSONArray("responseData");
                            for (int i = 0; i < responseData.length(); i++) {
                                JSONObject obj = responseData.getJSONObject(i);
                               String RTLVersion = obj.optString("RTLVersion");
                                String RTLMandatory = obj.optString("RTLMandatory");
                                if (RTLVersion.equals(version)){
                                    Intent intent=new Intent(SaleDashboardActivity.this,SalesManageActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);

                                }else {
                                    Toast.makeText(SaleDashboardActivity.this,"Please update your app",Toast.LENGTH_LONG).show();
                                }

                            }









                            // boolean _status = job1.getBoolean("status")

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SaleDashboardActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Intent intent=new Intent(SaleDashboardActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

                //Toast.makeText(LoginActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();

                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SaleDashboardActivity.this);
        requestQueue.add(stringRequest);

    }


}
