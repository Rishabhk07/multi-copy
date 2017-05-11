package com.condingblocks.multicopy.views.Custom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.condingblocks.multicopy.Interfaces.RemoveCallback;
import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.Utils.Serializer;
import com.condingblocks.multicopy.views.Activities.TextCallActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.formats.NativeAdView;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 08/05/17.
 */

public class MultiCopy extends View{
    private Context mContext;
    private View view;
    private NativeExpressAdView adView;
    ListView lv;
    TextView tvJustCopied;
    ImageView imClear;
    FrameLayout flNewClip;
    ArrayList<String> list;
    ArrayAdapter arrayAdapter;
    public static final String TAG = "multi copy view";
    public MultiCopy(Context context) {
        super(context);
        mContext = context;

    }

    public View addToWindowManager(String copiedText, final RemoveCallback removeCallback){
        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = li.inflate(R.layout.dialog_layout,null);
        adView = (NativeExpressAdView) view.findViewById(R.id.adView);
        lv = (ListView) view.findViewById(R.id.lv);
        tvJustCopied = (TextView) view.findViewById(R.id.multicopy);
        imClear = (ImageView) view.findViewById(R.id.ivClear);
        flNewClip = (FrameLayout) view.findViewById(R.id.flNewClip);
        imClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCallback.onViewRemoved();
            }
        });

        flNewClip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearArrayData();
            }
        });

        tvJustCopied.setText(copiedText);
        list = Serializer.getStringFromSharedPrefs(mContext);
        arrayAdapter = new ArrayAdapter(mContext,android.R.layout.simple_list_item_1 , android.R.id.text1,list);
        lv.setAdapter(arrayAdapter);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_ID")
                .build();
        adView.loadAd(adRequest);
        return view;
    }

    public void clearArrayData(){
        list = Serializer.getStringFromSharedPrefs(mContext);
        list.clear();
        Serializer.setStringToArrayPrefs(mContext,list);
        Log.d(TAG, "clearArrayData: " + list.toString());
        arrayAdapter.notifyDataSetChanged();
    }
}
