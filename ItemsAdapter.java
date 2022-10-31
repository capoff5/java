package com.dongyang.gg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.CustomViewHolder> {

    private ArrayList<ItemData> mList = null;
    private Activity context = null;





    public ItemsAdapter(Activity context, ArrayList<ItemData> list) {
        this.context = context;
        this.mList = list;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView pic;
        protected TextView name;
        protected TextView price;
        protected Button num;





        public CustomViewHolder(View view) {
            super(view);



            this.pic = (TextView) view.findViewById(R.id.textView_list_pic);
            this.name = (TextView) view.findViewById(R.id.textView_list_name);
            this.price = (TextView) view.findViewById(R.id.textView_list_price);
            this.num=(Button) view.findViewById(R.id.btn_list_detail);
            this.num.setOnClickListener(new View.OnClickListener() {
                private int num;

                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), DetailAdapter.class);
                    intent.putExtra("inum", num);

                    context.startActivity(intent);
                }
            });

        }
    }




    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_test, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.pic.setText(mList.get(position).getItem_pic());
        viewholder.name.setText(mList.get(position).getItem_name());
        viewholder.price.setText(mList.get(position).getItem_price());
        viewholder.num.setText(mList.get(position).getItem_num());

         String inum=mList.get(position).getItem_num();
        viewholder.num.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), DetailListActivity.class);
               intent.putExtra("i_num", inum);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }




}