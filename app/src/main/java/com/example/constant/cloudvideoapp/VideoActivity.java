package com.example.constant.cloudvideoapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements View.OnTouchListener {


    ProgressBar spinner;
    VideoView videoView;
    //Uri uri;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        spinner = (ProgressBar)findViewById(R.id.my_spinner);

        videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setVideoPath("https://s3-ap-southeast-1.amazonaws.com/singapore.video.app/video540.mp4");
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();

        spinner.setVisibility(View.GONE);

        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener()
        {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {

                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                {
                    spinner.setVisibility(View.GONE);
                    return true;
                }
                else if(what == MediaPlayer.MEDIA_INFO_BUFFERING_START)
                {
                    spinner.setVisibility(View.VISIBLE);
                    return false;
                }
                else
                {
                    spinner.setVisibility(View.GONE);
                    return true;
                }
            }
        });

        videoView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            videoView.start();
        }
        else if(event.getAction()==MotionEvent.ACTION_UP)
        {
            videoView.stopPlayback();
        }
        return true;
    }
}
