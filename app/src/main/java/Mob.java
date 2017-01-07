/**
 * Created by panca on 2017-01-07.
 */

public class Mob {
    private String name;
    private int hp;
    private int xp;
    private int gold;
    private int hpPot;
    private int apPot;
    private int darts;
    private int att;

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
