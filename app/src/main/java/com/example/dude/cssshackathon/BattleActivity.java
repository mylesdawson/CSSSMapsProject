package com.example.dude.cssshackathon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class BattleActivity extends AppCompatActivity {

    TextView playerHP;
    TextView enemyHP;
    ImageView playerImg;
    ImageView enemyImg;
    Button attackBtn;
    Button itemsBtn;
    Button abilitiesBtn;
    Button runBtn;

    private int enemyHpVal;
    private static int playerHpVal = 100;
    private static int lvl = 1;
    private static int ammo = 5;
    private static int hpPot = 5;
    private Random ran = new Random();

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.battle_theme);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        playerHP = (TextView) findViewById(R.id.playerInfo);
        enemyHP = (TextView) findViewById(R.id.enemyInfo);
        playerImg = (ImageView) findViewById(R.id.playerImg);
        enemyImg = (ImageView) findViewById(R.id.mobImg);
        attackBtn = (Button) findViewById(R.id.attack);
        itemsBtn = (Button) findViewById(R.id.items);
        abilitiesBtn = (Button) findViewById(R.id.abilities);
        runBtn = (Button) findViewById(R.id.run);

        playerImg.setImageResource(R.mipmap.farengar_sprite);
        enemyImg.setImageResource(R.mipmap.wraith);

        enemyHpVal = ran.nextInt(100 + lvl) + lvl;

        attackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Dmg = ran.nextInt(50+lvl*2) + lvl;
                enemyHpVal -= Dmg;
                setEnemyHP();
                if(enemyHpVal <= 0){
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                    lvl++;
                    ammo += ran.nextInt(lvl);
                    hpPot += ran.nextInt(lvl);
                    //Return to map
                    //Gain items/xp
                } else {
                    //enemy turn
                    playerHpVal -= ran.nextInt(50+lvl);
                }
            }
        });

        abilitiesBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(ammo > 0) {
                    int Dmg = ran.nextInt(50 + lvl * 2) + lvl;
                    enemyHpVal -= Dmg;
                    setEnemyHP();
                    ammo--;
                    if (enemyHpVal <= 0) {
                        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(intent);
                        lvl++;
                        ammo += ran.nextInt(lvl);
                        hpPot += ran.nextInt(lvl);
                        //Return to map
                        //Gain items/xp
                    }
                }
            }
        });

        setEnemyHP();
        setPlayerHP();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();

    }

    public void setEnemyHP(){
        enemyHP.setText("Enemy HP:" + enemyHpVal);
    }

    public void setPlayerHP(){
        playerHP.setText("Player HP:" + playerHpVal);
    }

    public void attLis(){
        Random ran = new Random();
        int Dmg = ran.nextInt(10);
        enemyHpVal -= Dmg;
        setEnemyHP();
        if(enemyHpVal <= 0){
            finish();
            //Return to map
            //Gain items/xp
        } else {
            //enemy turn
            playerHpVal -= ran.nextInt(10);
        }
    }


}
