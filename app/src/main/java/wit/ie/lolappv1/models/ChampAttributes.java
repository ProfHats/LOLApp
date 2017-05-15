package wit.ie.lolappv1.models;

import java.io.Serializable;

/**
 * Created by laptop user on 25/04/2017.
 */

public class ChampAttributes implements Serializable{
    public int attack;
    public int defense;
    public int magic;
    public int difficulty;

    public ChampAttributes(int attack, int defense, int magic, int difficulty){this.attack = attack; this.defense = defense; this.magic = magic; this.difficulty = difficulty; }
}
