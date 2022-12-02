package com.example.healite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
//import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

public class YoutubeActivity extends YouTubeBaseActivity {
    // Initialize variable
    Button btn;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

//        search.

        // Assign variable
        btn = findViewById(R.id.play);
        youTubePlayerView = findViewById(R.id.youtubePlayerView);

        // Initialize listener
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                // Load video using youtube video id
                youTubePlayer.loadVideo("lFcSrYw-ARY");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                // Display toast
                Toast.makeText(getApplicationContext(),
                        "Initialization Failed",
                        Toast.LENGTH_LONG).show();
            }
        };

        // Initialize player
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlayerView.initialize("AIzaSyB935UGWwmQ1zoWHBsi-4uef-RcLvwJVjY", onInitializedListener);
            }
        });
    }
}