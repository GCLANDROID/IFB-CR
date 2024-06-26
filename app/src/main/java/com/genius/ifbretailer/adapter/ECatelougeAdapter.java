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
import com.genius.ifbretailer.activity.ESubCatActivity;
import com.genius.ifbretailer.model.DownloadModel;
import com.genius.ifbretailer.model.ECatelogModel;
import com.genius.ifbretailer.model.EcatelougeModel;

import java.util.ArrayList;


public class ECatelougeAdapter extends RecyclerView.Adapter<ECatelougeAdapter.MyViewHolder> {
    ArrayList<ECatelogModel> itemList=new ArrayList<>();
    Context context;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ecatelouge_raw, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {



        myViewHolder.tvFileName.setText(itemList.get(i).getCatName());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ESubCatActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("catId",itemList.get(i).getCatId());
                intent.putExtra("ECatalogID",itemList.get(i).getECatalogID());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvFileName;
        LinearLayout llCopy;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFileName=(TextView)itemView.findViewById(R.id.tvFileName);

        }
    }

    public ECatelougeAdapter(ArrayList<ECatelogModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }
}
