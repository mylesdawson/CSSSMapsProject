package com.example.dude.cssshackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CharacterSheetActivity extends AppCompatActivity {
    TextView characterSheet = (TextView)findViewById(R.id.characterSheet);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_sheet);
        Game game = BattleActivity.game;
        characterSheet.setText("Name: " + game.name +
                                "HP: " + game.hp + "|" + game.hpMax +
                                "AP: " + game.ap + "|" + game.apMax +
                                "XP: " + game.xp + "|" + game.xpToNext +
                                "Gold: " + game.gold +
                                "HP Potions: " + game.hpPot +
                                "AP Potions: " + game.apPot +
                                "Darts: " + game.dart);
    }

    public void back(){
        finish();
    }
}
