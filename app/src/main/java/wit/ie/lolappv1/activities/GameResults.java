package wit.ie.lolappv1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.activities.db.BaseDBClass;
import wit.ie.lolappv1.adapters.GameAdapter;
import wit.ie.lolappv1.adapters.HorizontalListAdapter;
import wit.ie.lolappv1.models.SingleGame;

/**
 * Created by laptop user on 09/05/2017.
 */

public class GameResults extends BaseDBClass implements View.OnClickListener{
    public ListView listView;
    private HorizontalListAdapter gameAdapter;
    private List<SingleGame> games;
    private List<SingleGame> filteredList;
    private EditText searchBox;
    private Button searchButton;

public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    dbManager.open();
    setContentView(R.layout.games_page);
    games = dbManager.getAll();
    filteredList = new ArrayList<>();
    Log.v("DB WITH GAMES:", Integer.toString(games.size()));

    searchBox = (EditText) findViewById(R.id.myEditText);
    searchButton = (Button) findViewById(R.id.searchButton);
    searchButton.setOnClickListener(this);
    listView = (ListView) findViewById(R.id.gamesList2);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                            Intent goFullScreen = new Intent(getApplicationContext(), FullGameScreen.class);
                                            Bundle info = new Bundle();
                                            Log.v("DBManager: ", "The View's ID is " + view.getId());

                                            String string = new Integer(view.getId()).toString();
                                            String isThisTheID = new Integer(position).toString();
                                           //This is the ROOT of all the trouble. You need to get at a single Game's ID - it's called gameID

                                            //This is using what I believe is the wrong figure, needs the String that's auto generated - match ID or something...
                                            //THIS line, two above me, may be the culprit
                                            //maybe this explains why the ID kept coming back as -1, because we're not supposed to be looking at
                                            //the view.getId() -- THIS RETURNS THE android:id field from the XML!
                                            //but in a populated List, that's probably the same as the ID passed in to create said Game View...
                                            Log.v("DBManager: ", "Checking String isn't null: " + string);
                                            Log.v("DBMANAGER", "Checking the Position" + isThisTheID);
                                            SingleGame g = dbManager.get(string);
                                            //Log.v("THE GAME FROM THE DB:", g.toString());
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

    for(int i=0; i<games.size(); i++) {

        gameAdapter = new HorizontalListAdapter(getApplicationContext(), games);
        listView.setAdapter(gameAdapter);
    }


}
    @Override
    public void onClick(View v) {
    doFilter(searchBox.getText());//A String is-a CharSequence, so there's no need to convert from one to the other. You can just pass the String in
    }

public void doFilter(CharSequence criteria){

    ArrayList<SingleGame> results = new ArrayList<>(); //tried using List, but abstract list doesn't implement .add(Object) oddly enough. ArrayList does though

//if the criteria is null or empty, return the entire array (they don't want to filter at all)
if(criteria==null || criteria.length()==0 || criteria.equals("")){
filteredList=(ArrayList)games;
}
else//if it's not null, presumably they want to make some kind of search
{

final String crit = criteria.toString().toLowerCase();
for (int i=0; i<games.size(); i++)
{
SingleGame thisGame = games.get(i);
final String chaUsed = games.get(i).champUsed.toLowerCase();
final String regUsed = thisGame.region.toLowerCase();
final String rankUsed = thisGame.highestRank.toLowerCase();
final String resultUsed = thisGame.matchResult.toLowerCase();
if(chaUsed.contains(crit) || regUsed.contains(crit) || rankUsed.contains(crit) || resultUsed.contains(crit)){//If the match has a champUsed value equal to criteria, return the relevant champion
if(!filteredList.contains(games.get(i))) { //this should stop it from being added to the filtered list if it already exists there (this results in duplicates)
    filteredList.add(games.get(i));
}
}
}
}
//return results;
 publishChanges(); //hopefully, once the filtering has happened, we can then publish the changes
}

public void publishChanges() {

    HorizontalListAdapter newAdapter = new HorizontalListAdapter(getApplicationContext(), filteredList);
    newAdapter.notifyDataSetInvalidated();
    listView.setAdapter(newAdapter);
}

    //this is INTENDED to be the bit that actually gets the adapter changing to only display the new, filtered data list.
    //The hope is that, if the user decides to delete their criteria, then the doFilter method would automatically send
    //back the old, complete 'games' List, thus restoring the screen to its proper state.
}
