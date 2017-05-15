package wit.ie.lolappv1.activities.db;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by laptop user on 13/04/2017.
 */

public class BaseDBClass extends AppCompatActivity{
    public DBManager dbManager = new DBManager(this);

    @Override
    public void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
    dbManager.open();
    }

    public void onTerminate(){
        //super.onTerminate();
        dbManager.close();
    }

    protected void goToActivity(Activity current,
                                Class<? extends Activity> activityClass,
                                Bundle bundle) {
        Intent newActivity = new Intent(current, activityClass);

        if (bundle != null) newActivity.putExtras(bundle);

        current.startActivity(newActivity);
    }
}
