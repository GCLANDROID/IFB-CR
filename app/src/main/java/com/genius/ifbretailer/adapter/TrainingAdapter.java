package com.genius.ifbretailer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.ifbretailer.R;
import com.genius.ifbretailer.model.ELearningModel;
import com.genius.ifbretailer.model.TrainingModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.MyViewHolder> {
    ArrayList<TrainingModel> itemList=new ArrayList<>();
    Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.training_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.tvModelName.setText(itemList.get(i).getModelName());
        myViewHolder.tvCreatedOn.setText(itemList.get(i).getCreatedOn());
        myViewHolder.tvRating.setText(""+itemList.get(i).getRating());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemList.get(i).getUrl()));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        try {
            Picasso.with(context)
                    .load(itemList.get(i).getModelImage())
                    .into(myViewHolder.imgModel);
        } catch (Exception e) {
            e.printStackTrace();
        }





    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgModel;
        TextView tvModelName,tvCreatedOn,tvRating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgModel=(ImageView) itemView.findViewById(R.id.imgModel);
            tvModelName=(TextView) itemView.findViewById(R.id.tvModelName);
            tvCreatedOn=(TextView) itemView.findViewById(R.id.tvCreatedOn);
            tvRating=(TextView) itemView.findViewById(R.id.tvRating);




        }
    }

    public TrainingAdapter(ArrayList<TrainingModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }
}
