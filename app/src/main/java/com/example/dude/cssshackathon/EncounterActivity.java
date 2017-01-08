package com.example.dude.cssshackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class EncounterActivity extends AppCompatActivity {
    //public static Game game = new Game("Hello.");
    public static int playerHP = 100;
    public static int playerLVL = 1;
    //public static int playerGold = 0;
    int action = -1;
    Button attack;
    Button run;
    HashMap<String, int[]> mobs = new HashMap();
    HashMap<Integer, String> numMobs = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);
        attack = (Button)findViewById(R.id.attack);
        run = (Button)findViewById(R.id.run);
        battle();
    }

    private void battle(){
        Random ran = new Random();
        String randomMob = numMobs.get(ran.nextInt(numMobs.size()));
        int[] mobStat = mobs.get(randomMob);
        while(mobStat[0] > 0 && playerHP > 0) {
            while (action == -1);
            if (action == 1) mobStat[0] -= ran.nextInt(mobStat[0]) + playerLVL;
            if (action == 4) finish();
            playerHP -= mobStat[1];
            action = -1;
        }
        if(playerHP > 0){
            playerLVL++;
        }
        else Toast.makeText(this, "You Died", Toast.LENGTH_SHORT).show();
    }

    public void attLis(){
        action = 1;
    }
    public void runLis(){
        action = 0;
    }
}