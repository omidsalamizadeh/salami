package com.example.administrator.playsong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class songRecyclerViewAdapter extends RecyclerView.Adapter<songRecyclerViewAdapter.MyViewHolder> {
    private Context mc;
    private ArrayList<song> songArrayList;
    private TabHost tabHost;


    public songRecyclerViewAdapter(Context mc, ArrayList<song> songArrayList, TabHost tabHost) {
        this.mc = mc;
        this.songArrayList = songArrayList;
        this.tabHost = tabHost;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mc).inflate(R.layout.template_song, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final song tmpSong = songArrayList.get(position);

        final int tspo = position;

        System.out.println(tmpSong.getCover());


        Glide.with(mc).asBitmap().load(tmpSong.getCover())
                .into(holder.imgObj);


        holder.titleObj.setText(tmpSong.getTitle());

        holder.cvObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isInternetOn()) {
                    tabHost.setCurrentTab(0);
                    MainActivity.selectSong(mc, tspo);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgObj;
        TextView titleObj;
        CardView cvObj;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgObj = itemView.findViewById(R.id.img_song);
            titleObj = itemView.findViewById(R.id.txv_title);
            cvObj = itemView.findViewById(R.id.cv_song);
        }
    }


    public final boolean isInternetOn() {


        ConnectivityManager connec = (ConnectivityManager) mc.getSystemService(mc.CONNECTIVITY_SERVICE);



        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {


            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(mc, " اتصال اینترنت را بررسی کنید ", Toast.LENGTH_SHORT).show();
            Toast.makeText(mc, " به اینترنت متصل نمی باشید ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

}
