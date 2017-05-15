package wit.ie.lolappv1.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import wit.ie.lolappv1.activities.FullHeroScreen;
import wit.ie.lolappv1.adapters.ChampAdapter;
import wit.ie.lolappv1.models.Champion;

/**
 * Created by laptop user on 14/02/2017.
 */

public class ChampionFragment extends ListFragment implements OnClickListener{

    protected static ChampAdapter champAdapter;
    protected GridView gridView;

    public ChampionFragment()
    {

    }
    @Override
    public void onAttach(Context context){
    super.onAttach(context);
    }

  /*  @Override
    public void onListItemClick()
    {

    }
*/
    @Override
    public void onClick(View v) {
        Toast.makeText(this.getActivity(), "Clicked!", Toast.LENGTH_SHORT).show();
        Log.v("LOLApp", "Clicked");
    Bundle activityInfo = new Bundle();
    Intent goFullScreen = new Intent(getActivity(), FullHeroScreen.class);
        getActivity().startActivity(goFullScreen);
    }

    public void onListItemClick()
    {

    }
}
