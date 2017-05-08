package com.condingblocks.multicopy.views.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.condingblocks.multicopy.R;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 08/05/17.
 */

public class MultiCopy extends View{
    private Context mContext;
    private View view;
    public MultiCopy(Context context) {
        super(context);
        mContext = context;
    }

    public View addToWindowManager(){
        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = li.inflate(R.layout.dialog_layout,null);
        ListView lv = (ListView) view.findViewById(R.id.lv);
        ArrayList<String> list = new ArrayList<>();
        list.add("Rishabh");
        list.add("Khanna");
        list.add("Arnav");
        list.add("gupta");
        list.add("championswimmer");
        ArrayAdapter arrayAdapter = new ArrayAdapter(mContext,android.R.layout.simple_list_item_1 , android.R.id.text1,list);
        lv.setAdapter(arrayAdapter);
        return view;
    }
}
