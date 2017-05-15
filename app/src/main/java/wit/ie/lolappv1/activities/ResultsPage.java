package wit.ie.lolappv1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.activities.db.BaseDBClass;
import wit.ie.lolappv1.activities.db.DBManager;
import wit.ie.lolappv1.adapters.GameAdapter;
import wit.ie.lolappv1.models.SingleGame;

/**
 * Created by laptop user on 04/04/2017.
 */

public class ResultsPage extends BaseDBClass{
    private ListView listView;
    private Button addGame, backToHub, clearHistory, goToResults, map;
    private GameAdapter gameAdapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbManager.open();
        setContentView(R.layout.results_page);
        //setup();
        //TableLayout ll = (TableLayout) findViewById(R.id.table);
        List<SingleGame> games = dbManager.getAll();
        addGame = (Button) findViewById(R.id.addNewGame);
        backToHub = (Button) findViewById(R.id.backToHub);
        clearHistory = (Button) findViewById(R.id.clearHistory);
        goToResults = (Button) findViewById(R.id.goToResults);
        map = (Button) findViewById(R.id.mapButton);

        addGame.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAdd = new Intent(getApplicationContext(), AddGameScreen.class);
                Bundle info = new Bundle();
                startActivity(goToAdd);
            }
        }));

        backToHub.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToHub = new Intent(getApplicationContext(), HubScreen.class);
                Bundle info = new Bundle();
                startActivity(backToHub);
            }
        }));

        clearHistory.setOnClickListener((new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                dbManager.empty();
                startActivity(getIntent());
                Log.v("Something ", "You pushed the empty button successfully");


            }
        }));

        goToResults.setOnClickListener(
                (new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        Intent goToResults = new Intent(getApplicationContext(), GameResults.class);
                        startActivity(goToResults);
                        Log.v("Something ", "You pushed the empty button successfully");

                    }
                })

        );

        map.setOnClickListener(
                (new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        Intent goToMap = new Intent(getApplicationContext(), Map.class);
                        startActivity(goToMap);
                        Log.v("Something ", "You pushed the empty button successfully");

                    }
                })
        );

        Log.v("DBManager: ", "The list's length is " + games.size());
       //this part should be excised and sent to a different screen
/*
        listView = (ListView) findViewById(R.id.gamesList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                Intent goFullScreen = new Intent(getApplicationContext(), FullGameScreen.class);
                                                Bundle info = new Bundle();
                                                Log.v("DBManager: ", "The View's ID is " + view.getId());
                                                String string = new Integer(view.getId()).toString();
                                                Log.v("DBManager: ", "Checking String isn't null: " + string);
                                                SingleGame g = dbManager.get(string);

                                                if(g!=null) {
                                                    info.putString("matchID", g.matchID);
                                                    goFullScreen.putExtras(info);
                                                    goFullScreen.putExtra("region", g.region);
                                                    goFullScreen.putExtra("maxRank", g.highestRank);
                                                    goFullScreen.putExtra("result", g.matchResult);
                                                    goFullScreen.putExtra("champUsed", g.champUsed);
                                                    startActivity(goFullScreen);
                                                }
                                            }

                                        }
        );
*/

        for(int i=0; i<games.size(); i++){

        //gameAdapter = new GameAdapter(getApplicationContext(), games);
            //listView.setAdapter(gameAdapter);

       /*     TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView id, region,highestRank, match_Result, champUsed;
            id = new TextView(this);
            region = new TextView(this);
            highestRank = new TextView(this);
            match_Result = new TextView(this);
            champUsed = new TextView(this);
            SingleGame currentGame = games.get(i);
            id.setText(currentGame.getMatchID());
            region.setText(currentGame.getRegion());
            highestRank.setText(currentGame.getHighestRank());
            match_Result.setText(currentGame.getMatchResult());
            champUsed.setText(currentGame.getChampUsed());
            row.addView(id); row.addView(region); row.addView(highestRank); row.addView(match_Result); row.addView(champUsed);
            ll.addView(row, i);
            */
            //the above is all code to auto gen Rows: we're instead gonna use inflatable Views, so that we can go into an observe, CRUD the Match results independently


        }
    }

 /*   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = null;
        v = inflater.inflate(R.layout.results_page, container, false);
        listView = (ListView) v.findViewById(R.id.gamesList);
        return v;
    }
*/
    public void onResume() {
        super.onResume();

        //gameAdapter = new GameAdapter(getActivity(), )
    }

    public void onTerminate(){
        //super.onTerminate();
        dbManager.close();
    }

private void setup(){

}


}
