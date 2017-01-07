import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.io.*;

/**
 * Created by panca on 2017-01-07.
 */

public class Character extends AppCompatActivity{
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

    public Character(String inputName){
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
}
