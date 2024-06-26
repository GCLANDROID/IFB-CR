package com.genius.ifbretailer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.dhims.timerview.TimerTextView;
import com.genius.ifbretailer.R;
import com.genius.ifbretailer.adapter.TrainingAdapter;
import com.genius.ifbretailer.model.TrainingModel;
import com.genius.ifbretailer.utility.AppController;
import com.genius.ifbretailer.utility.PrefManager;
import com.genius.ifbretailer.utility.TimeDifferenceCalculator;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import im.delight.android.webview.AdvancedWebView;

public class DashboardActivity extends AppCompatActivity  {
    private static final String TAG = "DashboardActivity";
    LinearLayout llSales, llDisplay, llTarget, llE;
    TextView tvName, tvCounter, tvTime;
    PrefManager prefManager;
    LinearLayout llLoader, llMain;
    ImageView imgLogout;
    String version;
    AlertDialog al1;
    androidx.appcompat.app.AlertDialog trainingAlertDialog;
    String RTLMandatory;
    LinearLayout llIncentive, llQueries, llCP, llElearning;
    String RTLVersion;
    ImageView imgBatch;
    TextView tvBadge,tvRank,tvPoints;
    RecyclerView rvTraining;
    ArrayList<TrainingModel>itemList=new ArrayList<>();
    TextView tvSeeAll;
    String collaborationCookie,collaborationAccessToken;
    LinearLayout lnPointEarned,lnCurrentRank;
    String badge_url="";
    LinearLayout lnBadges;
    String link;
    String doc_name="",doc_type="",url="", mid ="",entity_id="",timespent="";
    PDFView pdfView;
    LinearLayout llPdfLoading;
    TextView tvPdfPageNo;
    Dialog dialog;
    private final static int INTERVAL = 40000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initview();
        createLink();
        loginFunction();

