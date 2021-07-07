package com.genius.ifbretailer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.androidnetworking.interfaces.UploadProgressListener;
import com.genius.ifbretailer.R;
import com.genius.ifbretailer.adapter.CustomAdapter;
import com.genius.ifbretailer.utility.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MonthWiseIncentiveFeddbackActivity extends AppCompatActivity {
    LinearLayout llYes,llNo,llYesAns;
    ImageView imgCalendar,imgYes,imgNo;
    TextView tvdate,tvMonth;
    EditText etMob,etAmt,etFeedback;
    PrefManager prefManager;
    String monthname;
    String salesDate="";
    String month;
    String rstatus="";
    AlertDialog alerDialog1;
    Button btnSubmit;
    int y;
    String year;
    String financialYear;
    LinearLayout llMain;
    String amt="0";
    String mode="0";
    Spinner spMode;
    ArrayList<String>modeList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_month_wise_incentive_feddback);
        this.setFinishOnTouchOutside(false);
        initView();
        feedbackSubmitChecking();
        onClick();
    }

    private void initView(){
        prefManager=new PrefManager(MonthWiseIncentiveFeddbackActivity.this);
        llYes=(LinearLayout)findViewById(R.id.llYes);
        llNo=(LinearLayout)findViewById(R.id.llNo);
        llYesAns=(LinearLayout)findViewById(R.id.llYesAns);

        imgCalendar=(ImageView)findViewById(R.id.imgCalendar);
        imgYes=(ImageView)findViewById(R.id.imgYes);
        imgNo=(ImageView)findViewById(R.id.imgNo);

        tvdate=(TextView)findViewById(R.id.tvdate);
        tvMonth=(TextView)findViewById(R.id.tvMonth);

        etMob=(EditText)findViewById(R.id.etMob);
        etAmt=(EditText)findViewById(R.id.etAmt);

        etFeedback=(EditText)findViewById(R.id.etFeedback);

        int m = Calendar.getInstance().get(Calendar.MONTH) + 1;
        Log.d("month", String.valueOf(m));
        if (m == 1) {
            month = "January";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 2) {
            month = "February";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 3) {
            month = "March";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 4) {
            month = "April";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 5) {
            month = "May";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 6) {
            month = "June";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 7) {
            month = "July";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 8) {
            month = "August";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 9) {
            month = "September";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 10) {
            month = "October";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 11) {
            month = "November";
            tvMonth.setText("Have you qualified for incentive in last month?");
        } else if (m == 12) {
            month = "December";
            tvMonth.setText("Have you qualified for incentive in last month?");
        }
        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        y = Calendar.getInstance().get(Calendar.YEAR);
        year = String.valueOf(y);
        Log.d("year", year);

        if(month.equals("January")){
            int futureyear = y - 1;
            financialYear = futureyear+"-"+year;
        }else if (month.equals("February")){
            int futureyear = y - 1;
            financialYear = futureyear+"-"+year;
        }else if (month.equals("March")){
            int futureyear = y - 1;
            financialYear = futureyear+"-"+year;
        }else {
            int futureyear = y + 1;
            financialYear = year+"-"+futureyear;
        }

        llMain=(LinearLayout)findViewById(R.id.llMain);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        salesDate = df.format(c);

        spMode=(Spinner)findViewById(R.id.spMode);





    }

    private void feedbackSubmitChecking(){
       final ProgressDialog progressDialog=new ProgressDialog(MonthWiseIncentiveFeddbackActivity.this);
       progressDialog.setMessage("Loading..");
       progressDialog.setCancelable(false);
       progressDialog.show();
        String surl = "http://111.93.182.173/IFBiOSApi/api/get_EmployeeIncFeedbackRTL?AEMEmployeeID="+prefManager.getUserId()+"&FinancialYear="+financialYear+"&Month="+month+"&Operation=2&SecurityCode="+prefManager.getSecurityCode();
        Log.d("inputSalesReport", surl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("responseAttendance", response);
                        progressDialog.dismiss();

                        // attendabceInfiList.clear();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");

                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                finish();






                                /*llNodata.setVisibility(View.GONE);
                                llAgain.setVisibility(View.GONE);*/

                            } else {

                             llMain.setVisibility(View.VISIBLE);
                             setMode();


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MonthWiseIncentiveFeddbackActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               progressDialog.dismiss();

                //Toast.makeText(SupAttenReportActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(MonthWiseIncentiveFeddbackActivity.this);
        requestQueue.add(stringRequest);
    }


    private void onClick(){
        llYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgYes.getVisibility()==View.GONE){
                    imgYes.setVisibility(View.VISIBLE);
                    imgNo.setVisibility(View.GONE);
                    llYesAns.setVisibility(View.VISIBLE);
                    rstatus="1";
                }else {
                    imgYes.setVisibility(View.GONE);
                    imgNo.setVisibility(View.GONE);
                    llYesAns.setVisibility(View.GONE);
                }
            }
        });

        llNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgNo.getVisibility()==View.GONE){
                    imgYes.setVisibility(View.GONE);
                    imgNo.setVisibility(View.VISIBLE);
                    llYesAns.setVisibility(View.GONE);
                    rstatus="0";
                }else {
                    imgYes.setVisibility(View.GONE);
                    imgNo.setVisibility(View.GONE);
                    llYesAns.setVisibility(View.GONE);
                }
            }
        });

        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        etAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAmt.getText().toString().length()>0){
                    amt=etAmt.getText().toString();
                }else {
                    amt="0";
                }

            }
        });

        spMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){
                    mode=modeList.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMob.getText().toString().length()>9){
                    if (!rstatus.equals("")){
                        if (etFeedback.getText().toString().length()>0){
                            submitChking();

                        }else {
                            Toast.makeText(MonthWiseIncentiveFeddbackActivity.this,"please enter feedback",Toast.LENGTH_LONG).show();

                        }

                    }else {
                        Toast.makeText(MonthWiseIncentiveFeddbackActivity.this,"please select Payment status",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(MonthWiseIncentiveFeddbackActivity.this,"Please enter 10 digits mobile number",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void submitChking(){
        if (rstatus.equals("1")){
            if (etAmt.getText().toString().length()>0){
                if (!salesDate.equals("")){
                    if (!mode.equals("0")){
                        postFeedback();

                    }else {
                        Toast.makeText(MonthWiseIncentiveFeddbackActivity.this,"Please select Payment Mode",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(MonthWiseIncentiveFeddbackActivity.this,"Please select Received Date",Toast.LENGTH_LONG).show();

                }

            }else {
                Toast.makeText(MonthWiseIncentiveFeddbackActivity.this,"Please enter Received Amount",Toast.LENGTH_LONG).show();
            }

        }else {
            postFeedback();
        }
    }

    private void showDateDialog() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                StringBuffer strBuf = new StringBuffer();
                strBuf.append("Select date is ");
                strBuf.append(year);
                strBuf.append("-");
                strBuf.append(month + 1);
                strBuf.append("-");
                strBuf.append(dayOfMonth);


            }
        };

        // Get current year, month and day.
        Calendar now = Calendar.getInstance();
        final int year2 = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);

        // Create the new DatePickerDialog instance.
        /*DatePickerDialog datePickerDialog = new DatePickerDialog(SalesManageActivity.this, android.R.style.Theme_Holo_Dialog, onDateSetListener, year, month, day);*/
        final DatePickerDialog dialog = new DatePickerDialog(MonthWiseIncentiveFeddbackActivity.this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {

                String sdate = (m + 1) + "/" + d + "/" + y;
                int s = (m + 1) + d + y;

                int month = (m + 1);
                if (month == 1) {
                    monthname = "Jan";

                } else if (month == 2) {
                    monthname = "Feb";
                } else if (month == 3) {
                    monthname = "March";
                } else if (month == 4) {
                    monthname = "April";
                } else if (month == 5) {
                    monthname = "May";
                } else if (month == 6) {
                    monthname = "June";
                } else if (month == 7) {
                    monthname = "July";
                } else if (month == 8) {
                    monthname = "August";
                } else if (month == 9) {
                    monthname = "Sep";
                } else if (month == 10) {
                    monthname = "Oct";
                } else if (month == 11) {
                    monthname = "Nov";
                } else if (month == 12) {
                    monthname = "Dec";
                }

                salesDate = d + "-" + monthname + "-" + y;

                tvdate.setText(salesDate);

                //  pref.saveDOJ(sdate);


            }
        }, year2, month, day);


        // Set dialog icon and title.
        dialog.setIcon(R.drawable.clockicon);
        dialog.setTitle("Please select date.");
        dialog.getDatePicker().setMaxDate((long) (System.currentTimeMillis() - 1000));

        // Popup the dialog.

        dialog.show();
    }

    private void postFeedback() {

        final ProgressDialog pd = new ProgressDialog(MonthWiseIncentiveFeddbackActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();

        AndroidNetworking.upload("http://111.93.182.173/IFBiOSApi/api/post_EmployeeIncFeedbackRTL")
                .addMultipartParameter("AEMEmployeeID", prefManager.getUserId())
                .addMultipartParameter("MobileNo", etMob.getText().toString())
                .addMultipartParameter("R_Status", rstatus)
                .addMultipartParameter("ReceivedAmount", amt)
                .addMultipartParameter("ReceivedDate", salesDate)
                .addMultipartParameter("ModeOfPayment", mode)
                .addMultipartParameter("Remarks", etFeedback.getText().toString())
                .addMultipartParameter("SecurityCode", prefManager.getSecurityCode())

                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        pd.show();

                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        JSONObject job1 = response;
                        Log.e("response12", "@@@@@@" + job1);
                        String responseText = job1.optString("responseText");
                        Log.d("responseText", responseText);
                        boolean responseStatus=job1.optBoolean("responseStatus");
                        if (responseStatus) {
                            successAlert(responseText);
                            pd.dismiss();


                        } else {
                            pd.dismiss();
                            Toast.makeText(MonthWiseIncentiveFeddbackActivity.this, responseText, Toast.LENGTH_LONG).show();

                        }


                        // boolean _status = job1.getBoolean("status");


                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {

                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG);
                    }
                });

    }

    private void successAlert(String text) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MonthWiseIncentiveFeddbackActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_success, null);
        dialogBuilder.setView(dialogView);
        TextView tvInvalidDate = (TextView) dialogView.findViewById(R.id.tvSuccess);
        tvInvalidDate.setText(text);

        Button btnOk = (Button) dialogView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alerDialog1.dismiss();
                Intent intent = new Intent(MonthWiseIncentiveFeddbackActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        alerDialog1 = dialogBuilder.create();
        alerDialog1.setCancelable(false);
        Window window = alerDialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alerDialog1.show();
    }

    private void setMode() {
        Log.d("hitr", "1");
        final ProgressDialog pd=new ProgressDialog(MonthWiseIncentiveFeddbackActivity.this);
        pd.setMessage("Loading..");
        pd.show();
        pd.setCancelable(false);

        String surl = "http://111.93.182.173/IFBiOSApi/api/CommonDDL?ModuleNo=900&ID=0&ID1=0&ID2=0&ID3=0&SecurityCode=" + prefManager.getSecurityCode();
        Log.d("ctegoryinput", surl);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseIFBCategory", response);
                       pd.dismiss();
                        modeList.clear();
                        modeList.add("Please select");


                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                //Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();
                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.getJSONObject(i);
                                    String value = obj.optString("value");
                                    modeList.add(value);



                                }




                                CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),modeList);
                                spMode.setAdapter(customAdapter);


                            } else {


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MonthWiseIncentiveFeddbackActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                //   Toast.makeText(DocumentManageActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.d("errort", "category");
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(MonthWiseIncentiveFeddbackActivity.this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}