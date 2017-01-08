package com.example.dude.cssshackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class EncounterActivity extends AppCompatActivity {
    //public static Game game = new Game("Hello.");
    public String latestAction;
    String mobName;
    int[] mob;
    Button attack;
    Button abilities;
    Button items;
    Button run;
    ImageView mobImg;
    ImageView playerImg;
    TextView mobInfo;
    TextView playerInfo;
    int att;
    int hp;
    public HashMap<String, int[]> mobMap = new HashMap<String, int[]>();
    //ame game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);
     //   game.encounter();
        attack = (Button)findViewById(R.id.attack);
    //    abilities = (Button)findViewById(R.id.abilities);
    //    items = (Button)findViewById(R.id.shopB);
    //    run = (Button)findViewById(R.id.run);
        mobImg = (ImageView)findViewById(R.id.mobImg);
        playerImg = (ImageView)findViewById(R.id.playerImg);
        mobInfo = (TextView)findViewById(R.id.mobInfo);
        playerInfo = (TextView)findViewById(R.id.playerInfo);
        //game = activity_second.game;

        int[][] arr = new int[5][7];
        arr[0][0] = 100;
        arr[0][1] = 5;
        arr[0][2] = 25;
        arr[0][3] = 10;
        arr[0][4] = 2;
        arr[0][5] = 2;
        arr[0][6] = 2;
        arr[1][0] = 100;
        arr[1][1] = 5;
        arr[1][2] = 25;
        arr[1][3] = 10;
        arr[1][4] = 2;
        arr[1][5] = 2;
        arr[1][6] = 2;
        arr[2][0] = 100;
        arr[2][1] = 5;
        arr[2][2] = 25;
        arr[2][3] = 10;
        arr[2][4] = 2;
        arr[2][5] = 2;
        arr[2][6] = 2;
        arr[2][0] = 100;
        arr[2][1] = 5;
        arr[2][2] = 25;
        arr[2][3] = 10;
        arr[2][4] = 2;
        arr[2][5] = 2;
        arr[2][6] = 2;
        arr[3][0] = 100;
        arr[3][1] = 5;
        arr[3][2] = 25;
        arr[3][3] = 10;
        arr[3][4] = 2;
        arr[3][5] = 2;
        arr[3][6] = 2;
        arr[4][0] = 100;
        arr[4][1] = 5;
        arr[4][2] = 25;
        arr[4][3] = 10;
        arr[4][4] = 2;
        arr[4][5] = 2;
        arr[4][6] = 2;
        mobMap.put("wraith", arr[0]);
        mobMap.put("wraithhot", arr[1]);
        mobMap.put("wraithcold", arr[2]);
        mobMap.put("wraithbright", arr[3]);
        mobMap.put("wraithdark", arr[4]);

        Random generator = new Random();
        Object[] getmobs = mobMap.values().toArray();
//       Toast.makeText(this, getmobs.length, Toast.LENGTH_SHORT).show();
        String randomMob = (String) getmobs[generator.nextInt(getmobs.length)];
        mob = mobMap.get(randomMob);
        mobName = randomMob;

       int mobBaseHP = mob[0];
        mobInfo.setText( mobName+ "\nhp: " + mob[0] +"|" + mobBaseHP + "\nattack: " + mob[1]);
        playerInfo.setText("you" + "\nhp: " + hp);// + "|" + hpMax + "\nap: " + ap + "|" + apMax);
        int id = getResources().getIdentifier("@mipmap-hdpi/" + mobName, null, null);
        mobImg.setImageResource(id);
        encounter();
    }

    public void attackSetText(){
        latestAction = attack.getText().toString();
    }
  /*  public void abilitiesSetText(){
        if(abilities.getText().toString().equals("abilities")){
            attack.setText(game.ability[0]);
            abilities.setText(game.ability[1]);
            items.setText(game.ability[2]);
            run.setText("back");
            return;
        }
        latestAction = abilities.getText().toString();
        attack.setText("attack");
        abilities.setText("abilities");
        items.setText("items");
        run.setText("run");
    }
    public void itemsSetText(){
        if(items.getText().toString().equals("items")){
            attack.setText("hp potion: " + game.hpPot);
            abilities.setText("dart: " + game.dart);
            items.setText("ap potion" + game.apPot);
            run.setText("back");
            return;
        }
        latestAction = items.getText().toString();
        attack.setText("attack");
        abilities.setText("abilities");
        items.setText("items");
        run.setText("run");
    }
    */
    public void runSetText(){
        if(items.getText().toString().equals("back")){
            latestAction = "back";
            attack.setText("attack");
            abilities.setText("abilities");
            items.setText("items");
            run.setText("run");
            return;
        }
        finish();
    }

    public void encounter() {
        //Random generator = new Random();
        //Object[] mobs = mobMap.values().toArray();
        //String randomMob = (String) mobs[generator.nextInt(mobs.length)];
        //int[]  mob = mobMap.get(randomMob);
        //EncounterActivity.setInfo(randomMob, mob);
        while(mob[0] > 0 && hp > 0) {
            update();
            combatAction(mob);
            update();
            if(mob[0] > 0) hp -= mob[1];
            update();
        }
       /* if(game.hp > 0){
            game.xp += game.mob[2];
            game.gold += game.mob[3];
            game.hpPot += game.mob[4];
            game.apPot += game.mob[5];
            game.dart += game.mob[6];
            Toast.makeText(this, "You rekt that " + game.mobName + "!", Toast.LENGTH_SHORT).show();
            game.lvlUp();
            game.save();
            finish();
        }*/
    }
    private void combatAction(int[] mob){
        boolean resolved = false;
        latestAction = "";
        Random ran = new Random();
        while(!resolved) {
            if (latestAction.equals("attack")) {
                int dmg = ran.nextInt();
                mob[0] -= dmg;
                Toast.makeText(this, "You Did " + dmg + "damage!", Toast.LENGTH_SHORT).show();
                resolved = true;
                continue;
            }
        /*    if (latestAction.equals(game.ability[0])){
                skill(0, mob);
                resolved = true;
                continue;
            }
            if (latestAction.equals(game.ability[1])){
                skill(1, mob);
                resolved = true;
                continue;
            }
            if (latestAction.equals(game.ability[2])){
                skill(2, mob);
                resolved = true;
                continue;
            }
            if(latestAction.equals("hp potion") && game.hpPot > 0){
                game.hp += game.hpMax;
                game.hpPot--;
            }
            if(latestAction.equals("ap potion") && game.apPot > 0){
                game.ap += game.apMax;
                game.apPot--;
            }
            if(latestAction.equals("dart") && game.dart > 0){
                mob[0] -= 5;
                game.dart--;
            }
            //if(action.equals("back")) Page3.Back(); */
        }
    }

   /* private void skill(int num, int[] mob){
        String use = game.ability[num];
        if(use.equals("strong attack: 10") && game.ap >= 10){
            mob[0] -= 15;
            game.ap -= 10;
            return;
        }
        if(use.equals("life steal: 20") && game.ap >= 20){
            mob[0] -= 5;
            game.hp+= 25;
            game.ap -= 20;
            return;
        }
        if(use.equals("double strike: 50") && game.ap >= 50){
            game.ap -= 50;
            combatAction(mob);
            mob[0] -= 10;
            return;
        }
        Toast.makeText(this, "Not enough AP, you have wasted a turn.", Toast.LENGTH_SHORT).show();
    }*/

    private void update(){
        mobInfo.setText(mobName + "\nhp: " + mob[0] + "\nattack: " + mob[1]);
        playerInfo.setText("you" + "\nhp: " + hp);// + "|" + game.hpMax + "\nap: " + game.ap + "|" + game.apMax);
    }
}
