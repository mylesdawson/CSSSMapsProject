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
    public static String name;
    private int hpMax;
    private int hp;
    private int apMax;
    private int ap;
    private int xp;
    private int lvl;
    private int xpToNext;
    public String[] ability = new String[3];
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
        ability[0] = "none";
        ability[1] = "none";
        ability[2] = "none";
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
            combatAction(mob);
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
    private void combatAction(int[] mob){
        boolean resolved = false;
        Page3.latestAction = "";
        String action = Page3.latestAction;
        while(!resolved){
            action = Page3.latestAction;
            if(action.equals("Attack")) mob[0] -= 20; resolved = true; break;
            if(action.equals(ability[0])) ability(0); resolved = true; break;
            if(action.equals(ability[1])) ability(1); resolved = true; break;
            if(action.equals(ability[1])) ability(2); resolved = true; break;
            if(action.equals("HP Potion")) hp += hpMax;
            if(action.equals("AP Potion")) ap += apMax;
            if(action.equals("Dart")) mob[0] -= 10;
            if(action.equals("Back")) Page3.Back();
        }
    }
    private void mapAction(){
        String action = MapsActivity.latestAction
        if(action.equals("Attack")) mob[0] -= 20;
        if(action.equals(ability[0])) ability(0);
        if(action.equals(ability[1])) ability(1);
        if(action.equals(ability[1])) ability(2);
        if(action.equals("HP Potion")) hp += hpMax;
        if(action.equals("AP Potion")) ap += apMax;
        if(action.equals("Dart")) mob[0] -= 10;
    }

    private void ability(int num){
        string ability
        if
    }
}
