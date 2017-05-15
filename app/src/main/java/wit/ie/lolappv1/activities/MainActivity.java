package wit.ie.lolappv1.activities;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.view.View.OnClickListener;


import org.json.JSONObject;

import java.io.Console;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.adapters.ChampAdapter;
import wit.ie.lolappv1.api.VolleyListener;
import wit.ie.lolappv1.fragments.ChampionFragment;
import wit.ie.lolappv1.models.Champion;
import android.database.sqlite.*;

import static java.security.AccessController.getContext;

//OK let's plan this out
//Let's create an object for Connection
//Then we should be able to populate the on screen elements with data from them

public class MainActivity extends AppCompatActivity{

    private static VolleyListener vListener;
    TextView hero1;
    RequestQueue queue;
    static String apiKey = "api_key=RGAPI-9f16225f-5342-4294-b25e-b4195b87f3b8";
    int champID;
    String getChampUrl;
    protected ArrayAdapter champAdapter;
    protected ArrayList<Champion> roster = new ArrayList<>();
    private int rosterStart = 1;
    private int rosterStop = 30;
    public GridView gridview;
    public Bitmap prtrt = null;
    Champion result;
    protected View gridChild;//might have to try changing this to Array of ChampionFragments


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            queue = Volley.newRequestQueue(this);
            for(int i = rosterStart; i<=rosterStop; i++) {
                //iterate over the roster of LoL chars
                Log.v("LOLApp", new Integer(i).toString());
                champRequest(i);
        }
                gridview = (GridView) findViewById(R.id.gridview);
                //we need to use onItemClickListener - this is the listener for each item in the
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent goFullScreen = new Intent(getApplicationContext(), FullHeroScreen.class);
                        Bundle info = new Bundle();
                        Champion c = getChamp(view.getId());

                        if(c!=null) {
                            info.putString("Hero Name", c.name);
                            goFullScreen.putExtras(info);
                            goFullScreen.putExtra("heroTitle", c.title);
                            goFullScreen.putExtra("portrait", c.portrait);
                            //goFullScreen.putExtra("allyTips", c.allytips);
                            //goFullScreen.putExtra("enemyTips", c.enemyTips);

                            goFullScreen.putExtra("tag1", c.tags[0]);
                            if(c.tags.length>1){
                            //No LoL Champion has more than two Tags, so I can simply presume that any time the array contains more than one Tag, it contains two.
                            goFullScreen.putExtra("tag2", c.tags[1]);
                            }
                            //goFullScreen.putExtra("tag2", c.tags[1]);
                            goFullScreen.putExtra("partype", c.partype);
                            goFullScreen.putExtra("attack", c.info.attack);
                            Log.v("The Attack Stat is:", Integer.toString(c.info.attack));

                            goFullScreen.putExtra("defense", c.info.defense);
                            goFullScreen.putExtra("magic", c.info.magic);
                            goFullScreen.putExtra("difficulty", c.info.difficulty);
                            //goFullScreen.putExtra("spells", c.spells);
                            goFullScreen.putExtra("lore", c.lore);
                            startActivity(goFullScreen);
                        }
            }
        });
        String size = new Integer(roster.size()).toString();
        Log.v("LOLApp:","Characters: " + size);

        //checkEm();
        //gridview.setAdapter(new ChampAdapter(this, roster));

    }

        //hero1 = (TextView)findViewById(R.id.hero1Name);

    public void champRequest(int champID)
    {
        RequestFuture<String> future = RequestFuture.newFuture();


        //to synchronise the downloads, implement a VolleyListener, with an onResponse method - see CoffeeMate5.0, CoffeeApi.java
        //see the equivalent procedure in CoffeeAPI.java, line 33 or so. You'll basically replace the (response) -> { thing
        //with a new Response.Listener<>() thing. You'll know it when you see it in CoffeeMate5

        getChampUrl = "https://global.api.pvp.net/api/lol/static-data/euw/v1.2/champion/" + champID + "?champData=all&" + apiKey;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getChampUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //use the GSON reference in here
                result = null;
                Type objType = new TypeToken<Champion>(){}.getType();
                Champion resultExperiment = new Gson().fromJson(response, Champion.class);
                result = new Gson().fromJson(response, objType);
                Log.v("EXPERIEMENT RESULT: ", resultExperiment.toString());
                if(result.id == 9)//Fiddlesticks has an idiosyncratic error that makes his key not match his image filename
                {
                result.key = "FiddleSticks";
                }
                Log.v("LOLApp", "Result " + result);
                queue.add(portraitRequest("https://ddragon.leagueoflegends.com/cdn/5.2.1/img/champion/"+result.key+".png", result));

                //vListener.setChamp(result);
                //vListener.updateUI((Fragment) vListener);
                //here's where the trouble lies - need this to wait until the portraitRequest returns
                roster.add(result);
                if(roster.size()==rosterStop){
                    gridview.setAdapter(new ChampAdapter(MainActivity.this, roster));
                    //This ensures that the data doesn't get loaded into the View until the Requests all return
                }
            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //hero1.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);

    }

    public ImageRequest portraitRequest(String portraitUrl, final Champion result)
    {

        ImageRequest imgReq = new ImageRequest(portraitUrl,

                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        result.setPortrait(response);

                    }
                }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something went wrong!");
                error.printStackTrace();
            }
        });

    return imgReq;
    }

    public Champion getChamp(int id)
    {
     Champion temp = null;
     for (int i=0; i<roster.size(); i++)
     {
     temp = roster.get(i);
         if(temp.id == id){
             return temp;
         }
     }
        return null;
    }

    public void checkEm(){
        //Log.v("Before Sort:", roster.get(1).toString());
        Collections.sort(roster);
        //Log.v("After Sort:", roster.get(1).toString());
    }
    public static void attachListener(VolleyListener fragment)
    {
        //System.out.println("Attaching Fragment : " + fragment);
        vListener = fragment;
    }

}



