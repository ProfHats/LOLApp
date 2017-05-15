package wit.ie.lolappv1.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.activities.db.BaseDBClass;
import wit.ie.lolappv1.models.SingleGame;

/**
 * Created by laptop user on 14/04/2017.
 */

public class updateGame extends BaseDBClass implements View.OnClickListener {
    public int mID;
    public String matchID, region, maxRank, result, champ;
    public TextView eMatchID;
    public Bundle activityInfo;
    public Spinner champUsed, resultChoice, maxRankChoice, regionChoice;
//public DBManager manager = new DBManager(this);

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_game);
        dbManager.open();

        activityInfo = getIntent().getExtras();
        matchID = activityInfo.get("Match ID").toString();
        region = activityInfo.get("Region").toString();
        maxRank = activityInfo.get("Max Rank").toString();
        result = activityInfo.get("Result").toString();
        champ = activityInfo.get("Champion Used").toString();

        Button submitButton = (Button) findViewById(R.id.confirmButton);
        eMatchID = (TextView) findViewById(R.id.matchIdOnEditScreen);
        regionChoice = (Spinner) findViewById(R.id.region);
        maxRankChoice = (Spinner) findViewById(R.id.maxRank);
        resultChoice = (Spinner) findViewById(R.id.matchResult);
        champUsed = (Spinner) findViewById(R.id.championUsed);

        eMatchID.setText(matchID);

        String[] champs = new String[]{"Annie", "Olaf", "Galio"}, results=new String[]{"Win", "Loss"}, ranks=new String[]{"Bronze", "Silver", "Gold", "Platinum", "Diamond", "Master", "Challenger"}, regions=new String[]{"North America", "Europe", "Korea", "China", "Southeast Asia"};
        int champIndex = getPosition(champ, champs);
        champUsed.setSelection(champIndex);
        int resultIndex = getPosition(result, results);
        resultChoice.setSelection(resultIndex);
        int maxRankIndex = getPosition(maxRank, ranks);
        maxRankChoice.setSelection(maxRankIndex);
        int regionIndex = getPosition(region, regions);
        regionChoice.setSelection(regionIndex);

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
        matchID = eMatchID.getText().toString();
        region = regionChoice.getSelectedItem().toString();
        maxRank = maxRankChoice.getSelectedItem().toString();
        result = resultChoice.getSelectedItem().toString();
        champ = champUsed.getSelectedItem().toString();//This might cause problems - you're getting the VIEW itself, which doesn't have any text - note you didn't use
        //getText, because Spinners don't have that method
        mID = new Integer(matchID);
        SingleGame s = new SingleGame(matchID, region, maxRank, result, champ);
        dbManager.update(s);
        goToActivity(this, ResultsPage.class, null);
    }

    public int getPosition(String sentIn, String[] list){
        int result=-1;
        for(int i=0; i<list.length; i++){
        if(sentIn.equals(list[i])){
        result = i;
        }
        }
        return result;
//This method allows us to find the index that the sent-in value exists at (if it does in fact exist in the list). We need this information
        //to set the default value of the Spinners when the user goes to edit them.
    }
}
