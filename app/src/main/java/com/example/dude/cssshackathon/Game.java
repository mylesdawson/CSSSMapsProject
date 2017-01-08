package com.example.dude.cssshackathon;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.io.*;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by panca on 2017-01-07.
 */

public class Game extends AppCompatActivity{
    private String path = Environment.getExternalStorageDirectory().getPath() + "/saveLocal";
    public String name;
    public int hpMax;
    public int hp;
    public int apMax;
    public int ap;
    public int xp;
    public int lvl;
    public int xpToNext;
    public String[] ability = new String[3];
    public int hpPot;
    public int apPot;
    public int dart;
    public int gold;
    public HashMap<String, int[]> mobMap = new HashMap<String, int[]>();
    public int[] mob = new int[6];
    public String mobName;
    //private static int numMob;
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
            out = openFileOutput(path+ "saveFile.txt", MODE_PRIVATE);
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
        } catch(Exception e){

        }
    }

    public void load(){
        FileInputStream in;
        try{
            in = getApplicationContext().openFileInput("saveFile.txt");
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
        } catch(Exception e){

        }
    }
    public void loadMob(){
        FileInputStream in;
        try{
            in = openFileInput("@mipmap-hdpi/mobs.txt");
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
        } catch(Exception e){

        }
    }

    public void encounter() {
        Random generator = new Random();
        Object[] getmobs = mobMap.values().toArray();
        Toast.makeText(this, getmobs.length, Toast.LENGTH_SHORT).show();
        String randomMob = (String) getmobs[generator.nextInt(getmobs.length)];
        mob = mobMap.get(randomMob);
        mobName = randomMob;
        Intent encounterIntent = new Intent(this, EncounterActivity.class);
        startActivity(encounterIntent);
    }
    /*    //EncounterActivity.setInfo(randomMob, mob);
        while(mob[0] > 0 && hp > 0) {
            //EncounterActivity.getAction();
            combatAction(mob);
            //EncounterActivity.updateInfo(mob);
            if(mob[0] > 0) hp -= mob[1];
           // EncounterActivity.updateInfo();
        }
        if(hp > 0){
            xp += mob[2];
            gold += mob[3];
            hpPot += mob[4];
            apPot += mob[5];
            dart += mob[6];
            lvlUp();
        }
    }
    private void combatAction(int[] mob){
        boolean resolved = false;
        EncounterActivity.latestAction = "";
        String action = EncounterActivity.latestAction;
        while(!resolved) {
            action = EncounterActivity.latestAction;
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
            if(action.equals("hp potion") && hpPot > 0){
                hp += hpMax;
                hpPot--;
            }
            if(action.equals("ap potion") && apPot > 0){
                ap += apMax;
                apPot--;
            }
            if(action.equals("dart") && dart > 0){
                mob[0] -= 5;
                dart--;
            }
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
    */
}
