package com.nepaliapps.nepalinews;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nepaliapps.nepalinews.Adapter.VideosPostAdapter;
import com.nepaliapps.nepalinews.FragmentMain.ChannelFragment;
import com.nepaliapps.nepalinews.Interface.OnItemClickListener;
import com.nepaliapps.nepalinews.Pojo.YoutubeDataModel;
import com.nepaliapps.nepalinews.Youtube.DetailActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {



    private RecyclerView mList_videos ;
    private VideosPostAdapter adapter;
    private ArrayList<YoutubeDataModel> mListData = new ArrayList<>();
    private  String CHANNEL_GET_URL ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String CHANNEL_GET = getIntent().getStringExtra("api_key");
        this.CHANNEL_GET_URL =  CHANNEL_GET;
        // mList_videos = view.findViewById(R.id.mList_videos);

        initList(mListData);
        new RequestYoutubeAPI().execute();


    }

    private void initList (ArrayList<YoutubeDataModel> mListData) {


        mList_videos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new VideosPostAdapter(getApplicationContext(), mListData, new OnItemClickListener() {
            @Override
            public void onItemClick(YoutubeDataModel item) {

                YoutubeDataModel youtubeDataModel = item;
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(YoutubeDataModel.class.toString(), (Parcelable) youtubeDataModel);
                startActivity(intent);

            }
        });

        mList_videos.setAdapter(adapter);

    }

    private class RequestYoutubeAPI extends AsyncTask<Void,String,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {



            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(CHANNEL_GET_URL);
            Log.e("URL",CHANNEL_GET_URL);

            try {
                HttpResponse response = httpclient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                String json = EntityUtils.toString(httpEntity);
                return json;

            } catch (IOException e) {
                e.printStackTrace();
            }



            return null;
        }


        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if(response != null){

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("response",jsonObject.toString());
                    mListData = parseVideoListFromResponse(jsonObject);
                    initList(mListData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private ArrayList<YoutubeDataModel> parseVideoListFromResponse(JSONObject jsonObject)  {

        ArrayList<YoutubeDataModel> mList = new ArrayList<>();

        if(jsonObject.has("items")){

            try {
                JSONArray jsonArray = jsonObject.getJSONArray("items");

                for(int i =0; i < jsonArray.length();i++){

                    JSONObject json = jsonArray.getJSONObject(i);

                    if(json.has("id")){

                        JSONObject jsonID = json.getJSONObject("id");
                        String video_id ="";
                        if(jsonID.has("videoId")){
                            video_id = jsonID.getString("videoId");
                        }

                        if(jsonID.has("kind")){

                            if(jsonID.getString("kind").equals("youtube#video")){
                                YoutubeDataModel youtube = new YoutubeDataModel();
                                //get data from snippet
                                JSONObject jsonSnippet = json.getJSONObject("snippet");
                                String title = jsonSnippet.getString("title");
                                String description = jsonSnippet.getString("description");
                                String publishedAt = jsonSnippet.getString("publishedAt");
                                String thumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("high").getString("url");


                                youtube.setTitle(title);
                                youtube.setDescription(description);
                                youtube.setPublishAt(publishedAt);
                                youtube.setThumbnail(thumbnail);
                                youtube.setVideo_id(video_id);

                                mList.add(youtube);

                            }
                        }
                    }

                }

            }catch(JSONException e){

                e.printStackTrace();
            }
        }


        return mList;

    }


}
