package com.genius.ifbretailer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.genius.ifbretailer.R;
import com.genius.ifbretailer.databinding.ActivityCompetitorSalesDashboardBinding;

public class CompetitorSalesDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityCompetitorSalesDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil. setContentView(this,R.layout.activity_competitor_sales_dashboard);
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
            Intent intent=new Intent(CompetitorSalesDashboardActivity.this,CompetitorSaleActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if (view==binding.llReport){
            Intent intent=new Intent(CompetitorSalesDashboardActivity.this,CompSaleReportActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if (view==binding.imgBack){
            onBackPressed();
        }else if (view==binding.imgHome){
            Intent intent=new Intent(CompetitorSalesDashboardActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }
}