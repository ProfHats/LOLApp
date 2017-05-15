package wit.ie.lolappv1.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.activities.db.BaseDBClass;

/**
 * Created by laptop user on 11/04/2017.
 */

public class FullGameScreen extends BaseDBClass implements View.OnClickListener{
    String matchID, region, maxRank, result, champUsed;
    Button deleteMe, editMe;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Context context = this;
        setContentView(R.layout.game_details_full);

        editMe = (Button) findViewById(R.id.editButton);
        deleteMe = (Button) findViewById(R.id.deleteButton);
        deleteMe.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dbManager.delete(matchID);
            backToResults();
            }
        }));

        matchID = getIntent().getSerializableExtra("matchID").toString();
        region = getIntent().getSerializableExtra("region").toString();
        maxRank = getIntent().getSerializableExtra("maxRank").toString();
        result = getIntent().getSerializableExtra("result").toString();
        champUsed = getIntent().getSerializableExtra("champUsed").toString();

        editMe.setOnClickListener(this);

        ((TextView) findViewById(R.id.matchID)).setText(matchID);
        ((TextView) findViewById(R.id.region)).setText(region);
        ((TextView) findViewById(R.id.maxRank)).setText(maxRank);
        ((TextView) findViewById(R.id.result)).setText(result);
        ((TextView) findViewById(R.id.champUsed)).setText(champUsed);
        /// /continue as above
    }

    public void backToResults(){
      goToActivity(this, ResultsPage.class, null);
    }

    @Override
    public void onClick(View v) {
    Bundle sent = new Bundle();
    sent.putString("Match ID", matchID);
    sent.putString("Region", region);
    sent.putString("Max Rank", maxRank);
    sent.putString("Result", result);
    sent.putString("Champion Used", champUsed);

    Intent goTo = new Intent(this, updateGame.class);
    goTo.putExtras(sent);
    startActivity(goTo);
    }
}
