package com.dongyang.gg;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SlogAdapter extends RecyclerView.Adapter<SlogAdapter.CustomViewHolder> {

    private ArrayList<String> keys = null;
    private Activity context = null;


    public SlogAdapter(Activity context, ArrayList<ItemData> list) {
        this.context = context;
        this.keys = keys;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView keyword;
        protected Button select;




        public CustomViewHolder(View view) {
            super(view);
            this.keyword = (TextView) view.findViewById(R.id.keyword);
            this.select = (Button) view.findViewById(R.id.btn_slog);
            this.select.setOnClickListener(new View.OnClickListener() {
                String keyword = this.keyword;
                public void onClick(View v) {
                    Intent intentd = new Intent(context.getApplicationContext(), SearchListActivity.class);
                    intentd.putExtra("key", keyword);//수정해야함

                    context.startActivity(intentd);
                }
            });

        }
    }




    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.slog_test, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.keyword.setText(keys.get(1));


         String keyword=keys.get(1);
        viewholder.select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), SearchListActivity.class);
                intent.putExtra("keyword", keyword);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != keys ? keys.size() : 0);
    }




}