        onClick();
    }

    private void initview() {
        prefManager = new PrefManager(DashboardActivity.this);
        dialog = new Dialog(DashboardActivity.this, R.style.CustomDialogNew2);
        //LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View dialogView = inflater.inflate(R.layout.training_popup_layout, null);
        dialog.setContentView(R.layout.training_popup_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        rvTraining=(RecyclerView)findViewById(R.id.rvTraining);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(DashboardActivity.this, LinearLayoutManager.VERTICAL, false);
        rvTraining.setLayoutManager(layoutManager);
        tvBadge=(TextView)findViewById(R.id.tvBadge);
        tvSeeAll=(TextView)findViewById(R.id.tvSeeAll);
        tvRank=(TextView)findViewById(R.id.tvRank);
        tvPoints=(TextView)findViewById(R.id.tvPoints);
        imgBatch=(ImageView)findViewById(R.id.imgBatch);
        llSales = (LinearLayout) findViewById(R.id.llSales);
        llCP = (LinearLayout) findViewById(R.id.llCP);
        llDisplay = (LinearLayout) findViewById(R.id.llDisplay);
        llTarget = (LinearLayout) findViewById(R.id.llTarget);
        llE = (LinearLayout) findViewById(R.id.llE);
        llIncentive = (LinearLayout) findViewById(R.id.llIncentive);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvCounter = (TextView) findViewById(R.id.tvCounter);
        tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(prefManager.getEmpName());
        tvCounter.setText(prefManager.getCounter());
        tvTime.setText(prefManager.getLoginTime());
        llMain = (LinearLayout) findViewById(R.id.llMain);
        llLoader = (LinearLayout) findViewById(R.id.llLoader);
        llQueries = (LinearLayout) findViewById(R.id.llQueries);
        llElearning = (LinearLayout) findViewById(R.id.llElearning);

        imgLogout = (ImageView) findViewById(R.id.imgLogout);

        try {
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;

            Log.d("sddk", version);
            Log.d("sdkl", String.valueOf(version));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(DashboardActivity.this, MonthWiseIncentiveFeddbackActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        lnCurrentRank=(LinearLayout) findViewById(R.id.lnCurrentRank);
        lnPointEarned=(LinearLayout) findViewById(R.id.lnPointEarned);
        lnBadges=(LinearLayout) findViewById(R.id.lnBadges);

    }

    public void loginFunction() {
        byte[] data = new byte[0];
        try {
            data = prefManager.getPassword().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\s+", "");
        ;

        String surl = AppController.APIURL+"api/RTLAuthenticateWithEncryption?LoginID=" + prefManager.getMasterId() + "&password=" + base64 + "&IMEI=1122&SecurityCode=" + prefManager.getSecurityCode() + "&DeviceID=1233&DeviceType=" + version;
        Log.d("inputLogin", surl);
        llLoader.setVisibility(View.VISIBLE);
        llMain.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseLogin", response);
                        llLoader.setVisibility(View.VISIBLE);
                        llMain.setVisibility(View.GONE);

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
                                    String Counter = obj.optString("Counter");
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
                                    String Sold = obj.optString("Sold");
                                    prefManager.saveSold(Sold);
                                    String Approved = obj.optString("Approved");
                                    prefManager.saveApproved(Approved);
                                    String SecurityCode = obj.optString("SecurityCode");
                                    prefManager.saveSecurityCode(SecurityCode);
                                    String Password = obj.optString("Password");
                                    prefManager.savePassword(Password);
                                    String WebSalesURL = obj.optString("WebSalesURL");
                                    prefManager.saveWebSales(WebSalesURL);
                                    String Code = obj.optString("Code");
                                    prefManager.saveUserCode(Code);
                                    String ZoneID = obj.optString("ZoneID");
                                    prefManager.saveZoneId(ZoneID);
                                    String HRDeskURL = obj.optString("HRDeskURL");
                                    prefManager.saveHRDeskURL(HRDeskURL);
                                    String ManualURL = obj.optString("ManualURL");
                                    prefManager.saveManualURL(ManualURL);
                                    String LeaveURL = obj.optString("LeaveURL");
                                    prefManager.saveLeaveURL(LeaveURL);
                                    String LeaveEncahURL = obj.optString("LeaveEncahURL");
                                    prefManager.saveLeaveEncahURL(LeaveEncahURL);
                                    String DigitalDocFlag = obj.optString("DigitalDocFlag");
                                    prefManager.saveDocFlag(DigitalDocFlag);
                                    String DailyActivityFlag = obj.optString("DailyActivityFlag");
                                    prefManager.saveDailyLogFlag(DailyActivityFlag);
                                    String CustomerVisitFlag = obj.optString("CustomerVisitFlag");
                                    prefManager.saveCVFlag(CustomerVisitFlag);
                                    String SalesInvCopyImgFlag = obj.optString("SalesInvCopyImgFlag");
                                    prefManager.saveInvoiceFlag(SalesInvCopyImgFlag);
                                    checkBersion();
                                }

                            } else {
                                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
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
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                fileList();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(DashboardActivity.this);
        requestQueue.add(stringRequest);
    }

    private void onClick() {
        lnBadges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!badge_url.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(badge_url));
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
        lnCurrentRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        lnPointEarned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        tvSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, TrainingActivity.class);
                intent.putExtra("cookie",collaborationCookie);
                intent.putExtra("accessToken",collaborationAccessToken);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        llSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RTLVersion.equals(version)) {
                    Intent intent = new Intent(DashboardActivity.this, SaleDashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(DashboardActivity.this, "please update your app", Toast.LENGTH_LONG).show();
                }
            }
        });

        llDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RTLVersion.equals(version)) {
                    Intent intent = new Intent(DashboardActivity.this, DisplaymatrixDashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(DashboardActivity.this, "please update your app", Toast.LENGTH_LONG).show();
                }
            }
        });
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                prefManager.saveRemberFlag("2");
            }
        });

        llTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SalesTargetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        llElearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ELearningActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        llCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ChangePasswordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        llQueries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RTLVersion.equals(version)) {
                    Intent intent = new Intent(DashboardActivity.this, IQueriesDashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(DashboardActivity.this, "please update your app", Toast.LENGTH_LONG).show();
                }
            }
        });

        llIncentive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, IncentiveActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        llE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ECatelogActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void checkBersion() {
        String surl = AppController.APIURL+"api/ApkVersionChecking";
        llLoader.setVisibility(View.VISIBLE);
        llMain.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseLeave", response);
                        llLoader.setVisibility(View.GONE);
                        llMain.setVisibility(View.VISIBLE);

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            JSONArray responseData = job1.optJSONArray("responseData");
                            for (int i = 0; i < responseData.length(); i++) {
                                JSONObject obj = responseData.getJSONObject(i);
                                RTLVersion = obj.optString("RTLVersion");
                                RTLMandatory = obj.optString("RTLMandatory");
                                if (RTLVersion.equals(version)) {
                                    collaborationLogin();
                                } else {
                                    upDateAlert();
                                }
                            }
                            // boolean _status = job1.getBoolean("status")
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DashboardActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                llLoader.setVisibility(View.GONE);
                llMain.setVisibility(View.GONE);
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                //Toast.makeText(LoginActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();

                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(DashboardActivity.this);
        requestQueue.add(stringRequest);

    }

    private void upDateAlert() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DashboardActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_update_alert, null);
        dialogBuilder.setView(dialogView);
        Button btnOk = (Button) dialogView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                }
                al1.dismiss();
            }
        });

        TextView tvSkip = (TextView) dialogView.findViewById(R.id.tvSkip);
        if (RTLMandatory.equals("Y")) {
            tvSkip.setVisibility(View.GONE);
        } else {
            tvSkip.setVisibility(View.GONE);
        }
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al1.dismiss();
            }
        });

        al1 = dialogBuilder.create();
        al1.setCancelable(false);
        Window window = al1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        al1.show();
    }

    private void collaborationLogin() {
        final ProgressDialog progressDialog=new ProgressDialog(DashboardActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        AndroidNetworking.post("https://apps.bsharpcorp.com/infocapture/access_lead_user/login")
                .addBodyParameter("api_key", "XZnvWRDDnvpWGGRmWUzLZhkN2jW5XziJEWavhhCFHbkX9jAYjNFNu9MCWoaNtcJr")
                .addBodyParameter("counter_id", "R"+prefManager.getUserCode())
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        boolean status = response.optBoolean("status");
                        String message=response.optString("message");
                        if (status){
                            String access_token=response.optString("access_token");
                            collaborationAccessToken=access_token;
                            String cookie=response.optString("cookie");
                            collaborationCookie=cookie;
                            getTrainingInform(cookie,access_token);
                        } else {
                            Toast.makeText(DashboardActivity.this,message,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        progressDialog.dismiss();
                        // handle error
                    }
                });
    }

    private void getTrainingInform(final String cookie, final String accesstoken) {
        final ProgressDialog progressDialog=new ProgressDialog(DashboardActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        AndroidNetworking.get("https://apps.bsharpcorp.com/infocapture/get_profile")
                .addHeaders("Cookie", cookie)
                .addHeaders("X-CSRF-Token", accesstoken)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        itemList.clear();
                        Log.d("informationresponse",response.toString());
                        String rank = response.optString("rank");
                        tvRank.setText("# "+rank);
                        int points = response.optInt("points");
                        tvPoints.setText(""+points);
                        String badge_title = response.optString("badge_title");
                        tvBadge.setText(badge_title);
                        String badge_icon = response.optString("badge_icon");
                        badge_url=response.optString("badge_url");
                        JSONArray modules=response.optJSONArray("modules");
                        if (modules.length()>0) {
                            for (int i=0;i<modules.length();i++){
                                JSONObject obj=modules.optJSONObject(i);
                                String module_name=obj.optString("module_name");
                                String module_image=obj.optString("module_image");
                                String created_on=obj.optString("created_on");
                                String url=obj.optString("url");;
                                int ratings=obj.optInt("ratings");

                                TrainingModel model=new TrainingModel();
                                model.setModelImage(module_image);
                                model.setModelName(module_name);
                                model.setRating(ratings);
                                model.setUrl(url);
                                model.setCreatedOn(created_on);
                                itemList.add(model);
                            }

                            TrainingAdapter tAdapter=new TrainingAdapter(itemList,DashboardActivity.this);
                            rvTraining.setAdapter(tAdapter);
                        }

                        getTrainingPopup(cookie,accesstoken);

                        try {
                            Picasso.with(DashboardActivity.this)
                                    .load(badge_icon)
                                    .into(imgBatch);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        progressDialog.dismiss();
                        // handle error
                    }
                });
    }


    private void getTrainingPopup(final String cookie, final String accesstoken) {
        final ProgressDialog progressDialog=new ProgressDialog(DashboardActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        AndroidNetworking.get("https://apps.bsharpcorp.com/infocapture/get_home_doc")
                .addHeaders("Cookie", cookie)
                .addHeaders("X-CSRF-Token", accesstoken)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("Information_Response: ",response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            if (jsonObject.getBoolean("status") == true){
                                Log.d("PDF_URL", jsonObject.getString("uri"));
                                doc_name = jsonObject.getString("doc_name");
                                doc_type =  jsonObject.getString("doc_type");
                                url = jsonObject.getString("uri");
                                mid = jsonObject.getString("mid");
                                entity_id = jsonObject.getString("entity_id");
                                String mid=jsonObject.optString("mid");
                                String doc_viewed=jsonObject.optString("doc_viewed");

                                JSONArray otherdocs=jsonObject.optJSONArray("otherdocs");



                                if (doc_type.equalsIgnoreCase("quiz") && doc_viewed.equals("0")){
                                    Intent intent = new Intent(DashboardActivity.this, QuizWebActivity.class);
                                    intent.putExtra("mdid", mid);
                                    intent.putExtra("imageurl", url);
                                    intent.putExtra("doc_type", doc_type);
                                    intent.putExtra("entity_id", entity_id);
                                    intent.putExtra("accessToken", accesstoken);
                                    intent.putExtra("cookies", cookie);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }else {
                                    if (doc_viewed.equals("0") ){
                                        openTrainingPopup(doc_name,doc_type,url,cookie,accesstoken,otherdocs,entity_id);
                                    }else {
                                        if (otherdocs.length()>0) {
                                            for (int i = otherdocs.length() - 1; i >= 0; i--) {
                                                JSONObject otherDocObj = otherdocs.optJSONObject(i);
                                                String doc_type = otherDocObj.optString("doc_type");
                                                String entity_id = otherDocObj.optString("entity_id");
                                                String docid = otherDocObj.optString("docid");
                                                String doc_name = otherDocObj.optString("doc_name");
                                                String docviewed = otherDocObj.optString("doc_viewed");
                                                String uri = otherDocObj.optString("uri");
                                                if (doc_type.equalsIgnoreCase("quiz") && docviewed.equals("0")) {
                                                    Intent intent = new Intent(DashboardActivity.this, QuizWebActivity.class);
                                                    intent.putExtra("mdid", mid);
                                                    intent.putExtra("imageurl", uri);
                                                    intent.putExtra("doc_type", doc_type);
                                                    intent.putExtra("entity_id", entity_id);
                                                    intent.putExtra("accessToken", accesstoken);
                                                    intent.putExtra("cookies", cookie);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                }else {
                                                    if (docviewed.equals("0")){
                                                        dialog.dismiss();
                                                        openTrainingPopup(doc_name,doc_type,uri,cookie,accesstoken,otherdocs,entity_id);


                                                    }
                                                }
                                            }
                                        }else {



                                        }
                                    }


                                }

                               /* if (doc_type.equalsIgnoreCase("pdf")){

                                } else {
                                    openTrainingPopup(doc_name,doc_type,url);
                                }*/
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        progressDialog.dismiss();
                        // handle error
                    }
                });
    }




    private void openTrainingPopup(String doc_name, final String doc_type, final String url, final String cookie, final String accesstoken, final JSONArray otherdocs, final String entity_id) {



        long futureTimestamp = System.currentTimeMillis() + (40000);
        final TimerTextView timerText = (TimerTextView) dialog.findViewById(R.id.timerText);
        timerText.setEndTime(futureTimestamp);


        TextView textView = dialog.findViewById(R.id.textView);
        textView.setText(doc_name);
        final ImageView imgCancel = dialog.findViewById(R.id.imgCancel);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timerText.setVisibility(View.GONE);
                imgCancel.setVisibility(View.VISIBLE);


            }
        }, INTERVAL);

        llPdfLoading = dialog.findViewById(R.id.llPdfLoading);
        tvPdfPageNo = dialog.findViewById(R.id.tvPdfPageNo);
        pdfView = dialog.findViewById(R.id.pdfView);

        long currentTimestampMillis = System.currentTimeMillis();
        final String  startTime;
        final String[] endTime = new String[1];
        // Create a SimpleDateFormat instance with your desired format
        final SimpleDateFormat[] dateFormat = {new SimpleDateFormat("HH:mm:ss")};
        // Format the current timestamp
        startTime = dateFormat[0].format(new Date(currentTimestampMillis));


        Log.e(TAG, "openTrainingPopup: OPEN URL: "+url);

        AdvancedWebView webview=(AdvancedWebView) dialog.findViewById(R.id.webview);
        if (doc_type.equalsIgnoreCase("pdf")){
            llPdfLoading.setVisibility(View.VISIBLE);
            pdfView.setVisibility(View.VISIBLE);
            new RetrievePdfFromUrl().execute(url);
        } else {
            llPdfLoading.setVisibility(View.GONE);
            pdfView.setVisibility(View.GONE);
            tvPdfPageNo.setVisibility(View.GONE);

        }


        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //dialog.cancel();
                long currentTimestampMillis = System.currentTimeMillis();
                endTime[0] = dateFormat[0].format(new Date(currentTimestampMillis));

                    try {
                        saveTrainingClosePop(startTime,endTime[0],cookie,accesstoken,doc_type,mid,entity_id);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }






            }
        });

        dialog.setCancelable(false);
        dialog.show();
    }

    private void saveTrainingClosePop(String startTime, String endTime, final String cookie, final String accesstoken,String doctype,String mid,String entity_id) throws JSONException {
        try {
            Log.e(TAG, "saveTrainingClosePop: startTime: "+startTime+" endTime: "+endTime);
            Log.e(TAG, "saveTrainingClosePop: Time Difference: "+ TimeDifferenceCalculator.calculateTimeDifference(startTime,endTime));
            timespent = TimeDifferenceCalculator.calculateTimeDifference(startTime,endTime);
            String data = "{"+mid+","+entity_id+","+doctype+","+TimeDifferenceCalculator.calculateTimeDifference(startTime,endTime)+"}";

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mid",mid);
            jsonObject.put("entity_id",entity_id);
            jsonObject.put("doc_type",doctype);
            jsonObject.put("timespent",timespent);
            Log.e(TAG, "saveTrainingClosePop: ==== "+jsonObject.toString());


            final ProgressDialog progressDialog=new ProgressDialog(DashboardActivity.this);
            progressDialog.setMessage("Loading..");
            progressDialog.setCancelable(false);
            progressDialog.show();

            AndroidNetworking.post("https://apps.bsharpcorp.com/infocapture/home_data/upload")
                    .addHeaders("Cookie", cookie)
                    .addHeaders("X-CSRF-Token", accesstoken)
                    .setTag("test")
                    .addJSONObjectBody(jsonObject)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.dismiss();
                            Log.e("Response: ",response.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                dialog.cancel();
                                if (jsonObject.optInt("status") == 200){

                                    dialog.dismiss();
                                    collaborationLogin();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError error) {
                            progressDialog.dismiss();
                            // handle error
                        }
                    });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void createLink(){
        JSONObject obj=new JSONObject();
        try {
            obj.put("access_key","a1dcac1cc6b9ba47asfafaf");
            obj.put("client_id","100001100002357");
            obj.put("counter_id","R"+prefManager.getUserCode());
            obj.put("destination","");
            obj.put("expires",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonobj=obj.toString();
        byte[] data = new byte[0];
        try {
            data = jsonobj.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64data = Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\s+", "");;
        Log.d("jsonobj",base64data);

        String token=base64data+"XZnvWRDDnvpWGGRmWUzLZhkN2jW5XziJEWavhhCFHbkX9jAYjNFNu9MCWoaNtcJr";
        String hashtoken=sha256String(token);
        Log.d("hashtoken",hashtoken);

        link="https://apps.bsharpcorp.com/sso_connect/"+hashtoken+"/"+base64data;
        Log.d("ssolink",link);
    }

    public static String sha256String(String source) {
        byte[] hash = null;
        String hashCode = null;// w  ww  .  j  a va 2 s.c  o m
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(source.getBytes());
        } catch (NoSuchAlgorithmException e) {

        }

        if (hash != null) {
            StringBuilder hashBuilder = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(hash[i]);
                if (hex.length() == 1) {
                    hashBuilder.append("0");
                    hashBuilder.append(hex.charAt(hex.length() - 1));
                } else {
                    hashBuilder.append(hex.substring(hex.length() - 2));
                }
            }
            hashCode = hashBuilder.toString();
        }
        return hashCode;
    }

    class RetrievePdfFromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            //openTrainingPopup(doc_name,doc_type,url,inputStream);

            pdfView.fromStream(inputStream)
                    .swipeHorizontal(true)
                    .onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {
                            Log.e(TAG, "onPageChanged: Current Page: " + page + " Total number of page: " + pageCount);
                            tvPdfPageNo.setText(page+1+" / "+pageCount);
                        }
                    })
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int nbPages) {
                            Log.e(TAG, "onInitiallyRendered: nbPages: " + nbPages);
                            llPdfLoading.setVisibility(View.GONE);
                            //DocumentLoadingProgress.showDialog(ViewPdfActivity.this,false);
                            //binding.pageNumber.setVisibility(View.VISIBLE);
                        }
                    })
                    .onTap(new OnTapListener() {
                        @Override
                        public boolean onTap(MotionEvent e) {
                            Log.e(TAG, "onTap: called.");
                            if (tvPdfPageNo.getVisibility() == View.VISIBLE){
                                tvPdfPageNo.setVisibility(View.GONE);
                            } else {
                                tvPdfPageNo.setVisibility(View.VISIBLE);
                            }
                            return false;
                        }
                    })
                    .spacing(15)
                    .pageSnap(true)
                    .autoSpacing(true)
                    .pageFling(true)
                    .load();
        }
    }

    public String createLink(String des){
        String link;
        JSONObject obj=new JSONObject();
        try {
            obj.put("access_key","a1dcac1cc6b9ba47asfafaf");
            obj.put("client_id","100001100002357");
            obj.put("counter_id",prefManager.getUserCode());
            obj.put("destination",des);
            obj.put("expires",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonobj=obj.toString();
        byte[] data = new byte[0];
        try {
            data = jsonobj.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64data = Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\s+", "");;
        Log.d("jsonobj",jsonobj);

        String token=base64data+"XZnvWRDDnvpWGGRmWUzLZhkN2jW5XziJEWavhhCFHbkX9jAYjNFNu9MCWoaNtcJr";
        String hashtoken=sha256String(token);
        Log.d("hashtoken",hashtoken);

        link="https://apps.bsharpcorp.com/sso_connect/"+hashtoken+"/"+base64data;
        Log.d("ssolink",link);
        return link;


    }



}
