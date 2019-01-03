package com.example.administrator.playsong;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    static MediaPlayer mp;
    static boolean mpAvailable = false;
    static boolean isPlaying = false;
    static Button playPause;
    static TabHost host;
    static SeekBar mediaSeekObj;
    static TextView lengthObj;
    static ImageView imgCoverObj;
    static TextView songTitleObj;
    static int position;
    TextView txvcurrentObj;
    RecyclerView rvObj;
    static ArrayList<song> songArrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*** Connect to interface ***/
        host = findViewById(R.id.tabHost);
        txvcurrentObj = findViewById(R.id.txv_current);
        mediaSeekObj = findViewById(R.id.sb_mediaSeek);
        rvObj = findViewById(R.id.rv_main);
        playPause = findViewById(R.id.btnPlayPause);
        lengthObj = findViewById(R.id.txv_length);
        imgCoverObj = findViewById(R.id.imageView);
        songTitleObj = findViewById(R.id.textView);





        /*** Creating objects ***/
        songArrayList = new ArrayList<>();

        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/91l4bhzc0il._sx355_.jpg", "Frank Sinatra - Have Yourself A Merry Little Christmas", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/01_-_frank_sinatra_-_have_yourself_a_merry_little_christmas.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/51fspwt8ol.jpg", "Bing Crosby - White Christmas", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/24_-_white_christmas.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/metallica-quiz.jpg", "Metallica - Mama Said", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/11.mama_said.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/latest_0.jpg", "Lana Del Rey - Summertime Sadness", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/09.summertime_sadness.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/paul-mccartney-hulton-archive-630x420.jpg", "Paul McCartney - Maybe I'm Amazed", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/17_-_maybe_im_amazed.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/lionel_richie_sayyousayme-99403.jpg", "Lionel Richie - Say You, Say Me", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/02-say_you_say_me.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/elton-john-goodbye-yellow-brick-road-original-hq.jpg", "Elton John - Goodbye Yellow Brick Road", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/12_-_goodbye_yellow_brick_road.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/p194k5rk8c1nph1c391qtssn3jujb.jpg", "Abba - Fernando", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/19_-_fernando.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/rs-103544-001_a_day_in_the_life.jpg", "The Beatles - A Day In The Life", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/23.a_day_in_the_life.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/68b637280dec970eb6eb54273cdc872e.jpg", "Celine Dion - Parler à Mon Père", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/01_-_parler_a_mon_pere.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/074d30487432.jpg", "Eros Ramazzotti & Tina Turner - Can't Stop Thinking Of You", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/10_-_cose_della_vita_-_cant_stop_thinking_of_you_with_tina_turner.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/1984bw.jpg", "U2 - I Still Haven't Found What I'm Looking For", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/02._i_still_havent_found_what_im_looking_for.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/photo_1359460209.jpg", "Andrea Bocelli & Helene Segara - Vivo Per Lei", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/03_-_vivo_per_lei_je_vis_pour_elle_duo_avec_andrea_bocelli.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/gipsy_kings.jpg", "Gipsy Kings & Ishtar Alabina - Ya Habibi Yalla", "https://www.tarafdari.com/sites/default/files/contents/user6984/content-sound/03.alabina.mp3"));
        songArrayList.add(new song("https://www.tarafdari.com/sites/default/files/contents/user7024/content-sound/61oeqh3tl._ss500.jpg", "Julio Iglesias - Ni Te Tengo", "https://www.tarafdari.com/sites/default/files/contents/user7024/content-sound/julio_iglesias_-_ni_te_tengo_ni_te_olvido.mp3"));

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        songRecyclerViewAdapter viewAdapter = new songRecyclerViewAdapter(getApplicationContext(), songArrayList, host);
        rvObj.setAdapter(viewAdapter);

        /*** Recyclerview setting***/
        rvObj.setItemAnimator(new DefaultItemAnimator());
        rvObj.setLayoutManager(layoutManager);

        /*** Tab setting ***/
        host.setup();
        TabHost.TabSpec spec = host.newTabSpec("Player");
        spec.setContent(R.id.playerTab);
        spec.setIndicator("Player");
        host.addTab(spec);
        spec = host.newTabSpec("List");
        spec.setContent(R.id.listTab);
        spec.setIndicator("List");
        host.addTab(spec);


        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mpAvailable && isPlaying) {
                    mediaSeekObj.setProgress(mp.getCurrentPosition());
                }
            }
        }, 1000, 1000);


        mediaSeekObj.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mp.seekTo(progress);
                }
                txvcurrentObj.setText(MillisToMin(mp.getCurrentPosition()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public static String MillisToMin(int millis) {
        int MillisInseconds = millis / 1000;
        int minutes = MillisInseconds / 60;
        int seconds = MillisInseconds - (minutes * 60);
        return minutes + ":" + seconds;
    }

    public static void selectSong(Context mc, int position) {
        MainActivity.position = position;
        song tmpSong = songArrayList.get(position);

        Glide.with(mc).asBitmap().load(tmpSong.getCover()).into(MainActivity.imgCoverObj);

        MainActivity.songTitleObj.setText(tmpSong.getTitle());

        if (MainActivity.isPlaying && MainActivity.mpAvailable) {
            MainActivity.mp.stop();
        }

        MainActivity.mp = MediaPlayer.create(mc, Uri.parse(tmpSong.getSongLink()));
        MainActivity.mp.start();
        MainActivity.mpAvailable = true;
        MainActivity.isPlaying = true;
        MainActivity.playPause.setText("Pause");
        MainActivity.mediaSeekObj.setMax(MainActivity.mp.getDuration());
        MainActivity.lengthObj.setText(MainActivity.MillisToMin(MainActivity.mp.getDuration()));

    }




    public void btnNext(View view) {
        position++;
        if (position > (songArrayList.size() - 1)) {
            position = 0;
        }
        selectSong(getApplicationContext(), position);
    }

    public void btnPrev(View view) {
        position--;
        if (position < 0) {
            position = songArrayList.size() - 1;
        }
        selectSong(getApplicationContext(), position);
    }


    public void btnPlay(View view) {
/*
        if (!mpAvailable) {
            //   mp = MediaPlayer.create(getApplicationContext(), R.raw.song);
            mpAvailable = true;
        }
*/

        if (mpAvailable) {
            if (!isPlaying) {
                mp.start();
                isPlaying = true;
                playPause.setText("Pause");
            } else {
                isPlaying = false;
                mp.pause();
                playPause.setText("Play");
            }
        } else {
            Toast.makeText(getApplicationContext(), "آهنگی انتخاب نشده است", Toast.LENGTH_SHORT).show();
        }


    }

    public void btnStop(View view) {
        if (mpAvailable) {
            mp.stop();
            mpAvailable = false;
            isPlaying = false;
            playPause.setText("Play");
            mediaSeekObj.setProgress(0);
        } else {
            Toast.makeText(getApplicationContext(), "آهنگی انتخاب نشده است", Toast.LENGTH_SHORT).show();
        }
    }

}
