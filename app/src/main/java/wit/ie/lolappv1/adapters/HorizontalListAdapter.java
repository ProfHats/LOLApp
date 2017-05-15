package wit.ie.lolappv1.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.models.SingleGame;

/**
 * Created by laptop user on 10/05/2017.
 */

public class HorizontalListAdapter extends ArrayAdapter<SingleGame> {


    public List<SingleGame> games;
    private Context context;
    public TextView txtFirst;
    public TextView txtSecond;
    public TextView txtThird;
    public TextView txtFourth;
    public TextView txtFifth;

    public HorizontalListAdapter(Context context, List<SingleGame> list){
    super(context, R.layout.horizontal_list, list);
    this.context = context;
    this.games = list;
    Collections.sort(this.games);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SingleGame theGame = this.games.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.horizontal_list, parent, false);
        }
        txtFirst=(TextView) convertView.findViewById(R.id.gameID);
        txtSecond=(TextView) convertView.findViewById(R.id.region);
        txtThird=(TextView) convertView.findViewById(R.id.maxRank);
        txtFourth=(TextView) convertView.findViewById(R.id.gameResult);
        txtFifth=(TextView) convertView.findViewById(R.id.champUsed);


        //we've loaded in the containers, now we need to load the containers up with data too

        txtFirst.setText(theGame.getMatchID());
        txtSecond.setText(theGame.getRegion());
        txtThird.setText(theGame.getHighestRank());
        txtFourth.setText(theGame.getMatchResult());
        try {
            txtFifth.setText(theGame.getChampUsed());
            int cvID = Integer.parseInt(theGame.matchID);
            convertView.setId(cvID);
        }catch (NumberFormatException e)
        {
        System.out.print(e.getMessage());
        }

        //GO TO GAMERESULTS AND CONNECT IT TO HORIZONTAL LIST INSTEAD...OR WAIT...

        return convertView;
    }
}
