package com.nepaliapps.nepalinews.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nepaliapps.nepalinews.Interface.OnItemClickListener;
import com.nepaliapps.nepalinews.Pojo.YoutubeDataModel;
import com.nepaliapps.nepalinews.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//import com.squareup.picasso.Picasso;

/**
 * Created by bino on 23/3/2018.
 */

public class VideosPostAdapter extends RecyclerView.Adapter<VideosPostAdapter.YoutubePostHolder> {



    private ArrayList<YoutubeDataModel> dataSet;
    private Context mContext = null;
    private final OnItemClickListener listener;

    public VideosPostAdapter(Context mContext, ArrayList<YoutubeDataModel> dataSet, OnItemClickListener listener) {
        this.dataSet = dataSet;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public YoutubePostHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_post_layout,parent,false);

        YoutubePostHolder postHolder = new YoutubePostHolder(view);

        return postHolder;
    }

    @Override
    public void onBindViewHolder(YoutubePostHolder holder, int position) {



        TextView textViewTitle = holder.textViewTitle;
        TextView textViewDes = holder.textViewDes;
        TextView textViewDate = holder.textViewDate;
        ImageView imageViewThumb = holder.imageViewThumb;

        YoutubeDataModel object = dataSet.get(position);

        textViewTitle.setText(object.getTitle());
        textViewDes.setText(object.getDescription());
        textViewDate.setText(object.getPublishAt());
        holder.bind(dataSet.get(position),listener);

        //TODO: image will be downloaded from url

        Picasso.with(mContext).load(object.getThumbnail()).into(imageViewThumb);



    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class YoutubePostHolder extends RecyclerView.ViewHolder{


     TextView textViewTitle;
     TextView textViewDes;
     TextView textViewDate;
     ImageView imageViewThumb;


        public YoutubePostHolder(View itemView) {
            super(itemView);

       this.textViewTitle = itemView.findViewById(R.id.textViewTitle);
       this.textViewDes = itemView.findViewById(R.id.textViewDes);
       this.textViewDate = itemView.findViewById(R.id.textviewDate);
       this.imageViewThumb = itemView.findViewById(R.id.imageViewThumb);

        }
        public void bind(final YoutubeDataModel item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}



