package wit.ie.lolappv1.activities;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import wit.ie.lolappv1.R;

/**
 * Created by laptop user on 13/05/2017.
 */

public class PerformSearch extends ListActivity {

@Override
public void onCreate(Bundle savedInstanceState){
super.onCreate(savedInstanceState);
setContentView(R.layout.search_page);//I THINK this is the right thing to set for the View. Might need to change it though.
    //search_page has only one element, a ListView called listOfGames

Intent intent = getIntent();//this is the Intent which is sent from the app where the "Search" option is selected
if(Intent.ACTION_SEARCH.equals(intent.getAction())){ //ACTION_SEARCH always contains a QUERY string
String query = intent.getStringExtra(SearchManager.QUERY);
   //doMySearch(query);
    //doMySearch is a local method that has yet to be created
}
}

}
