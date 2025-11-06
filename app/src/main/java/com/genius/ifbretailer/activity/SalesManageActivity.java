package com.genius.ifbretailer.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.genius.ifbretailer.R;
import com.genius.ifbretailer.model.ModelSpinnerModel;
import com.genius.ifbretailer.model.RcnModel;
import com.genius.ifbretailer.model.SpinnerItemModule;
import com.genius.ifbretailer.utility.AppController;
import com.genius.ifbretailer.utility.PrefManager;
import com.genius.ifbretailer.utility.ValidUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class SalesManageActivity extends AppCompatActivity {
    TextView tvCategory, tvModel, tvTitle, tvFname, tvLname, tvMob, tvAltMob, tvEmail, tvPinCode, tvState, tvCity, tvArea, tvHouse, tvStreet, tvLand, tvDateTitle;
    TextView tvDate, tvInvoiceValue, tvScheme;
    LinearLayout llExYes, llExYesD, llExNo, llExNoD, llSchYes, llSchYesD, llSchNo, llSchNoD, llScheme;
    Spinner spCategory, spTitle, spState, spCity, spArea, spScheme;
    SingleSpinnerSearch spModel;
    ArrayList<SpinnerItemModule> moduleCategory = new ArrayList<>();
    ArrayList<String> category = new ArrayList<>();

    ArrayList<ModelSpinnerModel> moduleModel = new ArrayList<>();
    ArrayList<String> model = new ArrayList<>();

    ArrayList<SpinnerItemModule> moduleTitle = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();

    ArrayList<SpinnerItemModule> moduleState = new ArrayList<>();
    ArrayList<String> state = new ArrayList<>();

    ArrayList<SpinnerItemModule> moduleCity = new ArrayList<>();
    ArrayList<String> city = new ArrayList<>();

    ArrayList<SpinnerItemModule> moduleArea = new ArrayList<>();
    ArrayList<String> area = new ArrayList<>();

    ArrayList<SpinnerItemModule> moduleScheme = new ArrayList<>();
    ArrayList<String> scheme = new ArrayList<>();


    ArrayList<SpinnerItemModule> moduleInstallation = new ArrayList<>();
    ArrayList<String> installation = new ArrayList<>();

    ArrayList<SpinnerItemModule> moduleSalesType = new ArrayList<>();
    ArrayList<String> salestype = new ArrayList<>();
    Spinner spWIFI, spInstallation, spSalesType;
    LinearLayout lnInstallation, llSalesType, llODU, llODUSerialNumber,lnPedestal;
    TextView tvSalesType;
    String installationBY = "";
    String wifi = "N";
    String selectedWIFI;
    String salesType = "";
    TextView tvMRP, tvInstallation;


    LinearLayout llSave;
    String stateId = "";
    String STATENAME;
    String REGIONNAME;
    TextView tvCityName;
    int y;
    String year, month, financialYear;
    String customerName;
    String modelId = "";
    String titleId = "";
    String schemeId = "0";
    String areaName = "";
    ImageView imgBack;
    LinearLayout llSubmit;
    String quantity = "1";
    String salesDate;
    String underExchange = "0";
    int schemeFlag = 1;
    String remarks = "0";
    AlertDialog alerDialog1;
    String responseText;
    float invoicevalue;
    ImageView imgHome;
    String frstUppercase, lastUppercase;
    String PINCODE;
    String mrp;
    float mrpPrice;
    String monthname;
    AlertDialog alertDialog;
    LinearLayout llLoader, llMain;
    String invalidEmail;
    AlertDialog alet1;
    TextView tvStateName;
    String altmob, invalidemailresponse;
    float valuePut;
    AlertDialog alert1;
    //IMAGE PARAMETER
    private String encodedImage;
    private Uri imageUri;
    private static final int CAMERA_REQUEST = 1;
    File file, compressedImageFile, file1;
    File dFile;
    private static final int REQUEST_GALLERY_CODE = 200;
    String mobNumber, emailId, pinCode, invoiceNumber, delivaryAddress, houseNo, landMark, fName, lName, altNumber, streetname, cityName, invoiceValue;
    PrefManager prefManager;
    String categoryId = "";
    EditText etQuantity;
    EditText etFirstName, etPinCode, etRemark, etInvoiceValue, etInvoiceNumber, etLandMark, etStreetName, etHouse, etEmailId, etPhnNumber, etMobNumber, etLastName;
    int MY_SOCKET_TIMEOUT_MS = 10000;
    ImageView imgCamera;
    LinearLayout llImage;
    Uri uri;
    ImageView imgPic;
    private static final String SERVER_PATH = AppController.APIURL+"api/";
    EditText c;

    ProgressDialog progressDialog;
    int imageTypeFlag;
    String userId, branchId, secirityCode, transNo;
    String saleFlag;
    String stringFile = "";
    LinearLayout llSerialNumber;
    List<EditText> allEds = new ArrayList<EditText>();
    ArrayList<String> serialNumberList = new ArrayList<>();
    EditText b;
    int imgFlag;
    ArrayList<KeyPairBoolData> keyModelList = new ArrayList<>();
    Spinner spCSD;
    String csdSales;
    ArrayList<String> csdSalesList = new ArrayList<>();
    ArrayList<ModelSpinnerModel> modelcsdSaleslist = new ArrayList<>();
    ArrayList<RcnModel> rcnList = new ArrayList<>();
    JSONObject outerObject;
    JSONArray jsonArray;
    String token;
    String product;

    LinearLayout lnDP, lnWIFI;
    TextView tvDP;

    List<EditText> allODEds = new ArrayList<EditText>();
    ArrayList<String> oduList = new ArrayList<>();
    String odunumber = "";
    String pedestial = "N";
    String selectedpedestial;
    Spinner spPedestal;

    JSONArray refArray;

    JSONObject csrOBJ=new JSONObject();
    String sucessText,currentDate;
    String AddressUpdateFlag;
    AlertDialog existingAddressFlag;
    EditText etMoreAddress;
    String crmArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_manage);
        initialize();
        setCategory();
        onClick();
    }

    private void initialize() {
        prefManager = new PrefManager(SalesManageActivity.this);
        currentDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault()).format(new Date());

        String next = "<font color='#EE0000'>*</font>";

        tvCategory = (TextView) findViewById(R.id.tvCategorySale);
        String category = "CATEGORY:";
        tvCategory.setText(Html.fromHtml(category + next));

        tvModel = (TextView) findViewById(R.id.tvModel);
        String model = "MODEL";
        tvModel.setText(Html.fromHtml(model + next));

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        String title = "TITLE";
        tvTitle.setText(Html.fromHtml(title + next));

        tvFname = (TextView) findViewById(R.id.tvFname);
        String fname = "FIRST NAME:";
        tvFname.setText(Html.fromHtml(fname + next));

        tvLname = (TextView) findViewById(R.id.tvLname);
        String lname = "LAST NAME:";
        tvLname.setText(Html.fromHtml(lname + next));

        tvMob = (TextView) findViewById(R.id.tvMob);
        String mob = "10 DIGITS MOBILE NUMBER:";
        tvMob.setText(Html.fromHtml(mob + next));

        tvAltMob = (TextView) findViewById(R.id.tvAltMob);
        String altmob = "ALTERNATIVE NUMBER:";
        tvAltMob.setText(altmob);

        tvEmail = (TextView) findViewById(R.id.tvEmail);
        String email = "EMAIL ";
        tvEmail.setText(Html.fromHtml(email + next));

        tvPinCode = (TextView) findViewById(R.id.tvPinCode);
        String pin = "DELIVERY PIN CODE:";
        tvPinCode.setText(Html.fromHtml(pin + next));

        tvState = (TextView) findViewById(R.id.tvState);
        String state = "STATE";
        tvState.setText(Html.fromHtml(state + next));

        tvCity = (TextView) findViewById(R.id.tvCity);
        String city = "CITY ";
        tvCity.setText(Html.fromHtml(city + next));


        tvSalesType = (TextView) findViewById(R.id.tvSalesType);
        String salestype = "SALES TYPE ";
        tvSalesType.setText(Html.fromHtml(salestype + next));

        tvArea = (TextView) findViewById(R.id.tvArea);
        String area = "AREA ";
        tvArea.setText(Html.fromHtml(area + next));

        tvHouse = (TextView) findViewById(R.id.tvHouse);
        String house = "HOUSE/FLAT/PLOT NO ";
        tvHouse.setText(Html.fromHtml(house + next));

        tvStreet = (TextView) findViewById(R.id.tvStreet);
        String street = "BUILDING NAME/STREET NAME  ";
        tvStreet.setText(Html.fromHtml(street + next));

        tvLand = (TextView) findViewById(R.id.tvLand);
        String land = "LANDMARK ";
        tvLand.setText(Html.fromHtml(land + next));

        tvDateTitle = (TextView) findViewById(R.id.tvDateTitle);
        String date = "INVOICE DATE ";
        tvDateTitle.setText(Html.fromHtml(date + next));

        tvDate = (TextView) findViewById(R.id.tvDate);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        salesDate = df.format(c);
        tvDate.setText(salesDate);

        tvInvoiceValue = (TextView) findViewById(R.id.tvInvoiceValue);
        String voice = "INVOICE VALUE ";
        tvInvoiceValue.setText(Html.fromHtml(voice + next));

        llExYes = (LinearLayout) findViewById(R.id.llExYes);
        llExYesD = (LinearLayout) findViewById(R.id.llExYesD);
        llExNo = (LinearLayout) findViewById(R.id.llExNo);
        llExNoD = (LinearLayout) findViewById(R.id.llExNoD);

        llSchYes = (LinearLayout) findViewById(R.id.llSchYes);
        llSchYesD = (LinearLayout) findViewById(R.id.llSchYesD);
        llSchNo = (LinearLayout) findViewById(R.id.llSchNo);
        llSchNoD = (LinearLayout) findViewById(R.id.llSchNoD);

        llScheme = (LinearLayout) findViewById(R.id.llScheme);
        lnPedestal=(LinearLayout)findViewById(R.id.lnPedestal);
        tvScheme = (TextView) findViewById(R.id.tvScheme);
        String scheme = "SELECT FINANCE SCHEME ";
        tvScheme.setText(Html.fromHtml(scheme + next));


        tvInstallation = (TextView) findViewById(R.id.tvInstallation);
        String installation = "INSTALLATION BY:";
        tvInstallation.setText(Html.fromHtml(installation + next));

        llODU = (LinearLayout) findViewById(R.id.llODU);
        llODUSerialNumber = (LinearLayout) findViewById(R.id.llODUSerialNumber);


        spCategory = (Spinner) findViewById(R.id.spCategory);
        spModel = (SingleSpinnerSearch) findViewById(R.id.spModel);
        spTitle = (Spinner) findViewById(R.id.spTitle);
        spState = (Spinner) findViewById(R.id.spState);
        spCity = (Spinner) findViewById(R.id.spCity);
        spArea = (Spinner) findViewById(R.id.spArea);
        spScheme = (Spinner) findViewById(R.id.spScheme);

        etPinCode = (EditText) findViewById(R.id.etPinCode);
        etRemark = (EditText) findViewById(R.id.etRemark);
        etInvoiceValue = (EditText) findViewById(R.id.etInvoiceValue);
        etInvoiceNumber = (EditText) findViewById(R.id.etInvoiceNumber);
        etLandMark = (EditText) findViewById(R.id.etLandMark);
        etStreetName = (EditText) findViewById(R.id.etStreetName);
        etHouse = (EditText) findViewById(R.id.etHouse);
        etEmailId = (EditText) findViewById(R.id.etEmailId);
        etPhnNumber = (EditText) findViewById(R.id.etPhnNumber);
        etMobNumber = (EditText) findViewById(R.id.etMobNumber);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etFirstName = (EditText) findViewById(R.id.etFirstName);

        tvCityName = (TextView) findViewById(R.id.tvCityName);


        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgHome = (ImageView) findViewById(R.id.imgHome);


        y = Calendar.getInstance().get(Calendar.YEAR);
        year = String.valueOf(y);
        Log.d("year", year);

        int m = Calendar.getInstance().get(Calendar.MONTH) + 1;
        Log.d("month", String.valueOf(m));
        if (m == 1) {
            monthname = "January";
        } else if (m == 2) {
            monthname = "February";
        } else if (m == 3) {
            monthname = "March";
        } else if (m == 4) {
            monthname = "April";
        } else if (m == 5) {
            monthname = "May";
        } else if (m == 6) {
            monthname = "June";
        } else if (m == 7) {
            monthname = "July";
        } else if (m == 8) {
            monthname = "August";
        } else if (m == 9) {
            monthname = "September";
        } else if (m == 10) {
            monthname = "October";
        } else if (m == 11) {
            monthname = "November";
        } else if (m == 12) {
            monthname = "December";
        }

        if (monthname.equals("January")) {
            int futureyear = y - 1;
            financialYear = futureyear + "-" + year;
        } else if (monthname.equals("February")) {
            int futureyear = y - 1;
            financialYear = futureyear + "-" + year;
        } else if (monthname.equals("March")) {
            int futureyear = y - 1;
            financialYear = futureyear + "-" + year;
        } else {
            int futureyear = y + 1;
            financialYear = year + "-" + futureyear;
        }


        llSubmit = (LinearLayout) findViewById(R.id.llSubmit);

        etQuantity = (EditText) findViewById(R.id.etQuantity);
        llLoader = (LinearLayout) findViewById(R.id.llLoader);
        llMain = (LinearLayout) findViewById(R.id.llMain);
        tvStateName = (TextView) findViewById(R.id.tvStateName);
        imgCamera = (ImageView) findViewById(R.id.imgCamera);
        imgPic = (ImageView) findViewById(R.id.imgPic);
        llImage = (LinearLayout) findViewById(R.id.llImage);
        if (prefManager.getInvoiceFlag().equals("1")) {
            llImage.setVisibility(View.VISIBLE);
        } else {
            llImage.setVisibility(View.GONE);
        }


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        llSerialNumber = (LinearLayout) findViewById(R.id.llSerialNumber);
        llSave = (LinearLayout) findViewById(R.id.llSave);
        spCSD = (Spinner) findViewById(R.id.spCSD);
        csdSalesList.add("No");
        csdSalesList.add("Yes");

        modelcsdSaleslist.add(new ModelSpinnerModel("N", "N", ""));
        modelcsdSaleslist.add(new ModelSpinnerModel("Y", "Y", ""));

        ArrayAdapter<String> spinnerCSDArrayAdapter = new ArrayAdapter<String>
                (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                        csdSalesList); //selected item will look like a spinner set from XML
        spinnerCSDArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCSD.setAdapter(spinnerCSDArrayAdapter);

        spWIFI = (Spinner) findViewById(R.id.spWIFI);
        ArrayAdapter<String> spinnerWifiArrayAdapter = new ArrayAdapter<String>
                (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                        csdSalesList); //selected item will look like a spinner set from XML
        spinnerWifiArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spWIFI.setAdapter(spinnerWifiArrayAdapter);

        spInstallation = (Spinner) findViewById(R.id.spInstallation);
        spSalesType = (Spinner) findViewById(R.id.spSalesType);
        lnInstallation = (LinearLayout) findViewById(R.id.lnInstallation);
        llSalesType = (LinearLayout) findViewById(R.id.llSalesType);
        tvMRP = (TextView) findViewById(R.id.tvMRP);
        tvDP = (TextView) findViewById(R.id.tvDP);

        lnDP = (LinearLayout) findViewById(R.id.lnDP);
        lnWIFI = (LinearLayout) findViewById(R.id.lnWIFI);

        spPedestal = (Spinner) findViewById(R.id.spPedestal);
        ArrayAdapter<String> spinnerPedestalAdapter = new ArrayAdapter<String>
                (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                        csdSalesList); //selected item will look like a spinner set from XML
        spinnerPedestalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPedestal.setAdapter(spinnerPedestalAdapter);
        etMoreAddress=(EditText) findViewById(R.id.etMoreAddress);


    }

    private void onClick() {

        spCSD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                csdSales = modelcsdSaleslist.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        llExYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llExYesD.setVisibility(View.VISIBLE);
                llExNoD.setVisibility(View.GONE);
                underExchange = "1";
            }
        });

        llExNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llExYesD.setVisibility(View.GONE);
                llExNoD.setVisibility(View.VISIBLE);
                underExchange = "0";
            }
        });


        llSchYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSchYesD.setVisibility(View.VISIBLE);
                llSchNoD.setVisibility(View.GONE);
                llScheme.setVisibility(View.VISIBLE);
                llSalesType.setVisibility(View.VISIBLE);
                schemeFlag = 1;

            }
        });

        llSchNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSchYesD.setVisibility(View.GONE);
                llSchNoD.setVisibility(View.VISIBLE);
                llScheme.setVisibility(View.GONE);
                llSalesType.setVisibility(View.GONE);
                schemeFlag = 0;
            }
        });

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryId = "";
                if (position > 0) {
                    categoryId = moduleCategory.get(position).getItemId();
                    Log.d("categoryId", categoryId);
                    setModel(categoryId);

                    if (categoryId.equals("IFBPC1000001")) {
                        lnWIFI.setVisibility(View.VISIBLE);
                        lnInstallation.setVisibility(View.VISIBLE);
                        setInstallation();
                        llODU.setVisibility(View.VISIBLE);
                        lnPedestal.setVisibility(View.GONE);
                    } else if (categoryId.equals("IFBPC1000013") || categoryId.equals("IFBPC1000040")){
                        lnPedestal.setVisibility(View.VISIBLE);
                        lnWIFI.setVisibility(View.GONE);
                    }else {
                        lnWIFI.setVisibility(View.GONE);
                        lnInstallation.setVisibility(View.GONE);
                        llODU.setVisibility(View.GONE);
                        lnPedestal.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spWIFI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedWIFI = csdSalesList.get(i);
                if (selectedWIFI.equalsIgnoreCase("NO")) {
                    wifi = "N";
                } else if (selectedWIFI.equalsIgnoreCase("YES")) {
                    wifi = "Y";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spInstallation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    installationBY = moduleInstallation.get(i).getItemId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spSalesType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    salesType = moduleSalesType.get(i).getItemId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etFirstName.getText().toString().length() > 0) {
                    frstUppercase = etFirstName.getText().toString().toUpperCase();
                }

            }
        });

        etPinCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etPinCode.getText().toString().length() == 6) {
                    String pincode = etPinCode.getText().toString();
                    pincodecheck(pincode);
                    if (pincode.equals("491001")) {
                        spState.setSelection(6);
                    }

                } else {

                }

            }
        });
        etMobNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etMobNumber.getText().toString().length() == 10) {
                    String mobnumber = etMobNumber.getText().toString();
                    getCusDetail(mobnumber);
                }

            }
        });


        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etQuantity.getText().toString().length() > 0) {
                    quantity = etQuantity.getText().toString();
                    int p = Integer.parseInt(quantity);
                    ScrollView sv = new ScrollView(SalesManageActivity.this);

                    sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    LinearLayout ll = new LinearLayout(SalesManageActivity.this);
                    ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ll.setOrientation(LinearLayout.VERTICAL);
                    sv.addView(ll);
                    for (int i = 0; i < p; i++) {
                        b = new EditText(SalesManageActivity.this);
                        b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        allEds.add(b);
                        b.setHint("please enter serial number ");
                        b.setTextSize(14);
                        b.setInputType(InputType.TYPE_CLASS_TEXT);
                        b.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)});
                        b.setId(i);
                        b.setSingleLine();
                        ll.addView(b);
                    }

                    llSerialNumber.addView(sv);

                    ScrollView sv1 = new ScrollView(SalesManageActivity.this);

                    sv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    LinearLayout ll1 = new LinearLayout(SalesManageActivity.this);
                    ll1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ll1.setOrientation(LinearLayout.VERTICAL);
                    sv1.addView(ll1);
                    for (int i = 0; i < p; i++) {
                        c = new EditText(SalesManageActivity.this);
                        c.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        allODEds.add(c);
                        c.setHint("Please Enter AC ODU number ");
                        c.setTextSize(14);
                        c.setInputType(InputType.TYPE_CLASS_TEXT);
                        c.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)});
                        c.setId(i);
                        c.setSingleLine();
                        ll1.addView(c);
                    }

                    llODUSerialNumber.addView(sv1);
                }

            }
        });


        etLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etLastName.getText().toString().length() > 0) {
                    customerName = etFirstName.getText().toString() + etLastName.getText().toString();
                    Log.d("cusnamr", customerName);
                    lastUppercase = etLastName.getText().toString().toUpperCase();
                }

            }
        });

        spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stateId = moduleState.get(position).getItemId();
                Log.d("stateId", stateId);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spPedestal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedpedestial = csdSalesList.get(i);
                if (selectedpedestial.equalsIgnoreCase("NO")) {
                    pedestial = "N";
                } else if (selectedpedestial.equalsIgnoreCase("YES")) {
                    pedestial = "Y";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spModel.setItems(keyModelList, -1, new SpinnerListener() {

            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {

                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {

                        modelId = items.get(i).getId();
                        Log.d("modelId", modelId);
                        mrp = items.get(i).getMrp();
                        Log.d("mrp", mrp);
                        mrpPrice = Float.parseFloat(mrp);
                        valuePut = mrpPrice / 2;
                        Log.d("valueput", String.valueOf(valuePut));
                        setDP(modelId);
                        tvMRP.setText(mrp);


                    }
                }
            }


        });


        spTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                titleId = "";
                if (position > 0) {
                    titleId = moduleTitle.get(position).getItemId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaName = area.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etRemark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etRemark.getText().toString().length() > 1) {
                    remarks = etRemark.getText().toString();
                }

            }
        });


        spScheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                schemeId = moduleScheme.get(position).getItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etInvoiceValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etInvoiceValue.getText().toString().length() > 0) {
                    String invoice = etInvoiceValue.getText().toString();
                    invoicevalue = Float.parseFloat(invoice);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesManageActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        etEmailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etEmailId.getText().toString().length() > 0) {
                    invalidEmail = etEmailId.getText().toString().toLowerCase();
                }

            }
        });
        etPhnNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etPhnNumber.getText().toString().length() > 0) {
                    altmob = etPhnNumber.getText().toString();
                } else {
                    altmob = "0000000000";
                }

            }
        });


        etLandMark.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    AddressUpdateFlag="Y";
                }
            }
        });

        etMoreAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    AddressUpdateFlag="Y";
                }
            }
        });

        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraDialog();
            }
        });

        llSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!categoryId.equals("")) {
                    if (!modelId.equals("")) {
                        if (!titleId.equals("")) {
                            if (etFirstName.getText().toString().length() > 0) {
                                if (etLastName.getText().toString().length() > 0) {
                                    if (etMobNumber.getText().toString().length() > 9) {

                                        if (!etPhnNumber.getText().toString().equals(etMobNumber.getText().toString())) {
                                            if (etEmailId.getText().toString().length() > 0) {
                                                if (ValidUtils.isValidEmail(etEmailId.getText().toString())) {
                                                    if (etPinCode.getText().toString().length() > 5) {
                                                        if (!REGIONNAME.equals("null")) {
                                                            if (etHouse.getText().toString().length() > 0) {
                                                                if (etStreetName.getText().toString().length() > 0) {
                                                                    if (etLandMark.getText().toString().length() > 0) {
                                                                        if (etInvoiceValue.getText().toString().length() > 0) {
                                                                            if (etQuantity.getText().toString().equals("1") || etQuantity.getText().toString().equals("2") || etQuantity.getText().toString().equals("3") || etQuantity.getText().toString().equals("4") || etQuantity.getText().toString().equals("5") || etQuantity.getText().toString().equals("")) {
                                                                                if (invoicevalue < mrpPrice || invoicevalue == mrpPrice) {
                                                                                    if (!frstUppercase.equals(lastUppercase)) {
                                                                                        if (invoicevalue > valuePut || invoicevalue == valuePut) {
                                                                                            if (!etMobNumber.getText().toString().equals("0000000000")) {
                                                                                                if (!etMobNumber.getText().toString().equals("1111111111")) {
                                                                                                    if (!etMobNumber.getText().toString().equals("2222222222")) {
                                                                                                        if (!etMobNumber.getText().toString().equals("3333333333")) {
                                                                                                            if (!etMobNumber.getText().toString().equals("4444444444")) {
                                                                                                                if (!etMobNumber.getText().toString().contains("5555555555")) {
                                                                                                                    if (!etMobNumber.getText().toString().contains("6666666666")) {
                                                                                                                        if (!etMobNumber.getText().toString().contains("7777777777")) {
                                                                                                                            if (!etMobNumber.getText().toString().contains("8888888888")) {
                                                                                                                                if (!etMobNumber.getText().toString().contains("9999999999")) {
                                                                                                                                    if (etQuantity.getText().toString().length() > 0) {

                                                                                                                                        instalationChecking();

                                                                                                                                    } else {
                                                                                                                                        etQuantity.setError("Please enter quantity");
                                                                                                                                        etQuantity.requestFocus();
                                                                                                                                    }


                                                                                                                                } else {
                                                                                                                                    etMobNumber.setError("Please enter Valid Phone Number");
                                                                                                                                    etMobNumber.requestFocus();
                                                                                                                                }

                                                                                                                            } else {
                                                                                                                                etMobNumber.setError("Please neter Valid Phone Number");
                                                                                                                                etMobNumber.requestFocus();
                                                                                                                            }

                                                                                                                        } else {
                                                                                                                            etMobNumber.setError("Please neter Valid Phone Number");
                                                                                                                            etMobNumber.requestFocus();
                                                                                                                        }

                                                                                                                    } else {
                                                                                                                        etMobNumber.setError("Please neter Valid Phone Number");
                                                                                                                        etMobNumber.requestFocus();
                                                                                                                    }

                                                                                                                } else {
                                                                                                                    etMobNumber.setError("Please neter Valid Phone Number");
                                                                                                                    etMobNumber.requestFocus();
                                                                                                                }

                                                                                                            } else {
                                                                                                                etMobNumber.setError("Please neter Valid Phone Number");
                                                                                                                etMobNumber.requestFocus();
                                                                                                            }

                                                                                                        } else {
                                                                                                            etMobNumber.setError("Please neter Valid Phone Number");
                                                                                                            etMobNumber.requestFocus();
                                                                                                        }

                                                                                                    } else {
                                                                                                        etMobNumber.setError("Please neter Valid Phone Number");
                                                                                                        etMobNumber.requestFocus();
                                                                                                    }

                                                                                                } else {
                                                                                                    etMobNumber.setError("Please neter Valid Phone Number");
                                                                                                    etMobNumber.requestFocus();
                                                                                                }

                                                                                            } else {
                                                                                                etMobNumber.setError("Please neter Valid Phone Number");
                                                                                                etMobNumber.requestFocus();
                                                                                            }


                                                                                        } else {
                                                                                            Toast.makeText(getApplicationContext(), "Invoice value should be greater than 50% of MRP of product", Toast.LENGTH_LONG).show();

                                                                                        }


                                                                                    } else {
                                                                                        Toast.makeText(getApplicationContext(), "First name and Last name Should be diiferent", Toast.LENGTH_LONG).show();
                                                                                    }


                                                                                } else {
                                                                                    Toast.makeText(getApplicationContext(), "Invoice value should not be greater than  MRP price", Toast.LENGTH_LONG).show();
                                                                                }

                                                                            } else {
                                                                                Toast.makeText(getApplicationContext(), "Please enter valid Quantity", Toast.LENGTH_LONG).show();

                                                                            }

                                                                        } else {
                                                                            Toast.makeText(getApplicationContext(), "Please enter Invoice value", Toast.LENGTH_LONG).show();

                                                                        }

                                                                    } else {
                                                                        Toast.makeText(getApplicationContext(), "Please enter Land Mark", Toast.LENGTH_LONG).show();
                                                                    }

                                                                } else {
                                                                    Toast.makeText(getApplicationContext(), "Please enter Street Name", Toast.LENGTH_LONG).show();

                                                                }

                                                            } else {
                                                                Toast.makeText(getApplicationContext(), "Please enter House/Flat/Plot No", Toast.LENGTH_LONG).show();

                                                            }

                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "Please enter Valid Pincode", Toast.LENGTH_LONG).show();

                                                        }

                                                    } else {
                                                        Toast.makeText(getApplicationContext(), "Please enter Valid Pincode", Toast.LENGTH_LONG).show();

                                                    }

                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Please enter Valid Email Id", Toast.LENGTH_LONG).show();

                                                }


                                            } else {
                                                Toast.makeText(getApplicationContext(), "Please enter Email Id", Toast.LENGTH_LONG).show();

                                            }

                                        } else {
                                            Toast.makeText(getApplicationContext(), "Mobile Number and Alternative Number should be different", Toast.LENGTH_LONG).show();

                                        }


                                    } else {
                                        Toast.makeText(getApplicationContext(), "Please enter Mobile Number", Toast.LENGTH_LONG).show();

                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Please enter Last Name", Toast.LENGTH_LONG).show();

                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter First Name", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Please select Title", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Please select Model", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Please select Category", Toast.LENGTH_LONG).show();
                }
            }
        });

        llSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addressConfirmation(etMoreAddress.getText().toString(),tvCityName.getText().toString(),etPinCode.getText().toString(),areaName,etStreetName.getText().toString(),etLandMark.getText().toString(),etHouse.getText().toString());



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
        final DatePickerDialog dialog = new DatePickerDialog(SalesManageActivity.this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
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


    private void setCategory() {
        Log.d("hitr", "1");

        String surl = AppController.APIURL+"api/CommonDDL?ModuleNo=4&ID=0&ID1=0&ID2=0&ID3=0&SecurityCode=" + prefManager.getSecurityCode();
        Log.d("ctegoryinput", surl);
        llLoader.setVisibility(View.VISIBLE);
        llMain.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseIFBCategory", response);
                        llLoader.setVisibility(View.VISIBLE);
                        llMain.setVisibility(View.GONE);
                        category.clear();
                        moduleCategory.clear();
                        category.add("Please select");
                        moduleCategory.add(new SpinnerItemModule("0", "0"));

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
                                    String id = obj.optString("id");
                                    category.add(value);
                                    SpinnerItemModule itemModule = new SpinnerItemModule(value, id);
                                    moduleCategory.add(itemModule);

                                }

                                setTitle();


                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                        (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                                                category); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spCategory.setAdapter(spinnerArrayAdapter);


                            } else {


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }

    private void setModel(String categoryId) {
        //String surl = "http://111.93.182.173/IFBiOSApi/api/CommonDDL?ModuleNo=18&ID=" + categoryId + "&ID1=0&ID2=0&ID3=0&SecurityCode=" + prefManager.getSecurityCode();
        String surl = AppController.APIURL+"api/CommonDDL?ModuleNo=18M&ID=" + categoryId + "&ID1=0&ID2="+prefManager.getBranchId()+"&ID3=0&SecurityCode=" + prefManager.getSecurityCode();

        Log.d("modelinput", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseModel", response);
                        progressBar.dismiss();

                        moduleModel.clear();
                        keyModelList.clear();

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
                                    String id = obj.optString("id");
                                    String MRP = obj.optString("MRP");
                                    model.add(value);
                                    ModelSpinnerModel itemModule = new ModelSpinnerModel(value, id, MRP);
                                    moduleModel.add(itemModule);

                                }

                                for (int j = 0; j < moduleModel.size(); j++) {
                                    KeyPairBoolData h = new KeyPairBoolData();
                                    h.setName(moduleModel.get(j).getValue());
                                    h.setId(moduleModel.get(j).getId());
                                    h.setMrp(moduleModel.get(j).getMrp());
                                    h.setSelected(false);
                                    keyModelList.add(h);

                                }


                            } else {


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                //   Toast.makeText(DocumentManageActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.d("errort", "model");
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }


    private void setTitle() {
        Log.d("hitr", "2");
        String surl = AppController.APIURL+"api/CommonDDL?ModuleNo=42&ID=0&ID1=0&ID2=0&ID3=0&SecurityCode=" + prefManager.getSecurityCode();
        llLoader.setVisibility(View.VISIBLE);
        llMain.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseTitle", response);
                        llLoader.setVisibility(View.VISIBLE);
                        llMain.setVisibility(View.GONE);
                        title.clear();
                        moduleTitle.clear();
                        title.add("Please select");
                        moduleTitle.add(new SpinnerItemModule("0", "0"));

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
                                    String id = obj.optString("id");
                                    title.add(value);
                                    SpinnerItemModule itemModule = new SpinnerItemModule(value, id);
                                    moduleTitle.add(itemModule);

                                }
                                setState();


                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                        (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                                                title); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spTitle.setAdapter(spinnerArrayAdapter);


                            } else {


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                //   Toast.makeText(DocumentManageActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.d("errort", "title");
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }


    private void pincodecheck(final String pincode) {
        Log.d("hitr", "6");
        String surl = "https://crmapi.ifbsupport.com/api/wa/find-area?PinCode=" + pincode;
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responsepincode", response);
                        progressBar.dismiss();


                        try {
                            JSONArray job1 = new JSONArray(response);

                            for (int i = 0; i < job1.length(); i++) {
                                JSONObject obj = job1.getJSONObject(i);
                                STATENAME = obj.getString("State").toUpperCase();
                                Log.d("statename", STATENAME);
                                PINCODE = obj.optString("PinCode");
                                REGIONNAME = obj.optString("City");
                                String Area = obj.optString("Area");
                                area.add(Area);


                            }

                            int index = state.indexOf(STATENAME);
                            Log.d("inderc", String.valueOf(index));
                            spState.setSelection(index);
                            tvCityName.setVisibility(View.VISIBLE);
                            spCity.setVisibility(View.GONE);
                            tvCityName.setText(REGIONNAME);
                            spState.setEnabled(false);
                            spArea.setVisibility(View.VISIBLE);



                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                    (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                                            area); //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spArea.setAdapter(spinnerArrayAdapter);
                            int pos=area.indexOf(crmArea);
                            spArea.setSelection(pos);

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("errort", e.toString());
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                //   Toast.makeText(DocumentManageActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer V0hBVFNBUFA6d2FVU0VS");
                params.put("Cookie", "TS013f4d0e=0175b9c4a690ee000d09f27c739e8ddc6598c7da8e3b96ffc2c170e8d24c349a15cf26ed8188e9728e693bc57722ad375fc741c358");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }


    private void setArea(String pincode) {
        Log.d("hhjjk", "kkkk");
        String surl = "https://cloud.geniusconsultant.com/GeniusPinCodeApi/api/PinCode?id=" + pincode;
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responsearea", response);
                        progressBar.dismiss();
                        area.clear();
                        moduleArea.clear();


                        try {
                            JSONArray job1 = new JSONArray(response);

                            for (int i = 0; i < job1.length(); i++) {
                                JSONObject obj = job1.getJSONObject(i);
                                String OFFICENAME = obj.optString("OFFICENAME");
                                String PINCODE = obj.optString("PINCODE");
                                area.add(OFFICENAME);

                                SpinnerItemModule itemModule = new SpinnerItemModule(OFFICENAME, PINCODE);
                                moduleArea.add(itemModule);

                            }


                            spArea.setVisibility(View.VISIBLE);


                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                    (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                                            area); //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spArea.setAdapter(spinnerArrayAdapter);

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("errort", e.toString());
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                //   Toast.makeText(DocumentManageActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }


    private void setScheme() {
        Log.d("hitr", "5");
        String surl = AppController.APIURL+"api/CommonDDL?ModuleNo=35&ID=0&ID1=0&ID2=0&ID3=0&SecurityCode=" + prefManager.getSecurityCode();
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseScheme", response);
                        progressBar.dismiss();
                        scheme.clear();
                        moduleScheme.clear();
                        scheme.add("Please select");
                        moduleScheme.add(new SpinnerItemModule("0", "0"));

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
                                    String id = obj.optString("id");
                                    scheme.add(value);
                                    SpinnerItemModule itemModule = new SpinnerItemModule(value, id);
                                    moduleScheme.add(itemModule);

                                }

                                //setTitle();


                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                        (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                                                scheme); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spScheme.setAdapter(spinnerArrayAdapter);


                            } else {


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                //   Toast.makeText(DocumentManageActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }


    private void setState() {
        Log.d("hitr", "3");
        String surl = AppController.APIURL+"api/CommonDDL?ModuleNo=2&ID=0&ID1=0&ID2=0&ID3=0&SecurityCode=" + prefManager.getSecurityCode();
        Log.d("stateinput", surl);
        llLoader.setVisibility(View.VISIBLE);
        llMain.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responsestate", response);
                        llLoader.setVisibility(View.GONE);
                        llMain.setVisibility(View.VISIBLE);
                        state.clear();
                        moduleState.clear();


                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("responseState", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                //Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();
                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.getJSONObject(i);
                                    String value = obj.optString("value");
                                    String id = obj.optString("id");
                                    state.add(value);
                                    SpinnerItemModule itemModule = new SpinnerItemModule(value, id);
                                    moduleState.add(itemModule);

                                }
                              //  setCity();

                                setScheme();
                                setSalesType();

                                spState.setVisibility(View.VISIBLE);
                                spCity.setVisibility(View.VISIBLE);


                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                        (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                                                state); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spState.setAdapter(spinnerArrayAdapter);
                                spState.setSelection(42);


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("errort", e.toString());
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                //   Toast.makeText(DocumentManageActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.d("errort", "state");
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }

    private void setCity() {
        Log.d("hitr", "4");
        tvCityName.setVisibility(View.GONE);
        String surl = AppController.APIURL+"api/CommonDDL?ModuleNo=14&ID=0&ID1=0&ID2=0&ID3=0&SecurityCode=" + prefManager.getSecurityCode();
        llLoader.setVisibility(View.VISIBLE);
        llMain.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseCategory", response);
                        llLoader.setVisibility(View.GONE);
                        llMain.setVisibility(View.VISIBLE);
                        city.clear();
                        moduleCity.clear();


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
                                    String id = obj.optString("id");
                                    city.add(value);
                                    SpinnerItemModule itemModule = new SpinnerItemModule(value, id);
                                    moduleCity.add(itemModule);

                                }


                                spCity.setVisibility(View.VISIBLE);
                                tvCityName.setVisibility(View.GONE);


                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                        (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                                                city); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spCity.setAdapter(spinnerArrayAdapter);
                                spCity.setSelection(2200);


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                llLoader.setVisibility(View.VISIBLE);
                llMain.setVisibility(View.GONE);

                Toast.makeText(SalesManageActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();
                Log.d("errort", "city");
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private void setSalesEntry() {
        String surl = AppController.APIURL+"api/post_SalesEntry?TransNo=0&AEMEmployeeID=" + prefManager.getUserId() + "&_SalesDate=" + salesDate + "&FinancialYear=" + financialYear + "&Month=" + monthname + "&CategoryID=" + categoryId + "&Quantity=" + quantity + "&xmldata=0&UserID=" + prefManager.getUserId() + "&BranchID=" + prefManager.getBranchId() + "&ModelID=" + modelId + "&CustomerName=" + customerName.replaceAll("\\s+", "-") + "&CustomerPhNo=" + etMobNumber.getText().toString() + "&CustomerPinCode=" + etPinCode.getText().toString() + "&CustomerEmail=" + etEmailId.getText().toString() + "&InvoiceNo=" + etInvoiceNumber.getText().toString() + "&FinanceScheme=" + schemeId + "&DeliveryAddress=" + etHouse.getText().toString() + "-" + etLandMark.getText().toString().replaceAll("\\s+", "-") + "&FirstName=" + etFirstName.getText().toString().replaceAll("\\s+", "-") + "&LastName=" + etLastName.getText().toString().replaceAll("\\s+", "-") + "&CustomerAlternateNumber=" + altmob + "&HouseNo=" + etHouse.getText().toString().replaceAll("\\s+", "-") + "&StreetName=" + etStreetName.getText().toString().replaceAll("\\s+", "-") + "&Landmark=" + etLandMark.getText().toString().replaceAll("\\s+", "-") + "&Title=" + titleId + "&StateID=" + stateId + "&City=" + tvCityName.getText().toString().replaceAll("\\s+", "-") + "&InvoiceValue=" + etInvoiceValue.getText().toString() + "&Remarks=" + remarks + "&UnderExchange=" + underExchange + "&SalesEntryFlag=-1&Area=" + areaName + "&SecurityCode=" + prefManager.getSecurityCode();
        Log.d("salesentry", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseCategory", response);
                        progressBar.dismiss();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            responseText = job1.optString("responseText");
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                Toast.makeText(getApplicationContext(), responseText, Toast.LENGTH_LONG).show();


                            } else {
                                Toast.makeText(getApplicationContext(), responseText, Toast.LENGTH_LONG).show();

                            }


                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("errort", e.toString());
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                Toast.makeText(SalesManageActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }


    private void successAlert(String text) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SalesManageActivity.this, R.style.CustomDialogNew);
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
                onBackPressed();

            }
        });

        alerDialog1 = dialogBuilder.create();
        alerDialog1.setCancelable(false);
        Window window = alerDialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alerDialog1.show();
    }


    private void quatityalert() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SalesManageActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_alerts, null);
        dialogBuilder.setView(dialogView);

        Button btnYes = (Button) dialogView.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                emailcheck1();

            }
        });

        Button btnNo = (Button) dialogView.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                etQuantity.setError("Check details");
                etQuantity.requestFocus();
            }
        });

        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(false);
        Window window = alertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog.show();
    }

    private void invalidemailalert(String text) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SalesManageActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_error, null);
        dialogBuilder.setView(dialogView);
        TextView tvInValidEmail = (TextView) dialogView.findViewById(R.id.tvSuccess);
        tvInValidEmail.setText(text);

        Button btnOk = (Button) dialogView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alet1.dismiss();

            }
        });


        alet1 = dialogBuilder.create();
        alet1.setCancelable(false);
        Window window = alet1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alet1.show();
    }


    private void emailcheck1() {
        String surl = AppController.APIURL+"api/CheckInvalidEmailID?EmailID=" + etEmailId.getText().toString();
        Log.d("emailcheck", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseCategory", response);
                        progressBar.dismiss();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean emailstatus2 = job1.optBoolean("responseStatus");
                            if (emailstatus2) {
                                // ssaleFunction();
                                mobNumbercheck();
                            } else {
                                invalidemailalert(responseText);
                            }

                            //boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("errort", e.toString());
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                Toast.makeText(SalesManageActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }

    private void mobNumbercheck() {
        String surl = AppController.APIURL+"api/CheckInvalidMobileNo?MobileNo=" + etMobNumber.getText().toString();
        Log.d("phnnumbercheck", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseCategory", response);
                        progressBar.dismiss();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean emailstatus2 = job1.optBoolean("responseStatus");
                            if (emailstatus2) {
                                if (etPhnNumber.getText().toString().length() > 9) {
                                    altNumbercheck();
                                } else {
                                    llSubmit.setVisibility(View.VISIBLE);
                                    llSave.setVisibility(View.GONE);
                                }
                            } else {
                                invalidemailalert(responseText);
                            }

                            //boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("errort", e.toString());
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                Toast.makeText(SalesManageActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }

    private void altNumbercheck() {
        String surl = AppController.APIURL+"api/CheckInvalidMobileNo?MobileNo=" + etPhnNumber.getText().toString();
        Log.d("phnnumbercheck", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseCategory", response);
                        progressBar.dismiss();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean emailstatus2 = job1.optBoolean("responseStatus");
                            if (emailstatus2) {
                                llSubmit.setVisibility(View.VISIBLE);
                                llSave.setVisibility(View.GONE);
                            } else {
                                invalidemailalert(responseText);
                            }

                            //boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("errort", e.toString());
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                Toast.makeText(SalesManageActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }

    private void cameraDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SalesManageActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.camera_dialog, null);
        dialogBuilder.setView(dialogView);
        LinearLayout llCamera = (LinearLayout) dialogView.findViewById(R.id.llCamera);
        LinearLayout llGallery = (LinearLayout) dialogView.findViewById(R.id.llGallery);
        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntent();
            }
        });

        llGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryIntent();

            }
        });


        alert1 = dialogBuilder.create();
        alert1.setCancelable(false);
        Window window = alert1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alert1.show();
    }

    private void galleryIntent() {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setType("image/*");
        startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
    }


    private void cameraIntent() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Profile Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:

                if (resultCode == Activity.RESULT_OK) {
                    try {
                        try {
                            String imageurl = /*"file://" +*/ getRealPathFromURI(imageUri);
                            file = new File(imageurl);
                            long length = file.length();
                            double m = length / 1024.0;
                            Log.d("size", String.valueOf(m));

                            BitmapFactory.Options o = new BitmapFactory.Options();
                            o.inSampleSize = 2;
                            Bitmap bm = cropToSquare(BitmapFactory.decodeFile(imageurl, o));
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bm.compress(Bitmap.CompressFormat.JPEG, 10, baos); //bm is the bitmap object
                            byte[] b = baos.toByteArray();
                            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                            Log.d("images", encodedImage);
                            imgPic.setImageBitmap(bm);
                            alert1.dismiss();
                            imageTypeFlag = 1;
                            imgFlag = 1;
                            String contentType = "image/jpg";
                            String[] brkDown = imageurl.split("/");
                            String name = brkDown[5];
                            stringFile = name + "_" + encodedImage + "_" + contentType;


                            // _pref.saveImage(encodedImage);
                            //saveImage(encodedImage);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                    }

                }
                break;
            case REQUEST_GALLERY_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    InputStream imageStream = null;
                    try {
                        try {
                            uri = data.getData();
                            String filePath = getRealPathFromURIPath(uri, SalesManageActivity.this);
                            file = new File(filePath);
                            //  Log.d(TAG, "filePath=" + filePath);
                            imageStream = getContentResolver().openInputStream(uri);
                            Bitmap bm = cropToSquare(BitmapFactory.decodeStream(imageStream));
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bm.compress(Bitmap.CompressFormat.JPEG, 10, baos); //bm is the bitmap object
                            byte[] b = baos.toByteArray();
                            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                            imgPic.setImageBitmap(bm);
                            alert1.dismiss();
                            imageTypeFlag = 2;
                            imgFlag = 1;
                            String contentType = "image/jpg";
                            String[] brkDown = filePath.split("/");
                            String name = brkDown[5];
                            stringFile = name + "_" + encodedImage + "_" + contentType;


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                    }

                }
                break;


        }


    }


    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    public static Bitmap cropToSquare(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = (height > width) ? width : height;
        int newHeight = (height > width) ? height - (height - width) : height;
        int cropW = (width - height) / 2;
        cropW = (cropW < 0) ? 0 : cropW;
        int cropH = (height - width) / 2;
        cropH = (cropH < 0) ? 0 : cropH;
        Bitmap cropImg = Bitmap.createBitmap(bitmap, cropW, cropH, newWidth, newHeight);

        return cropImg;
    }


    private void postSale(String serailNumber, String odunumber) {
        llSubmit.setEnabled(false);
        userId = prefManager.getUserId();
        secirityCode = prefManager.getSecurityCode();
        branchId = prefManager.getBranchId();
        mobNumber = etMobNumber.getText().toString();
        emailId = etEmailId.getText().toString();
        pinCode = etPinCode.getText().toString();
        invoiceNumber = etInvoiceNumber.getText().toString();
        delivaryAddress = etHouse.getText().toString() + "-" + etLandMark.getText().toString() + "-" + etStreetName.getText().toString();
        houseNo = etHouse.getText().toString();
        landMark = etLandMark.getText().toString();
        fName = etFirstName.getText().toString();
        lName = etLastName.getText().toString();
        altNumber = etPhnNumber.getText().toString();
        streetname = etStreetName.getText().toString();
        cityName = tvCityName.getText().toString();
        invoiceValue = etInvoiceValue.getText().toString();
        remarks = etRemark.getText().toString();
        transNo = "0";
        saleFlag = "1";
        final ProgressDialog pd = new ProgressDialog(SalesManageActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();
        AndroidNetworking.upload(AppController.APIURL+"api/post_EmployeeDummySalesWithInvoiceCSDV3")
                .addMultipartParameter("TransNo", transNo)
                .addMultipartParameter("AEMEmployeeID", userId)
                .addMultipartParameter("SalesDate", salesDate)
                .addMultipartParameter("FinancialYear", financialYear)
                .addMultipartParameter("Month", monthname)
                .addMultipartParameter("CategoryID", categoryId)
                .addMultipartParameter("Quantity", quantity)
                .addMultipartParameter("xmldata", transNo)
                .addMultipartParameter("UserID", userId)
                .addMultipartParameter("BranchID", branchId)
                .addMultipartParameter("ModelID", modelId)
                .addMultipartParameter("CustomerName", customerName)
                .addMultipartParameter("CustomerPhNo", mobNumber)
                .addMultipartParameter("CustomerPinCode", pinCode)
                .addMultipartParameter("CustomerEmail", emailId)
                .addMultipartParameter("InvoiceNo", invoiceNumber)
                .addMultipartParameter("FinanceScheme", schemeId)
                .addMultipartParameter("DeliveryAddress", delivaryAddress)
                .addMultipartParameter("FirstName", fName)
                .addMultipartParameter("LastName", lName)
                .addMultipartParameter("CustomerAlternateNumber", "")

                .addMultipartParameter("HouseNo", houseNo)
                .addMultipartParameter("StreetName", streetname)
                .addMultipartParameter("Landmark", landMark)
                .addMultipartParameter("Title", titleId)
                .addMultipartParameter("StateID", stateId)
                .addMultipartParameter("City", cityName)
                .addMultipartParameter("InvoiceValue", invoiceValue)
                .addMultipartParameter("Remarks", remarks)
                .addMultipartParameter("UnderExchange", underExchange)
                .addMultipartParameter("Area", areaName)
                .addMultipartParameter("SalesEntryFlag", saleFlag)
                .addMultipartParameter("SerialNo", serailNumber)
                .addMultipartParameter("SerialNo1", odunumber)
                .addMultipartParameter("InstallationBy", installationBY)
                .addMultipartParameter("SalesType", salesType)
                .addMultipartParameter("WiFiDeviceStatus", wifi)
                .addMultipartParameter("CSD_Sales", csdSales)
                .addMultipartParameter("PedestalSales", pedestial)
                .addMultipartParameter("DeliveryAddress2", etMoreAddress.getText().toString())
                .addMultipartParameter("AddressUpdateFlag", AddressUpdateFlag)
                .addMultipartParameter("SecurityCode", secirityCode)

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

                        llSubmit.setEnabled(true);
                        JSONObject job1 = response;
                        Log.e("response12", "@@@@@@" + job1);
                        sucessText = job1.optString("responseText");
                        String tokenNo = job1.optString("responseData");
                        token = tokenNo;
                        boolean responseStatus = job1.optBoolean("responseStatus");
                        if (responseStatus) {
                            getToken(token);
                            pd.dismiss();


                        } else {
                            pd.dismiss();
                            Toast.makeText(SalesManageActivity.this, ""+responseText, Toast.LENGTH_LONG).show();

                        }


                        // boolean _status = job1.getBoolean("status");


                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        llSubmit.setEnabled(true);
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG);
                    }
                });

    }


    private void postSaleWithImage(String serailNumber, String odunumber) {
        llSubmit.setEnabled(false);
        userId = prefManager.getUserId();
        secirityCode = prefManager.getSecurityCode();
        branchId = prefManager.getBranchId();
        mobNumber = etMobNumber.getText().toString();
        emailId = etEmailId.getText().toString();
        pinCode = etPinCode.getText().toString();
        invoiceNumber = etInvoiceNumber.getText().toString();
        delivaryAddress = etHouse.getText().toString() + "-" + etLandMark.getText().toString() + "-" + etStreetName.getText().toString();
        houseNo = etHouse.getText().toString();
        landMark = etLandMark.getText().toString();
        fName = etFirstName.getText().toString();
        lName = etLastName.getText().toString();
        altNumber = etPhnNumber.getText().toString();
        streetname = etStreetName.getText().toString();
        cityName = tvCityName.getText().toString();
        invoiceValue = etInvoiceValue.getText().toString();
        remarks = etRemark.getText().toString();
        transNo = "0";
        saleFlag = "1";
        final ProgressDialog pd = new ProgressDialog(SalesManageActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();
        AndroidNetworking.upload(AppController.APIURL+"api/post_EmployeeDummySalesWithInvoiceCSDV2")
                .addMultipartParameter("TransNo", transNo)
                .addMultipartParameter("AEMEmployeeID", userId)
                .addMultipartParameter("SalesDate", salesDate)
                .addMultipartParameter("FinancialYear", financialYear)
                .addMultipartParameter("Month", monthname)
                .addMultipartParameter("CategoryID", categoryId)
                .addMultipartParameter("Quantity", quantity)
                .addMultipartParameter("xmldata", transNo)
                .addMultipartParameter("UserID", userId)
                .addMultipartParameter("BranchID", branchId)
                .addMultipartParameter("ModelID", modelId)
                .addMultipartParameter("CustomerName", customerName)
                .addMultipartParameter("CustomerPhNo", mobNumber)
                .addMultipartParameter("CustomerPinCode", pinCode)
                .addMultipartParameter("CustomerEmail", emailId)
                .addMultipartParameter("InvoiceNo", invoiceNumber)
                .addMultipartParameter("FinanceScheme", schemeId)
                .addMultipartParameter("DeliveryAddress", delivaryAddress)
                .addMultipartParameter("FirstName", fName)
                .addMultipartParameter("LastName", lName)
                .addMultipartParameter("CustomerAlternateNumber", "")
                .addMultipartParameter("HouseNo", houseNo)
                .addMultipartParameter("StreetName", streetname)
                .addMultipartParameter("Landmark", landMark)
                .addMultipartParameter("Title", titleId)
                .addMultipartParameter("StateID", stateId)
                .addMultipartParameter("City", cityName)
                .addMultipartParameter("InvoiceValue", invoiceValue)
                .addMultipartParameter("Remarks", remarks)
                .addMultipartParameter("UnderExchange", underExchange)
                .addMultipartParameter("Area", areaName)
                .addMultipartParameter("SalesEntryFlag", saleFlag)
                .addMultipartParameter("Invoicecopy", stringFile)
                .addMultipartParameter("SerialNo", serailNumber)
                .addMultipartParameter("SerialNo1", odunumber)
                .addMultipartParameter("InstallationBy", installationBY)
                .addMultipartParameter("SalesType", salesType)
                .addMultipartParameter("WiFiDeviceStatus", wifi)
                .addMultipartParameter("CSD_Sales", csdSales)
                .addMultipartParameter("PedestalSales", pedestial)
                .addMultipartParameter("SecurityCode", secirityCode)

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

                        llSubmit.setEnabled(true);
                        JSONObject job1 = response;
                        Log.e("response12", "@@@@@@" + job1);
                        sucessText = job1.optString("responseText");
                        String tokenNo = job1.optString("responseData");
                        token = tokenNo;
                        boolean responseStatus = job1.optBoolean("responseStatus");
                        if (responseStatus) {
                            getToken(token);
                            pd.dismiss();

                        } else {
                            pd.dismiss();
                            Toast.makeText(SalesManageActivity.this, ""+responseText, Toast.LENGTH_LONG).show();

                        }


                        // boolean _status = job1.getBoolean("status");


                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        llSubmit.setEnabled(true);
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG);
                    }
                });
    }

    private void ssaleFunction() {
        String serialNumber = "";
        for (int i = 0; i < allEds.size(); i++) {
            if (allEds.get(i).getText().toString().length() > 17) {
                serialNumberList.add(allEds.get(i).getText().toString());
                serialNumber = serialNumberList.toString().replace("[", "").replace("]", "").concat(",");
                Log.d("Value ", serialNumber);

            }


            for (int j = 0; j < allODEds.size(); j++) {
                if (allODEds.get(j).getText().toString().length() > 17) {
                    oduList.add(allODEds.get(j).getText().toString());
                    odunumber = oduList.toString().replace("[", "").replace("]", "").concat(",");
                    Log.d("Value ", odunumber);
                } else {

                }
            }


        }

        if (stringFile.equals("")) {
            if (categoryId.equals("IFBPC1000001")) {

                if (installationBY.equalsIgnoreCase("IFB Franchisee")) {
                    postSale(serialNumber, odunumber);
                } else {
                    if (serialNumberList.size() > 0) {

                        //checkSerialNumberForIDU(serialNumber);
                        postSale(serialNumber, odunumber);

                    } else {
                        postSale(serialNumber, odunumber);
                      //  Toast.makeText(SalesManageActivity.this, "Please Enter Serial / AC IDU Number", Toast.LENGTH_LONG).show();
                    }
                }

            } else {
                postSale(serialNumber, odunumber);
            }
        } else {
            if (categoryId.equals("IFBPC1000001")) {
                if (installationBY.equalsIgnoreCase("IFB Franchisee")) {
                    postSaleWithImage(serialNumber, odunumber);
                } else {
                    if (serialNumberList.size() > 0) {

                       // checkSerialNumberForIDUForImage(serialNumber);
                        postSaleWithImage(serialNumber, odunumber);


                    } else {
                        postSaleWithImage(serialNumber, odunumber);
                      //  Toast.makeText(SalesManageActivity.this, "Please Enter Serial / AC IDU Number", Toast.LENGTH_LONG).show();
                    }
                }

            } else {
                postSaleWithImage(serialNumber, odunumber);
            }
        }


    }


    public void getToken(String token) {
        String surl = AppController.APIURL+"api/get_CRMDummyTokenByReference?ReferenceNo=" + token + "&SecurityCode=" + prefManager.getSecurityCode();
        Log.d("inputCheck", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
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
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                JSONArray responseData = job1.optJSONArray("responseData");
                                csrOBJ=new JSONObject();
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject object = responseData.optJSONObject(i);
                                    String TokenNo = object.optString("TokenNo");
                                    String CategoryShortName = object.optString("CategoryShortName");
                                    String SerialNo = object.optString("SerialNo");
                                    String SerialNo2 = object.optString("SerialNo2");
                                    String ModelCode=object.optString("ModelCode");
                                    String FirstName=object.optString("FirstName");
                                    String LastName=object.optString("LastName");
                                    String DeliveryAddress = object.optString("DeliveryAddress");
                                    String Landmark = object.optString("Landmark");
                                    String Area = object.optString("Area");
                                    String HouseNo=object.optString("HouseNo");
                                    String DeliveryAddress2 = object.optString("DeliveryAddress2");
                                    String AddressUpdateFlag = object.optString("AddressUpdateFlag");

                                    String CustomerPinCode=object.optString("CustomerPinCode");
                                    String City=object.optString("City");
                                    String StateName=object.optString("StateName");
                                    String CustomerPhNo=object.optString("CustomerPhNo");
                                    String AlternateNumber=object.optString("AlternateNumber");
                                    String CustomerEmail=object.optString("CustomerEmail");
                                    String SalesDate=object.optString("SalesDate");
                                    String ShipPartyCode=object.optString("ShipPartyCode");
                                    String MultipleProduct=object.optString("MultipleProduct");
                                    String WiFiDeviceStatus=object.optString("WiFiDeviceStatus");
                                    String RELIANCEFLAG=object.optString("RELIANCEFLAG");
                                    String InstallationBy=object.optString("InstallationBy");
                                    RcnModel rcnModel = new RcnModel();
                                    rcnModel.setToken(TokenNo);
                                    rcnModel.setSerNumber(SerialNo);
                                    rcnModel.setShortName(CategoryShortName);
                                    rcnList.add(rcnModel);

                                    csrOBJ.put("MODEL",ModelCode);
                                    csrOBJ.put("PRODUCT",CategoryShortName);
                                    csrOBJ.put("CUSTOMERFIRSTNAME",FirstName);
                                    csrOBJ.put("CUSTOMERLASTNAME",LastName);
                                    csrOBJ.put("ADDRESS",DeliveryAddress);
                                    csrOBJ.put("STREET",Area);
                                    csrOBJ.put("PINCODE",CustomerPinCode);
                                    csrOBJ.put("CITY",City);
                                    csrOBJ.put("STATE",StateName);
                                    csrOBJ.put("MOBILENO",CustomerPhNo);
                                    csrOBJ.put("ALTMOBNO",AlternateNumber);
                                    csrOBJ.put("EMAIL",CustomerEmail);
                                    csrOBJ.put("PURCHASEDATE",SalesDate);
                                    csrOBJ.put("DEALER",ShipPartyCode);
                                    csrOBJ.put("TOKENNO",TokenNo);
                                    csrOBJ.put("CREATEDBY","R"+prefManager.getUserCode());
                                    csrOBJ.put("RELIANCEFRANCH","");
                                    csrOBJ.put("RELIANCEFLAG",RELIANCEFLAG);
                                    csrOBJ.put("MULTIPLEQUANTITY",MultipleProduct);
                                    csrOBJ.put("TOKENCREATED",currentDate);
                                    csrOBJ.put("INSTALLATIONBY",InstallationBy);
                                    csrOBJ.put("IDUSERIAL",SerialNo);
                                    csrOBJ.put("ODUSERIAL",SerialNo2);
                                    csrOBJ.put("WIFI",WiFiDeviceStatus);
                                    csrOBJ.put("FILECREATED",currentDate);
                                    csrOBJ.put("ADDRESS2", DeliveryAddress2);
                                    csrOBJ.put("HOUSENO", HouseNo);
                                    csrOBJ.put("LANDMARK", Landmark);
                                    csrOBJ.put("ADDRESSUPDATEDFLAG", AddressUpdateFlag);

                                    sendCSRData(csrOBJ,TokenNo);
                                }
                                JSONObject obj = responseData.optJSONObject(0);
                                product = obj.optString("CategoryShortName");
                                String Ref_Status = obj.optString("Ref_Status");
                                if (Ref_Status.equalsIgnoreCase("N")){
                                    getTicketNumber();
                                }else {
                                    setRefArray();
                                }


                            }else {
                                successAlert(sucessText);
                            }


                        } catch (JSONException e) {
                            successAlert(sucessText);
                            e.printStackTrace();
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();
                Toast.makeText(SalesManageActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }

    private void sendCSRData(JSONObject jsonObject, final String token) {


        String credentials = "Genius" + ":" + "genius@345&";
        String auth = "Basic "
                + Base64.encodeToString(credentials.getBytes(),
                Base64.NO_WRAP);

        final ProgressDialog pd = new ProgressDialog(SalesManageActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();

        AndroidNetworking.post("https://ifbapi.ifbsupport.com/api/CSRDATA")

                .addJSONObjectBody(jsonObject)
                .addHeaders("Authorization", auth)
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()

                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pd.dismiss();



                        String Message=response.optString("Message");
                        Toast.makeText(SalesManageActivity.this,Message,Toast.LENGTH_LONG).show();
                        postTokenStatus(token,Message);



                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        String errormessgae=error.getMessage();
                        postTokenStatus(token,errormessgae);
                        Toast.makeText(SalesManageActivity.this,"Wrong",Toast.LENGTH_LONG).show();

                    }
                });



    }

    private void postTokenStatus(String token,String status) {


        final ProgressDialog pd = new ProgressDialog(SalesManageActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();

        AndroidNetworking.upload(AppController.APIURL+"api/post_CRMTokenPushedStatus")

                .addMultipartParameter("TokenNo",token)
                .addMultipartParameter("Remarks",status)
                .addMultipartParameter("SecurityCode",prefManager.getSecurityCode())
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()

                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pd.dismiss();


                        successAlert(sucessText);

                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        successAlert(sucessText);
                        Toast.makeText(SalesManageActivity.this,"Wrong",Toast.LENGTH_LONG).show();

                    }
                });



    }


    private void setRefArray() {

        String dop= AppController.changeAnyDateFormat(salesDate,"dd-MMM-yyyy","yyyy-MM-dd");

        ArrayList<String> tokenList = new ArrayList<>();
        ArrayList<String>serialList=new ArrayList<>();
        ArrayList<String>shortNameList=new ArrayList<>();

        JSONObject innerObj = new JSONObject();
        refArray = new JSONArray();


        //token

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getToken();
            if (rcnList.get(i).getToken() != null) {
                tokenList.add(customername);
            }

        }




        //serial


        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getSerNumber();
            if (rcnList.get(i).getToken() != null) {
                serialList.add(customername);
            }

        }



        //shortname

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getShortName();
            if (rcnList.get(i).getToken() != null) {
                shortNameList.add(customername);
            }

        }

        for (int j = 0; j <shortNameList.size(); j++) {
            try {
                innerObj.put("serial", rcnList.get(j).getSerNumber());
                innerObj.put("token", rcnList.get(j).getToken());
                innerObj.put("prod_category", shortNameList.get(j));
                innerObj.put("Method", "I");
                innerObj.put("csrid", prefManager.getUserCode());
                innerObj.put("mobile", etMobNumber.getText().toString());
                innerObj.put("model", modelId);
                innerObj.put("dealer_code", prefManager.getUserCode());
                innerObj.put("DOP", dop);
                innerObj.put("Customer_name", etFirstName.getText().toString()+" "+etLastName.getText().toString());
                innerObj.put("Customer_address",delivaryAddress);
                innerObj.put("customer_pincode", etPinCode.getText().toString());
                refArray.put(innerObj);
                innerObj=new JSONObject();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        final ProgressDialog pd = new ProgressDialog(SalesManageActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();

        AndroidNetworking.post("https://api.ifbanalytics.com/v1/CRM/ref_cust")

                .addJSONArrayBody(refArray)
                .addHeaders("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoid2hhdHNhcHAiLCJleHAiOjE3MDkzODMwNzZ9.FijFDU49U8yRgxrRac6cboLI8xTKf6mjmjU03QsUuoc")
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()

                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pd.dismiss();


                        getTicketNumber();



                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        getTicketNumber();
                        // getInformationFromToken();
                    }
                });



    }

    private void getTicketNumber() {

        final ProgressDialog pd = new ProgressDialog(SalesManageActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("rcn", etMobNumber.getText().toString());
            jsonObject.put("modelcode", modelId);
            jsonObject.put("MatGrp", product);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("https://crmapi.ifbsupport.com/api/v.1/csr/search/ticket")

                .addJSONObjectBody(jsonObject)
                .addHeaders("Authorization", "Bearer Q1NSVVNFUjpjc3JVc2Vy")
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()

                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        JSONObject job1 = response;
                        Log.e("response12", "@@@@@@" + job1);
                        String status = job1.optString("status");


                        if (status.equalsIgnoreCase("200")) {

                            pd.dismiss();
                            JSONArray TicketDetails = job1.optJSONArray("TicketDetails");
                            for (int i = 0; i < TicketDetails.length(); i++) {
                                JSONObject obj = TicketDetails.optJSONObject(i);
                                String Ticketno = obj.optString("Ticketno");
                                String dealername = obj.optString("dealername");
                                String DealerPhone = obj.optString("DealerPhone");
                                String CustomerCode = obj.optString("CustomerCode");
                                String CustomerName = obj.optString("CustomerName");
                                String Address = obj.optString("Address");
                                String Pincode = obj.optString("Pincode");
                                String CustomerMobile = obj.optString("CustomerMobile");
                                String CustomerEmail = obj.optString("CustomerEmail");
                                String DOP = obj.optString("DOP");
                                String ProductDOI = obj.optString("ProductDOI");
                                String TicketStatusCode = obj.optString("TicketStatusCode");
                                String CancelledReason = obj.optString("CancelledReason");
                                String CancelledReasonDescription = obj.optString("CancelledReasonDescription");
                                String BranchCode = obj.optString("BranchCode");
                                String BranchName = obj.optString("BranchName");
                                String TicketCallType = obj.optString("TicketCallType");
                                String CallBookDate = obj.optString("CallBookDate");
                                String CallClosedDateValue = obj.optString("CallClosedDateValue");
                                String FGCode = obj.optString("FGCode");
                                RcnModel rcnModel = new RcnModel();
                                rcnModel.setTicket(Ticketno);
                                rcnModel.setDelearName(dealername);
                                rcnModel.setDelearPhone(DealerPhone);
                                rcnModel.setCustomerCode(CustomerCode);
                                rcnModel.setCustomerName(CustomerName);
                                rcnModel.setAddress(Address);
                                rcnModel.setPincode(Pincode);
                                rcnModel.setCustomerMobile(CustomerMobile);
                                rcnModel.setCustomerEmail(CustomerEmail);
                                rcnModel.setDop(DOP);
                                rcnModel.setDoi(ProductDOI);
                                rcnModel.setStatusCode(TicketStatusCode);
                                rcnModel.setCancelledReason(CancelledReason);
                                rcnModel.setCancelledReasonDescription(CancelledReasonDescription);
                                rcnModel.setBranch(BranchCode);
                                rcnModel.setBranchName(BranchName);
                                rcnModel.setCallType(TicketCallType);
                                rcnModel.setCallBookDate(CallBookDate);
                                rcnModel.setCallClosedDate(CallClosedDateValue);
                                rcnModel.setModelcode(FGCode);
                                rcnList.add(rcnModel);
                            }
                            setRcnArray();


                        } else {
                            pd.dismiss();
                            successAlert(sucessText);


                        }


                        // boolean _status = job1.getBoolean("status");


                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        successAlert(sucessText);
                    }
                });
    }

    private void setRcnArray() {
        ArrayList<String> delaernameList = new ArrayList<>();
        ArrayList<String> tokenList = new ArrayList<>();
        ArrayList<String> ticketList = new ArrayList<>();
        ArrayList<String> refList = new ArrayList<>();
        ArrayList<String> dealerPhnList = new ArrayList<>();
        ArrayList<String> customerCodeList = new ArrayList<>();
        ArrayList<String> customerNameList = new ArrayList<>();
        ArrayList<String> addressList = new ArrayList<>();
        ArrayList<String> pincodeList = new ArrayList<>();
        ArrayList<String> cusMobList = new ArrayList<>();
        ArrayList<String> cusEmailList = new ArrayList<>();
        ArrayList<String> modelcodeList = new ArrayList<>();
        ArrayList<String> dopList = new ArrayList<>();
        ArrayList<String> doiList = new ArrayList<>();
        ArrayList<String> calltypeList = new ArrayList<>();
        ArrayList<String> statuscodeList = new ArrayList<>();
        ArrayList<String> cancelledListList = new ArrayList<>();
        ArrayList<String> cancelledDescListList = new ArrayList<>();
        ArrayList<String> branchList = new ArrayList<>();
        ArrayList<String> branchNameList = new ArrayList<>();
        ArrayList<String> callBookList = new ArrayList<>();
        ArrayList<String> callclosedList = new ArrayList<>();


        outerObject = new JSONObject();
        JSONObject innerObj = new JSONObject();
        jsonArray = new JSONArray();


        //token

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getToken();
            if (rcnList.get(i).getToken() != null) {
                tokenList.add(customername);
            }

        }

        for (int j = tokenList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("TokenNo", tokenList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        //ref
        for (int i = 0; i < rcnList.size(); i++) {

            refList.add(token);


        }

        for (int j = 0; j < refList.size(); j++) {
            try {
                innerObj.put("ReferenceNo", refList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        //Dealername

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getDelearName();
            if (rcnList.get(i).getDelearName() != null) {
                delaernameList.add(customername);
            }

        }
        for (int j = delaernameList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("DealerName", delaernameList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        //ticketNumber
        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getTicket();
            if (rcnList.get(i).getTicket() != null) {
                ticketList.add(customername);
            }

        }

        for (int j = ticketList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("TicketNumber", ticketList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        //dealerPhn

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getDelearPhone();
            if (rcnList.get(i).getDelearPhone() != null) {
                dealerPhnList.add(customername);
            }

        }

        for (int j = dealerPhnList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("DealerPhone", dealerPhnList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        //CustomerCode
        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getCustomerCode();
            if (rcnList.get(i).getCustomerCode() != null) {
                customerCodeList.add(customername);
            }

        }

        for (int j = customerCodeList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("CustomerCode", customerCodeList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //customername
        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getCustomerName();
            if (rcnList.get(i).getCustomerName() != null) {
                customerNameList.add(customername);
            }

        }

        for (int j = customerNameList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("CustomerName", customerNameList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //address

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getAddress();
            if (rcnList.get(i).getAddress() != null) {
                addressList.add(customername);
            }

        }

        for (int j = addressList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("CustomerAddress", addressList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Pincode

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getPincode();
            if (rcnList.get(i).getPincode() != null) {
                pincodeList.add(customername);
            }

        }

        for (int j = pincodeList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("Pincode", pincodeList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //CustomerMobile

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getCustomerMobile();
            if (rcnList.get(i).getCustomerMobile() != null) {
                cusMobList.add(customername);
            }

        }

        for (int j = cusMobList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("CustomerMobile", cusMobList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //CustomerEmail

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getCustomerEmail();
            if (rcnList.get(i).getCustomerEmail() != null) {
                cusEmailList.add(customername);
            }

        }

        for (int j = cusEmailList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("CustomerEmail", cusEmailList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //ModelCode

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getModelcode();
            if (rcnList.get(i).getModelcode() != null) {
                modelcodeList.add(customername);
            }

        }

        for (int j = modelcodeList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("ModelCode", modelcodeList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //DOP
        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getDop();
            if (rcnList.get(i).getDop() != null) {
                dopList.add(customername);
            }

        }

        for (int j = dopList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("DOP", dopList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //DOI

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getDoi();
            if (rcnList.get(i).getDoi() != null) {
                doiList.add(customername);
            }

        }

        for (int j = doiList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("DOI", doiList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //CallType

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getCallType();
            if (rcnList.get(i).getCallType() != null) {
                calltypeList.add(customername);
            }

        }

        for (int j = calltypeList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("CallType", calltypeList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //StatusCode

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getStatusCode();
            if (rcnList.get(i).getStatusCode() != null) {
                statuscodeList.add(customername);
            }

        }

        for (int j = statuscodeList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("StatusCode", statuscodeList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //CancelledReason
        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getCancelledReason();
            if (rcnList.get(i).getCancelledReason() != null) {
                cancelledListList.add(customername);
            }

        }

        for (int j = cancelledListList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("CancelledReason", cancelledListList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //CancelledReasonDescription

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getCancelledReasonDescription();
            if (rcnList.get(i).getCancelledReasonDescription() != null) {
                cancelledDescListList.add(customername);
            }

        }

        for (int j = cancelledDescListList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("CancelledReasonDescription", cancelledDescListList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Branch

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getBranch();
            if (rcnList.get(i).getBranch() != null) {
                branchList.add(customername);
            }

        }

        for (int j = branchList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("Branch", branchList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //BranchName

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getBranchName();
            if (rcnList.get(i).getBranchName() != null) {
                branchNameList.add(customername);
            }

        }

        for (int j = branchNameList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("BranchName", branchNameList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //CallBookDate

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getCallBookDate();
            if (rcnList.get(i).getCallBookDate() != null) {
                callBookList.add(customername);
            }

        }

        for (int j = callBookList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("CallBookDate", callBookList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //CallClosedDate

        for (int i = 0; i < rcnList.size(); i++) {
            String customername = rcnList.get(i).getCallClosedDate();
            if (rcnList.get(i).getCallClosedDate() != null) {
                callclosedList.add(customername);
            }

        }

        for (int j = callclosedList.size() - 1; j >= 0; j--) {
            try {
                innerObj.put("CallClosedDate", callclosedList.get(j));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        jsonArray.put(innerObj);
        try {
            outerObject.put("CRMData", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        postTicketNo(outerObject.toString());
    }

    private void postTicketNo(String CRMData) {

        final ProgressDialog pd = new ProgressDialog(SalesManageActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();


        AndroidNetworking.upload(AppController.APIURL+"api/post_CRMDummyReferenceTicket_V1")
                .addMultipartParameter("CRMData", CRMData)
                .addMultipartParameter("UserID", prefManager.getUserId())
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
                        boolean responseStatus = job1.optBoolean("responseStatus");
                        if (responseStatus) {
                            successAlert(sucessText);
                            pd.dismiss();

                        } else {
                            pd.dismiss();
                            Toast.makeText(SalesManageActivity.this, responseText, Toast.LENGTH_LONG).show();

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

    private void setSalesType() {
        String surl = AppController.APIURL+"api/CommonDDL?ModuleNo=SISY&ID=0&ID1=0&ID2=0&ID3=0&SecurityCode=" + prefManager.getSecurityCode();
        Log.d("modelinput", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseModel", response);
                        progressBar.dismiss();
                        salestype.add("Please Select");
                        moduleSalesType.add(new SpinnerItemModule("0", "0"));


                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.getJSONObject(i);
                                    String value = obj.optString("value");
                                    String id = obj.optString("id");
                                    salestype.add(value);
                                    SpinnerItemModule itemModule = new SpinnerItemModule(value, id);
                                    moduleSalesType.add(itemModule);

                                }
                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                        (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                                                salestype); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spSalesType.setAdapter(spinnerArrayAdapter);


                            } else {


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                //   Toast.makeText(DocumentManageActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.d("errort", "model");
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }

    private void setInstallation() {
        String surl = AppController.APIURL+"api/CommonDDL?ModuleNo=717&ID="+prefManager.getSalesPartyCode()+"&ID1=0&ID2=0&ID3=0&SecurityCode=" + prefManager.getSecurityCode();
        Log.d("modelinput", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseModel", response);
                        progressBar.dismiss();
                        installation.add("Please Select");
                        moduleInstallation.add(new SpinnerItemModule("0", "0"));


                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.getJSONObject(i);
                                    String value = obj.optString("value");
                                    String id = obj.optString("id");
                                    installation.add(value);
                                    SpinnerItemModule itemModule = new SpinnerItemModule(value, id);
                                    moduleInstallation.add(itemModule);

                                }
                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                        (SalesManageActivity.this, android.R.layout.simple_spinner_item,
                                                installation); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spInstallation.setAdapter(spinnerArrayAdapter);


                            } else {


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                //   Toast.makeText(DocumentManageActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.d("errort", "model");
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }

    private void setDP(String modelID) {
        String surl = AppController.APIURL+"api/CommonDDL?ModuleNo=MDPR&ID=" + modelID + "&ID1=0&ID2=0&ID3=0&SecurityCode=" + prefManager.getSecurityCode();
        Log.d("modelinput", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseModel", response);
                        progressBar.dismiss();


                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                //Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();
                                JSONArray responseData = job1.optJSONArray("responseData");

                                JSONObject obj = responseData.getJSONObject(0);

                                String MRP = obj.optString("MRP");
                                tvDP.setText(MRP);
                                lnDP.setVisibility(View.VISIBLE);


                            } else {


                            }

                            // boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                //   Toast.makeText(DocumentManageActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.d("errort", "model");
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);

    }


    private void checkSerialNumberForIDU(final String serialNumber) {

        final ProgressDialog pd = new ProgressDialog(SalesManageActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("SerialNumber", serialNumber.replaceAll(",", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("https://crm.ifbsupport.com/technician/api/csr/serialNumberFinder")

                .addJSONObjectBody(jsonObject)
                .addHeaders("Authorization", "Bearer Q1NSVVNFUjpjc3JVc2Vy")
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()

                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        JSONObject job1 = response;
                        Log.e("response12", "@@@@@@" + job1);
                        boolean status = job1.optBoolean("status");
                        pd.dismiss();
                        serialNumberList.clear();

                        if (status) {

                            JSONArray product = response.optJSONArray("product");
                            JSONObject productOBJ = product.optJSONObject(0);
                            String ModelCode = productOBJ.optString("ModelCode");
                            String MatlGroup = productOBJ.optString("MatlGroup");


                            if (MatlGroup.equalsIgnoreCase("IDU") || MatlGroup.equalsIgnoreCase("AC")) {


                                postSale(serialNumber, odunumber);

                            } else {

                                Toast.makeText(SalesManageActivity.this, "Please enter correct IDU Number", Toast.LENGTH_LONG).show();


                            }


                        } else {
                            Toast.makeText(SalesManageActivity.this, "Please enter correct Serial Number.", Toast.LENGTH_LONG).show();


                        }


                        // boolean _status = job1.getBoolean("status");


                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        serialNumberList.clear();
                        Toast.makeText(SalesManageActivity.this, "Please enter correct Serial Number.", Toast.LENGTH_LONG).show();

                        // getInformationFromToken();
                    }
                });
    }


    private void checkSerialNumberForIDUForImage(final String serialNumber) {

        final ProgressDialog pd = new ProgressDialog(SalesManageActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("SerialNumber", serialNumber.replaceAll(",", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("https://crm.ifbsupport.com/technician/api/csr/serialNumberFinder")

                .addJSONObjectBody(jsonObject)
                .addHeaders("Authorization", "Bearer Q1NSVVNFUjpjc3JVc2Vy")
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()

                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        JSONObject job1 = response;
                        Log.e("response12", "@@@@@@" + job1);
                        boolean status = job1.optBoolean("status");
                        pd.dismiss();

                        if (status) {

                            JSONArray product = response.optJSONArray("product");
                            JSONObject productOBJ = product.optJSONObject(0);
                            String ModelCode = productOBJ.optString("ModelCode");
                            String MatlGroup = productOBJ.optString("MatlGroup");


                            if (MatlGroup.equalsIgnoreCase("IDU") || MatlGroup.equalsIgnoreCase("AC")) {


                                postSaleWithImage(serialNumber, odunumber);

                            } else {

                                Toast.makeText(SalesManageActivity.this, "Please enter correct IDU Number", Toast.LENGTH_LONG).show();


                            }


                        } else {
                            Toast.makeText(SalesManageActivity.this, "Please enter correct Serial Number.", Toast.LENGTH_LONG).show();


                        }


                        // boolean _status = job1.getBoolean("status");


                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        Toast.makeText(SalesManageActivity.this, "Please enter correct Serial Number.", Toast.LENGTH_LONG).show();

                        // getInformationFromToken();
                    }
                });
    }

    private void instalationChecking() {
        if (categoryId.equals("IFBPC1000001")) {
            if (!installationBY.equals("")) {
                if (schemeFlag == 1) {
                    if (!schemeId.equals("0")) {
                        if (!salesType.equals("")) {
                            if (etQuantity.getText().toString().equals("2") || etQuantity.getText().toString().equals("3") || etQuantity.getText().toString().equals("4") || etQuantity.getText().toString().equals("5")) {


                                quatityalert();
                            } else {
                                emailcheck1();
                            }
                        } else {
                            Toast.makeText(SalesManageActivity.this, "Please Select Sales Type", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        Toast.makeText(SalesManageActivity.this, "Please Select Financial Scheme", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (etQuantity.getText().toString().equals("2") || etQuantity.getText().toString().equals("3") || etQuantity.getText().toString().equals("4") || etQuantity.getText().toString().equals("5")) {


                        quatityalert();
                    } else {
                        emailcheck1();
                    }
                }
            } else {
                 Toast.makeText(SalesManageActivity.this,"Please Select Installation By",Toast.LENGTH_LONG).show();
            }

        } else {
            if (schemeFlag == 1) {
                if (!schemeId.equals("0")) {
                    if (!salesType.equals("")) {
                        if (etQuantity.getText().toString().equals("2") || etQuantity.getText().toString().equals("3") || etQuantity.getText().toString().equals("4") || etQuantity.getText().toString().equals("5")) {


                            quatityalert();
                        } else {
                            emailcheck1();
                        }
                    } else {
                        Toast.makeText(SalesManageActivity.this, "Please Select Sales Type", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(SalesManageActivity.this, "Please Select Financial Scheme", Toast.LENGTH_LONG).show();
                }
            } else {
                if (etQuantity.getText().toString().equals("2") || etQuantity.getText().toString().equals("3") || etQuantity.getText().toString().equals("4") || etQuantity.getText().toString().equals("5")) {


                    quatityalert();
                } else {
                    emailcheck1();
                }
            }
        }
    }

    private void getCusDetail(String contactNumber) {

        String surl = "https://crmapi.ifbsupport.com/api/v1/customers/search?contact=" + contactNumber;
        Log.d("emailcheck", surl);
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseCategory", response);
                        progressBar.dismiss();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Boolean Status = job1.optBoolean("Status");

                            JSONArray Data = job1.optJSONArray("Data");
                            if (Data != null) {
                                if (Data.length() > 0) {
                                    JSONObject jobj = Data.getJSONObject(0);
                                    String emailID = jobj.optString("zzemail");
                                    // etEmailId.setText(emailID);
                                    String postalCode = jobj.optString("zzpost_code1");



                                    String area = jobj.optString("zzstreet");


                                    String street = jobj.optString("zzstr_suppl1");




                                    String landMark = jobj.optString("zzstr_suppl3");


                                    String city1 = jobj.optString("city1");


                                    String addressTwo = jobj.optString("zzstr_suppl2");



                                    String House_num1 = jobj.optString("House_num1");


                                    existingAddressAlert(addressTwo,city1,postalCode,area,street,landMark,House_num1);



                                }else {
                                    AddressUpdateFlag="Y";
                                }
                            }


                            //boolean _status = job1.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("errort", e.toString());
                            Toast.makeText(SalesManageActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                Toast.makeText(SalesManageActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer ZW55dXNlcjplbnl1JGVy");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SalesManageActivity.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


    private void existingAddressAlert( String addressTwo,String city,String pincode,String area,String streetName,String landMark,String House_num1) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SalesManageActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_existing_address, null);
        dialogBuilder.setView(dialogView);

       /* TextView tvState = (TextView) dialogView.findViewById(R.id.tvState);
        tvState.setText(state);*/

        TextView tvCity = (TextView) dialogView.findViewById(R.id.tvCity);
        tvCity.setText(city);

        TextView tvArea = (TextView) dialogView.findViewById(R.id.tvArea);
        tvArea.setText(area);

        TextView tvPincode = (TextView) dialogView.findViewById(R.id.tvPincode);
        tvPincode.setText(pincode);



        TextView tvAddressTwo = (TextView) dialogView.findViewById(R.id.tvAddressTwo);
        tvAddressTwo.setText(addressTwo);

        TextView tvStreetName = (TextView) dialogView.findViewById(R.id.tvStreetName);
        tvStreetName.setText(streetName);

        TextView tvLandMark = (TextView) dialogView.findViewById(R.id.tvLandMark);
        tvLandMark.setText(landMark);

        Button btnContinue=(Button)dialogView.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etPinCode.setText(pincode);
                etPinCode.setEnabled(false);
                crmArea=area;
                etStreetName.setText(streetName);
                etStreetName.setEnabled(false);
                etLandMark.setText(landMark);
                REGIONNAME=city;
                tvCityName.setText(REGIONNAME);
                etHouse.setText(House_num1);
                etHouse.setEnabled(false);
                etMoreAddress.setText(addressTwo);
                pincodecheck(pincode);
                existingAddressFlag.dismiss();
                AddressUpdateFlag="N";
            }
        });

        Button btnChange=(Button)dialogView.findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                existingAddressFlag.dismiss();
                AddressUpdateFlag="Y";
                etPinCode.setText(pincode);
                etStreetName.setText(streetName);
                etLandMark.setText(landMark);
                REGIONNAME=city;
                tvCityName.setText(REGIONNAME);
                etHouse.setText(House_num1);
                etMoreAddress.setText(addressTwo);
                crmArea=area;
                pincodecheck(pincode);
            }
        });




        existingAddressFlag = dialogBuilder.create();
        existingAddressFlag.setCancelable(false);
        Window window = existingAddressFlag.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        existingAddressFlag.show();
    }


    private void addressConfirmation( String addressTwo,String city,String pincode,String area,String streetName,String landMark,String House_num1) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SalesManageActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_address_confirmation, null);
        dialogBuilder.setView(dialogView);

       /* TextView tvState = (TextView) dialogView.findViewById(R.id.tvState);
        tvState.setText(state);*/

        TextView tvCity = (TextView) dialogView.findViewById(R.id.tvCity);
        tvCity.setText(city);

        TextView tvArea = (TextView) dialogView.findViewById(R.id.tvArea);
        tvArea.setText(area);

        TextView tvPincode = (TextView) dialogView.findViewById(R.id.tvPincode);
        tvPincode.setText(pincode);


        TextView tvHouseNo = (TextView) dialogView.findViewById(R.id.tvHouseNo);
        tvHouseNo.setText(House_num1);



        TextView tvAddressTwo = (TextView) dialogView.findViewById(R.id.tvAddressTwo);
        tvAddressTwo.setText(addressTwo);

        TextView tvStreetName = (TextView) dialogView.findViewById(R.id.tvStreetName);
        tvStreetName.setText(streetName);

        TextView tvLandMark = (TextView) dialogView.findViewById(R.id.tvLandMark);
        tvLandMark.setText(landMark);

        Button btnContinue=(Button)dialogView.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                existingAddressFlag.dismiss();
                ssaleFunction();
            }
        });

        Button btnChange=(Button)dialogView.findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                existingAddressFlag.dismiss();
                etPinCode.setEnabled(true);
                etStreetName.setEnabled(true);
                etHouse.setEnabled(true);
                AddressUpdateFlag="Y";


            }
        });




        existingAddressFlag = dialogBuilder.create();
        existingAddressFlag.setCancelable(false);
        Window window = existingAddressFlag.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        existingAddressFlag.show();
    }


}
