package me.rishabhkhanna.multicopy.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rishabhkhanna on 29/05/17.
 */

public class PrefsManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String PREFS_NAME = "multicopy-intro";

    public static final String IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";

    public PrefsManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setIsFirstTimeLaunch(Boolean isFirstTimeLaunch){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH , isFirstTimeLaunch);
        editor.commit();
    }

    public boolean isFirstTimeLaunch(){
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }

}
