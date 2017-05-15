package wit.ie.lolappv1.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.Collections;
import java.util.List;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.models.Champion;

/**
 * Created by laptop user on 22/02/2017.
 */

public class ChampAdapter extends ArrayAdapter<Champion> {
    private Context context;
    private OnClickListener selectListener;
    public List<Champion> roster;
    public RequestQueue queue;
    public Bitmap portrait;

    public ChampAdapter(Context context, List<Champion> roster){
        super(context, R.layout.champ_details_fragment, roster);
        Log.v("LOLApp", "Creating Adapter");
        this.context = context;
        this.roster = roster;
        Collections.sort(this.roster);
        Log.v("LOLApp","Sorted List" + this.roster);
    }

    public ChampAdapter(Context context, OnClickListener selectListener, List<Champion> roster){
    super(context, 0, roster);
        this.context = context;
        this.selectListener = selectListener;
        this.roster = roster;
    }

    public ChampAdapter(Context context, List<Champion> roster, Bitmap portrait){
        super(context, R.layout.champ_details_fragment, roster);
        this.context = context;
        this.roster = roster;
        this.portrait = portrait;

    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            Champion champ = this.roster.get(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.champ_details_fragment, parent, false);
            }
            TextView heroName = (TextView) convertView.findViewById(R.id.heroName);
            TextView heroTitle = (TextView) convertView.findViewById(R.id.heroTitle);
            ImageView portrait = (ImageView) convertView.findViewById(R.id.portrait);
            portrait.getLayoutParams().height = 150;
            portrait.getLayoutParams().width = 150;
            //may need to add new TextViews for the tags here
            //CAN'T do that, champ_details_fragment doesn't actually contain that item
            //TextView tag1 = (TextView) convertView.findViewById(R.id.tag1);
            //TextView tag2 = (TextView) convertView.findViewById(R.id.tag2);
            heroName.setText(champ.name);
            heroTitle.setText(champ.title);
            portrait.setImageBitmap(champ.portrait);
            //tag1.setText(champ.tags[0].toString());
            //tag2.setText(champ.tags[1].toString());
            convertView.setId(champ.id);//

        return convertView;
    }
}
