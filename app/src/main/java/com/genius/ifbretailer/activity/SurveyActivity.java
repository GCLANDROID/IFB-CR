package com.genius.ifbretailer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.genius.ifbretailer.R;
import com.genius.ifbretailer.model.SpinnerItemModule;
import com.genius.ifbretailer.utility.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {
    Spinner spAirYes, spDishYes, spIFBDishYes;
    MultiSpinnerSearch spMulBrand, spMulTopBrand, spMulSalesVolume;
    EditText etRemark;
    Button btnSubmit;
    LinearLayout llDealingBrand, llTop3Brand, llVolume, llRemarks;
    ArrayList<String> yesnoList = new ArrayList<>();
    ArrayList<SpinnerItemModule> brandList = new ArrayList<>();
    ArrayList<SpinnerItemModule> top3brandList = new ArrayList<>();


    ArrayList<KeyPairBoolData> brandListK = new ArrayList<>();

    PrefManager prefManager;

    ArrayList<String> bbrandList = new ArrayList<>();
    ArrayList<String> btop3brandList = new ArrayList<>();


    String acYes = "";
    String dishYes = "";
    String ifbdishYes = "";
    String brandName = "0";
    String topBrandName = "0";
    String volume = "0";
    Spinner spTop1, spTop2, spTop3;
    LinearLayout llLess50, ll51100, ll101200, ll201500, llMore500;
    ImageView imgLess50, img51100, img101200, img201500, imgMore500;

    String top1 = "0";
    String top2 = "0";
    String top3 = "0";

    String v1 = "0";
    String v2 = "0";
    String v3 = "0";
    String v4 = "0";
    String v5 = "0";
    int clickFlag = 0;
    AlertDialog alerDialog1;
    ScrollView scMain;
    LinearLayout llLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        initView();
        setSubmitChecking();
        onClick();
    }

    private void initView() {
        prefManager = new PrefManager(SurveyActivity.this);
        spAirYes = (Spinner) findViewById(R.id.spAirYes);
        spDishYes = (Spinner) findViewById(R.id.spDishYes);
        spIFBDishYes = (Spinner) findViewById(R.id.spIFBDishYes);

        yesnoList.add("Please Select");
        yesnoList.add("Yes");
        yesnoList.add("No");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (SurveyActivity.this, android.R.layout.simple_spinner_item,
                        yesnoList); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAirYes.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>
                (SurveyActivity.this, android.R.layout.simple_spinner_item,
                        yesnoList); //selected item will look like a spinner set from XML
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDishYes.setAdapter(spinnerArrayAdapter1);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>
                (SurveyActivity.this, android.R.layout.simple_spinner_item,
                        yesnoList); //selected item will look like a spinner set from XML
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spIFBDishYes.setAdapter(spinnerArrayAdapter2);

        spMulBrand = (MultiSpinnerSearch) findViewById(R.id.spMulBrand);
        spMulTopBrand = (MultiSpinnerSearch) findViewById(R.id.spMulTopBrand);
        spMulSalesVolume = (MultiSpinnerSearch) findViewById(R.id.spMulSalesVolume);

        llDealingBrand = (LinearLayout) findViewById(R.id.llDealingBrand);
        llTop3Brand = (LinearLayout) findViewById(R.id.llTop3Brand);
        llVolume = (LinearLayout) findViewById(R.id.llVolume);
        llRemarks = (LinearLayout) findViewById(R.id.llRemarks);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        etRemark = (EditText) findViewById(R.id.etRemark);

        spTop1 = (Spinner) findViewById(R.id.spTop1);
        spTop2 = (Spinner) findViewById(R.id.spTop2);
        spTop3 = (Spinner) findViewById(R.id.spTop3);

        llLess50 = (LinearLayout) findViewById(R.id.llLess50);
        ll51100 = (LinearLayout) findViewById(R.id.ll51100);
        ll101200 = (LinearLayout) findViewById(R.id.ll101200);
        ll201500 = (LinearLayout) findViewById(R.id.ll201500);
        llMore500 = (LinearLayout) findViewById(R.id.llMore500);

        imgLess50 = (ImageView) findViewById(R.id.imgLess50);
        img51100 = (ImageView) findViewById(R.id.img51100);
        img101200 = (ImageView) findViewById(R.id.img101200);
        img201500 = (ImageView) findViewById(R.id.img201500);
        imgMore500 = (ImageView) findViewById(R.id.imgMore500);

        scMain=(ScrollView)findViewById(R.id.scMain);
        llLoader=(LinearLayout)findViewById(R.id.llLoader);
    }

    private void setSubmitChecking() {
        Log.d("hitr", "1");

        String surl = "http://111.93.182.173/IFBiOSApi/api/get_EmployeeCRQuestionRTL?EmployeeID="+prefManager.getUserId()+"&Operation=1&SecurityCode="+prefManager.getSecurityCode();
        Log.d("ctegoryinput", surl);
        llLoader.setVisibility(View.VISIBLE);
        scMain.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseIFBCategory", response);

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                //Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();

                                llLoader.setVisibility(View.VISIBLE);
                                scMain.setVisibility(View.GONE);
                                Intent intent=new Intent(SurveyActivity.this,DashboardActivity.class);
                                startActivity(intent);
                                finish();




                            } else {
                                llLoader.setVisibility(View.GONE);
                                scMain.setVisibility(View.VISIBLE);
                                setCompanyList();

                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {

                            Toast.makeText(SurveyActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(SurveyActivity.this);
        requestQueue.add(stringRequest);

    }

    private void setCompanyList() {
        Log.d("hitr", "1");

        String surl = "http://111.93.182.173/IFBiOSApi/api/CommonDDL?ModuleNo=8002&ID=0&ID1=0&ID2=0&ID3=0&SecurityCode=RTL";
        Log.d("ctegoryinput", surl);
        final ProgressDialog progressDialog = new ProgressDialog(SurveyActivity.this);
        progressDialog.setMessage("Loading.");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseIFBCategory", response);
                        progressDialog.dismiss();
                        top3brandList.add(new SpinnerItemModule("0", "0"));
                        btop3brandList.add("Please Select");

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
                                    String ModelName = obj.optString("value");
                                    String ModelCode = obj.optString("id");
                                    SpinnerItemModule spModel = new SpinnerItemModule(ModelName, ModelCode);
                                    brandList.add(spModel);
                                    top3brandList.add(spModel);
                                    btop3brandList.add(ModelName);


                                }


                                for (int j = 0; j < brandList.size(); j++) {
                                    KeyPairBoolData h = new KeyPairBoolData();
                                    h.setName(brandList.get(j).getItem());
                                    h.setId(brandList.get(j).getItemId());
                                    h.setSelected(false);
                                    brandListK.add(h);

                                }

                                ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>
                                        (SurveyActivity.this, android.R.layout.simple_spinner_item,
                                                btop3brandList); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spTop1.setAdapter(spinnerArrayAdapter1);


                                ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>
                                        (SurveyActivity.this, android.R.layout.simple_spinner_item,
                                                btop3brandList); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spTop2.setAdapter(spinnerArrayAdapter2);

                                ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>
                                        (SurveyActivity.this, android.R.layout.simple_spinner_item,
                                                btop3brandList); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spTop3.setAdapter(spinnerArrayAdapter3);


                            } else {


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SurveyActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(SurveyActivity.this);
        requestQueue.add(stringRequest);

    }

    private void onClick() {
        spMulBrand.setItems(brandListK, -1, new SpinnerListener() {

            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {

                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        String id = items.get(i).getId();
                        bbrandList.add(id);
                        brandName = bbrandList.toString().replace("[", "").replace("]", "").replaceAll(",","&");
                        /*compList.add(comName);
                        String comp = compList.toString();
                        kaID = comp.replace("[", "").replace("]", "").replace("[", "");
                        Log.d("comName", kaID);*/


                    }
                }
            }


        });


        spAirYes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    acYes = yesnoList.get(position);
                }
                if (acYes.equals("Yes")) {
                    llDealingBrand.setVisibility(View.VISIBLE);
                    llTop3Brand.setVisibility(View.VISIBLE);
                    llVolume.setVisibility(View.VISIBLE);
                } else {
                    llDealingBrand.setVisibility(View.GONE);
                    llTop3Brand.setVisibility(View.GONE);
                    llVolume.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spIFBDishYes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    ifbdishYes = yesnoList.get(position);


                }

                if (ifbdishYes.equals("No")) {
                    llRemarks.setVisibility(View.VISIBLE);
                } else {
                    llRemarks.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDishYes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    dishYes = yesnoList.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTop1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    top1 = top3brandList.get(position).getItemId();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTop2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    top2 = top3brandList.get(position).getItemId();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTop3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    top3 = top3brandList.get(position).getItemId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        llLess50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgLess50.getVisibility() == View.GONE) {
                    imgLess50.setVisibility(View.VISIBLE);
                    v1 = "Less than 50 Units";
                    clickFlag = 1;
                } else {
                    imgLess50.setVisibility(View.GONE);
                    v1 = "0";
                    clickFlag = 0;
                }
            }
        });

        ll51100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img51100.getVisibility() == View.GONE) {
                    img51100.setVisibility(View.VISIBLE);
                    v2 = "51-100 units";
                    clickFlag = 1;
                } else {
                    img51100.setVisibility(View.GONE);
                    v2 = "0";
                    clickFlag = 0;
                }
            }
        });

        ll101200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img101200.getVisibility() == View.GONE) {
                    img101200.setVisibility(View.VISIBLE);
                    v3 = "101-200 units";
                    clickFlag = 1;
                } else {
                    img101200.setVisibility(View.GONE);
                    v3 = "0";
                    clickFlag = 0;
                }
            }
        });

        ll201500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img201500.getVisibility() == View.GONE) {
                    img201500.setVisibility(View.VISIBLE);
                    v4 = "201-500 units";
                    clickFlag = 1;
                } else {
                    img201500.setVisibility(View.GONE);
                    v4 = "0";
                    clickFlag = 0;
                }
            }
        });

        llMore500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgMore500.getVisibility() == View.GONE) {
                    imgMore500.setVisibility(View.VISIBLE);
                    v5 = "More than 500 units";
                    clickFlag = 1;
                } else {
                    imgMore500.setVisibility(View.GONE);
                    v5 = "0";
                    clickFlag = 0;
                }
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!acYes.equals("")) {
                    if (!dishYes.equals("")) {
                        if (!ifbdishYes.equals("")) {
                            checking();
                        } else {
                            Toast.makeText(SurveyActivity.this, "Please Select Are you willing to sale IFB Dishwasher  or Not", Toast.LENGTH_LONG).show();

                        }

                    } else {
                        Toast.makeText(SurveyActivity.this, "Please Select Is your counter selling Dishwasher of any Brand or Not", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(SurveyActivity.this, "Please Select Is your counter dealing with Split Air Conditioner or Not", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void submitSurvey(){
        final ProgressDialog pd = new ProgressDialog(SurveyActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();


        AndroidNetworking.upload("http://111.93.182.173/IFBiOSApi/api/post_EmployeeCRQuestionRTL")
                .addMultipartParameter("AEMEmployeeID", prefManager.getUserId())
                .addMultipartParameter("Q1CID", brandName)
                .addMultipartParameter("Q1Flag", acYes)
                .addMultipartParameter("Q2Flag", acYes)
                .addMultipartParameter("Q21CID", top1)
                .addMultipartParameter("Q22CID", top2)
                .addMultipartParameter("Q23CID", top3)
                .addMultipartParameter("Q31R", v1)
                .addMultipartParameter("Q32R", v2)
                .addMultipartParameter("Q33R", v3)
                .addMultipartParameter("Q34R", v4)
                .addMultipartParameter("Q35R", v5)
                .addMultipartParameter("Q4Flag", dishYes)
                .addMultipartParameter("Q5Flag", ifbdishYes)
                .addMultipartParameter("Q5R", etRemark.getText().toString())
                .addMultipartParameter("SecurityCode", "RTL")

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
                            Toast.makeText(SurveyActivity.this, responseText, Toast.LENGTH_LONG).show();

                        }


                        // boolean _status = job1.getBoolean("status");


                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {

                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void checking() {
        if (acYes.equals("Yes")) {
            if (bbrandList.size() > 0) {
                if (!top1.equals("0")) {
                    if (!top2.equals("0")) {
                        if (!top3.equals("0")) {
                            if (!v1.equals("0") || !v2.equals("0") || !v3.equals("0") || !v4.equals("0") || !v5.equals("0")) {
                                if (!top1.equals(top2)) {
                                    if (!top3.equals(top1)){
                                        if (!top3.equals(top2)){
                                            ifbCheking();
                                        }else {
                                            Toast.makeText(SurveyActivity.this,"Top 2 Brand and Top 3 Brand can not be Same",Toast.LENGTH_LONG).show();

                                        }

                                    }else {
                                        Toast.makeText(SurveyActivity.this,"Top 1 Brand and Top 3 Brand can not be Same",Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    Toast.makeText(SurveyActivity.this, "Top 1 Brand and Top 2 Brand can not be Same", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(SurveyActivity.this, "Please Select Split AC Sales volume April 19 to Mar 20", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(SurveyActivity.this, "Please Select top 3 Brand", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(SurveyActivity.this, "Please Select top 2 Brand", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(SurveyActivity.this, "Please Select top 1 Brand", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(SurveyActivity.this, "Please Select Dealing Brand", Toast.LENGTH_LONG).show();
            }
        } else {
            ifbCheking();
        }

    }

    private void ifbCheking() {
        if (ifbdishYes.equals("No")) {
            if (etRemark.getText().toString().length() > 0) {
                submitSurvey();
            } else {
                Toast.makeText(SurveyActivity.this, "Please Enter Remarks", Toast.LENGTH_LONG).show();
            }
        }else {
            submitSurvey();
        }
    }


    private void successAlert(String text) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SurveyActivity.this, R.style.CustomDialogNew);
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
                Intent intent = new Intent(SurveyActivity.this, DashboardActivity.class);
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