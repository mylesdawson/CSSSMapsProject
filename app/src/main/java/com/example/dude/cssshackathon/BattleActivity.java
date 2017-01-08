package com.example.dude.cssshackathon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
    MediaPlayer atkPlayer;
    MediaPlayer deathPlayer;

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
                    lvl++;
                    ammo += ran.nextInt(lvl);
                    hpPot += ran.nextInt(lvl);

                    atkPlayer = MediaPlayer.create(getApplicationContext(), R.raw.explosion);
                    atkPlayer.start();

                    save();
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                    return;
                    //Return to map
                    //Gain items/xp
                }
                    //enemy turn
                    playerHpVal -= ran.nextInt(50+lvl);

                    if(playerHpVal <= 0) {
                        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(intent);
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
                    abilitiesBtn.setText("AMMO: " + ammo);
                    if (enemyHpVal <= 0) {
                        lvl++;
                        ammo += ran.nextInt(lvl);
                        hpPot += ran.nextInt(lvl);
                        save();
                        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(intent);
                        //Return to map
                        //Gain items/xp
                    }
                }
            }
        });

        itemsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (hpPot > 0) {
                    playerHpVal += ran.nextInt(lvl) + (int) (100+lvl*2)/3;
                    hpPot--;
                    itemsBtn.setText("Heal: " + hpPot);
                }
            }
        });

        runBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        }));

        setEnemyHP();
        setPlayerHP();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.battle_theme);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
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

    private void save(){
        FileOutputStream out;
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        try{
            out = openFileOutput(path + "save.txt", getApplicationContext().MODE_PRIVATE);
            out.write(playerHpVal + '\n');
            out.write(lvl + '\n');
            out.write(ammo + '\n');
            out.write(hpPot + '\n');
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e){

        }
    }
    public int load(){
        FileInputStream in;
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        try{
            in = getApplicationContext().openFileInput(path + "save.txt");
            StringBuilder strb = new StringBuilder();
            int ch;
            while((ch = in.read()) != -1) {
                strb.append((char) ch);
            }
            String[] data =  strb.toString().split("\\s+");
            playerHpVal = Integer.parseInt(data[0]);
            lvl = Integer.parseInt(data[1]);
            ammo = Integer.parseInt(data[2]);
            hpPot = Integer.parseInt(data[3]);
            Toast.makeText(this, "Saved Game Loaded", Toast.LENGTH_SHORT).show();
            return 0;
        } catch(Exception e) {
            return 1;
        }
    }
}
