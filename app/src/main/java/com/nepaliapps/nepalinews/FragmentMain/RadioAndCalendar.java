package com.nepaliapps.nepalinews.FragmentMain;

import android.app.FragmentManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.nepaliapps.nepalinews.Adapter.MyFragmentAdapter;
import com.nepaliapps.nepalinews.Adapter.MyRecyclerAdapter;
import com.nepaliapps.nepalinews.CheckNetwork;
import com.nepaliapps.nepalinews.MainActivity;
import com.nepaliapps.nepalinews.MyService;
import com.nepaliapps.nepalinews.Pojo.NewsPojo;
import com.nepaliapps.nepalinews.R;
import com.nepaliapps.nepalinews.RadioActivity;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by bino on 28/3/2018.
 */

public class RadioAndCalendar extends Fragment implements View.OnClickListener, AudioManager.OnAudioFocusChangeListener {
     ProgressBar progressBar;
    GridLayout mainGrid;
    private  static String STREAM_URL;
    private ProgressDialog dialog;
    private static MediaPlayer player;
    private AudioManager audioManager;
    private static boolean isPlaying = false;
    private  static Context context;
    ImageButton btnPlay1,btnPlay2,btnPlay3,btnPlay4,btnPlay5,btnPlay6,btnPlay7,
                btnPlay8,btnPlay9,btnPlay10,btnPlay11,btnPlay12,btnPlay13,
                btnPlay14;
    ImageButton  btnPause1, btnPause2, btnPause3, btnPause4, btnPause5, btnPause6,
            btnPause7,btnPause8,btnPause9,btnPause10,btnPause11,btnPause12,btnPause13,
            btnPause14;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.radio_fragment,null);
        context = getContext();
       // player = new MediaPlayer();



       // audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);



        initPhoneCallListener();
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        mainGrid = view.findViewById(R.id.mainGrid);
        btnPlay1 = view.findViewById(R.id.btnPlay1);
        btnPlay2 = view.findViewById(R.id.btnPlay2);
        btnPlay3 = view.findViewById(R.id.btnPlay3);
        btnPlay4 =  view.findViewById(R.id.btnPlay4);
        btnPlay5 = view.findViewById(R.id.btnPlay5);
        btnPlay6 = view.findViewById(R.id.btnPlay6);
        btnPlay7 = view.findViewById(R.id.btnPlay7);
        btnPlay8 = view.findViewById(R.id.btnPlay8);
        btnPlay9 = view.findViewById(R.id.btnPlay9);
        btnPlay10 = view.findViewById(R.id.btnPlay10);
        btnPlay11 = view.findViewById(R.id.btnPlay11);
        btnPlay12 =  view.findViewById(R.id.btnPlay12);
        btnPlay13 = view.findViewById(R.id.btnPlay13);
        btnPlay14= view.findViewById(R.id.btnPlay14);


        btnPlay1.setOnClickListener(this);
        btnPlay2.setOnClickListener(this);
        btnPlay3.setOnClickListener(this);
        btnPlay4.setOnClickListener(this);
        btnPlay5.setOnClickListener(this);
        btnPlay6.setOnClickListener(this);
        btnPlay7.setOnClickListener(this);
        btnPlay8.setOnClickListener(this);
        btnPlay9.setOnClickListener(this);
        btnPlay10.setOnClickListener(this);
        btnPlay11.setOnClickListener(this);
        btnPlay12.setOnClickListener(this);
        btnPlay13.setOnClickListener(this);
        btnPlay14.setOnClickListener(this);


        btnPause1 = view.findViewById(R.id.btnPause1);
        btnPause2 = view.findViewById(R.id.btnPause2);
        btnPause3 = view.findViewById(R.id.btnPause3);
        btnPause4 =  view.findViewById(R.id.btnPause4);
        btnPause5 = view.findViewById(R.id.btnPause5);
        btnPause6 = view.findViewById(R.id.btnPause6);
        btnPause7 = view.findViewById(R.id.btnPause7);
        btnPause8 = view.findViewById(R.id.btnPause8);
        btnPause9 = view.findViewById(R.id.btnPause9);
        btnPause10 = view.findViewById(R.id.btnPause10);
        btnPause11 = view.findViewById(R.id.btnPause11);
        btnPause12 =  view.findViewById(R.id.btnPause12);
        btnPause13 = view.findViewById(R.id.btnPause13);
        btnPause14= view.findViewById(R.id.btnPause14);

       btnPause1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //stopRadio();

               getActivity().stopService(new Intent(getActivity(),MyService.class));

           }
       });

//       btnPause2.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               player.stop();
//           }
//       });
//
//       btnPause3.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               player.stop();
//
//           }
//       });
//
//       btnPause4.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               stopRadio();
//           }
//       });
//
//       btnPause5.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               stopRadio();
//           }
//       });
//
//       btnPause6.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               stopRadio();
//           }
//       });
//
//       btnPause7.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               stopRadio();
//           }
//       });
//
//       btnPause8.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               stopRadio();
//           }
//       });
//
//        btnPause9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stopRadio();
//            }
//        });
//
//        btnPause10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stopRadio();
//            }
//        });
//
//        btnPause11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stopRadio();
//            }
//        });
//
//        btnPause12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stopRadio();
//            }
//        });
//
//        btnPause13.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stopRadio();
//            }
//        });
//
//        btnPause14.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stopRadio();
//            }
//        });
//



        return view;
    }

    private void initPhoneCallListener() {
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                if (state == TelephonyManager.CALL_STATE_RINGING) {
                    //Incoming call: Pause music

                    getActivity().stopService(new Intent(getActivity(),MyService.class));

                    //player.pause();
                } else if (state == TelephonyManager.CALL_STATE_IDLE) {
                    //Not in call: Play music
                   // playMusic();
                    //playMusic();
//                    Log.d(TAG, "onCallStateChanged: ");
//                    resumeVideo();
                } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                    //A call is dialing, active or on hold
                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };

        TelephonyManager mgr = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        if (mgr != null) {
            mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }


    public static void customBigNotification(Context context){
        RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.big_notification);

        NotificationCompat.Builder nc = new NotificationCompat.Builder(context);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
       // Intent notifyIntent = new Intent(context, MainActivity.class);

        Intent notifyIntent = new Intent(context, RadioAndCalendar.class);
        notifyIntent.setAction(Intent.ACTION_MAIN);
        notifyIntent.addCategory(Intent.CATEGORY_LAUNCHER);

