package com.example.dude.cssshackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class BattleActivity extends AppCompatActivity {

    ImageView playerImg;
    ImageView enemyImg;
    Button attackBtn;
    Button itemsBtn;
    Button abilitiesBtn;
    Button runBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        playerImg = (ImageView) findViewById(R.id.playerImg);
        enemyImg = (ImageView) findViewById(R.id.mobImg);
        attackBtn = (Button) findViewById(R.id.attack);
        itemsBtn = (Button) findViewById(R.id.items);
        abilitiesBtn = (Button) findViewById(R.id.abilities);
        runBtn = (Button) findViewById(R.id.run);

        playerImg.setImageResource(R.mipmap.farengar_sprite);
        enemyImg.setImageResource(R.mipmap.wraith);

        battle();

    }

    //public static Game game = new Game("Hello.");
    public static int playerHP = 100;
    public static int playerLVL = 1;
    //public static int playerGold = 0;
    int action = -1;


    private int[] randomizeMob(){
        Random ran = new Random();
        int[] ret = new int[2];
        ret[0] = ran.nextInt(playerLVL) + playerLVL;
        ret[1] = ran.nextInt(playerLVL) + playerLVL;
        return ret;
    }

    private void battle(){
        Random ran = new Random();
        int[] mobStat = randomizeMob();
        while(mobStat[0] > 0 && playerHP > 0) {
            while (action == -1);
            if (action == 1) mobStat[0] -= ran.nextInt(mobStat[0]) + playerLVL;
            if (action == 4) finish();
            playerHP -= mobStat[1];
            action = -1;
        }
        if(playerHP > 0){
            playerLVL++;
            playerHP = 100 + playerLVL*2;
        }
        else Toast.makeText(this, "You Died", Toast.LENGTH_SHORT).show();
    }

    public void attLis(){
        action = 1;
    }
    public void runLis(){
        action = 4;
    }




}
