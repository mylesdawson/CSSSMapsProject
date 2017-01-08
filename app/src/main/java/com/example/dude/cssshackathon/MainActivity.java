package com.example.dude.cssshackathon;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.all_of_us);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        Button loadB = (Button)findViewById(R.id.loadB);
        loadB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BattleActivity tmp = new BattleActivity();
                tmp.load();
            }
        });
    }

    //skipping second activity for now
    public void startSecondActivity(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();

    }


}
