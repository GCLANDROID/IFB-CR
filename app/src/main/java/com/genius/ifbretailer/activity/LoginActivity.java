package com.genius.ifbretailer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.ifbretailer.R;
import com.genius.ifbretailer.utility.AppController;
import com.genius.ifbretailer.utility.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {
    ImageView imgLogin;
    EditText etUserID,etPassword,etSecurityCode;
    PrefManager prefManager;
    AlertDialog alertDialog;
    TextView tvForgot;
    String version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        onClick();
    }

    private void initView(){
        prefManager=new PrefManager(LoginActivity.this);
        imgLogin=(ImageView)findViewById(R.id.imgLogin);
        etUserID=(EditText)findViewById(R.id.etUserID);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etSecurityCode=(EditText)findViewById(R.id.etSecurityCode);
        if (prefManager.getRemberFlag().equals("2")){
            etUserID.setText(prefManager.getMasterId());
            etPassword.setText(prefManager.getPassword());
            etSecurityCode.setText(prefManager.getSecurityCode());
        }else {
            etUserID.setText("");
            etPassword.setText("");
            etSecurityCode.setText("");
        }
        tvForgot=(TextView)findViewById(R.id.tvForgot);
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
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUserID.getText().toString().length()>0){
                    if (etPassword.getText().toString().length()>0){
                        if (etSecurityCode.getText().toString().length()>0){
                            loginFunction();

                        }else {
                            Toast.makeText(LoginActivity.this,"Please enter securitycode",Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(LoginActivity.this,"Please enter password",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this,"Please enter user id",Toast.LENGTH_LONG).show();
                }
            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }


    public void loginFunction() {
        byte[] data = new byte[0];
        try {
            data = etPassword.getText().toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\s+", "");;

        String surl = AppController.APIURL+"api/RTLAuthenticateWithEncryption?LoginID=" + etUserID.getText().toString() + "&password=" +base64+"&IMEI=1122&SecurityCode=" + etSecurityCode.getText().toString() + "&DeviceID=1233&DeviceType="+version;
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

                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.getJSONObject(i);
                                    String UserName = obj.optString("UserName");
                                    prefManager.saveEmpName(UserName);
                                    String LastLogin = obj.optString("LastLogin");
                                    prefManager.saveLoginTime(LastLogin);
                                    String Counter= obj.optString("Counter");
                                    prefManager.saveCounter(Counter);
                                    String BranchId = obj.optString("BranchId");
                                    prefManager.saveBranchId(BranchId);
                                    String UserTypeId = obj.optString("UserTypeId");
                                    prefManager.saveUserTypeId(UserTypeId);
                                    String ClientID = obj.optString("ClientID");
                                    prefManager.saveClintId(ClientID);
                                    String ConsultantID = obj.optString("ConsultantID");
                                    String UserID = obj.optString("UserID");
                                    prefManager.saveUserId(UserID);
                                    String MasterID = obj.optString("MasterID");

                                    String Target = obj.optString("Target");
                                    prefManager.saveTarget(Target);
                                    String Pending = obj.optString("Pending");
                                    prefManager.savePending(Pending);
                                    String MonthlyTarget = obj.optString("MonthlyTarget");
                                    prefManager.saveMonthlyTarget(MonthlyTarget);
                                    String Sold=obj.optString("Sold");
                                    prefManager.saveSold(Sold);
                                    String Approved=obj.optString("Approved");
                                    prefManager.saveApproved(Approved);
                                    String SecurityCode = obj.optString("SecurityCode");
                                    prefManager.saveSecurityCode(SecurityCode);
                                    String Password = obj.optString("Password");
                                    prefManager.savePassword(Password);
                                    String WebSalesURL = obj.optString("WebSalesURL");
                                    prefManager.saveWebSales(WebSalesURL);
                                    String Code=obj.optString("Code");
                                    prefManager.saveUserCode(Code);
                                    String ZoneID=obj.optString("ZoneID");
                                    prefManager.saveZoneId(ZoneID);
                                    String HRDeskURL=obj.optString("HRDeskURL");
                                    prefManager.saveHRDeskURL(HRDeskURL);
                                    String ManualURL=obj.optString("ManualURL");
                                    prefManager.saveManualURL(ManualURL);
                                    String LeaveURL=obj.optString("LeaveURL");
                                    prefManager.saveLeaveURL(LeaveURL);
                                    String LeaveEncahURL=obj.optString("LeaveEncahURL");
                                    prefManager.saveLeaveEncahURL(LeaveEncahURL);
                                    String DigitalDocFlag=obj.optString("DigitalDocFlag");
                                    prefManager.saveDocFlag(DigitalDocFlag);
                                    String DailyActivityFlag=obj.optString("DailyActivityFlag");
                                    prefManager.saveDailyLogFlag(DailyActivityFlag);
                                    String CustomerVisitFlag=obj.optString("CustomerVisitFlag");
                                    prefManager.saveCVFlag(CustomerVisitFlag);
                                    String SalesInvCopyImgFlag=obj.optString("SalesInvCopyImgFlag");
                                    prefManager.saveInvoiceFlag(SalesInvCopyImgFlag);
                                    prefManager.saveRemberFlag("1");
                                    String SubDearlerType= AppController.getFreshValue(obj.optString("SubDearlerType"),"");
                                    prefManager.saveSubDealerType(SubDearlerType);
                                    String SalesPartyCode=obj.optString("SalesPartyCode");
                                    prefManager.saveSalesPartyCode(SalesPartyCode);



                                }
                                Intent intent = new Intent(LoginActivity.this, SurveyActivity.class);
                                startActivity(intent);
                                finish();
                                prefManager.saveMasterId(etUserID.getText().toString());
                                prefManager.savePassword(etPassword.getText().toString());

                            } else {
                                shoeDialog();

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
                showAlert();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);

    }


    private void shoeDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_error, null);
        dialogBuilder.setView(dialogView);
        Button btnOk = (Button) dialogView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        TextView tvSuccess = (TextView) dialogView.findViewById(R.id.tvSuccess);
        tvSuccess.setText("Sorry!Invalid Credentials");

        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(true);
        Window window = alertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog.show();
    }

    private void showAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Somthing went wrong");
        alertDialogBuilder.setPositiveButton("ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });
        alertDialogBuilder.show();


    }



}
