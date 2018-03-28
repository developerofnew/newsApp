package com.nepaliapps.nepalinews.FragmentMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nepaliapps.nepalinews.Adapter.MyRecyclerAdapter;
import com.nepaliapps.nepalinews.Pojo.NewsPojo;
import com.nepaliapps.nepalinews.R;


import java.util.ArrayList;

/**
 * Created by bino on 17/2/18.
 */

public class VideoNews extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.videonews,null);

        //recyclerview
        RecyclerView layoutManager = v.findViewById(R.id.videonews);
        // layoutManager.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        layoutManager.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
        //layoutManager.setLayoutManager(new StaggeredGridLayoutManager(2,2));
        layoutManager.setAdapter(new MyRecyclerAdapter(this.getActivity(),getVideos()));

        return v;
    }

    //set title for the tab
    private ArrayList<NewsPojo> getVideos(){

        ArrayList<NewsPojo> newsPojo = new ArrayList<>();
        NewsPojo news = new NewsPojo("https://stackoverflow.com/questions/2169294/how-to-add-manifest-permission-to-android-application/2169311",R.drawable.pen);
        newsPojo.add(news);

        news= new NewsPojo("https://stackoverflow.com/questions/2169294/how-to-add-manifest-permission-to-android-application/2169311",R.drawable.youtube);
        newsPojo.add(news);

        news= new NewsPojo("https://stackoverflow.com/questions/2169294/how-to-add-manifest-permission-to-android-application/2169311",R.drawable.mobile);
        newsPojo.add(news);

        news= new NewsPojo("himalayan",R.drawable.compass);
        newsPojo.add(news);

        news= new NewsPojo("himalayan",R.drawable.google);
        newsPojo.add(news);

        return newsPojo;
    }


    @Override
    public String toString() {
        return  "Video News";
    }
}
