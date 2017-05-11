package com.condingblocks.multicopy.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;

import com.condingblocks.multicopy.Constants.SharedPrefs;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 11/05/17.
 */

public class Serializer {
    public static boolean setStringToArrayPrefs(Context context , String key , ArrayList<String> values ){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefs.PREFS_KEY , Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONArray jsonArray= new JSONArray();
        for(int i = 0 ;i < values.size() ; i ++ ){
            jsonArray.put(values.get(i));
        }
        if(!values.isEmpty()){
            editor.putString(key , jsonArray.toString());
            editor.apply();
            return true;
        }else{
            return false;
        }
    }

    public static ArrayList<String> getStringFromSharedPrefs(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefs.PREFS_KEY,Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(SharedPrefs.PREFS_KEY,null);
        ArrayList<String> arrayList = new ArrayList<>();
        if (json != null){
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0 ;i < json.length() ;i++){
                        String thisCopyData = jsonArray.optString(i);
                        arrayList.add(thisCopyData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
