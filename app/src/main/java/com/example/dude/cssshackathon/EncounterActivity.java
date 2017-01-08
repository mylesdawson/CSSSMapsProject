package com.example.dude.cssshackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.dude.cssshackathon.activity_second.game;

public class EncounterActivity extends AppCompatActivity {
    public static String latestAction;
    public static String mobName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);
        Game game = activity_second.game;
        game.encounter();
    }
    public static void getAction(){
        boolean click = false;
        while(click = false){

        }
    }
}
