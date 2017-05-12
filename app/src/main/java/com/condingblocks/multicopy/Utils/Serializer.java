package com.condingblocks.multicopy.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.util.Log;


import com.condingblocks.multicopy.model.CopyTextModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 11/05/17.
 */

public class Serializer {
    public static final String TAG = "Serializer";
    public static void setStringToArrayPrefs(Context context , ArrayList<String> values ){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFS_DB_NAME , Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONArray jsonArray= new JSONArray();
        Log.d(TAG, "setStringToArrayPrefs:" + values.toString());
        for(int i = 0 ;i < values.size() ; i ++ ){
            jsonArray.put(values.get(i));

        }
            editor.putString(Constants.PREFS_KEY , jsonArray.toString());
            editor.apply();
    }

    public static CopyTextModel getStringFromSharedPrefs(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFS_DB_NAME,Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(Constants.PREFS_KEY,null);
        String storeCopyData  = "";

        Log.d(TAG, "getStringFromSharedPrefs: " + json);
        ArrayList<String> arrayList = new ArrayList<>();
        if (json != null){
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0 ;i < json.length() ;i++){
                        String thisCopyData = jsonArray.getString(i);
                        arrayList.add(thisCopyData);
                    storeCopyData +=thisCopyData;
                    storeCopyData += "\n";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new CopyTextModel(storeCopyData,arrayList);
    }

}
