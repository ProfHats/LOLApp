package wit.ie.lolappv1.api;

import android.app.Fragment;

import java.util.List;

import wit.ie.lolappv1.models.Champion;

/**
 * Created by laptop user on 07/03/2017.
 */

public interface VolleyListener {
    void setChamp(Champion champ);
    void updateUI(Fragment fragment);
}

