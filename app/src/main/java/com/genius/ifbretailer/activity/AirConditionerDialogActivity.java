package com.genius.ifbretailer.activity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.ifbretailer.R;
import com.genius.ifbretailer.adapter.AirConditionerDialogItemAdapter;
import com.genius.ifbretailer.model.DialogItemModule;
import com.genius.ifbretailer.model.DisplayMatrixModel;
import com.genius.ifbretailer.utility.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class AirConditionerDialogActivity extends AppCompatActivity {
    ArrayList<DialogItemModule> itemList = new ArrayList<>();
    RecyclerView rvItem;
    AirConditionerDialogItemAdapter itemAdapter;
    LinearLayout llCancel;
    LinearLayout llLoader, llMain, llAgain, llSave;
    PrefManager prefManager;
    ArrayList<String> item = new ArrayList<>();
    String itemId = "";
    String categoryId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_air_conditioner_dialog);
        this.setFinishOnTouchOutside(false);
        initialize();
        getDialogItemList();
        setAdapter();
        onClick();
    }

    private void initialize() {
        prefManager = new PrefManager(AirConditionerDialogActivity.this);
        rvItem = (RecyclerView) findViewById(R.id.rvItem);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(AirConditionerDialogActivity.this, LinearLayoutManager.VERTICAL, false);
        rvItem.setLayoutManager(layoutManager);
        llCancel = (LinearLayout) findViewById(R.id.llCancel);
        llLoader = (LinearLayout) findViewById(R.id.llLoader);
        llMain = (LinearLayout) findViewById(R.id.llMain);
        llSave = (LinearLayout) findViewById(R.id.llSave);
        llAgain = (LinearLayout) findViewById(R.id.llAgain);
        categoryId="IFBPC1000001";
    }

    private void getDialogItemList() {
        llLoader.setVisibility(View.VISIBLE);
        llMain.setVisibility(View.GONE);
        llAgain.setVisibility(View.GONE);
        String surl = "http://111.93.182.173/IFBiOSApi/api/ModelByCategory?CategoryID="+categoryId+"&SecurityCode=" + prefManager.getSecurityCode();
        Log.d("inputReport", surl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("responseAttendance", response);

                        // attendabceInfiList.clear();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("responseAir", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");

                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {
                                //          Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();
                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.getJSONObject(i);
                                    String ModelCode = obj.optString("ModelCode");
                                    String ModelName = obj.optString("ModelName");

                                    DialogItemModule itemModel = new DialogItemModule(ModelName, ModelCode);
                                    itemList.add(itemModel);


                                }

                                llLoader.setVisibility(View.GONE);
                                llMain.setVisibility(View.VISIBLE);
                                llAgain.setVisibility(View.GONE);
                                /*llNodata.setVisibility(View.GONE);
                                llAgain.setVisibility(View.GONE);*/

                            } else {
                                llLoader.setVisibility(View.GONE);
                                llMain.setVisibility(View.GONE);
                                llAgain.setVisibility(View.GONE);

                                Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AirConditionerDialogActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                llLoader.setVisibility(View.GONE);
                llMain.setVisibility(View.GONE);
                llAgain.setVisibility(View.VISIBLE);

                //Toast.makeText(SupAttenReportActivity.this, "volly 2"+error.toString(), Toast.LENGTH_LONG).show();
                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(AirConditionerDialogActivity.this);
        requestQueue.add(stringRequest);

    }

    private void setAdapter() {
        itemAdapter = new AirConditionerDialogItemAdapter(itemList, AirConditionerDialogActivity.this);
        rvItem.setAdapter(itemAdapter);
    }


    public void updateItemStatus(int position, boolean status) {
        itemList.get(position).setSelected(status);
        if (itemList.get(position).isSelected() == true) {
            item.add("IFBPC1000001" + "-" + itemList.get(position).getItemId());
            int size=item.size();
            String itemsize= String.valueOf(size);
            DisplayMatrixModel model=new DisplayMatrixModel();
            model.setEditVolume(itemsize);
            prefManager.saveAirIfbSize(size);
            Log.d("airifbsize", String.valueOf(prefManager.getAirIfbSize()));
        } else {
            item.clear();
        }


        Log.d("arpan", item.toString());
        String i = item.toString();
        String d = i.replace("[", "").replace("]", "");
        itemId = d.replaceAll("\\s+", "");
        Log.d("commas", itemId);
        prefManager.saveAirConditionerId(itemId);



        itemAdapter.notifyDataSetChanged();
    }

    private void onClick() {
        llCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        llSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
