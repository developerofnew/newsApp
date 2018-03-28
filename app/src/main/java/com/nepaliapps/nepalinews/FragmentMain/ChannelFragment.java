package com.nepaliapps.nepalinews.FragmentMain;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nepaliapps.nepalinews.Adapter.VideosPostAdapter;
import com.nepaliapps.nepalinews.Interface.OnItemClickListener;
import com.nepaliapps.nepalinews.Pojo.YoutubeDataModel;
import com.nepaliapps.nepalinews.R;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment {

  private static String GOOGLE_YOUTUBE_API_KEY = "AIzaSyBVCX3kSZCynU8HPSPUn2a5GpDQyDOu1oM";
  private static String CHANNEL_ID = "UCoMdktPbSTixAyNGwb-UYkQ";
  private static String CHANNEL_GET_URL ="";
    //"https://www.googleapis.com/youtube/v3/search?part=snippet&channelId="+CHANNEL_ID+"&maxResults=20&order=date&key="+GOOGLE_YOUTUBE_API_KEY+"";
//"https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCoMdktPbSTixAyNGwb-UYkQ&maxResults=20&order=date&key=AIzaSyBVCX3kSZCynU8HPSPUn2a5GpDQyDOu1oM;



/*
    private static String GOOGLE_YOUTUBE_API_KEY = "AIzaSyAdDix7i7a3an-gyXiquTV_14cIsr8-DZg";//here you should use your api key for testing purpose you can use this api also
    private static String CHANNEL_ID = "UCoMdktPbSTixAyNGwb-UYkQ"; //here you should use your channel id for testing purpose you can use this api also
    private static String CHANNEL_GET_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&channelId=" + CHANNEL_ID + "&maxResults=20&key=" + GOOGLE_YOUTUBE_API_KEY + "";

*/


private RecyclerView mList_videos ;
private VideosPostAdapter adapter;
private ArrayList<YoutubeDataModel> mListData = new ArrayList<>();

    public ChannelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_channel, container, false);

        String CHANNEL_GET =
        getActivity().getIntent().getStringExtra("api_key");
        ChannelFragment.this.CHANNEL_GET_URL =  CHANNEL_GET;
       // mList_videos = view.findViewById(R.id.mList_videos);

        initList(mListData);
        new RequestYoutubeAPI().execute();

      return  view;
    }

    private void initList (ArrayList<YoutubeDataModel> mListData){


     mList_videos.setLayoutManager(new LinearLayoutManager(getActivity()));
     adapter = new VideosPostAdapter(getActivity(), mListData, new OnItemClickListener() {
         @Override
         public void onItemClick(YoutubeDataModel item) {

             YoutubeDataModel youtubeDataModel = item;
             Intent intent = new Intent(getActivity(), DetailActivity.class);
             intent.putExtra(YoutubeDataModel.class.toString(), (Parcelable) youtubeDataModel);
             startActivity(intent);

         }
     });


     mList_videos.setAdapter(adapter);


    }

    private class RequestYoutubeAPI extends AsyncTask<Void,String,String>{


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
                HttpResponse   response = httpclient.execute(httpGet);
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
