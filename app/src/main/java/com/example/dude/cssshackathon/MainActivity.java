package com.example.dude.cssshackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    public Button btnFight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFight = (Button) findViewById(R.id.fight);
    }

    public void startSecondActivity(View view){
        Intent intent = new Intent(this, activity_second.class);
        startActivity(intent);
    }

}
