package com.genius.ifbretailer.activity;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.text.TextWatcher;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
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
import com.genius.ifbretailer.utility.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;




public class DisplayMatrixDynamicActivity extends AppCompatActivity {
    EditText etAirIFB, etAirLG, etAirSamSung, etAirDaikin, etCarrier, etAirBlueStar, etAirVoltas, etAirOnida, etAirPanaSonic, etAirWhirlPool, etAirOGenaral, etAirGodrej, etAirHaier, etAirLloyds, etAirOthers;
    //airconditoner
    //daikin
    String airDaikin = "IFBPC1000001" + "-" + "IFBCC000009" + "#" + "0";
    //ifb
    String airIfb = "IFBPC1000001" + "-" + "IFBCC000015" + "#" + "0";
    //LG
    String airLg = "IFBPC1000001" + "-" + "IFBCC000001" + "#" + "0";
    //LLYODS
    String airLloyds = "IFBPC1000001" + "-" + "IFBCC000010" + "#" + "0";
    //OTHERS
    String airOthers = "IFBPC1000001" + "-" + "IFBCC000004" + "#" + "0";
    //VOLTAS
    String airVoltas = "IFBPC1000001" + "-" + "IFBCC000008" + "#" + "0";
    //
    String airSAMSUNG = "IFBPC1000001" + "-" + "IFBCC000002" + "#" + "0";
    //CARRIER
    String airCARRIER = "IFBPC1000001" + "-" + "IFBCC000018" + "#" + "0";
    //BLUESTAR
    String airBLUESTAR = "IFBPC1000001" + "-" + "IFBCC000019" + "#" + "0";
    //ONIDA
    String airONIDA = "IFBPC1000001" + "-" + "IFBCC000017" + "#" + "0";
    //PANASONIC
    String airPANASONIC = "IFBPC1000001" + "-" + "IFBCC000007" + "#" + "0";
    //WHIRLPOOL
    String airWHIRLPOOL = "IFBPC1000001" + "-" + "IFBCC000005" + "#" + "0";
    //OGENERAL
    String airOGENERAL = "IFBPC1000001" + "-" + "IFBCC000020" + "#" + "0";
    //GODREJ
    String airGODREJ = "IFBPC1000001" + "-" + "IFBCC000006" + "#" + "0";
    //HAIER
    String airHAIER = "IFBPC1000001" + "-" + "IFBCC000021" + "#" + "0";

    //cloths
    EditText etClothsIFB, etClothsBosch;

    String clothsIFB = "IFBPC1000005" + "-" + "IFBCC000015" + "#" + "0";
    String clothsBOSCH = "IFBPC1000005" + "-" + "IFBCC000003" + "#" + "0";

    //dishwasher

    EditText etDishIFB, etDishBosch, etDishLg, etDishSamsung, etDishOther;

    String dishIfb = "IFBPC1000007" + "-" + "IFBCC000015" + "#" + "0";
    String dishBosch = "IFBPC1000007" + "-" + "IFBCC000003" + "#" + "0";
    String dishLg = "IFBPC1000007" + "-" + "IFBCC000001" + "#" + "0";
    String dishSamsung = "IFBPC1000007" + "-" + "IFBCC000002" + "#" + "0";
    String dishOthers = "IFBPC1000007" + "-" + "IFBCC000004" + "#" + "0";

    //MICROOVEN
    EditText etMicroIfb, etMicroLg, etMicroSamsung, etMicroWhirlPool, etMicroPanasonic, etMicroGodrej, etMicroOnida, etMicroOthers;

    String microIfb = "IFBPC1000011" + "-" + "IFBCC000015" + "#" + "0";
    String microLg = "IFBPC1000011" + "-" + "IFBCC000001" + "#" + "0";
    String microSamSung = "IFBPC1000011" + "-" + "IFBCC000002" + "#" + "0";
    String microWhirlPool = "IFBPC1000011" + "-" + "IFBCC000005" + "#" + "0";
    String microPanasonic = "IFBPC1000011" + "-" + "IFBCC000007" + "#" + "0";
    String microGodrej = "IFBPC1000011" + "-" + "IFBCC000006" + "#" + "0";
    String microOnida = "IFBPC1000011" + "-" + "IFBCC000017" + "#" + "0";
    String microOthers = "IFBPC1000011" + "-" + "IFBCC000004" + "#" + "0";

    //KA
    EditText etKAIfb, etKAFaber, etKASunFlame, etKAElica, etKAKaff, etKABosch, etKAOthers;
    String kaIfb = "IFBPC1000035" + "-" + "IFBCC000015" + "#" + "0";
    String KaFaber = "IFBPC1000035" + "-" + "IFBCC000013" + "#" + "0";
    String KaSunFlame = "IFBPC1000035" + "-" + "IFBCC000022" + "#" + "0";
    String KaElica = "IFBPC1000035" + "-" + "IFBCC000014" + "#" + "0";
    String KaKaff = "IFBPC1000035" + "-" + "IFBCC000012" + "#" + "0";
    String KaBosch = "IFBPC1000035" + "-" + "IFBCC000003" + "#" + "0";
    String KaOthers = "IFBPC1000035" + "-" + "IFBCC000004" + "#" + "0";

    //FLU
    EditText etFLUIfb, etFLULg, etFLUSamsung, etFLUBosch, etFLUWhirlPool, etFLUBeko, etFLUOthers;

    String FLUIfb = "IFBPC1000021" + "-" + "IFBCC000015" + "#" + "0";
    String FLULg = "IFBPC1000021" + "-" + "IFBCC000001" + "#" + "0";
    String FLUSamsung = "IFBPC1000021" + "-" + "IFBCC000002" + "#" + "0";
    String FLUBosch = "IFBPC1000021" + "-" + "IFBCC000003" + "#" + "0";
    String FLUWhirlPool = "IFBPC1000021" + "-" + "IFBCC000005" + "#" + "0";
    String FLUBeko = "IFBPC1000021" + "-" + "IFBCC000024" + "#" + "0";
    String FLUOthers = "IFBPC1000021" + "-" + "IFBCC000004" + "#" + "0";

    //TL
    EditText etTLIfb, etTLLg, etTLSamsung, etTLBosch, etTLWhirlPool, etTLPanasonic, etTLGodrej, etTLOnida, etTLOthers;

    String TLIfb = "IFBPC1000025" + "-" + "IFBCC000015" + "#" + "0";
    String TLLg = "IFBPC1000025" + "-" + "IFBCC000001" + "#" + "0";
    String TLSamsung = "IFBPC1000025" + "-" + "IFBCC000002" + "#" + "0";
    String TLBosch = "IFBPC1000025" + "-" + "IFBCC000003" + "#" + "0";
    String TLWhirlPool = "IFBPC1000025" + "-" + "IFBCC000005" + "#" + "0";
    String TLPanasonic = "IFBPC1000025" + "-" + "IFBCC000007" + "#" + "0";
    String TLGodrej = "IFBPC1000025" + "-" + "IFBCC000006" + "#" + "0";
    String TLOnida = "IFBPC1000025" + "-" + "IFBCC000017" + "#" + "0";
    String TLOthers = "IFBPC1000025" + "-" + "IFBCC000004" + "#" + "0";



    //Waher Disher

    EditText etWasherDisherOthers,etWasherDisherOnida,etWasherDisherGodrej,etWasherDisherPanasonic,etWasherDisherWhirlPool,etWasherDisherSamsung,etWasherDisherLg,etWasherDisherIfb;
    TextView tvWasherDisherAdd;

    String washerIfb = "IFBPC1000039" + "-" + "IFBCC000015" + "#" + "0";
    String washerLg = "IFBPC1000039" + "-" + "IFBCC000001" + "#" + "0";
    String washerSamSung = "IFBPC1000039" + "-" + "IFBCC000002" + "#" + "0";
    String washerWhirlPool = "IFBPC1000039" + "-" + "IFBCC000005" + "#" + "0";
    String washerPanasonic = "IFBPC1000039" + "-" + "IFBCC000007" + "#" + "0";
    String washerGodrej = "IFBPC1000039" + "-" + "IFBCC000006" + "#" + "0";
    String washerOnida = "IFBPC1000039" + "-" + "IFBCC000017" + "#" + "0";
    String washerOthers = "IFBPC1000039" + "-" + "IFBCC000004" + "#" + "0";
    private static final String SERVER_PATH = "http://111.93.182.173/IFBiOSApi/api/";

    ProgressDialog progressDialog;
    String salesdate;
    String msg = "";
    String securitycode, userid;
    String formattedDate;

    String modelId = "";
    String category = airDaikin + "," + airIfb + "," + "," + airLg + "," + airLloyds + "," + airOthers + "," + airVoltas + "," + airSAMSUNG + "," + airCARRIER + "," + airBLUESTAR + "," + airONIDA + "," + airPANASONIC + "," + airWHIRLPOOL + "," + airOGENERAL + "," + airGODREJ + "," + airHAIER + "," + clothsIFB + "," + clothsBOSCH + "," + dishIfb + "," + dishBosch + "," + dishLg + "," + dishSamsung + "," + dishOthers + "," + microIfb + "," + microLg + "," + microSamSung + "," + microWhirlPool + "," + microPanasonic + "," + microGodrej + "," + microOnida + "," + microOthers + "," + kaIfb + "," + KaFaber + "," + KaSunFlame + "," + KaElica + "," + KaKaff + "," + KaBosch + "," + KaOthers + "," + FLUIfb + "," + FLULg + "," + FLUSamsung + "," + FLUBosch + "," + FLUWhirlPool + "," + FLUBeko + "," + FLUOthers + "," + TLIfb + "," + TLLg + "," + TLSamsung + "," + TLBosch + "," + TLWhirlPool + "," + TLPanasonic + "," + TLGodrej + "," + TLOnida + "," + TLOthers+","+washerIfb + "," + washerLg + "," + washerSamSung + "," + washerWhirlPool + "," + washerPanasonic + "," + washerGodrej + "," + washerOnida + "," + washerOthers ;
    String model = "0";
    PrefManager prefManager;

    TextView tvAirAdd, tvSave, tvClothsAdd, tvDishAdd, tvMicroAdd, tvKAAdd, tvFLUAdd, tvTLAdd;
    //ifbitemid
    String airItem = "0";
    String clothsItem = "0";
    String dishItem = "0";
    String microItem = "0";
    String KAItem = "0";
    String FLUItem = "0";
    String tlItem = "0";
    String washerDyerItem="0";



    String year, month;

    TextView tvDate;
    String showMonth, showYear;
    ImageView imgBack, imgHome;

    AlertDialog alerDialog1, alertDialog, alertDialog2,alertDialog3;

    String responseText, premonth, finalcialchecking;

    ArrayList<String> airConditionerModel = new ArrayList<>();
    ArrayList<String> clothsdryerModel = new ArrayList<>();
    ArrayList<String> dishwasherModel = new ArrayList<>();
    ArrayList<String> microOvenModel = new ArrayList<>();
    ArrayList<String> kitchenModel = new ArrayList<>();
    ArrayList<String> wmFluModel = new ArrayList<>();
    ArrayList<String> wmTLModel = new ArrayList<>();
    ArrayList<String> dryerModel = new ArrayList<>();

