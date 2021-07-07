package com.genius.ifbretailer.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.genius.ifbretailer.R;


public class SalesManageDashboardActivity extends AppCompatActivity {
    LinearLayout llManage,llDelivery,llReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_manage_dashboard);
        initView();
    }
    private void initView(){
        llManage=(LinearLayout)findViewById(R.id.llManage);
        llDelivery=(LinearLayout)findViewById(R.id.llDelivery);
        llReport=(LinearLayout)findViewById(R.id.llReport);

        llManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SalesManageDashboardActivity.this,SalesManageActivity.class);
                startActivity(intent);
            }
        });

        llDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SalesManageDashboardActivity.this,DeliveryDetailsActivity.class);
                startActivity(intent);
            }
        });

        llReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SalesManageDashboardActivity.this,RefNoReportActivity.class);
                startActivity(intent);
            }
        });
    }
}