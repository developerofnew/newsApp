package com.nepaliapps.nepalinews.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.nepaliapps.nepalinews.Interface.ItemClickInterface;
import com.nepaliapps.nepalinews.Pojo.NewsPojo;
import com.nepaliapps.nepalinews.R;
import com.nepaliapps.nepalinews.WebView;

import java.util.ArrayList;

/**
 * Created by bino on 17/2/18.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private ArrayList<NewsPojo> newsPojos = new ArrayList<>();
    private Context c;

    public MyRecyclerAdapter(Context c, ArrayList<NewsPojo> newsPojos) {

        this.c = c;
        this.newsPojos = newsPojos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, null);
        ViewHolder holder = new ViewHolder(view);


        holder.img = view.findViewById(R.id.img_model);
        holder.webLink = view.findViewById(R.id.model_web_link);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final String url = newsPojos.get(position).getWebLink();

        holder.img.setImageResource(newsPojos.get(position).getImage());
        holder.webLink.setText(url);

        holder.setItemClick(new ItemClickInterface() {
            @Override
            public void onClick(int position) {

                Intent intent = new Intent(c, WebView.class);
                intent.putExtra("web_key", url);
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsPojos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView img;
        TextView webLink;

        ItemClickInterface itemClickInterface;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        public void setItemClick(ItemClickInterface itemClickInterface) {

            this.itemClickInterface = itemClickInterface;
        }

        @Override
        public void onClick(View v) {

            this.itemClickInterface.onClick(this.getLayoutPosition());


        }
    }
}
