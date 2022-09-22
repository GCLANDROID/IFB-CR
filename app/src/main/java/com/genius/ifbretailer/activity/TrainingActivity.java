package com.genius.ifbretailer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.genius.ifbretailer.R;
import com.genius.ifbretailer.adapter.TrainingAdapter;
import com.genius.ifbretailer.model.TrainingModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rvTraining;
    ArrayList<TrainingModel> itemList=new ArrayList<>();
    String cookie,accessToken;
    ImageView imgBack,imgHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        initView();
        getTrainingInform();
    }

    private void initView(){
        imgHome=(ImageView)findViewById(R.id.imgHome);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        accessToken=getIntent().getStringExtra("accessToken");
        cookie=getIntent().getStringExtra("cookie");
        rvTraining=(RecyclerView)findViewById(R.id.rvTraining);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(TrainingActivity.this, LinearLayoutManager.VERTICAL, false);
        rvTraining.setLayoutManager(layoutManager);
    }

    private void getTrainingInform() {
        final ProgressDialog progressDialog=new ProgressDialog(TrainingActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        AndroidNetworking.get("https://apps.bsharpcorp.com/infocapture/get_profile")
                .addHeaders("Cookie", cookie)
                .addHeaders("X-CSRF-Token", accessToken)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        JSONArray modules=response.optJSONArray("modules");
                        for (int i=3;i<modules.length();i++){
                            JSONObject obj=modules.optJSONObject(i);
                            String module_name=obj.optString("module_name");
                            String module_image=obj.optString("module_image");
                            String created_on=obj.optString("created_on");
                            String url=obj.optString("url");
                            int ratings=obj.optInt("ratings");

                            TrainingModel model=new TrainingModel();
                            model.setModelImage(module_image);
                            model.setModelName(module_name);
                            model.setRating(ratings);
                            model.setUrl(url);
                            model.setCreatedOn(created_on);
                            itemList.add(model);
                        }

                        TrainingAdapter tAdapter=new TrainingAdapter(itemList,TrainingActivity.this);
                        rvTraining.setAdapter(tAdapter);



                    }

                    @Override
                    public void onError(ANError error) {
                        progressDialog.dismiss();
                        // handle error
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view==imgBack){
            onBackPressed();
        }else if (view==imgHome){
            Intent intent=new Intent(TrainingActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }
}