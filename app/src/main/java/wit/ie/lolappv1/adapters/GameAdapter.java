package wit.ie.lolappv1.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.models.Champion;
import wit.ie.lolappv1.models.SingleGame;

/**
 * Created by laptop user on 08/04/2017.
 */

public class GameAdapter extends ArrayAdapter<SingleGame> {

    private Context context;
    private View.OnClickListener selectListener;
    public List<SingleGame> games;
    public RequestQueue queue;
    public Bitmap portrait;

    public GameAdapter(Context context, List<SingleGame> games){
        super(context, R.layout.game_details_fragment, games);
        Log.v("LOLApp", "Creating Adapter");
        this.context = context;
        this.games = games;
        Collections.sort(this.games);
        Log.v("LOLApp","Sorted List" + this.games);
    }

    public GameAdapter(Context context, View.OnClickListener selectListener, List<SingleGame> games){
        super(context, 0, games);
        this.context = context;
        this.selectListener = selectListener;
        this.games = games;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        SingleGame game = this.games.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.game_details_fragment, parent, false);
        }
        TextView matchID = (TextView) convertView.findViewById(R.id.matchID);
        TextView region = (TextView) convertView.findViewById(R.id.region);
        TextView maxRank = (TextView) convertView.findViewById(R.id.maxRank);
        TextView result = (TextView) convertView.findViewById(R.id.result);
        TextView champUsed = (TextView) convertView.findViewById(R.id.champUsed);

        matchID.setText(game.matchID);
        region.setText(game.region);
        maxRank.setText(game.highestRank);
        result.setText(game.matchResult);
        champUsed.setText(game.champUsed);
        Log.v("Checking Game MatchID", "MatchID is " + game.matchID);

        convertView.setId(new Integer(game.matchID));//error: says we get a NumberFormatException trying to enter 222222222 - is it not a String? Or sth?
        //not sure about this at all, copied from ChampAdapter, may need to rethink
        return convertView;
    }
}

