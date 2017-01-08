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
public Game(){

}
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
        loadMob();
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
        try{
            out = openFileOutput("saveFile.txt", this.MODE_PRIVATE);
            out.write(name.getBytes());
            out.write('\n');
            out.write(hp + '\n');
            out.write(ap + '\n');
            out.write(hpMax + '\n');
            out.write(apMax + '\n');
            out.write(xp + '\n');
            out.write(lvl + '\n');
            out.write(xpToNext + '\n');
            out.write(ability[0].getBytes());
            out.write('\n');
            out.write(ability[1].getBytes());
            out.write('\n');
            out.write(ability[2].getBytes());
            out.write('\n');
            out.write(hpPot + '\n');
            out.write(apPot + '\n');
            out.write(dart + '\n');
            out.write(gold + '\n');
        } catch(IOException e){

        }
    }

    public void load(){
        FileInputStream in;
        try{
            in = openFileInput("saveFile.txt");
            StringBuilder builder = new StringBuilder();
            int ch;
            while((ch = in.read()) != -1){
                builder.append((char)ch);
            }
            String[] data = builder.toString().split("\\s+");
            name = data[0];
            hp = Integer.parseInt(data[0]);
            ap = Integer.parseInt(data[0]);
            hpMax = Integer.parseInt(data[0]);
            apMax = Integer.parseInt(data[0]);
            xp = Integer.parseInt(data[0]);
            lvl = Integer.parseInt(data[0]);
            xpToNext = Integer.parseInt(data[0]);
            ability[0] = data[0];
            ability[1] = data[0];
            ability[2] = data[0];
            hpPot = Integer.parseInt(data[0]);
            apPot = Integer.parseInt(data[0]);
            dart = Integer.parseInt(data[0]);
            gold = Integer.parseInt(data[0]);
        } catch(IOException e){

        }
    }
    public void loadMob(){
        FileInputStream in;
        try{
            in = openFileInput("saveFile.txt");
            StringBuilder builder = new StringBuilder();
            int ch;
            while((ch = in.read()) != -1){
                builder.append((char)ch);
            }
            String[] data = builder.toString().split("\\s+");
            for(int i = 0; i < data.length; i+=8){
                int[] arr = new int[6];
                arr[0] = Integer.parseInt(data[1+i]);
                arr[1] = Integer.parseInt(data[2+i]);
                arr[2] = Integer.parseInt(data[3+i]);
                arr[3] = Integer.parseInt(data[4+i]);
                arr[4] = Integer.parseInt(data[5+i]);
                arr[5] = Integer.parseInt(data[6+i]);
                arr[6] = Integer.parseInt(data[6+i]);
                mobMap.put(data[0], arr);
            }
        } catch(IOException e){

        }
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
            lvlUp();
            return 0;
        }
        return 1;
    }
    private void combatAction(int[] mob){
        boolean resolved = false;
        EncounterFrame.latestAction = "";
        String action = EncounterFrame.latestAction;
        while(!resolved) {
            action = EncounterFrame.latestAction;
            if (action.equals("attack")) {
                mob[0] -= 10;
                resolved = true;
                continue;
            }
            if (action.equals(ability[0])){
                skill(0, mob);
                resolved = true;
                continue;
            }
            if (action.equals(ability[1])){
                skill(1, mob);
                resolved = true;
                continue;
            }
            if (action.equals(ability[2])){
                skill(2, mob);
                resolved = true;
                continue;
            }
            if(action.equals("hp potion")) hp += hpMax;
            if(action.equals("ap potion")) ap += apMax;
            if(action.equals("dart")) mob[0] -= 10;
            //if(action.equals("back")) Page3.Back();
        }
    }
    private void mapAction(){

    }

    private void skill(int num, int[] mob){
        String use = ability[num];
        if(use.equals("strong attack: 10") && ap >= 10){
            mob[0] -= 15;
            ap -= 10;
            return;
        }
        if(use.equals("life steal: 20") && ap >= 20){
            mob[0] -= 5;
            hp+= 25;
            ap -= 20;
            return;
        }
        if(use.equals("double strike: 50") && ap >= 50){
            ap -= 50;
            combatAction(mob);
            mob[0] -= 10;
            return;
        }
        Toast.makeText(this, "Not enough AP, you have wasted a turn.", Toast.LENGTH_SHORT).show();
    }
}
