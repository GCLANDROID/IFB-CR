package com.genius.ifbretailer.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.genius.ifbretailer.R;
import com.genius.ifbretailer.utility.PrefManager;

import org.json.JSONObject;

import java.util.Calendar;



public class DeliveryAddressUpdateActivity extends AppCompatActivity {
    LinearLayout llDate;
    TextView tvDate;
    String salesDate="",monthname;
    String refNo;
    PrefManager prefManager;
    EditText etRemark;
    AlertDialog alerDialog1;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address_update);
        initView();
        onClick();
    }

    private void initView(){
        prefManager=new PrefManager(DeliveryAddressUpdateActivity.this);
        llDate=(LinearLayout)findViewById(R.id.llDate);
        tvDate=(TextView)findViewById(R.id.tvDate);
        refNo=getIntent().getStringExtra("refNo");
        etRemark=(EditText)findViewById(R.id.etRemark);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
    }

    private void onClick(){
        llDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!salesDate.equals("")){
                    if (etRemark.getText().toString().length()>0){
                        updateDeliveryAddress();
                    }else {
                        Toast.makeText(DeliveryAddressUpdateActivity.this,"Please Enter Remarks", Toast.LENGTH_LONG).show();

                    }

                }else {
                    Toast.makeText(DeliveryAddressUpdateActivity.this,"Please Select Delivery Date", Toast.LENGTH_LONG).show();
                }
            }
        });

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
        final DatePickerDialog dialog = new DatePickerDialog(DeliveryAddressUpdateActivity.this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
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

                tvDate.setText(salesDate);

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

    private void updateDeliveryAddress() {

        final ProgressDialog pd = new ProgressDialog(DeliveryAddressUpdateActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);

        AndroidNetworking.upload("http://111.93.182.173/IFBiOSApi/api/post_EmployeeSalesManage")
                .addMultipartParameter("TransNo", "0")
                .addMultipartParameter("ReferenceNo", refNo)
                .addMultipartParameter("AEMEmployeeID", prefManager.getUserId())
                .addMultipartParameter("SalesDate", salesDate)
                .addMultipartParameter("FinancialYear", "0")
                .addMultipartParameter("Month", "0")
                .addMultipartParameter("CategoryID", "0")
                .addMultipartParameter("Quantity", "0")
                .addMultipartParameter("UserID", "0")
                .addMultipartParameter("BranchID", "0")
                .addMultipartParameter("ModelID", "0")
                .addMultipartParameter("CustomerName", "0")
                .addMultipartParameter("CustomerPhNo", "0")
                .addMultipartParameter("CustomerPinCode", "0")
                .addMultipartParameter("CustomerEmail", "0")
                .addMultipartParameter("InvoiceNo", "0")
                .addMultipartParameter("FinanceScheme", "0")
                .addMultipartParameter("DeliveryAddress", "0")
                .addMultipartParameter("FirstName", "0")
                .addMultipartParameter("LastName", "0")
                .addMultipartParameter("CustomerAlternateNumber", "0")
                .addMultipartParameter("HouseNo", "0")
                .addMultipartParameter("StreetName", "0")
                .addMultipartParameter("Landmark", "0")
                .addMultipartParameter("Title", "0")
                .addMultipartParameter("StateID", "0")
                .addMultipartParameter("City", "0")
                .addMultipartParameter("InvoiceValue", "0")
                .addMultipartParameter("Remarks", "0")
                .addMultipartParameter("UnderExchange", "0")
                .addMultipartParameter("Area", "0")
                .addMultipartParameter("SalesEntryFlag", "0")
                .addMultipartParameter("Invoicecopy", "")
                .addMultipartParameter("SerialNo", "0")
                .addMultipartParameter("Delivery_Date", salesDate)
                .addMultipartParameter("Delivery_Remarks", etRemark.getText().toString())
                .addMultipartParameter("Operation", "3")
                .addMultipartParameter("SubOperation", "4")
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
                            Toast.makeText(DeliveryAddressUpdateActivity.this, responseText, Toast.LENGTH_LONG).show();

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
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DeliveryAddressUpdateActivity.this, R.style.CustomDialogNew);
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
                Intent intent = new Intent(DeliveryAddressUpdateActivity.this, DeliveryDetailsActivity.class);
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
}