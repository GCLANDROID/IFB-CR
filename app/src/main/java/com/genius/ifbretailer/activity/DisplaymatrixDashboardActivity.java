package com.genius.ifbretailer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.genius.ifbretailer.R;
import com.genius.ifbretailer.databinding.ActivityDisplaymatrixDashboardBinding;

public class DisplaymatrixDashboardActivity extends AppCompatActivity implements View.OnClickListener {
   ActivityDisplaymatrixDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_displaymatrix_dashboard);
        initView();
    }

    private void initView(){
        binding.llManage.setOnClickListener(this);
        binding.llReport.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.imgHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==binding.llManage){
            Intent intent=new Intent(DisplaymatrixDashboardActivity.this,DisplayMatrixDynamicActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if (view==binding.llReport){
            Intent intent=new Intent(DisplaymatrixDashboardActivity.this,DisplayMatrixReportActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if (view==binding.imgBack){
            onBackPressed();
        }else if (view==binding.imgHome){
            onBackPressed();
        }
    }
}