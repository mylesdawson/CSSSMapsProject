package com.example.dude.cssshackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class activity_second extends AppCompatActivity {

    public Button btn;
    public static Game game = new Game("Hello.");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn = (Button) findViewById(R.id.newGameButton);

    }

    public void startNewGame(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}
