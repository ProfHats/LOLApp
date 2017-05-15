package wit.ie.lolappv1.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.models.ChampAttributes;
import wit.ie.lolappv1.models.Champion;

/**
 * Created by laptop user on 03/03/2017.
 */

public class FullHeroScreen extends Activity {
 //variables for the hero's details
private Context context;
private TextView heroName;
private TextView heroTitle;
private ImageView portrait;
private TextView trait1, trait2;
private ProgressBar attackBar, defenseBar, magicBar, difficultyBar;
private String tag2;

    //then include a method to put them into the variables
    //from the Bundle that was send in


@Override
    protected void onCreate(Bundle savedInstanceState)
{
super.onCreate(savedInstanceState);
context = this;
setContentView(R.layout.champ_details_full);
String heroName = getIntent().getSerializableExtra("Hero Name").toString();
    String heroTitle =  getIntent().getSerializableExtra("heroTitle").toString();
    Bitmap portrait = getIntent().getExtras().getParcelable("portrait");
    String tag1 = getIntent().getSerializableExtra("tag1").toString();
    //This won't always have anything in it - check for this beforehand or it'll cause errors
    Serializable v2 = getIntent().getSerializableExtra("tag2");
    if(v2!=null) {
         tag2 = getIntent().getSerializableExtra("tag2").toString();
    }
    String attack = getIntent().getSerializableExtra("attack").toString();
    String defense = getIntent().getSerializableExtra("defense").toString();
    String magic = getIntent().getSerializableExtra("magic").toString();
    String difficulty = getIntent().getSerializableExtra("difficulty").toString();
    //String info = getIntent().getSerializableExtra("info").toString();
    //Log.v("The Info stat is: ", info);

((TextView)findViewById(R.id.heroName)).setText(heroName);
    ((TextView)findViewById(R.id.heroTitle)).setText(heroTitle);
    ((ImageView)findViewById(R.id.portrait)).setImageBitmap(portrait);
   ((TextView) findViewById(R.id.tag1)).setText(tag1);
    if(tag2 != null) {
        ((TextView) findViewById(R.id.tag2)).setText(tag2);
    }
    Log.v("The Attack Stat is:", attack);
    ((ProgressBar)findViewById(R.id.attackBar)).setMax(10);
    ((ProgressBar)findViewById(R.id.attackBar)).setProgress(Integer.parseInt(attack));
    ((ProgressBar)findViewById(R.id.attackBar)).getIndeterminateDrawable().setColorFilter(
            Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
    ((ProgressBar)findViewById(R.id.defenseBar)).setMax(10);
    ((ProgressBar)findViewById(R.id.defenseBar)).setProgress(Integer.parseInt(defense));
    ((ProgressBar)findViewById(R.id.magicBar)).setMax(10);
    ((ProgressBar)findViewById(R.id.magicBar)).setProgress(Integer.parseInt(magic));
    ((ProgressBar)findViewById(R.id.difficultyBar)).setMax(10);
    ((ProgressBar)findViewById(R.id.difficultyBar)).setProgress(Integer.parseInt(difficulty));
}
}
