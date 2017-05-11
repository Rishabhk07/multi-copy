package com.condingblocks.multicopy.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.util.Log;

import com.condingblocks.multicopy.Constants.SharedPrefs;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 11/05/17.
 */

public class Serializer {
    public static final String TAG = "Serializer";
    public static void setStringToArrayPrefs(Context context , ArrayList<String> values ){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefs.PREFS_DB_NAME , Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONArray jsonArray= new JSONArray();
        Log.d(TAG, "setStringToArrayPrefs:" + values.toString());
        for(int i = 0 ;i < values.size() ; i ++ ){
            jsonArray.put(values.get(i));

        }
            editor.putString(SharedPrefs.PREFS_KEY , jsonArray.toString());
            editor.apply();

    }

    public static ArrayList<String> getStringFromSharedPrefs(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefs.PREFS_DB_NAME,Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(SharedPrefs.PREFS_KEY,null);
        Log.d(TAG, "getStringFromSharedPrefs: " + json);
        ArrayList<String> arrayList = new ArrayList<>();
        if (json != null){
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0 ;i < json.length() ;i++){
                        String thisCopyData = jsonArray.getString(i);
                        arrayList.add(thisCopyData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
