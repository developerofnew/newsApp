package com.nepaliapps.nepalinews.FragmentMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nepaliapps.nepalinews.Adapter.MyRecyclerAdapter;
import com.nepaliapps.nepalinews.Pojo.NewsPojo;
import com.nepaliapps.nepalinews.R;

import java.util.ArrayList;

/**
 * Created by bino on 17/2/18.
 */

public class NewsPaper extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.newspaper, null);

        RecyclerView recyclerView =  v.findViewById(R.id.recyclerview_newspaper);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
        recyclerView.setAdapter(new MyRecyclerAdapter(this.getActivity(),get()));

        return v;
    }

    private ArrayList<NewsPojo> get() {

        ArrayList<NewsPojo> newsPojos = new ArrayList<>();

        NewsPojo news = new NewsPojo("https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCoMdktPbSTixAyNGwb-UYkQ&maxResults=20&order=date&key=AIzaSyBVCX3kSZCynU8HPSPUn2a5GpDQyDOu1oM",R.drawable.compass);

        newsPojos.add(news);

        news = new NewsPojo("http://www.news24nepal.tv/",R.drawable.youtube);

        newsPojos.add(news);

        news = new NewsPojo("http://baahrakhari.com/",R.drawable.google);

        newsPojos.add(news);

        news = new NewsPojo("http://baahrakhari.com/",R.drawable.tree);

        newsPojos.add(news);



        return newsPojos;
    }
}


