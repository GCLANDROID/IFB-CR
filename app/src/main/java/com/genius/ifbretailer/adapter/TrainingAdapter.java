package com.genius.ifbretailer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
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
import com.genius.ifbretailer.utility.PrefManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.MyViewHolder> {
    ArrayList<TrainingModel> itemList=new ArrayList<>();
    Context context;
    PrefManager prefManager;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.training_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        prefManager=new PrefManager(context);

        myViewHolder.tvModelName.setText(itemList.get(i).getModelName());
        myViewHolder.tvCreatedOn.setText(itemList.get(i).getCreatedOn());
        myViewHolder.tvRating.setText(""+itemList.get(i).getRating());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linked=createLink(itemList.get(i).getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linked));
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

    public String createLink(String des){
        String link;
        JSONObject obj=new JSONObject();
        try {
            obj.put("access_key","a1dcac1cc6b9ba47asfafaf");
            obj.put("client_id","100001100002357");
            obj.put("counter_id","R"+prefManager.getUserCode());
            obj.put("destination",des);
            obj.put("expires",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonobj=obj.toString();
        byte[] data = new byte[0];
        try {
            data = jsonobj.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64data = Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\s+", "");;
        Log.d("jsonobj",jsonobj);

        String token=base64data+"XZnvWRDDnvpWGGRmWUzLZhkN2jW5XziJEWavhhCFHbkX9jAYjNFNu9MCWoaNtcJr";
        String hashtoken=sha256String(token);
        Log.d("hashtoken",hashtoken);

         link="https://apps.bsharpcorp.com/sso_connect/"+hashtoken+"/"+base64data;
        Log.d("ssolink",link);
        return link;


    }

    public static String sha256String(String source) {
        byte[] hash = null;
        String hashCode = null;// w  ww  .  j  a va 2 s.c  o m
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(source.getBytes());
        } catch (NoSuchAlgorithmException e) {

        }

        if (hash != null) {
            StringBuilder hashBuilder = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(hash[i]);
                if (hex.length() == 1) {
                    hashBuilder.append("0");
                    hashBuilder.append(hex.charAt(hex.length() - 1));
                } else {
                    hashBuilder.append(hex.substring(hex.length() - 2));
                }
            }
            hashCode = hashBuilder.toString();
        }

        return hashCode;
    }
}