    ProgressDialog pd;
    ImageView imgPic1, imgPic2, imgPic3;
    private Uri imageUri, imageUri1, imageUri2;
    private static final int CAMERA_REQUEST = 1;
    private static final int CAMERA_REQUEST1 = 2;
    private static final int CAMERA_REQUEST2 = 3;
    File file, file1, file2;
    String encodedImage, encodedImage1, encodedImage2;
    String stringFile, stringFile1, stringFile2;
    int pic1Flag = 0;
    int pic2Flag = 0;
    int pic3Flag = 0;
    String acFlag;
    ArrayList<String> sendACModelList=new ArrayList<>();
    String previousMonthData="false";
    int y;
    ArrayList<String> modelArray=new ArrayList<>();
    RecyclerView rvAirModelItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_matrix_dynamic);
        init();
        displayMatrixChecking();
        onClick();

    }


    private void init() {


        prefManager = new PrefManager(getApplicationContext());
        sendACModelList.clear();
        prefManager.saveAirConditionerId("");
        prefManager.saveClothsDryerId("");
        prefManager.saveMicroOvenId("");
        prefManager.saveDishWasherId("");
        prefManager.SaveKAItemId("");
        prefManager.saveWasherDryerId("");
        prefManager.saveWashingFLUId("");
        prefManager.saveWashingTLId("");

         y = Calendar.getInstance().get(Calendar.YEAR);
        year = String.valueOf(y);
        Log.d("year", year);

        int m = Calendar.getInstance().get(Calendar.MONTH) + 1;
        Log.d("month", String.valueOf(m));
        if (m == 1) {
            month = "January";
        } else if (m == 2) {
            month = "February";
        } else if (m == 3) {
            month = "March";
        } else if (m == 4) {
            month = "April";
        } else if (m == 5) {
            month = "May";
        } else if (m == 6) {
            month = "June";
        } else if (m == 7) {
            month = "July";
        } else if (m == 8) {
            month = "August";
        } else if (m == 9) {
            month = "September";
        } else if (m == 10) {
            month = "October";
        } else if (m == 11) {
            month = "November";
        } else if (m == 12) {
            month = "December";
        }

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        securitycode = prefManager.getSecurityCode();
        userid = prefManager.getUserId();
        salesdate = formattedDate;

        //Air Conditioner
        etAirIFB = (EditText) findViewById(R.id.etAirIFB);
        etAirIFB.setEnabled(false);
        String size = String.valueOf(prefManager.getAirIfbSize());
        Log.d("size", size);
        etAirIFB.setText(size);

        etAirLG = (EditText) findViewById(R.id.etAirLG);
        etAirSamSung = (EditText) findViewById(R.id.etAirSamSung);
        etAirDaikin = (EditText) findViewById(R.id.etAirDaikin);
        etCarrier = (EditText) findViewById(R.id.etCarrier);
        etAirBlueStar = (EditText) findViewById(R.id.etAirBlueStar);
        etAirVoltas = (EditText) findViewById(R.id.etAirVoltas);
        etAirOnida = (EditText) findViewById(R.id.etAirOnida);
        etAirPanaSonic = (EditText) findViewById(R.id.etAirPanaSonic);
        etAirWhirlPool = (EditText) findViewById(R.id.etAirWhirlPool);
        etAirOGenaral = (EditText) findViewById(R.id.etAirOGenaral);
        etAirGodrej = (EditText) findViewById(R.id.etAirGodrej);
        etAirHaier = (EditText) findViewById(R.id.etAirHaier);
        etAirLloyds = (EditText) findViewById(R.id.etAirLloyds);
        etAirOthers = (EditText) findViewById(R.id.etAirOthers);

        tvAirAdd = (TextView) findViewById(R.id.tvAirAdd);
        tvSave = (TextView) findViewById(R.id.tvSave);
        tvClothsAdd = (TextView) findViewById(R.id.tvClothsAdd);
        tvDishAdd = (TextView) findViewById(R.id.tvDishAdd);
        tvMicroAdd = (TextView) findViewById(R.id.tvMicroAdd);
        tvKAAdd = (TextView) findViewById(R.id.tvKAAdd);
        tvFLUAdd = (TextView) findViewById(R.id.tvFLUAdd);
        tvTLAdd = (TextView) findViewById(R.id.tvTLAdd);


        airIfb = "IFBPC1000001" + "-" + "IFBCC000015" + "#" + prefManager.getAirIfbSize();
        clothsIFB = "IFBPC1000005" + "-" + "IFBCC000015" + "#" + prefManager.getClothsIfbSize();
        dishIfb = "IFBPC1000007" + "-" + "IFBCC000015" + "#" + prefManager.getDishIfbSize();
        microIfb = "IFBPC1000011" + "-" + "IFBCC000015" + "#" + prefManager.getMicroOvenIfbSize();
        kaIfb = "IFBPC1000035" + "-" + "IFBCC000015" + "#" + prefManager.getKAIfbSize();
        FLUIfb = "IFBPC1000021" + "-" + "IFBCC000015" + "#" + prefManager.getWMFLUIfbSize();
        TLIfb = "IFBPC1000025" + "-" + "IFBCC000015" + "#" + prefManager.getWMTLIFBSize();


        //clothes
        etClothsIFB = (EditText) findViewById(R.id.etClothsIFB);
        etClothsIFB.setEnabled(false);
        String clothsifbsize = String.valueOf(prefManager.getClothsIfbSize());
        Log.d("size", clothsifbsize);
        etClothsIFB.setText(clothsifbsize);

        etClothsBosch = (EditText) findViewById(R.id.etClothsBosch);

        //dishwasher
        etDishIFB = (EditText) findViewById(R.id.etDishIFB);
        String dishifbsize = String.valueOf(prefManager.getDishIfbSize());
        Log.d("size", clothsifbsize);
        etDishIFB.setText(dishifbsize);

        etDishBosch = (EditText) findViewById(R.id.etDishBosch);
        etDishLg = (EditText) findViewById(R.id.etDishLg);
        etDishSamsung = (EditText) findViewById(R.id.etDishSamsung);
        etDishOther = (EditText) findViewById(R.id.etDishOther);

        //micooven
        etMicroIfb = (EditText) findViewById(R.id.etMicroIfb);
        String microifbsize = String.valueOf(prefManager.getMicroOvenIfbSize());
        Log.d("size", microifbsize);
        etMicroIfb.setText(microifbsize);

        etMicroLg = (EditText) findViewById(R.id.etMicroLg);
        etMicroSamsung = (EditText) findViewById(R.id.etMicroSamsung);
        etMicroWhirlPool = (EditText) findViewById(R.id.etMicroWhirlPool);
        etMicroPanasonic = (EditText) findViewById(R.id.etMicroPanasonic);
        etMicroGodrej = (EditText) findViewById(R.id.etMicroGodrej);
        etMicroOnida = (EditText) findViewById(R.id.etMicroOnida);
        etMicroOthers = (EditText) findViewById(R.id.etMicroOthers);

        //KA
        etKAIfb = (EditText) findViewById(R.id.etKAIfb);
        String kaifbsize = String.valueOf(prefManager.getKAIfbSize());
        Log.d("size", kaifbsize);
        etKAIfb.setText(kaifbsize);

        etKAFaber = (EditText) findViewById(R.id.etKAFaber);
        etKASunFlame = (EditText) findViewById(R.id.etKASunFlame);
        etKAElica = (EditText) findViewById(R.id.etKAElica);
        etKAKaff = (EditText) findViewById(R.id.etKAKaff);
        etKABosch = (EditText) findViewById(R.id.etKABosch);
        etKAOthers = (EditText) findViewById(R.id.etKAOthers);


        //FLU
        etFLUIfb = (EditText) findViewById(R.id.etFLUIfb);
        String FLUIfbSize = String.valueOf(prefManager.getWMFLUIfbSize());
        Log.d("size", FLUIfbSize);
        etFLUIfb.setText(FLUIfbSize);


        etFLULg = (EditText) findViewById(R.id.etFLULg);
        etFLUSamsung = (EditText) findViewById(R.id.etFLUSamsung);
        etFLUBosch = (EditText) findViewById(R.id.etFLUBosch);
        etFLUWhirlPool = (EditText) findViewById(R.id.etFLUWhirlPool);
        etFLUBeko = (EditText) findViewById(R.id.etFLUBeko);
        etFLUOthers = (EditText) findViewById(R.id.etFLUOthers);

        //TL

        etTLIfb = (EditText) findViewById(R.id.etTLIfb);
        String tlIfbSize = String.valueOf(prefManager.getWMTLIFBSize());
        Log.d("size", tlIfbSize);
        etTLIfb.setText(tlIfbSize);
        etTLLg = (EditText) findViewById(R.id.etTLLg);
        etTLSamsung = (EditText) findViewById(R.id.etTLSamsung);
        etTLBosch = (EditText) findViewById(R.id.etTLBosch);
        etTLWhirlPool = (EditText) findViewById(R.id.etTLWhirlPool);
        etTLPanasonic = (EditText) findViewById(R.id.etTLPanasonic);
        etTLGodrej = (EditText) findViewById(R.id.etTLGodrej);
        etTLOnida = (EditText) findViewById(R.id.etTLOnida);
        etTLOthers = (EditText) findViewById(R.id.etTLOthers);

        //Washer Disher

        etWasherDisherIfb = (EditText) findViewById(R.id.etWasherDisherIfb);
        String washerdryerifbsize = String.valueOf(prefManager.getWasherDryerIfbSize());
        Log.d("washerdryerifbsize",washerdryerifbsize);
        etWasherDisherIfb.setText(washerdryerifbsize);

        etWasherDisherLg = (EditText) findViewById(R.id.etWasherDisherLg);
        etWasherDisherSamsung = (EditText) findViewById(R.id.etWasherDisherSamsung);
        etWasherDisherWhirlPool = (EditText) findViewById(R.id.etWasherDisherWhirlPool);
        etWasherDisherPanasonic = (EditText) findViewById(R.id.etWasherDisherPanasonic);
        etWasherDisherGodrej = (EditText) findViewById(R.id.etWasherDisherGodrej);
        etWasherDisherOnida = (EditText) findViewById(R.id.etWasherDisherOnida);
        etWasherDisherOthers = (EditText) findViewById(R.id.etWasherDisherOthers);

        if (!prefManager.getAirConditionerId().equals("")) {
            airItem = prefManager.getAirConditionerId();

        } else {
            airItem = "0";

        }

        if (!prefManager.getClothsDryerId().equals("")) {
            clothsItem = prefManager.getClothsDryerId();
        } else {
            clothsItem = "0";
        }

        if (!prefManager.getDishWasherId().equals("")) {
            dishItem = prefManager.getDishWasherId();
        } else {
            dishItem = "0";
        }

        if (!prefManager.getMicroOvenId().equals("")) {
            microItem = prefManager.getMicroOvenId();
        } else {
            microItem = "0";
        }


        if (!prefManager.getKAItemId().equals("")) {
            KAItem = prefManager.getKAItemId();
        } else {
            KAItem = "0";
        }

        if (!prefManager.getWashingFLUId().equals("")) {
            FLUItem = prefManager.getWashingFLUId();
        } else {
            FLUItem = "0";
        }

        if (!prefManager.getWashingTLId().equals("")) {
            tlItem = prefManager.getWashingTLId();
        } else {
            tlItem = "0";
        }

        if (!prefManager.getWasherDryerId().equals("")) {
            washerDyerItem = prefManager.getWasherDryerId();
        } else {
            washerDyerItem = "0";
        }


        model = airItem + "," + clothsItem + "," + dishItem + "," + microItem + "," + KAItem + "," + FLUItem + "," + tlItem+","+washerDyerItem;
        modelId = model.replaceAll("\\s+", "");


        tvDate = (TextView) findViewById(R.id.tvDate);
        tvWasherDisherAdd = (TextView) findViewById(R.id.tvWasherDisherAdd);

        if (month.equals("January")) {
            showYear = String.valueOf(y - 1);
            showMonth = "January" + "-" + year;
        } else if (month.equals("February")) {
            showMonth = "February" + "-" + year;

        } else if (month.equals("March")) {
            showMonth = "March" + "-" + year;

        } else if (month.equals("April")) {
            showMonth = "April" + "-" + year;

        } else if (month.equals("May")) {
            showMonth = "May" + "-" + year;

        } else if (month.equals("June")) {
            showMonth = "June" + "-" + year;

        } else if (month.equals("July")) {
            showMonth = "July" + "-" + year;

        } else if (month.equals("August")) {
            showMonth = "August" + "-" + year;

        } else if (month.equals("September")) {
            showMonth = "September" + "-" + year;

        } else if (month.equals("October")) {
            showMonth = "October" + "-" + year;

        } else if (month.equals("November")) {
            showMonth = "November" + "-" + year;

        } else if (month.equals("December")) {
            showMonth = "December" + "-" + year;

        }

        tvDate.setText("For the" + " " + showMonth);

        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgHome = (ImageView) findViewById(R.id.imgHome);


        if (month.equals("January")) {
            showYear = String.valueOf(y - 1);
            premonth = "December";
        } else if (month.equals("February")) {
            premonth = "January";

        } else if (month.equals("March")) {
            premonth = "February";

        } else if (month.equals("April")) {
            premonth = "March";

        } else if (month.equals("May")) {
            premonth = "April";

        } else if (month.equals("June")) {
            premonth = "May";

        } else if (month.equals("July")) {
            premonth = "June";

        } else if (month.equals("August")) {
            premonth = "July";

        } else if (month.equals("September")) {
            premonth = "August";

        } else if (month.equals("October")) {
            premonth = "September";

        } else if (month.equals("November")) {
            premonth = "October";
        } else if (month.equals("December")) {
            premonth = "November";

        }


        if (month.equals("January")) {
            int futureyear = y - 1;
            finalcialchecking = futureyear + "-" + year;
        } else if (month.equals("February")) {
            int futureyear = y - 1;
            finalcialchecking = futureyear + "-" + year;
        } else if (month.equals("March")) {
            int futureyear = y - 1;
            finalcialchecking = futureyear + "-" + year;
        } else {
            int futureyear = y + 1;
            finalcialchecking = year + "-" + futureyear;
        }
        Log.d("finalcialchecking",finalcialchecking);

        pd = new ProgressDialog(DisplayMatrixDynamicActivity.this);
        pd.setMessage("Loading");
        pd.setCancelable(false);




    }

    private void onClick() {

        tvAirAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, AirConditionerDialogActivity.class);
                intent.putStringArrayListExtra("sendAcModel",sendACModelList);
                intent.putExtra("previousmonthStatus",previousMonthData);
                startActivity(intent);


            }
        });

        tvClothsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, ClothsDryerDialogActivity.class);
                intent.putExtra("previousmonthStatus",previousMonthData);
                startActivity(intent);

            }
        });
        tvWasherDisherAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, WasherDryerDialogActivity.class);
                intent.putExtra("previousmonthStatus",previousMonthData);
                startActivity(intent);

            }
        });
        tvDishAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, DishwasherDialogActivity.class);
                intent.putExtra("previousmonthStatus",previousMonthData);
                startActivity(intent);
            }
        });
        tvMicroAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, MicroOvenDialogActivity.class);
                intent.putExtra("previousmonthStatus",previousMonthData);
                startActivity(intent);
            }
        });

        tvKAAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, KADialogActivity.class);
                intent.putExtra("previousmonthStatus",previousMonthData);
                startActivity(intent);
            }
        });

        tvFLUAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, WMFLUDialogActivity.class);
                intent.putExtra("previousmonthStatus",previousMonthData);
                startActivity(intent);
            }
        });

        tvTLAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, WMTLDialogActivity.class);
                intent.putExtra("previousmonthStatus",previousMonthData);
                startActivity(intent);
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
                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });


        etAirLG.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirLG.getText().toString().length() > 0) {
                    prefManager.saveAirLG(etAirLG.getText().toString());
                    airLg = "IFBPC1000001" + "-" + "IFBCC000001" + "#" + etAirLG.getText().toString();
                    category = airDaikin + "," + airIfb + "," + "," + airLg + "," + airLloyds + "," + airOthers + "," + airVoltas + "," + airSAMSUNG + "," + airCARRIER + "," + airBLUESTAR + "," + airONIDA + "," + airPANASONIC + "," + airWHIRLPOOL + "," + airOGENERAL + "," + airGODREJ + "," + airHAIER + "," + clothsIFB + "," + clothsBOSCH + "," + dishIfb + "," + dishBosch + "," + dishLg + "," + dishSamsung + "," + dishOthers + "," + microIfb + "," + microLg + "," + microSamSung + "," + microWhirlPool + "," + microPanasonic + "," + microGodrej + "," + microOnida + "," + microOthers + "," + kaIfb + "," + KaFaber + "," + KaSunFlame + "," + KaElica + "," + KaKaff + "," + KaBosch + "," + KaOthers;
                    Log.d("categoryyy", category);
                }

            }
        });

        etAirSamSung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirSamSung.getText().toString().length() > 0) {

                    airSAMSUNG = "IFBPC1000001" + "-" + "IFBCC000002" + "#" + etAirSamSung.getText().toString();
                    category = airDaikin + "," + airIfb + "," + "," + airLg + "," + airLloyds + "," + airOthers + "," + airVoltas + "," + airSAMSUNG + "," + airCARRIER + "," + airBLUESTAR + "," + airONIDA + "," + airPANASONIC + "," + airWHIRLPOOL + "," + airOGENERAL + "," + airGODREJ + "," + airHAIER + "," + clothsIFB + "," + clothsBOSCH + "," + dishIfb + "," + dishBosch + "," + dishLg + "," + dishSamsung + "," + dishOthers + "," + microIfb + "," + microLg + "," + microSamSung + "," + microWhirlPool + "," + microPanasonic + "," + microGodrej + "," + microOnida + "," + microOthers + "," + kaIfb + "," + KaFaber + "," + KaSunFlame + "," + KaElica + "," + KaKaff + "," + KaBosch + "," + KaOthers;
                    Log.d("categoryyy", category);
                }

            }
        });

        etAirDaikin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirDaikin.getText().toString().length() > 0) {
                    airDaikin = "IFBPC1000001" + "-" + "IFBCC000009" + "#" + etAirDaikin.getText().toString();
                    category = airDaikin + "," + airIfb + "," + "," + airLg + "," + airLloyds + "," + airOthers + "," + airVoltas + "," + airSAMSUNG + "," + airCARRIER + "," + airBLUESTAR + "," + airONIDA + "," + airPANASONIC + "," + airWHIRLPOOL + "," + airOGENERAL + "," + airGODREJ + "," + airHAIER + "," + clothsIFB + "," + clothsBOSCH + "," + dishIfb + "," + dishBosch + "," + dishLg + "," + dishSamsung + "," + dishOthers + "," + microIfb + "," + microLg + "," + microSamSung + "," + microWhirlPool + "," + microPanasonic + "," + microGodrej + "," + microOnida + "," + microOthers + "," + kaIfb + "," + KaFaber + "," + KaSunFlame + "," + KaElica + "," + KaKaff + "," + KaBosch + "," + KaOthers;
                }

            }
        });

        etCarrier.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etCarrier.getText().toString().length() > 0) {
                    airCARRIER = "IFBPC1000001" + "-" + "IFBCC000018" + "#" + etCarrier.getText().toString();
                }

            }
        });

        etAirBlueStar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirBlueStar.getText().toString().length() > 0) {
                    airBLUESTAR = "IFBPC1000001" + "-" + "IFBCC000019" + "#" + etAirBlueStar.getText().toString();
                }

            }
        });
        etAirVoltas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirVoltas.getText().toString().length() > 0) {
                    airVoltas = "IFBPC1000001" + "-" + "IFBCC000008" + "#" + etAirVoltas.getText().toString();
                }

            }
        });

        etAirOnida.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirOnida.getText().toString().length() > 0) {
                    airONIDA = "IFBPC1000001" + "-" + "IFBCC000017" + "#" + etAirOnida.getText().toString();

                }

            }
        });

        etAirPanaSonic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirPanaSonic.getText().toString().length() > 0) {
                    airPANASONIC = "IFBPC1000001" + "-" + "IFBCC000007" + "#" + etAirPanaSonic.getText().toString();
                }

            }
        });

        etAirWhirlPool.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirWhirlPool.getText().toString().length() > 0) {
                    airWHIRLPOOL = "IFBPC1000001" + "-" + "IFBCC000005" + "#" + etAirWhirlPool.getText().toString();
                }

            }
        });

        etAirOGenaral.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirOGenaral.getText().toString().length() > 0) {
                    airOGENERAL = "IFBPC1000001" + "-" + "IFBCC000020" + "#" + etAirOGenaral.getText().toString();

                }

            }
        });

        etAirGodrej.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirGodrej.getText().toString().length() > 0) {
                    airGODREJ = "IFBPC1000001" + "-" + "IFBCC000006" + "#" + etAirGodrej.getText().toString();
                }

            }
        });

        etAirHaier.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirHaier.getText().toString().length() > 0) {
                    airHAIER = "IFBPC1000001" + "-" + "IFBCC000021" + "#" + etAirHaier.getText().toString();
                }

            }
        });

        etAirLloyds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirLloyds.getText().toString().length() > 0) {
                    airLloyds = "IFBPC1000001" + "-" + "IFBCC000010" + "#" + etAirLloyds.getText().toString();
                }

            }
        });

        etAirOthers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAirOthers.getText().toString().length() > 0) {
                    airOthers = "IFBPC1000001" + "-" + "IFBCC000004" + "#" + etAirOthers.getText().toString();
                }

            }
        });

        //cloths
        etClothsBosch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etClothsBosch.getText().toString().length() > 0) {
                    clothsBOSCH = "IFBPC1000005" + "-" + "IFBCC000003" + "#" + etClothsBosch.getText().toString();
                }

            }
        });


        etDishBosch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etDishBosch.getText().toString().length() > 0) {
                    dishBosch = "IFBPC1000007" + "-" + "IFBCC000003" + "#" + etDishBosch.getText().toString();
                }

            }
        });

        etDishLg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etDishLg.getText().toString().length() > 0) {
                    dishLg = "IFBPC1000007" + "-" + "IFBCC000001" + "#" + etDishLg.getText().toString();
                    Log.d("categoryyy", category);
                }
            }
        });

        etDishSamsung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etDishSamsung.getText().toString().length() > 0) {
                    dishSamsung = "IFBPC1000007" + "-" + "IFBCC000002" + "#" + etDishSamsung.getText().toString();
                    Log.d("categoryyy", category);

                }
            }
        });

        etDishOther.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etDishOther.getText().toString().length() > 0) {
                    dishOthers = "IFBPC1000007" + "-" + "IFBCC000004" + "#" + etDishOther.getText().toString();
                    Log.d("categoryyy", category);


                }
            }
        });

        etMicroLg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etMicroLg.getText().toString().length() > 0) {
                    microLg = "IFBPC1000011" + "-" + "IFBCC000001" + "#" + etMicroLg.getText().toString();

                }

            }
        });

        etMicroSamsung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etMicroLg.getText().toString().length() > 0) {
                    microSamSung = "IFBPC1000011" + "-" + "IFBCC000002" + "#" + etMicroSamsung.getText().toString();


                }

            }
        });

        etMicroWhirlPool.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etMicroLg.getText().toString().length() > 0) {
                    microWhirlPool = "IFBPC1000011" + "-" + "IFBCC000005" + "#" + etMicroWhirlPool.getText().toString();


                }

            }
        });

        etMicroPanasonic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etMicroLg.getText().toString().length() > 0) {
                    microPanasonic = "IFBPC1000011" + "-" + "IFBCC000007" + "#" + etMicroPanasonic.getText().toString();

                }

            }
        });

        etMicroGodrej.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etMicroLg.getText().toString().length() > 0) {
                    microGodrej = "IFBPC1000011" + "-" + "IFBCC000006" + "#" + etMicroGodrej.getText().toString();

                }

            }
        });

        etMicroOnida.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etMicroLg.getText().toString().length() > 0) {
                    microOnida = "IFBPC1000011" + "-" + "IFBCC000017" + "#" + etMicroOnida.getText().toString();

                }

            }
        });

        etMicroOthers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etMicroLg.getText().toString().length() > 0) {
                    microOthers = "IFBPC1000011" + "-" + "IFBCC000004" + "#" + etMicroOthers.getText().toString();

                }

            }
        });

        etKAFaber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etKAFaber.getText().toString().length() > 0) {
                    KaFaber = "IFBPC1000035" + "-" + "IFBCC000013" + "#" + etKAFaber.getText().toString();

                }

            }
        });

        etKASunFlame.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etKASunFlame.getText().toString().length() > 0) {
                    KaSunFlame = "IFBPC1000035" + "-" + "IFBCC000022" + "#" + etKASunFlame.getText().toString();

                }

            }
        });

        etKAElica.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etKAElica.getText().toString().length() > 0) {
                    KaElica = "IFBPC1000035" + "-" + "IFBCC000014" + "#" + etKAElica.getText().toString();

                }

            }
        });

        etKAKaff.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etKAKaff.getText().toString().length() > 0) {
                    KaKaff = "IFBPC1000035" + "-" + "IFBCC000012" + "#" + etKAKaff.getText().toString();

                }

            }
        });
        etKABosch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etKABosch.getText().toString().length() > 0) {
                    KaBosch = "IFBPC1000035" + "-" + "IFBCC000003" + "#" + etKABosch.getText().toString();

                }

            }
        });

        etKAOthers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etKAOthers.getText().toString().length() > 0) {
                    KaOthers = "IFBPC1000035" + "-" + "IFBCC000004" + "#" + etKAOthers.getText().toString();

                }

            }
        });

        etFLULg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etFLULg.getText().toString().length() > 0) {
                    FLULg = "IFBPC1000021" + "-" + "IFBCC000001" + "#" + etFLULg.getText().toString();

                }

            }
        });

        etFLUSamsung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etFLUSamsung.getText().toString().length() > 0) {
                    FLUSamsung = "IFBPC1000021" + "-" + "IFBCC000002" + "#" + etFLUSamsung.getText().toString();

                }

            }
        });

        etFLUBosch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etFLUBosch.getText().toString().length() > 0) {
                    FLUBosch = "IFBPC1000021" + "-" + "IFBCC000003" + "#" + etFLUBosch.getText().toString();

                }

            }
        });

        etFLUWhirlPool.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etFLUWhirlPool.getText().toString().length() > 0) {
                    FLUWhirlPool = "IFBPC1000021" + "-" + "IFBCC000005" + "#" + etFLUWhirlPool.getText().toString();

                }

            }
        });

        etFLUBeko.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etFLUBeko.getText().toString().length() > 0) {
                    FLUBeko = "IFBPC1000021" + "-" + "IFBCC000024" + "#" + etFLUBeko.getText().toString();

                }

            }
        });

        etFLUOthers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etFLUOthers.getText().toString().length() > 0) {
                    FLUOthers = "IFBPC1000021" + "-" + "IFBCC000004" + "#" + etFLUOthers.getText().toString();

                }

            }
        });

        etTLLg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etTLLg.getText().toString().length() > 0) {
                    TLLg = "IFBPC1000025" + "-" + "IFBCC000001" + "#" + etTLLg.getText().toString();

                }

            }
        });

        etTLSamsung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etTLSamsung.getText().toString().length() > 0) {
                    TLSamsung = "IFBPC1000025" + "-" + "IFBCC000002" + "#" + etTLSamsung.getText().toString();

                }

            }
        });

        etTLBosch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etTLBosch.getText().toString().length() > 0) {
                    TLBosch = "IFBPC1000025" + "-" + "IFBCC000003" + "#" + etTLBosch.getText().toString();

                }

            }
        });

        etTLWhirlPool.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etTLWhirlPool.getText().toString().length() > 0) {
                    TLWhirlPool = "IFBPC1000025" + "-" + "IFBCC000005" + "#" + etTLWhirlPool.getText().toString();

                }

            }
        });

        etTLPanasonic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etTLPanasonic.getText().toString().length() > 0) {
                    TLPanasonic = "IFBPC1000025" + "-" + "IFBCC000007" + "#" + etTLPanasonic.getText().toString();

                }

            }
        });

        etTLGodrej.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etTLGodrej.getText().toString().length() > 0) {
                    TLGodrej = "IFBPC1000025" + "-" + "IFBCC000006" + "#" + etTLGodrej.getText().toString();

                }

            }
        });

        etTLOnida.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etTLOnida.getText().toString().length() > 0) {
                    TLOnida = "IFBPC1000025" + "-" + "IFBCC000017" + "#" + etTLOnida.getText().toString();

                }

            }
        });

        etTLOthers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etTLOthers.getText().toString().length() > 0) {
                    TLOthers = "IFBPC1000025" + "-" + "IFBCC000004" + "#" + etTLOthers.getText().toString();

                }

            }
        });


        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAirIFB.getText().toString().length() > 0) {
                    if (etAirLG.getText().toString().length() > 0) {
                        if (etAirSamSung.getText().toString().length() > 0) {
                            if (etAirDaikin.getText().toString().length() > 0) {
                                if (etCarrier.getText().toString().length() > 0) {
                                    if (etAirBlueStar.getText().toString().length() > 0) {
                                        if (etAirVoltas.getText().toString().length() > 0) {
                                            if (etAirOnida.getText().toString().length() > 0) {
                                                if (etAirPanaSonic.getText().toString().length() > 0) {
                                                    if (etAirWhirlPool.getText().toString().length() > 0) {
                                                        if (etAirOGenaral.getText().toString().length() > 0) {
                                                            if (etAirGodrej.getText().toString().length() > 0) {
                                                                if (etAirHaier.getText().toString().length() > 0) {
                                                                    if (etAirLloyds.getText().toString().length() > 0) {
                                                                        if (etAirOthers.getText().toString().length() > 0) {
                                                                            if (etClothsIFB.getText().toString().length() > 0) {
                                                                                if (etClothsBosch.getText().toString().length() > 0) {
                                                                                    if (etDishIFB.getText().toString().length() > 0) {
                                                                                        if (etDishBosch.getText().toString().length() > 0) {
                                                                                            if (etDishLg.getText().toString().length() > 0) {
                                                                                                if (etDishSamsung.getText().toString().length() > 0) {
                                                                                                    if (etDishOther.getText().toString().length() > 0) {
                                                                                                        if (etMicroIfb.getText().toString().length() > 0) {
                                                                                                            if (etMicroLg.getText().toString().length() > 0) {
                                                                                                                if (etMicroSamsung.getText().toString().length() > 0) {
                                                                                                                    if (etMicroWhirlPool.getText().toString().length() > 0) {
                                                                                                                        if (etMicroPanasonic.getText().toString().length() > 0) {
                                                                                                                            if (etMicroGodrej.getText().toString().length() > 0) {
                                                                                                                                if (etMicroOnida.getText().toString().length() > 0) {
                                                                                                                                    if (etMicroOthers.getText().toString().length() > 0) {
                                                                                                                                        if (etKAIfb.getText().toString().length() > 0) {
                                                                                                                                            if (etKAFaber.getText().toString().length() > 0) {
                                                                                                                                                if (etKASunFlame.getText().toString().length() > 0) {
                                                                                                                                                    if (etKAElica.getText().toString().length() > 0) {
                                                                                                                                                        if (etKAKaff.getText().toString().length() > 0) {
                                                                                                                                                            if (etKABosch.getText().toString().length() > 0) {
                                                                                                                                                                if (etKAOthers.getText().toString().length() > 0) {
                                                                                                                                                                    if (etFLUIfb.getText().toString().length() > 0) {
                                                                                                                                                                        if (etFLULg.getText().toString().length() > 0) {
                                                                                                                                                                            if (etFLUSamsung.getText().toString().length() > 0) {
                                                                                                                                                                                if (etFLUBosch.getText().toString().length() > 0) {
                                                                                                                                                                                    if (etFLUWhirlPool.getText().toString().length() > 0) {
                                                                                                                                                                                        if (etFLUBeko.getText().toString().length() > 0) {
                                                                                                                                                                                            if (etFLUOthers.getText().toString().length() > 0) {
                                                                                                                                                                                                if (etTLIfb.getText().toString().length() > 0) {
                                                                                                                                                                                                    if (etTLLg.getText().toString().length() > 0) {
                                                                                                                                                                                                        if (etTLSamsung.getText().toString().length() > 0) {
                                                                                                                                                                                                            if (etTLBosch.getText().toString().length() > 0) {
                                                                                                                                                                                                                if (etTLWhirlPool.getText().toString().length() > 0) {
                                                                                                                                                                                                                    if (etTLPanasonic.getText().toString().length() > 0) {
                                                                                                                                                                                                                        if (etTLGodrej.getText().toString().length() > 0) {
                                                                                                                                                                                                                            if (etTLOnida.getText().toString().length() > 0) {
                                                                                                                                                                                                                                if (etTLOthers.getText().toString().length() > 0) {
                                                                                                                                                                                                                                    if (!etAirIFB.getText().toString().equals("0")  || !etClothsIFB.getText().toString().equals("0") || !etDishIFB.getText().toString().equals("0") || !etMicroIfb.getText().toString().equals("0") || !etKAIfb.getText().toString().equals("0") || !etFLUIfb.getText().toString().equals("0") || !etTLIfb.getText().toString().equals("0") || !etWasherDisherIfb.getText().toString().equals("0")) {


                                                                                                                                                                                                                                        postDisplaymatrix();
                                                                                                                                                                                                                                    }else {
                                                                                                                                                                                                                                        ifbAlert();

                                                                                                                                                                                                                                    }

                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                    etTLOthers.setError("Please enter quantity");
                                                                                                                                                                                                                                    etTLOthers.requestFocus();
                                                                                                                                                                                                                                }


                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                etTLOnida.setError("Please enter quantity");
                                                                                                                                                                                                                                etTLOnida.requestFocus();
                                                                                                                                                                                                                            }


                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                            etTLGodrej.setError("Please enter quantity");
                                                                                                                                                                                                                            etTLGodrej.requestFocus();
                                                                                                                                                                                                                        }


                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                        etTLPanasonic.setError("Please enter quantity");
                                                                                                                                                                                                                        etTLPanasonic.requestFocus();
                                                                                                                                                                                                                    }


                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                    etTLWhirlPool.setError("Please enter quantity");
                                                                                                                                                                                                                    etTLWhirlPool.requestFocus();
                                                                                                                                                                                                                }


                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                etTLBosch.setError("Please enter quantity");
                                                                                                                                                                                                                etTLBosch.requestFocus();
                                                                                                                                                                                                            }


                                                                                                                                                                                                        } else {
                                                                                                                                                                                                            etTLSamsung.setError("Please enter quantity");
                                                                                                                                                                                                            etTLSamsung.requestFocus();
                                                                                                                                                                                                        }


                                                                                                                                                                                                    } else {
                                                                                                                                                                                                        etTLLg.setError("Please enter quantity");
                                                                                                                                                                                                        etTLLg.requestFocus();
                                                                                                                                                                                                    }


                                                                                                                                                                                                } else {
                                                                                                                                                                                                    etTLIfb.setError("Please enter quantity");
                                                                                                                                                                                                    etTLIfb.requestFocus();
                                                                                                                                                                                                }

                                                                                                                                                                                            } else {
                                                                                                                                                                                                etFLUOthers.setError("Please enter quantity");
                                                                                                                                                                                                etFLUOthers.requestFocus();
                                                                                                                                                                                            }

                                                                                                                                                                                        } else {
                                                                                                                                                                                            etFLUBeko.setError("Please enter quantity");
                                                                                                                                                                                            etFLUBeko.requestFocus();
                                                                                                                                                                                        }

                                                                                                                                                                                    } else {
                                                                                                                                                                                        etFLUWhirlPool.setError("Please enter quantity");
                                                                                                                                                                                        etFLUWhirlPool.requestFocus();
                                                                                                                                                                                    }

                                                                                                                                                                                } else {
                                                                                                                                                                                    etFLUBosch.setError("Please enter quantity");
                                                                                                                                                                                    etFLUBosch.requestFocus();
                                                                                                                                                                                }

                                                                                                                                                                            } else {
                                                                                                                                                                                etFLUSamsung.setError("Please enter quantity");
                                                                                                                                                                                etFLUSamsung.requestFocus();
                                                                                                                                                                            }

                                                                                                                                                                        } else {
                                                                                                                                                                            etFLULg.setError("Please enter quantity");
                                                                                                                                                                            etFLULg.requestFocus();
                                                                                                                                                                        }

                                                                                                                                                                    } else {
                                                                                                                                                                        etFLUIfb.setError("Please enter quantity");
                                                                                                                                                                        etFLUIfb.requestFocus();
                                                                                                                                                                    }

                                                                                                                                                                } else {
                                                                                                                                                                    etKAOthers.setError("Please enter quantity");
                                                                                                                                                                    etKAOthers.requestFocus();
                                                                                                                                                                }

                                                                                                                                                            } else {
                                                                                                                                                                etKABosch.setError("Please enter quantity");
                                                                                                                                                                etKABosch.requestFocus();
                                                                                                                                                            }

                                                                                                                                                        } else {
                                                                                                                                                            etKAKaff.setError("Please enter quantity");
                                                                                                                                                            etKAKaff.requestFocus();
                                                                                                                                                        }

                                                                                                                                                    } else {
                                                                                                                                                        etKAElica.setError("Please enter quantity");
                                                                                                                                                        etKAElica.requestFocus();
                                                                                                                                                    }

                                                                                                                                                } else {
                                                                                                                                                    etKASunFlame.setError("Please enter quantity");
                                                                                                                                                    etKASunFlame.requestFocus();
                                                                                                                                                }

                                                                                                                                            } else {
                                                                                                                                                etKAFaber.setError("Please enter quantity");
                                                                                                                                                etKAFaber.requestFocus();
                                                                                                                                            }

                                                                                                                                        } else {
                                                                                                                                            etKAIfb.setError("Please enter quantity");
                                                                                                                                            etKAIfb.requestFocus();
                                                                                                                                        }

                                                                                                                                    } else {
                                                                                                                                        etMicroOthers.setError("Please enter quantity");
                                                                                                                                        etMicroOthers.requestFocus();
                                                                                                                                    }

                                                                                                                                } else {
                                                                                                                                    etMicroOnida.setError("Please enter quantity");
                                                                                                                                    etMicroOnida.requestFocus();
                                                                                                                                }

                                                                                                                            } else {
                                                                                                                                etMicroGodrej.setError("Please enter quantity");
                                                                                                                                etMicroGodrej.requestFocus();
                                                                                                                            }

                                                                                                                        } else {
                                                                                                                            etMicroPanasonic.setError("Please enter quantity");
                                                                                                                            etMicroPanasonic.requestFocus();
                                                                                                                        }

                                                                                                                    } else {
                                                                                                                        etMicroWhirlPool.setError("Please enter quantity");
                                                                                                                        etMicroWhirlPool.requestFocus();
                                                                                                                    }


                                                                                                                } else {
                                                                                                                    etMicroSamsung.setError("Please enter quantity");
                                                                                                                    etMicroSamsung.requestFocus();
                                                                                                                }

                                                                                                            } else {
                                                                                                                etMicroLg.setError("Please enter quantity");
                                                                                                                etMicroLg.requestFocus();
                                                                                                            }

                                                                                                        } else {
                                                                                                            etMicroIfb.setError("Please enter quantity");
                                                                                                            etMicroIfb.requestFocus();
                                                                                                        }


                                                                                                    } else {
                                                                                                        etDishOther.setError("Please enter quantity");
                                                                                                        etDishOther.requestFocus();
                                                                                                    }

                                                                                                } else {
                                                                                                    etDishSamsung.setError("Please enter quantity");
                                                                                                    etDishSamsung.requestFocus();
                                                                                                }

                                                                                            } else {
                                                                                                etDishLg.setError("Please enter quantity");
                                                                                                etDishLg.requestFocus();
                                                                                            }


                                                                                        } else {
                                                                                            etDishBosch.setError("Please enter quantity");
                                                                                            etDishBosch.requestFocus();
                                                                                        }


                                                                                    } else {
                                                                                        etDishIFB.setError("Please enter quantity");
                                                                                        etDishIFB.requestFocus();
                                                                                    }


                                                                                } else {
                                                                                    etClothsBosch.setError("Please enter quantity");
                                                                                    etClothsBosch.requestFocus();
                                                                                }

                                                                            } else {
                                                                                etClothsIFB.setError("Please enter quantity");
                                                                                etClothsIFB.requestFocus();
                                                                            }

                                                                        } else {
                                                                            etAirOthers.setError("Please enter quantity");
                                                                            etAirOthers.requestFocus();
                                                                        }

                                                                    } else {
                                                                        etAirLloyds.setError("Please enter quantity");
                                                                        etAirLloyds.requestFocus();
                                                                    }

                                                                } else {
                                                                    etAirHaier.setError("Please enter quantity");
                                                                    etAirHaier.requestFocus();
                                                                }

                                                            } else {
                                                                etAirGodrej.setError("Please enter quantity");
                                                                etAirGodrej.requestFocus();
                                                            }

                                                        } else {
                                                            etAirOGenaral.setError("Please enter quantity");
                                                            etAirOGenaral.requestFocus();
                                                        }

                                                    } else {
                                                        etAirWhirlPool.setError("Please enter quantity");
                                                        etAirWhirlPool.requestFocus();
                                                    }

                                                } else {
                                                    etAirPanaSonic.setError("Please enter quantity");
                                                    etAirPanaSonic.requestFocus();
                                                }

                                            } else {
                                                etAirOnida.setError("Please enter quantity");
                                                etAirOnida.requestFocus();
                                            }

                                        } else {
                                            etAirVoltas.setError("Please enter quantity");
                                            etAirVoltas.requestFocus();
                                        }

                                    } else {
                                        etAirBlueStar.setError("Please enter quantity");
                                        etAirBlueStar.requestFocus();
                                    }


                                } else {
                                    etCarrier.setError("Please enter quantity");
                                    etCarrier.requestFocus();
                                }

                            } else {
                                etAirDaikin.setError("Please enter quantity");
                                etAirDaikin.requestFocus();
                            }

                        } else {
                            etAirSamSung.setError("Please enter quantity");
                            etAirSamSung.requestFocus();
                        }

                    } else {
                        etAirLG.setError("Please enter quantity");
                        etAirLG.requestFocus();
                    }

                } else {
                    etAirIFB.setError("Please enter quantity");
                    etAirIFB.requestFocus();
                }

            }
        });
    }


   /* private void postDisplaymatrix() {
        category = airDaikin + "," + airIfb + "," + "," + airLg + "," + airLloyds + "," + airOthers + "," + airVoltas + "," + airSAMSUNG + "," + airCARRIER + "," + airBLUESTAR + "," + airONIDA + "," + airPANASONIC + "," + airWHIRLPOOL + "," + airOGENERAL + "," + airGODREJ + "," + airHAIER + "," + clothsIFB + "," + clothsBOSCH + "," + dishIfb + "," + dishBosch + "," + dishLg + "," + dishSamsung + "," + dishOthers + "," + microIfb + "," + microLg + "," + microSamSung + "," + microWhirlPool + "," + microPanasonic + "," + microGodrej + "," + microOnida + "," + microOthers + "," + kaIfb + "," + KaFaber + "," + KaSunFlame + "," + KaElica + "," + KaKaff + "," + KaBosch + "," + KaOthers + "," + FLUIfb + "," + FLULg + "," + FLUSamsung + "," + FLUBosch + "," + FLUWhirlPool + "," + FLUBeko + "," + FLUOthers + "," + TLIfb + "," + TLLg + "," + TLSamsung + "," + TLBosch + "," + TLWhirlPool + "," + TLPanasonic + "," + TLGodrej + "," + TLOnida + "," + TLOthers;
        Log.d("discategory", category);
        progressDialog.show();

        Call<UploadObject> fileUpload = uploadService.postdisplaymatrix(salesdate, category, modelId, userid, securitycode);
        fileUpload.enqueue(new Callback<UploadObject>() {
            @Override
            public void onResponse(Call<UploadObject> call, retrofit2.Response<UploadObject> response) {
                progressDialog.dismiss();
                UploadObject extraWorkingDayModel = response.body();
                if (extraWorkingDayModel.isResponseStatus()) {
                    msg = extraWorkingDayModel.getResponseText();
                    // Toast.makeText(getApplicationContext(), extraWorkingDayModel.getResponseText(), Toast.LENGTH_SHORT).show();
                    Log.d("riku", "withocamera");
                    prefManager.saveAirConditionerId("0");
                    prefManager.saveDishWasherId("0");
                    prefManager.saveClothsDryerId("0");
                    prefManager.saveAirIfbSize(0);
                    prefManager.saveClothsIfbSize(0);
                    prefManager.saveDishIfbSize(0);
                    prefManager.saveMicroOvenId("0");
                    prefManager.saveMicroOvenIfbSize(0);
                    prefManager.SaveKAItemId("0");
                    prefManager.saveKAItemSize(0);
                    prefManager.saveWashingFLUId("0");
                    prefManager.saveWMFLUIfbSize(0);
                    prefManager.saveWashingTLId("0");
                    prefManager.saveWMTLIFBSize(0);
                    successAlert();

                } else {
                    //  Toast.makeText(getApplicationContext(), extraWorkingDayModel.getResponseText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UploadObject> call, Throwable t) {
                progressDialog.dismiss();

                Log.e("error", "Error " + t.getMessage());
                //  Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();

                //   Toast.makeText(AttendanceManageActivity.this,"attendance saved without image",Toast.LENGTH_LONG).show();
            }

        });
    }*/

    private void postDisplaymatrix() {
        airIfb = "IFBPC1000001" + "-" + "IFBCC000015" + "#" + etAirIFB.getText().toString();
        clothsIFB = "IFBPC1000005" + "-" + "IFBCC000015" + "#" +etClothsIFB.getText().toString();
        dishIfb = "IFBPC1000007" + "-" + "IFBCC000015" + "#" + etDishIFB.getText().toString();
        microIfb = "IFBPC1000011" + "-" + "IFBCC000015" + "#" + etMicroIfb.getText().toString();
        kaIfb = "IFBPC1000035" + "-" + "IFBCC000015" + "#" + etKAIfb.getText().toString();
        FLUIfb = "IFBPC1000021" + "-" + "IFBCC000015" + "#" +etFLUIfb.getText().toString();
        TLIfb = "IFBPC1000025" + "-" + "IFBCC000015" + "#" + etTLIfb.getText().toString();



        washerIfb = "IFBPC1000039" + "-" + "IFBCC000015" + "#" + etWasherDisherIfb.getText().toString();
        washerLg = "IFBPC1000039" + "-" + "IFBCC000001" + "#" + etWasherDisherLg.getText().toString();
        washerSamSung = "IFBPC1000039" + "-" + "IFBCC000002" + "#" + etWasherDisherSamsung.getText().toString();
        washerWhirlPool = "IFBPC1000039" + "-" + "IFBCC000005" + "#" + etWasherDisherWhirlPool.getText().toString();
        washerPanasonic = "IFBPC1000039" + "-" + "IFBCC000007" + "#" + etWasherDisherPanasonic.getText().toString();
        washerGodrej = "IFBPC1000039" + "-" + "IFBCC000006" + "#" + etWasherDisherGodrej.getText().toString();
        washerOnida = "IFBPC1000039" + "-" + "IFBCC000017" + "#" + etWasherDisherOnida.getText().toString();
        washerOthers = "IFBPC1000039" + "-" + "IFBCC000004" + "#" + etWasherDisherOthers.getText().toString();

        if (!prefManager.getAirConditionerId().equals("")) {
            airItem = prefManager.getAirConditionerId();

        } else {
            airItem = "0";

        }

        if (!prefManager.getClothsDryerId().equals("")) {
            clothsItem = prefManager.getClothsDryerId();
        } else {
            clothsItem = "0";
        }

        if (!prefManager.getDishWasherId().equals("")) {
            dishItem = prefManager.getDishWasherId();
        } else {
            dishItem = "0";
        }

        if (!prefManager.getMicroOvenId().equals("")) {
            microItem = prefManager.getMicroOvenId();
        } else {
            microItem = "0";
        }


        if (!prefManager.getKAItemId().equals("")) {
            KAItem = prefManager.getKAItemId();
        } else {
            KAItem = "0";
        }

        if (!prefManager.getWashingFLUId().equals("")) {
            FLUItem = prefManager.getWashingFLUId();
        } else {
            FLUItem = "0";
        }

        if (!prefManager.getWashingTLId().equals("")) {
            tlItem = prefManager.getWashingTLId();
        } else {
            tlItem = "0";
        }

        if (!prefManager.getWasherDryerId().equals("")) {
            washerDyerItem = prefManager.getWasherDryerId();
        } else {
            washerDyerItem = "0";
        }
        model = airItem + "," + clothsItem + "," + dishItem + "," + microItem + "," + KAItem + "," + FLUItem + "," + tlItem+","+washerDyerItem;
        modelId = model.replaceAll("\\s+", "");
        modelArray.add(model);
        Log.d("newList",modelArray.toString());

        category=airDaikin + "," + airIfb + "," + "," + airLg + "," + airLloyds + "," + airOthers + "," + airVoltas + "," + airSAMSUNG + "," + airCARRIER + "," + airBLUESTAR + "," + airONIDA + "," + airPANASONIC + "," + airWHIRLPOOL + "," + airOGENERAL + "," + airGODREJ + "," + airHAIER + "," + clothsIFB + "," + clothsBOSCH + "," + dishIfb + "," + dishBosch + "," + dishLg + "," + dishSamsung + "," + dishOthers + "," + microIfb + "," + microLg + "," + microSamSung + "," + microWhirlPool + "," + microPanasonic + "," + microGodrej + "," + microOnida + "," + microOthers + "," + kaIfb + "," + KaFaber + "," + KaSunFlame + "," + KaElica + "," + KaKaff + "," + KaBosch + "," + KaOthers + "," + FLUIfb + "," + FLULg + "," + FLUSamsung + "," + FLUBosch + "," + FLUWhirlPool + "," + FLUBeko + "," + FLUOthers + "," + TLIfb + "," + TLLg + "," + TLSamsung + "," + TLBosch + "," + TLWhirlPool + "," + TLPanasonic + "," + TLGodrej + "," + TLOnida + "," + TLOthers+","+washerIfb + "," + washerLg + "," + washerSamSung + "," + washerWhirlPool + "," + washerPanasonic + "," + washerGodrej + "," + washerOnida + "," + washerOthers ;
      //  category = airDaikin + "," + airIfb + "," + "," + airLg + "," + airLloyds + "," + airOthers + "," + airVoltas + "," + airSAMSUNG + "," + airCARRIER + "," + airBLUESTAR + "," + airONIDA + "," + airPANASONIC + "," + airWHIRLPOOL + "," + airOGENERAL + "," + airGODREJ + "," + airHAIER + "," + clothsIFB + "," + clothsBOSCH + "," + dishIfb + "," + dishBosch + "," + dishLg + "," + dishSamsung + "," + dishOthers + "," + microIfb + "," + microLg + "," + microSamSung + "," + microWhirlPool + "," + microPanasonic + "," + microGodrej + "," + microOnida + "," + microOthers + "," + kaIfb + "," + KaFaber + "," + KaSunFlame + "," + KaElica + "," + KaKaff + "," + KaBosch + "," + KaOthers + "," + FLUIfb + "," + FLULg + "," + FLUSamsung + "," + FLUBosch + "," + FLUWhirlPool + "," + FLUBeko + "," + FLUOthers + "," + TLIfb + "," + TLLg + "," + TLSamsung + "," + TLBosch + "," + TLWhirlPool + "," + TLPanasonic + "," + TLGodrej + "," + TLOnida + "," + TLOthers;
        final ProgressDialog pd = new ProgressDialog(DisplayMatrixDynamicActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);

        AndroidNetworking.upload("http://111.93.182.173/IFBiOSApi/api/post_DisplayMatrix")
                .addMultipartParameter("SalesDate", salesdate)
                .addMultipartParameter("Category", category)
                .addMultipartParameter("Model", modelId)
                .addMultipartParameter("AEMEmployeeID", userid)
                .addMultipartParameter("SecurityCode", securitycode)
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

                        boolean responseStatus = job1.optBoolean("responseStatus");
                        Log.d("responseText", responseText);
                        if (responseStatus) {

                            imageAlert();
                            pd.dismiss();

                            JSONArray jsonArray = job1.optJSONArray("responseData");

                            JSONObject object = jsonArray.optJSONObject(0);
                            String RowNum = object.optString("RowNum");
                            acFlag = RowNum;
                            sendACModelList.clear();
                            prefManager.saveAirConditionerId("");
                            prefManager.saveClothsDryerId("");
                            prefManager.saveMicroOvenId("");
                            prefManager.saveDishWasherId("");
                            prefManager.SaveKAItemId("");
                            prefManager.saveWasherDryerId("");
                            prefManager.saveWashingFLUId("");
                            prefManager.saveWashingTLId("");


                        } else {
                            pd.dismiss();
                            Toast.makeText(DisplayMatrixDynamicActivity.this, responseText, Toast.LENGTH_LONG).show();

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


    @Override
    protected void onResume() {
        super.onResume();

        etAirIFB.setEnabled(false);
        String size = String.valueOf(prefManager.getAirIfbSize());
        Log.d("size", size);
        etAirIFB.setText(size);

        String clothsifbsize1 = String.valueOf(prefManager.getClothsIfbSize());
        Log.d("size", clothsifbsize1);
        etClothsIFB.setText(clothsifbsize1);

        etDishIFB = (EditText) findViewById(R.id.etDishIFB);
        String dishifbsize = String.valueOf(prefManager.getDishIfbSize());
        etDishIFB.setText(dishifbsize);

        etMicroIfb = (EditText) findViewById(R.id.etMicroIfb);
        String microifbsize = String.valueOf(prefManager.getMicroOvenIfbSize());
        Log.d("size", microifbsize);
        etMicroIfb.setText(microifbsize);

        etKAIfb = (EditText) findViewById(R.id.etKAIfb);
        String kaifbsize = String.valueOf(prefManager.getKAIfbSize());
        Log.d("size", kaifbsize);
        etKAIfb.setText(kaifbsize);


        String FLUIfbSize = String.valueOf(prefManager.getWMFLUIfbSize());
        Log.d("size", FLUIfbSize);
        etFLUIfb.setText(FLUIfbSize);

        String tlIfbSize = String.valueOf(prefManager.getWMTLIFBSize());
        Log.d("size", tlIfbSize);
        etTLIfb.setText(tlIfbSize);

        String washerIFBSize= String.valueOf(prefManager.getWasherDryerIfbSize());
        etWasherDisherIfb.setText(washerIFBSize);


        if (!prefManager.getAirConditionerId().equals("")) {
            airItem = prefManager.getAirConditionerId();


        } else {
            airItem = "0";

        }

        if (!prefManager.getClothsDryerId().equals("")) {
            clothsItem = prefManager.getClothsDryerId();
        } else {
            clothsItem = "0";
        }


        if (!prefManager.getDishWasherId().equals("")) {
            dishItem = prefManager.getDishWasherId();
        } else {
            dishItem = "0";
        }


        if (!prefManager.getMicroOvenId().equals("")) {
            microItem = prefManager.getMicroOvenId();
        } else {
            microItem = "0";
        }


        if (!prefManager.getKAItemId().equals("")) {
            KAItem = prefManager.getKAItemId();
        } else {
            KAItem = "0";
        }

        if (!prefManager.getWashingFLUId().equals("")) {
            FLUItem = prefManager.getWashingFLUId();
        } else {
            FLUItem = "0";
        }

        if (!prefManager.getWashingTLId().equals("")) {
            tlItem = prefManager.getWashingTLId();
        } else {
            tlItem = "0";
        }



        if (!prefManager.getWasherDryerId().equals("")) {
            washerDyerItem = prefManager.getWasherDryerId();
        } else {
            washerDyerItem = "0";
        }



        model = airItem + "," + clothsItem + "," + dishItem + "," + microItem + "," + KAItem + "," + FLUItem + "," + tlItem+","+washerDyerItem;
        modelId = model.replaceAll("\\s+", "");
        Log.d("modelid", modelId);
        category = airDaikin + "," + airIfb + "," + "," + airLg + "," + airLloyds + "," + airOthers + "," + airVoltas + "," + airSAMSUNG + "," + airCARRIER + "," + airBLUESTAR + "," + airONIDA + "," + airPANASONIC + "," + airWHIRLPOOL + "," + airOGENERAL + "," + airGODREJ + "," + airHAIER + "," + clothsIFB + "," + clothsBOSCH + "," + dishIfb + "," + dishBosch + "," + dishLg + "," + dishSamsung + "," + dishOthers + "," + microIfb + "," + microLg + "," + microSamSung + "," + microWhirlPool + "," + microPanasonic + "," + microGodrej + "," + microOnida + "," + microOthers + "," + kaIfb + "," + KaFaber + "," + KaSunFlame + "," + KaElica + "," + KaKaff + "," + KaBosch + "," + KaOthers + "," + FLUIfb + "," + FLULg + "," + FLUSamsung + "," + FLUBosch + "," + FLUWhirlPool + "," + FLUBeko + "," + FLUOthers + "," + TLIfb + "," + TLLg + "," + TLSamsung + "," + TLBosch + "," + TLWhirlPool + "," + TLPanasonic + "," + TLGodrej + "," + TLOnida + "," + TLOthers;


    }



    private void successAlert(String msg) {
       AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DisplayMatrixDynamicActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_success, null);
        dialogBuilder.setView(dialogView);
        TextView tvInvalidDate = (TextView) dialogView.findViewById(R.id.tvSuccess);
        tvInvalidDate.setText(msg);

        Button btnOk = (Button) dialogView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alerDialog1.dismiss();


                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, DisplayMatrixReportActivity.class);
                startActivity(intent);
                finish();


            }
        });

        alerDialog1 = dialogBuilder.create();
        alerDialog1.setCancelable(true);
        Window window = alerDialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alerDialog1.show();
    }


    private void imageAlert() {
       AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DisplayMatrixDynamicActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_display_camera, null);
        dialogBuilder.setView(dialogView);

        imgPic1 = (ImageView) dialogView.findViewById(R.id.imgPic1);
        imgPic2 = (ImageView) dialogView.findViewById(R.id.imgPic2);
        imgPic3 = (ImageView) dialogView.findViewById(R.id.imgPic3);

        imgPic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntentforPic1();
            }
        });
        imgPic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntentforPic2();
            }
        });

        imgPic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntentforPic3();
            }
        });

        Button btnSave = (Button) dialogView.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pic1Flag == 1) {
                    if (pic2Flag == 1) {
                        acChecking();

                    } else {
                        Toast.makeText(DisplayMatrixDynamicActivity.this, "Please Upload Image 2", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(DisplayMatrixDynamicActivity.this, "Please Upload Image 1", Toast.LENGTH_LONG).show();
                }

            }
        });


        alertDialog2 = dialogBuilder.create();
        alertDialog2.setCancelable(false);
        Window window = alertDialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog2.show();
    }


    private void displayMatrixChecking() {
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Authenticating...");
        progressBar.show();
        String surl = "http://111.93.182.173/IFBiOSApi/api/get_DisplayMatrixReport?AEMEmployeeID=" + prefManager.getUserId() + "&FinancialYear=" + finalcialchecking + "&Month=" + month + "&SecurityCode=" + prefManager.getSecurityCode();
        Log.d("inputtlreport", surl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.dismiss();

                        Log.d("responsetlreport", response);

                        // attendabceInfiList.clear();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            responseText = job1.optString("responseText");

                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                //          Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();

                                displayMatrixAlert();



                            } else {

                                displayMatrixAlertForPreviousMonth();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DisplayMatrixDynamicActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();

                //Toast.makeText(SupAttenReportActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };


        RequestQueue requestQueue = Volley.newRequestQueue(DisplayMatrixDynamicActivity.this);
        requestQueue.add(stringRequest);

    }


    private void displayMatrixAlert() {
       AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DisplayMatrixDynamicActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_compsale, null);
        dialogBuilder.setView(dialogView);
        Button btnNow = (Button) dialogView.findViewById(R.id.btnNow);
        TextView tvResponse = (TextView) dialogView.findViewById(R.id.tvResponse);
        tvResponse.setText(responseText + " .Do you want to update ?");
        btnNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                getReportList();

            }
        });

        Button btnLate = (Button) dialogView.findViewById(R.id.btnLate);
        btnLate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMatrixDynamicActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(false);
        Window window = alertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog.show();
    }

    private void ifbAlert() {
       AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DisplayMatrixDynamicActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_compsale, null);
        dialogBuilder.setView(dialogView);
        Button btnNow = (Button) dialogView.findViewById(R.id.btnNow);
        TextView tvResponse = (TextView) dialogView.findViewById(R.id.tvResponse);
        tvResponse.setText("Are You Sure,IFB Models Zero Display In Your Store");
        btnNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog3.dismiss();
                postDisplaymatrix();


            }
        });

        Button btnLate = (Button) dialogView.findViewById(R.id.btnLate);
        btnLate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDisplaymatrix();
                alertDialog3.dismiss();
            }
        });
        alertDialog3 = dialogBuilder.create();
        alertDialog3.setCancelable(false);
        Window window = alertDialog3.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog3.show();
    }


    private void displayMatrixAlertForPreviousMonth() {
       AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DisplayMatrixDynamicActivity.this, R.style.CustomDialogNew);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_compsale, null);
        dialogBuilder.setView(dialogView);
        Button btnNow = (Button) dialogView.findViewById(R.id.btnNow);
        TextView tvResponse = (TextView) dialogView.findViewById(R.id.tvResponse);
        tvResponse.setText("Will you carry forward the previous month display data?");
        btnNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                getReportListForPreviousMonth();

            }
        });

        Button btnLate = (Button) dialogView.findViewById(R.id.btnLate);
        btnLate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(false);
        Window window = alertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog.show();
    }

    private void getReportList() {

        pd.show();

        String surl = "http://111.93.182.173/IFBiOSApi/api/get_DisplayMatrixForUpdate?AEMEmployeeID=" + prefManager.getUserId() + "&FinancialYear=" + finalcialchecking + "&Month=" + month + "&SecurityCode=" + prefManager.getSecurityCode() + "&Opertaion=1";
        Log.d("inputtlreport", surl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.d("responsetlreport", response);

                        // attendabceInfiList.clear();
                        pd.show();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");

                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                //          Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();
                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.getJSONObject(i);
                                    String CategoryName = obj.optString("CategoryName");
                                    String CompanyName = obj.optString("CompanyName");
                                    String Quantity = obj.optString("Quantity");
                                    String FinancialYear = obj.optString("FinancialYear");
                                    String Month = obj.optString("Month");
                                    String CategoryID = obj.optString("CategoryID");
                                    String CompetitorCompanyID = obj.optString("CompetitorCompanyID");
                                    //Airconditioner
                                    if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etAirLG.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etAirSamSung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etAirOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000005")) {
                                        etAirWhirlPool.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000006")) {
                                        etAirGodrej.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000007")) {
                                        etAirPanaSonic.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000008")) {
                                        etAirVoltas.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000009")) {
                                        etAirDaikin.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000010")) {
                                        etAirLloyds.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etAirIFB.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000017")) {
                                        etAirOnida.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000018")) {
                                        etCarrier.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000019")) {
                                        etAirBlueStar.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000020")) {
                                        etAirOGenaral.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000021")) {
                                        etAirHaier.setText(Quantity);
                                    }

                                    //CLOTHS DRYER

                                    if (CategoryID.equals("IFBPC1000005") && CompetitorCompanyID.equals("IFBCC000003")) {
                                        etClothsBosch.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000005") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etClothsIFB.setText(Quantity);
                                    }

                                    //DISHWASHER

                                    if (CategoryID.equals("IFBPC1000007") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etDishLg.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000007") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etDishSamsung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000007") && CompetitorCompanyID.equals("IFBCC000003")) {
                                        etDishBosch.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000007") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etDishOther.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000007") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etDishIFB.setText(Quantity);
                                    }

                                    //MICROVEN

                                    if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etMicroLg.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etMicroSamsung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etMicroOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000005")) {
                                        etMicroWhirlPool.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000006")) {
                                        etMicroGodrej.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000007")) {
                                        etMicroPanasonic.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etMicroIfb.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000017")) {
                                        etMicroOnida.setText(Quantity);
                                    }

                                    //KITCHEN APPLIANCE


                                    if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000003")) {
                                        etKABosch.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etKAOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000012")) {
                                        etKAKaff.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000013")) {
                                        etKAFaber.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000014")) {
                                        etKAElica.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etKAIfb.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000022")) {
                                        etKASunFlame.setText(Quantity);
                                    }

                                    //WASHING FLU


                                    if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etFLULg.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etFLUSamsung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000003")) {
                                        etFLUBosch.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etFLUOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000005")) {
                                        etFLUWhirlPool.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etFLUIfb.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000024")) {
                                        etFLUBeko.setText(Quantity);
                                    }
                                    //WASHING TL

                                    if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etTLLg.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etTLSamsung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000003")) {
                                        etTLBosch.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etTLOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000005")) {
                                        etTLWhirlPool.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000006")) {
                                        etTLGodrej.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000007")) {
                                        etTLPanasonic.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etTLIfb.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000017")) {
                                        etTLOnida.setText(Quantity);
                                    }

                                    //WASHER DRYER

                                    if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etWasherDisherLg.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etWasherDisherSamsung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etWasherDisherOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000005")) {
                                        etWasherDisherWhirlPool.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000006")) {
                                        etWasherDisherGodrej.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000007")) {
                                        etWasherDisherPanasonic.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etWasherDisherIfb.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000017")) {
                                        etWasherDisherOnida.setText(Quantity);
                                    }





                                }
                                getReportListForModel();


                            } else {


                                //Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DisplayMatrixDynamicActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();


                //Toast.makeText(SupAttenReportActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };


        RequestQueue requestQueue = Volley.newRequestQueue(DisplayMatrixDynamicActivity.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                6000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void getReportListForModel() {

        pd.show();


        String surl = "http://111.93.182.173/IFBiOSApi/api/get_DisplayMatrixForUpdate?AEMEmployeeID=" + prefManager.getUserId() + "&FinancialYear=" + finalcialchecking + "&Month=" + month + "&SecurityCode=" + prefManager.getSecurityCode() + "&Opertaion=2";
        Log.d("inputtlreport", surl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.d("responsetlreport", response);

                        airConditionerModel.clear();
                        clothsdryerModel.clear();
                        dishwasherModel.clear();
                        microOvenModel.clear();
                        kitchenModel.clear();
                        wmFluModel.clear();
                        wmTLModel.clear();
                        dryerModel.clear();
                        // attendabceInfiList.clear();
                        pd.dismiss();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");

                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                //          Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();
                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.getJSONObject(i);
                                    String CategoryID = obj.optString("CategoryID");
                                    String ModelID = obj.optString("ModelID");
                                    String ModelName=obj.optString("ModelName");
                                    //Airconditioner
                                    if (CategoryID.equals("IFBPC1000001")) {
                                        airConditionerModel.add(CategoryID + "-" + ModelID);
                                        //sendACModelList.add(ModelName);
                                    } else if (CategoryID.equals("IFBPC1000005")) {
                                        clothsdryerModel.add(CategoryID + "-" + ModelID);
                                    } else if (CategoryID.equals("IFBPC1000007")) {
                                        dishwasherModel.add(CategoryID + "-" + ModelID);
                                    } else if (CategoryID.equals("IFBPC1000011")) {
                                        microOvenModel.add(CategoryID + "-" + ModelID);
                                    } else if (CategoryID.equals("IFBPC1000035")) {
                                        kitchenModel.add(CategoryID + "-" + ModelID);
                                    } else if (CategoryID.equals("IFBPC1000021")) {
                                        wmFluModel.add(CategoryID + "-" + ModelID);
                                    } else if (CategoryID.equals("IFBPC1000025")) {
                                        wmTLModel.add(CategoryID + "-" + ModelID);
                                    }else if (CategoryID.equals("IFBPC1000039")) {
                                        dryerModel.add(CategoryID + "-" + ModelID);
                                    }


                                }

                                Set<String> set = new HashSet<String>(airConditionerModel);
                                airConditionerModel.clear();
                                airConditionerModel.addAll(set);

                                String airConditionerItem = String.valueOf(airConditionerModel);
                                String refreshairConditionerItem = airConditionerItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveAirConditionerId(refreshairConditionerItem);

                                //CTOTHS
                                Set<String> set1 = new HashSet<String>(clothsdryerModel);
                                clothsdryerModel.clear();
                                clothsdryerModel.addAll(set1);


                                String ClothsItem = String.valueOf(clothsdryerModel);
                                String refreshairClothsItem = ClothsItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveClothsDryerId(refreshairClothsItem);

                                //DISHWASHER

                                Set<String> set2 = new HashSet<String>(dishwasherModel);
                                dishwasherModel.clear();
                                dishwasherModel.addAll(set2);

                                String dishwasherItem = String.valueOf(dishwasherModel);
                                String refreshdishwasherItem = dishwasherItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveDishWasherId(refreshdishwasherItem);

                                //MICROOVEN

                                Set<String> set3 = new HashSet<String>(microOvenModel);
                                microOvenModel.clear();
                                microOvenModel.addAll(set3);

                                String microOvenItem = String.valueOf(microOvenModel);
                                String refreshmicroOvenItem = microOvenItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveMicroOvenId(refreshmicroOvenItem);

                                //Kitchen

                                Set<String> set4 = new HashSet<String>(kitchenModel);
                                kitchenModel.clear();
                                kitchenModel.addAll(set4);

                                String kaItem = String.valueOf(kitchenModel);
                                String refreshkaItem = kaItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.SaveKAItemId(refreshkaItem);

                                //WMFLU

                                Set<String> set5 = new HashSet<String>(wmFluModel);
                                wmFluModel.clear();
                                wmFluModel.addAll(set5);


                                String wmfluItem = String.valueOf(wmFluModel);
                                String refreshwmfluItem = wmfluItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveWashingFLUId(refreshwmfluItem);

                                //WMTL

                                Set<String> set6 = new HashSet<String>(wmTLModel);
                                wmTLModel.clear();
                                wmTLModel.addAll(set6);


                                String wmtlItem = String.valueOf(wmTLModel);
                                String refreshwmtlItem = wmtlItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveWashingTLId(refreshwmtlItem);

                                //Dryer

                                Set<String> set7 = new HashSet<String>(dryerModel);
                                dryerModel.clear();
                                dryerModel.addAll(set7);

                                String dryerItem= String.valueOf(dryerModel);
                                String refreshdryerItem = dryerItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveWasherDryerId(refreshdryerItem);



                            } else {


                                //    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DisplayMatrixDynamicActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();


                //Toast.makeText(SupAttenReportActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };


        RequestQueue requestQueue = Volley.newRequestQueue(DisplayMatrixDynamicActivity.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                6000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void getReportListForPreviousMonth() {
        if (premonth.equals("January")) {
            int futureyear = y - 1;
            finalcialchecking = futureyear + "-" + year;
        } else if (premonth.equals("February")) {
            int futureyear = y - 1;
            finalcialchecking = futureyear + "-" + year;
        } else if (premonth.equals("March")) {
            int futureyear = y - 1;
            finalcialchecking = futureyear + "-" + year;
        } else {
            int futureyear = y + 1;
            finalcialchecking = year + "-" + futureyear;
        }

        pd.show();

        String surl = "http://111.93.182.173/IFBiOSApi/api/get_DisplayMatrixForUpdate?AEMEmployeeID=" + prefManager.getUserId() + "&FinancialYear=" + finalcialchecking + "&Month=" + premonth + "&SecurityCode=" + prefManager.getSecurityCode() + "&Opertaion=1";
        Log.d("inputtlreportpre", surl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.d("responsetlreport", response);

                        // attendabceInfiList.clear();
                        pd.show();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");

                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                previousMonthData="true";
                                //          Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();
                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.getJSONObject(i);
                                    String CategoryName = obj.optString("CategoryName");
                                    String CompanyName = obj.optString("CompanyName");
                                    String Quantity = obj.optString("Quantity");
                                    String FinancialYear = obj.optString("FinancialYear");
                                    String Month = obj.optString("Month");
                                    String CategoryID = obj.optString("CategoryID");
                                    String CompetitorCompanyID = obj.optString("CompetitorCompanyID");
                                    //Airconditioner
                                    if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etAirLG.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etAirSamSung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etAirOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000005")) {
                                        etAirWhirlPool.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000006")) {
                                        etAirGodrej.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000007")) {
                                        etAirPanaSonic.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000008")) {
                                        etAirVoltas.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000009")) {
                                        etAirDaikin.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000010")) {
                                        etAirLloyds.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etAirIFB.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000017")) {
                                        etAirOnida.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000018")) {
                                        etCarrier.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000019")) {
                                        etAirBlueStar.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000020")) {
                                        etAirOGenaral.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000001") && CompetitorCompanyID.equals("IFBCC000021")) {
                                        etAirHaier.setText(Quantity);
                                    }

                                    //CLOTHS DRYER

                                    if (CategoryID.equals("IFBPC1000005") && CompetitorCompanyID.equals("IFBCC000003")) {
                                        etClothsBosch.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000005") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etClothsIFB.setText(Quantity);
                                    }

                                    //DISHWASHER

                                    if (CategoryID.equals("IFBPC1000007") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etDishLg.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000007") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etDishSamsung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000007") && CompetitorCompanyID.equals("IFBCC000003")) {
                                        etDishBosch.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000007") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etDishOther.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000007") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etDishIFB.setText(Quantity);
                                    }

                                    //MICROVEN

                                    if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etMicroLg.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etMicroSamsung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etMicroOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000005")) {
                                        etMicroWhirlPool.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000006")) {
                                        etMicroGodrej.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000007")) {
                                        etMicroPanasonic.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etMicroIfb.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000011") && CompetitorCompanyID.equals("IFBCC000017")) {
                                        etMicroOnida.setText(Quantity);
                                    }

                                    //KITCHEN APPLIANCE


                                    if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000003")) {
                                        etKABosch.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etKAOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000012")) {
                                        etKAKaff.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000013")) {
                                        etKAFaber.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000014")) {
                                        etKAElica.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etKAIfb.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000035") && CompetitorCompanyID.equals("IFBCC000022")) {
                                        etKASunFlame.setText(Quantity);
                                    }

                                    //WASHING FLU


                                    if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etFLULg.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etFLUSamsung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000003")) {
                                        etFLUBosch.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etFLUOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000005")) {
                                        etFLUWhirlPool.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etFLUIfb.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000021") && CompetitorCompanyID.equals("IFBCC000024")) {
                                        etFLUBeko.setText(Quantity);
                                    }
                                    //WASHING TL

                                    if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etTLLg.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etTLSamsung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000003")) {
                                        etTLBosch.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etTLOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000005")) {
                                        etTLWhirlPool.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000006")) {
                                        etTLGodrej.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000007")) {
                                        etTLPanasonic.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etTLIfb.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000025") && CompetitorCompanyID.equals("IFBCC000017")) {
                                        etTLOnida.setText(Quantity);
                                    }
                                    //dryer

                                    if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000001")) {
                                        etWasherDisherLg.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000002")) {
                                        etWasherDisherSamsung.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000004")) {
                                        etWasherDisherOthers.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000005")) {
                                        etWasherDisherWhirlPool.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000006")) {
                                        etWasherDisherGodrej.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000007")) {
                                        etWasherDisherPanasonic.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000015")) {
                                        etWasherDisherIfb.setText(Quantity);
                                    } else if (CategoryID.equals("IFBPC1000039") && CompetitorCompanyID.equals("IFBCC000017")) {
                                        etWasherDisherOnida.setText(Quantity);
                                    }


                                    getReportListForModelPreviousMonth();


                                }


                            } else {

                                previousMonthData="false";
                                pd.dismiss();
                                //Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DisplayMatrixDynamicActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();


                //Toast.makeText(SupAttenReportActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };
       RequestQueue requestQueue = Volley.newRequestQueue(DisplayMatrixDynamicActivity.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                6000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void getReportListForModelPreviousMonth() {
        if (premonth.equals("January")) {
            int futureyear = y - 1;
            finalcialchecking = futureyear + "-" + year;
        } else if (premonth.equals("February")) {
            int futureyear = y - 1;
            finalcialchecking = futureyear + "-" + year;
        } else if (premonth.equals("March")) {
            int futureyear = y - 1;
            finalcialchecking = futureyear + "-" + year;
        } else {
            int futureyear = y + 1;
            finalcialchecking = year + "-" + futureyear;
        }

        pd.show();
        String surl = "http://111.93.182.173/IFBiOSApi/api/get_DisplayMatrixForUpdate?AEMEmployeeID=" + prefManager.getUserId() + "&FinancialYear=" + finalcialchecking + "&Month=" + premonth + "&SecurityCode=" + prefManager.getSecurityCode() + "&Opertaion=2";
        Log.d("inputtlreportpre", surl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       Log.d("responsetlreport", response);
                        sendACModelList.clear();
                        airConditionerModel.clear();
                        clothsdryerModel.clear();
                        dishwasherModel.clear();
                        microOvenModel.clear();
                        kitchenModel.clear();
                        wmFluModel.clear();
                        wmTLModel.clear();
                        dryerModel.clear();
                        // attendabceInfiList.clear();
                        pd.dismiss();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");

                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                //          Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();
                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.getJSONObject(i);
                                    String CategoryID = obj.optString("CategoryID");
                                    String ModelID = obj.optString("ModelID");
                                    String ModelName=obj.optString("ModelName");
                                    //Airconditioner
                                    if (CategoryID.equals("IFBPC1000001")) {
                                        airConditionerModel.add(CategoryID + "-" + ModelID);
                                        sendACModelList.add(ModelName);
                                    } else if (CategoryID.equals("IFBPC1000005")) {
                                        clothsdryerModel.add(CategoryID + "-" + ModelID);
                                    } else if (CategoryID.equals("IFBPC1000007")) {
                                        dishwasherModel.add(CategoryID + "-" + ModelID);
                                    } else if (CategoryID.equals("IFBPC1000011")) {
                                        microOvenModel.add(CategoryID + "-" + ModelID);
                                    } else if (CategoryID.equals("IFBPC1000035")) {
                                        kitchenModel.add(CategoryID + "-" + ModelID);
                                    } else if (CategoryID.equals("IFBPC1000021")) {
                                        wmFluModel.add(CategoryID + "-" + ModelID);
                                    } else if (CategoryID.equals("IFBPC1000025")) {
                                        wmTLModel.add(CategoryID + "-" + ModelID);
                                    }else if (CategoryID.equals("IFBPC1000039")) {
                                        dryerModel.add(CategoryID + "-" + ModelID);
                                    }


                                }

                                Set<String> set = new HashSet<String>(airConditionerModel);
                                airConditionerModel.clear();
                                airConditionerModel.addAll(set);

                                String airConditionerItem = String.valueOf(airConditionerModel);
                                String refreshairConditionerItem = airConditionerItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveAirConditionerId(refreshairConditionerItem);

                                //CTOTHS
                                Set<String> set1 = new HashSet<String>(clothsdryerModel);
                                clothsdryerModel.clear();
                                clothsdryerModel.addAll(set1);


                                String ClothsItem = String.valueOf(clothsdryerModel);
                                String refreshairClothsItem = ClothsItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveClothsDryerId(refreshairClothsItem);

                                //DISHWASHER

                                Set<String> set2 = new HashSet<String>(dishwasherModel);
                                dishwasherModel.clear();
                                dishwasherModel.addAll(set2);

                                String dishwasherItem = String.valueOf(dishwasherModel);
                                String refreshdishwasherItem = dishwasherItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveDishWasherId(refreshdishwasherItem);

                                //MICROOVEN

                                Set<String> set3 = new HashSet<String>(microOvenModel);
                                microOvenModel.clear();
                                microOvenModel.addAll(set3);

                                String microOvenItem = String.valueOf(microOvenModel);
                                String refreshmicroOvenItem = microOvenItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveMicroOvenId(refreshmicroOvenItem);

                                //Kitchen

                                Set<String> set4 = new HashSet<String>(kitchenModel);
                                kitchenModel.clear();
                                kitchenModel.addAll(set4);

                                String kaItem = String.valueOf(kitchenModel);
                                String refreshkaItem = kaItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.SaveKAItemId(refreshkaItem);

                                //WMFLU

                                Set<String> set5 = new HashSet<String>(wmFluModel);
                                wmFluModel.clear();
                                wmFluModel.addAll(set5);


                                String wmfluItem = String.valueOf(wmFluModel);
                                String refreshwmfluItem = wmfluItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveWashingFLUId(refreshwmfluItem);

                                //WMTL

                                Set<String> set6 = new HashSet<String>(wmTLModel);
                                wmTLModel.clear();
                                wmTLModel.addAll(set6);


                                String wmtlItem = String.valueOf(wmTLModel);
                                String refreshwmtlItem = wmtlItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveWashingTLId(refreshwmtlItem);

                                //Dryer

                                Set<String> set7 = new HashSet<String>(dryerModel);
                                dryerModel.clear();
                                dryerModel.addAll(set7);

                                String dryerItem= String.valueOf(dryerModel);
                                String refreshdryerItem = dryerItem.replace("[", "").replace("]", "").replaceAll("\\s+", "");
                                prefManager.saveWasherDryerId(refreshdryerItem);


                            } else {

                                   pd.dismiss();
                                //    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DisplayMatrixDynamicActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();


                //Toast.makeText(SupAttenReportActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };


        RequestQueue requestQueue = Volley.newRequestQueue(DisplayMatrixDynamicActivity.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                6000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


    private void cameraIntentforPic1() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Profile Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    private void cameraIntentforPic2() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Profile Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri1 = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri1);
        startActivityForResult(cameraIntent, CAMERA_REQUEST1);
    }

    private void cameraIntentforPic3() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Profile Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri2 = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri2);
        startActivityForResult(cameraIntent, CAMERA_REQUEST2);
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

                            BitmapFactory.Options o = new BitmapFactory.Options();
                            o.inSampleSize = 2;
                            Bitmap bm = cropToSquare(BitmapFactory.decodeFile(imageurl, o));
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bm.compress(Bitmap.CompressFormat.JPEG, 10, baos); //bm is the bitmap object
                            byte[] b = baos.toByteArray();
                            imgPic1.setImageBitmap(bm);
                            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                            String contentType = "image/jpg";
                            String[] brkDown = imageurl.split("/");
                            String name = brkDown[5];
                            stringFile = name + "_" + encodedImage + "_" + contentType;
                            pic1Flag = 1;


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
            case CAMERA_REQUEST1:

                if (resultCode == Activity.RESULT_OK) {
                    try {
                        try {
                            String imageurl = /*"file://" +*/ getRealPathFromURI(imageUri1);
                            file1 = new File(imageurl);

                            BitmapFactory.Options o = new BitmapFactory.Options();
                            o.inSampleSize = 2;
                            Bitmap bm = cropToSquare(BitmapFactory.decodeFile(imageurl, o));
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bm.compress(Bitmap.CompressFormat.JPEG, 10, baos); //bm is the bitmap object
                            byte[] b = baos.toByteArray();
                            imgPic2.setImageBitmap(bm);
                            encodedImage1 = Base64.encodeToString(b, Base64.DEFAULT);

                            String contentType = "image/jpg";
                            String[] brkDown = imageurl.split("/");
                            String name = brkDown[5];
                            stringFile1 = name + "_" + encodedImage1 + "_" + contentType;
                            pic2Flag = 1;


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
            case CAMERA_REQUEST2:

                if (resultCode == Activity.RESULT_OK) {
                    try {
                        try {
                            String imageurl = /*"file://" +*/ getRealPathFromURI(imageUri2);
                            file2 = new File(imageurl);

                            BitmapFactory.Options o = new BitmapFactory.Options();
                            o.inSampleSize = 2;
                            Bitmap bm = cropToSquare(BitmapFactory.decodeFile(imageurl, o));
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bm.compress(Bitmap.CompressFormat.JPEG, 10, baos); //bm is the bitmap object
                            byte[] b = baos.toByteArray();
                            imgPic3.setImageBitmap(bm);
                            encodedImage2 = Base64.encodeToString(b, Base64.DEFAULT);

                            String contentType = "image/jpg";
                            String[] brkDown = imageurl.split("/");
                            String name = brkDown[5];
                            stringFile2 = name + "_" + encodedImage2 + "_" + contentType;
                            pic3Flag = 1;


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


    private void postImage() {

        final ProgressDialog pd = new ProgressDialog(DisplayMatrixDynamicActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        Log.d("shubusen", "1");

        AndroidNetworking.upload("http://111.93.182.173/IFBiOSApi/api/post_DisplayMatrixWithProductCopy")
                .addMultipartParameter("AEMEmployeeID", prefManager.getUserId())
                .addMultipartParameter("CategoryID1", "IFBPC1000024")
                .addMultipartParameter("ProductCopy1", stringFile)
                .addMultipartParameter("CategoryID2", "IFBPC1000024")
                .addMultipartParameter("ProductCopy2", stringFile1)
                .addMultipartParameter("CategoryID3", "IFBPC1000001")
                .addMultipartParameter("ProductCopy3", stringFile2)
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
                            successAlert(responseText);
                            pd.dismiss();

                        } else {
                            pd.dismiss();
                            Toast.makeText(DisplayMatrixDynamicActivity.this, responseText, Toast.LENGTH_LONG).show();

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

    private void acChecking() {
        if (acFlag.equals("1")) {
            if (pic3Flag == 1) {
                postImage();
            } else {
                Toast.makeText(DisplayMatrixDynamicActivity.this, "Please Upload AC Products Image", Toast.LENGTH_LONG).show();
            }
        } else {
            postImage();

        }

    }

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }



}
