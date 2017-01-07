/**
 * Created by panca on 2017-01-07.
 */

public class Mob {
    public static String name;
    public int hp;
    public int xp;
    public int gold;
    public int hpPot;
    public int apPot;
    public int darts;
    public int att;

    public Mob(String nameInput, int[] stats){
        name = nameInput;
        hp = stats[0];
        xp = stats[1];
        gold = stats[2];
        hpPot= stats[3];
        apPot = stats[4];
        darts = stats[5];
        att = stats[6];
    }
}
