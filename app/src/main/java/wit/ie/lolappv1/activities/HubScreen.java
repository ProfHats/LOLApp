package wit.ie.lolappv1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Console;

import wit.ie.lolappv1.R;

/**
 * Created by laptop user on 31/03/2017.
 */

public class HubScreen extends Activity {


    protected void onCreate(Bundle savedInsanceState){
        super.onCreate(savedInsanceState);
       setContentView(R.layout.hub_screen);
        TextView choice = (TextView) findViewById(R.id.click);
        Log.v("The Button should be", "Visible" + choice);
        choice.setText("Welcome to LoLApp V0.1!");
        Button champsButton = (Button) findViewById(R.id.goToChamps);
        champsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
        //in response to click, do something...
        //launch new Intent
        Intent intent = new Intent(HubScreen.this, MainActivity.class);
        HubScreen.this.startActivity(intent);
    }
}

);

        Button gamesButton = (Button) findViewById(R.id.goToGames);
        gamesButton.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v){
                                                //in response to click, do something...
                                                //launch new Intent
                                                Intent intent = new Intent(HubScreen.this, ResultsPage.class);
                                                HubScreen.this.startActivity(intent);
                                            }
                                        }

        );

    }
}
