package com.example.dude.cssshackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.dude.cssshackathon.activity_second.game;

public class EncounterActivity extends AppCompatActivity {
    public String latestAction;
    public String mobName;
    private Button attack = (Button)findViewById(R.id.attack);
    private Button abilities = (Button)findViewById(R.id.abilities);
    private Button items = (Button)findViewById(R.id.items);
    private Button run = (Button)findViewById(R.id.run);
    private ImageView mobImg = (ImageView)findViewById(R.id.mobImg);
    private ImageView playerImg = (ImageView)findViewById(R.id.playerImg);
    private TextView mobInfo = (TextView)findViewById(R.id.mobInfo);
    private TextView playerInfo = (TextView)findViewById(R.id.playerInfo);
    Game game = activity_second.game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);
    }

    public void encounter() {
        //Random generator = new Random();
        //Object[] mobs = mobMap.values().toArray();
        //String randomMob = (String) mobs[generator.nextInt(mobs.length)];
        //int[]  mob = mobMap.get(randomMob);
        //EncounterActivity.setInfo(randomMob, mob);
        while(game.mob[0] > 0 && game.hp > 0) {
            //EncounterActivity.getAction();
            combatAction(game.mob);
            //EncounterActivity.updateInfo(mob);
            if(game.mob[0] > 0) game.hp -= game.mob[1];
            // EncounterActivity.updateInfo();
        }
        if(game.hp > 0){
            game.xp += game.mob[2];
            game.gold += game.mob[3];
            game.hpPot += game.mob[4];
            game.apPot += game.mob[5];
            game.dart += game.mob[6];
            Toast.makeText(this, "You rekt that " + mobName + "!", Toast.LENGTH_SHORT).show();
            game.lvlUp();
            finish();
        }
    }
    private void combatAction(int[] mob){
        boolean resolved = false;
        latestAction = "";
        while(!resolved) {
            if (latestAction.equals("attack")) {
                mob[0] -= 10;
                resolved = true;
                continue;
            }
            if (latestAction.equals(game.ability[0])){
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
            //if(action.equals("back")) Page3.Back();
        }
    }

    private void skill(int num, int[] mob){
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
    }

}
