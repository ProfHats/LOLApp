package wit.ie.lolappv1.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ObjectInput;
import java.io.Serializable;

/**
 * Created by laptop user on 14/02/2017.
 */

public class Champion implements Serializable, Comparable<Champion>{
    public int id;
    public String key;
    public String name;
    public String title;
    public String portraitURL;
    public Bitmap portrait;
    //public String[] allytips;
    //public String[] enemyTips;
    public String[] tags;
    public String partype;//type of resource used- mana, rage, etc
    public ChampAttributes info;
    //public ChampionSpell[] spells;
    public String lore;

    public Champion(String keyIn, String nameIn, String titleIn){
        key = keyIn;
        name = nameIn;
        title = titleIn;
        portraitURL = "https://ddragon.leagueoflegends.com/cdn/5.2.1/img/champion/" +name+ ".png"; //This is the ddragon URL to get the portraits for the appropriate hero
    }

    public void setPortrait(Bitmap portrait){
    this.portrait = portrait;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Key: " + key + ", Name: " + name + ", Title: " + title;
    }

    @Override
     public int compareTo(Champion other) {
        return this.id - other.id;
        //if returns 0, both are equal, <0, 'other' is bigger than 'this', if  >0, 'this' is bigger than 'other'
    }
}
