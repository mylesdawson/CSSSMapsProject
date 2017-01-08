package com.example.dude.cssshackathon;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class CleanActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    static int hp = 100;
    static int lvl = 1;
    static int hpPot = 3;
    static int arrow = 5;
    int action = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.battle_theme);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        fight();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();

    }

    private int[] mob(){
        Random ran = new Random();
        int[] ret = new int[2];
        ret[0] = ran.nextInt(100) + lvl;
        ret[1] = ran.nextInt(lvl) + lvl;
        return ret;
    }

    private void fight(){
        Random ran = new Random();
        int[] mob = mob();
        while(hp > 0 && mob[0] > 0) {
            if (action == 1) {
                mob[0] -= ran.nextInt((int)mob[0]/2) + lvl;
                hp -= ran.nextInt(mob[1]*2);
            } else if (action == 2) hp += (int)hp/3;
            else if(action == 3){
                mob[0] -= ran.nextInt((int)mob[0]/2) + lvl;
                arrow--;
            }
            else if(action == 4){
                hp = 100+lvl*2;
                finish();
            }
            action = -1;
        }
        if(hp > 0){
            lvl++;
            arrow+=ran.nextInt(lvl);
            hpPot+=ran.nextInt(lvl);
            hp = 100+lvl*2;
            return;
        }
        hp = 100+lvl*2;
    }
    public void attLis(){action = 1;}
    public void arrLis(){action = 3;}
    public void potLis(){action = 2;}
    public void runLis(){action = 4;}
}
