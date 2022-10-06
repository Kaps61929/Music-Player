package com.example.mediaapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play=findViewById(R.id.play);
        Button pause=findViewById(R.id.pause);
        SeekBar bar =findViewById(R.id.bar);
//        mediaplayer =MediaPlayer.create(this,R.raw.music);
        mediaplayer=new MediaPlayer();
        try {
            mediaplayer.setDataSource("http://penguinradio.dominican.edu/Sound%20FX%20Collection/20th%20Century%20Fox.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                bar.setMax(mediaplayer.getDuration());
                bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(fromUser){
                    mediaplayer.seekTo(progress);}
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });


        play.setOnClickListener(v -> mediaplayer.start());
        mediaplayer.prepareAsync();
        pause.setOnClickListener(v -> mediaplayer.pause());

        }


    }


