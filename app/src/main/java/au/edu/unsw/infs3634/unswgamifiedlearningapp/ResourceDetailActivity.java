package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class ResourceDetailActivity extends AppCompatActivity {
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resourcedetail);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("transferMsg");
        Video video = Video.getName(msg);

        TextView name = findViewById(R.id.tvName);
        name.setText(video.getName());

        TextView desc = findViewById(R.id.tvDesc);
        desc.setText(video.getDesc());

        TextView source = findViewById(R.id.tvSource);
        source.setText(video.getSource());

        ImageView picture = findViewById(R.id.ivPicture);
        picture.setImageResource(video.getPicture());

        //youtube player function-reference: https://github.com/PierfrancescoSoffritti/android-youtube-player
        //embedded youtube video into resourcedetailactivity
        youTubePlayerView = findViewById(R.id.activity_youtubePlayerView);
        //ensures the video stops playing when exited
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String vId = video.getVideoId();
                //loads video automatically when someone clicks the item row
                youTubePlayer.loadVideo(vId, 0);
            }
        });

    }
}

