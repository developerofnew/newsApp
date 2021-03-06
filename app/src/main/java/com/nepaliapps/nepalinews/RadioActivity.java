package com.nepaliapps.nepalinews;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.nepaliapps.nepalinews.FragmentMain.RadioAndCalendar;

import java.io.IOException;

public class RadioActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {


    private ProgressDialog dialog;
    private  static Context context;
    private  static String STREAM_URL;
    private ImageButton btnPlay;
    private static MediaPlayer player;
    private SeekBar bar;
    private AudioManager audioManager;
    private static boolean isPlaying = false;
    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        context = RadioActivity.this;

        //this.STREAM_URL = getIntent().getStringExtra("web_key");

         mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);



}


    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(RadioActivity.this,MainActivity.class);
                    intent.putExtra("info","This is activity from card item index  "+finalI);
                    startActivity(intent);

                }
            });
        }
    }


//                            if (isPlaying) {
//                                stopRadio();
//                            }
//
//                            STREAM_URL = "http://kantipur-stream.softnep.com:7248/";
//                            init();
//                            startRadio();
//


       // init();
       // startRadio();

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isPlaying) {
//                    stopRadio();
//                }
//
//                STREAM_URL = "http://kantipur-stream.softnep.com:7248/";
//                init();
//                startRadio();
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isPlaying) {
//                    stopRadio();
//                }
//
//                STREAM_URL = "http://kalika-stream.softnep.com:7740/";
//                init();
//                startRadio();
//            }
//        });









    private void init() {


        //TextView desc = (TextView) findViewById(R.id.textView);
      //  desc.setText("dexc");

//        dialog = new ProgressDialog(context);
//        dialog.setMessage("loading");
//        dialog.setCancelable(true);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//
        player = new MediaPlayer();
        audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
//        btnPlay = (ImageButton) findViewById(R.id.btnPlayPause);
//        btnPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startRadio();
//            }
//        });
//        btnPlay.setImageResource(android.R.drawable.ic_media_play);

       // seekBarStuff();
    }

//    private void seekBarStuff() {
//        bar = (SeekBar) findViewById(R.id.seekBar);
//        bar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
//        bar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
//        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//    }



    //Pressing play button
    public void startRadio() {
        if (CheckNetwork.isNetwrokAvailable(context)) {
            dialog.show();
            if (!isPlaying) {
                btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                playMusic();
            } else {
                stopRadio();
            }
        } else {
            if (isPlaying) {
                stopRadio();
            }
            Toast.makeText(context,"no internet", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void stopRadio() {
        isPlaying = false;
        if (dialog != null) dialog.dismiss();
        if (player != null && player.isPlaying()) {
            player.stop();
            player.reset();
        }
        btnPlay.setImageResource(android.R.drawable.ic_media_play);
    }

    @Override
    public void onBackPressed() {

        //keep music playing but doesnot goes to previous activity

    String kantipur = "http://kalika-stream.softnep.com:7740/";

            Intent openMainActivity = new Intent(RadioActivity.this, MainActivity.class);
            openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(openMainActivity, 2);


//                Intent mainActivity = new Intent(Intent.ACTION_MAIN);
//                mainActivity.addCategory(Intent.CATEGORY_HOME);
//                mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);





        //keep music playing but goes to home

        //go back to main but plays many radio

//        Intent intent = new Intent(RadioActivity.this,MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);

        //go back to same fragment but stop playing
//
    //   FragmentManager fm = getFragmentManager();
    //    if (fm.getBackStackEntryCount() > 0) {

//

//
//            startActivity(mainActivity);
          //  fm.popBackStack();

      //    } else {
//
//
       //     super.onBackPressed();
      //  }


      //  moveTaskToBack(true);

    }



//    void gobacktopreviousactivity(){
//        stop music player
//        player.stop();
//        player.release();
//    }

    private void playMusic() {
        new Thread((new Runnable() {
            @Override
            public void run() {
                try {
                    player.setAudioStreamType(audioManager.STREAM_MUSIC);
                    player.setDataSource(STREAM_URL);
                    player.prepareAsync();
                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                            isPlaying = true;
                            if (dialog != null) dialog.dismiss();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        })).start();
    }

    //Listen for the volume button events
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_VOLUME_DOWN) {
            int currVolume = bar.getProgress();
            bar.setProgress(currVolume - 1);
            return true;
        } else if (keyCode == event.KEYCODE_VOLUME_UP) {
            int currVolume = bar.getProgress();
            bar.setProgress(currVolume + 1);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //stopRadio();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dialog != null && dialog.isShowing()) dialog.dismiss();
        //Runtime.getRuntime().gc();
       // startRadio();
    }



    // check if any phone call receives, if so, stop music
    @Override
    public void onAudioFocusChange(int focusChange) {
        if (focusChange <= 0) {
            //LOSS -> pause
            stopRadio();
        } else {
            // GAIN -> play
            startRadio();
        }
    }


}
