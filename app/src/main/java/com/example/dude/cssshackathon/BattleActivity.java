package com.example.dude.cssshackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

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

        attackBtn.bringToFront();
        itemsBtn.bringToFront();
        abilitiesBtn.bringToFront();
        runBtn.bringToFront();

    }




}
