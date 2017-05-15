package wit.ie.lolappv1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.activities.db.BaseDBClass;
import wit.ie.lolappv1.activities.db.DBManager;
import wit.ie.lolappv1.models.SingleGame;

/**
 * Created by laptop user on 12/04/2017.
 */

public class AddGameScreen extends BaseDBClass implements View.OnClickListener{
    public int mID;
    public String matchID, region, maxRank, result, champ;
    public EditText eMatchID;
    public String genID="";
    public int idPiece;
public Spinner champUsed, resultChoice, maxRankChoice, regionChoice;
//public DBManager manager = new DBManager(this);

public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_game);
    dbManager.open();

    Button submitButton = (Button) findViewById(R.id.confirmButton);
    //eMatchID = (EditText) findViewById(R.id.matchID);
    regionChoice = (Spinner) findViewById(R.id.region);
    maxRankChoice = (Spinner) findViewById(R.id.maxRank);
    resultChoice = (Spinner) findViewById(R.id.matchResult);
    champUsed = (Spinner) findViewById(R.id.championUsed);
    String[] champs = new String[]{"Annie", "Olaf", "Galio"}, results=new String[]{"Win", "Loss"}, ranks=new String[]{"Bronze", "Silver", "Gold", "Platinum", "Diamond", "Master", "Challenger"}, regions=new String[]{"North America", "Europe", "Korea", "China", "Southeast Asia"};
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, champs);
    ArrayAdapter<String> resAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, results);
    ArrayAdapter<String> rankAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ranks);
    ArrayAdapter<String> regionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, regions);
    champUsed.setAdapter(adapter);
    resultChoice.setAdapter(resAdapter);
    maxRankChoice.setAdapter(rankAdapter);
    regionChoice.setAdapter(regionAdapter);
    submitButton.setOnClickListener(this);


}

    @Override
    public void onClick(View v) {

        for(int i=0; i<9; i++){
            idPiece = (int) Math.floor(Math.random() * (9*1)) + 1; //SHOULD produce random between 1 and 9...I hope
            Log.v("IDPIECE: ", Integer.toString(idPiece));
           genID += Integer.toString(idPiece);
            Log.v("AFTER ADDING A PIECE: ", genID);
            try {
                mID = Integer.parseInt(genID);
            }catch (NumberFormatException e){

            }
            }

    region = regionChoice.getSelectedItem().toString();
    maxRank = maxRankChoice.getSelectedItem().toString();
    result = resultChoice.getSelectedItem().toString();
    champ = champUsed.getSelectedItem().toString();//This might cause problems - you're getting the VIEW itself, which doesn't have any text - note you didn't use
        //getText, because Spinners don't have that method
        Log.wtf("AFTERCONCAT: ", genID);
        matchID = genID;
        SingleGame s = new SingleGame(matchID, region, maxRank, result, champ); //hopefully, this results in the auto genned ID being sent into the Game
        Log.v("PRESUBMIT: ",  s.matchID);
        Log.v("PRESUBMIT: ",  s.champUsed);
        dbManager.insert(s);

        goToActivity(this, ResultsPage.class, null);
    }


    public void onTerminate(){
        dbManager.close();
    }
}