//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                intent, 0);
//        mBuilder.setContentIntent(pendingIntent);

        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        nc.setContentIntent(pendingIntent);
        nc.setSmallIcon(R.drawable.annapurna);
        nc.setAutoCancel(true);
        nc.setCustomBigContentView(expandedView);
        nc.setContentTitle("Music Player");
        nc.setContentText("Control Audio");
       // nc.getBigContentView().setTextViewText(R.id.textSongName, "Adele");

        //setListeners(expandedView, context);


        assert nm != null;
        nm.notify(9, nc.build());
    }





    @Override
    public void onClick(View v) {

        switch ((v.getId())) {

            case R.id.btnPlay1:

                STREAM_URL = "http://kantipur-stream.softnep.com:7248/";
                break;

            case R.id.btnPlay2:
                STREAM_URL = "http://kalika-stream.softnep.com:7740/";
                break;

            case R.id.btnPlay3:
               //image fm
                STREAM_URL = "http://streaming.hamropatro.com:8631/;stream.mp3";
                break;
            case R.id.btnPlay4:
                STREAM_URL =  "http://www.radionepal.gov.np:40100/stream";

                break;

            case R.id.btnPlay5:
                //ujyalo
                STREAM_URL = "http://stream.zenolive.com/wtuvp08xq1duv";
                break;

            case R.id.btnPlay6:

                STREAM_URL = "http://stream.zenolive.com/wtuvp08xq1duv";
                break;

            case R.id.btnPlay7:
                STREAM_URL = "http://kalika-stream.softnep.com:7740/";
                break;

            case R.id.btnPlay8:
                //Hits fm
                STREAM_URL = "http://198.27.83.198:5098/stream";

                break;
            case R.id.btnPlay9:

                break;

            case R.id.btnPlay10:

                break;

            case R.id.btnPlay11:
                STREAM_URL = "http://kalika-stream.softnep.com:7740/";
                break;

            case R.id.btnPlay12:

                break;
            case R.id.btnPlay13:

                break;

            case R.id.btnPlay14:

                break;

            default:
                break;
        }


      //  if (isPlaying) {
//           // stopRadio();
           // getActivity().stopService(new Intent(getActivity(),MyService.class));

         //   Intent intent = new Intent(getActivity(),MyService.class);
           // intent.putExtra("url",STREAM_URL);
        //    getActivity().stopService(intent);


//            if (player != null && player.isPlaying()) {
//                player.stop();
//                player.reset();
//
//                player = null;
//            }
     //   } else
//
//            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//        int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC,
//                AudioManager.AUDIOFOCUS_GAIN);
//
//        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//            // could not get audio focus.
//
//            startRadio();
//
//       }

      //
         Intent   intent = new Intent(getActivity(),MyService.class);
        intent.putExtra("url",STREAM_URL);
        getActivity().startService(intent);



    }





    //Pressing play button
//    public void startRadio() {
 //       if (CheckNetwork.isNetwrokAvailable(context)) {
          //  dialog.show();
//            progressBar.setVisibility(View.VISIBLE);
 //           if (!isPlaying) {
               // btnPlay1.setImageResource(android.R.drawable.ic_media_pause);
                //playMusic();
//
//                getActivity().startService(new Intent(getActivity(),MyService.class));
//
//
//                customBigNotification(getContext());
//
//            } else {
//                //stopRadio();
//            }
//        } else {
//            if (isPlaying) {
//               // stopRadio();
//            }
//            Toast.makeText(context,"No internet connection!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//    }






    //Listen for the volume button events
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == event.KEYCODE_VOLUME_DOWN) {
//            int currVolume = bar.getProgress();
//            bar.setProgress(currVolume - 1);
//            return true;
//        } else if (keyCode == event.KEYCODE_VOLUME_UP) {
//            int currVolume = bar.getProgress();
//            bar.setProgress(currVolume + 1);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        //stopRadio();
    }

    @Override
    public void onResume() {
        super.onResume();
        //if (dialog != null && dialog.isShowing()) dialog.dismiss();
        //Runtime.getRuntime().gc();
        // startRadio();
    }



    // check if any phone call receives, if so, stop music
    @Override
    public void onAudioFocusChange(int focusChange) {



        if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            audioManager.abandonAudioFocus(this);
           // player.stop();

            getActivity().stopService(new Intent(getActivity(),MyService.class));


        }



        switch (focusChange)
        {
            case AudioManager.AUDIOFOCUS_GAIN:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
               // playMusic(); // Resume your media player here
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
              //  player.stop();// Pause your media player here

                getActivity().stopService(new Intent(getActivity(),MyService.class));
                break;
        }



//
//        if (focusChange <= 0) {
//            //LOSS -> pause
//
//            stopRadio();
//        } else {
//            // GAIN -> play
//            startRadio();
//        }
   }



}




