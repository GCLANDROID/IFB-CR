package com.genius.ifbretailer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.ifbretailer.R;
import com.genius.ifbretailer.model.IncentiveModel;
import com.genius.ifbretailer.model.SalesModule;

import java.util.ArrayList;


public class IncentiveAdapter extends RecyclerView.Adapter<IncentiveAdapter.MyViewHolder> {
    ArrayList<IncentiveModel> reportList=new ArrayList<>();
    Context context;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.incentive_raw, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvMonth.setText(reportList.get(i).getIncMonth());
        myViewHolder.tvBaseTarget.setText(reportList.get(i).getBaseTarget());
        myViewHolder.tvMonthlyTarget.setText(reportList.get(i).getMonthTarget());
        myViewHolder.tvTotalSold.setText(reportList.get(i).getTotalSold());
        myViewHolder.tvPercentage.setText(reportList.get(i).getPercentage());
        myViewHolder.tvAmount.setText(reportList.get(i).getAmount());

    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMonth,tvBaseTarget,tvMonthlyTarget,tvTotalSold,tvPercentage,tvAmount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonth=(TextView)itemView.findViewById(R.id.tvMonth);
            tvBaseTarget=(TextView)itemView.findViewById(R.id.tvBaseTarget);
            tvMonthlyTarget=(TextView)itemView.findViewById(R.id.tvMonthlyTarget);
            tvTotalSold=(TextView)itemView.findViewById(R.id.tvTotalSold);
            tvPercentage=(TextView)itemView.findViewById(R.id.tvPercentage);
            tvAmount=(TextView)itemView.findViewById(R.id.tvAmount);

        }
    }

    public IncentiveAdapter(ArrayList<IncentiveModel> reportList, Context context) {
        this.reportList = reportList;
        this.context = context;
    }
}
