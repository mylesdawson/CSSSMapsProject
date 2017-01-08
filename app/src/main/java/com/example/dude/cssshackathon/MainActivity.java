package com.example.dude.cssshackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc); //OR stopService(svc);
    }

    public void startSecondActivity(View view){
        Intent intent = new Intent(this, activity_second.class);
        startActivity(intent);
    }

}
