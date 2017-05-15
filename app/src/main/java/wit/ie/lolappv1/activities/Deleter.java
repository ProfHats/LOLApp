package wit.ie.lolappv1.activities;

import android.os.Bundle;

import wit.ie.lolappv1.activities.db.BaseDBClass;

/**
 * Created by laptop user on 13/04/2017.
 */

public class Deleter extends BaseDBClass{


@Override
public void onCreate(Bundle SavedInstanceState){
  dbManager.open();
}

    public String id;
    public Deleter(String id){
    this.id = id;
    }

    public void delete(){
    dbManager.delete(id);
    }

}
