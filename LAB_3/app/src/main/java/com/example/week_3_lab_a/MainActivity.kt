package com.example.week_3_lab_a

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.VideoView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoView = findViewById<VideoView>(R.id.vPlayer);
        val videoUri = Uri.parse("android.resource://"+packageName+"/"+R.raw.bike);
        videoView.setVideoURI(videoUri);
        videoView.start()

    }


    public fun playVideo(view: View) {
        val videoView = findViewById<VideoView>(R.id.vPlayer);
        videoView.start()
    }
    public fun stopVideo(view: View) {
        val videoView = findViewById<VideoView>(R.id.vPlayer);
        videoView.pause()
    }
}