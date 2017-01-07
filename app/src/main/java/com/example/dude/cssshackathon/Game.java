package com.example.dude.cssshackathon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.io.*;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by panca on 2017-01-07.
 */

public class Game extends AppCompatActivity{
    String name;
    private int hpMax;
    private int hp;
    private int apMax;
    private int ap;
    private int xp;
    private int lvl;
    private int xpToNext;
    private String[] ability = new String[3];
    private int hpPot;
    private int apPot;
    private int dart;
    private int gold;
    public static HashMap<String, int[]> mobMap = new HashMap<String, int[]>();
    private static int numMob;

    public Game(String inputName){
        name = inputName;
        hp = 100;
        ap = 100;
        hpMax = 100;
        apMax = 100;
        xp = 0;
        lvl = 0;
        xpToNext = 100;
        ability[0] = "\0";
        ability[1] = "\0";
        ability[2] = "\0";
        hpPot = 5;
        apPot = 5;
        dart = 10;
        gold = 0;
    }

    public void lvlUp(){
        if(xp >= xpToNext){
            lvl++;
            xp = xp - xpToNext;
            //xpToNext += 100;
            hpMax += 5;
            apMax += 5;
            Toast.makeText(this, "You have leveled up!", Toast.LENGTH_SHORT).show();
        }
    }

    public void save(){
        FileOutputStream out;
        //    try{
        //      out = openFileOutput
        //}
    }

    public void load(){

    }
    public void loadMob(){

    }

    public int encounter() {
        Random generator = new Random();
        Object[] mobs = mobMap.values().toArray();
        String randomMob = (String) mobs[generator.nextInt(mobs.length)];
        int[]  mob = mobMap.get(randomMob);
        while(mob[0] > 0 && hp > 0) {
            combatAction();
            if(mob[0] > 0) hp -= mob[1];
        }
        if(hp > 0){
            xp += mob[2];
            gold += mob[3];
            hpPot += mob[4];
            apPot += mob[5];
            dart += mob[6];
            hp = hpMax;
            ap = apMax;
            return 0;
        }
        return 1;
    }
    private void combatAction(){
        String action = Page3.latestAction;
    }
    private void mapAction(){

    }
}
