package com.nepaliapps.nepalinews.FragmentMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.nepaliapps.nepalinews.VideoActivity;
import com.nepaliapps.nepalinews.Youtube.DetailActivity;

import java.util.ArrayList;

/**
 * Created by bino on 17/2/18.
 */

public class VideoNews extends Fragment {

    Button btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.videonews,null);


        btn = view.findViewById(R.id.btnVideo);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCoMdktPbSTixAyNGwb-UYkQ&maxResults=20&order=date&key=AIzaSyBVCX3kSZCynU8HPSPUn2a5GpDQyDOu1oM";
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra("api_key", url);
                startActivity(intent);

            }

        });

        return view;
    }






}
