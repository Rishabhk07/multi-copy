package com.condingblocks.multicopy.views.Custom;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.condingblocks.multicopy.Adapters.CopyDataAdapter;
import com.condingblocks.multicopy.Interfaces.RemoveCallback;
import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.Utils.Constants;
import com.condingblocks.multicopy.Utils.Serializer;
import com.condingblocks.multicopy.model.ClipboardTextModel;
import com.condingblocks.multicopy.model.NotesModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by rishabhkhanna on 08/05/17.
 */

public class MultiCopy extends View {
    private Context mContext;
    private View view;
    private NativeExpressAdView adView;
    RecyclerView recyclerView;
    TextView tvJustCopied;
    ImageView imClear;
    FrameLayout flNewClip;
    FrameLayout flSaveNotes;
    FrameLayout flSmartCopy;
    ArrayList<String> list;
    CopyDataAdapter copyDataAdapter;
    LinearLayoutManager linearLayoutManager;
    ClipboardManager clipboardManager;
    public static final String TAG = "multi copy view";

    public MultiCopy(Context context) {
        super(context);
        mContext = context;

    }

    public View addToWindowManager(final String copiedText, final RemoveCallback removeCallback) {
        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        view = li.inflate(R.layout.dialog_layout, null);
        adView = (NativeExpressAdView) view.findViewById(R.id.adView);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvList);
        tvJustCopied = (TextView) view.findViewById(R.id.multicopy);
        imClear = (ImageView) view.findViewById(R.id.ivClear);
        flNewClip = (FrameLayout) view.findViewById(R.id.flNewClip);
        flSaveNotes = (FrameLayout) view.findViewById(R.id.flTakeNotes);
        flSmartCopy = (FrameLayout) view.findViewById(R.id.flSmartCopy);
        imClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCallback.onViewRemoved();
            }
        });
        Realm.init(mContext);
        final Realm realm = Realm.getDefaultInstance();
        final ClipboardTextModel thisText = Serializer.getStringFromSharedPrefs(mContext);


        flNewClip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearArrayData();
            }
        });

        flSaveNotes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                realm.beginTransaction();
                //do all the realm saving work here
                NotesModel notesModel = realm.createObject(NotesModel.class);
                notesModel.setNote(thisText.getText());
                notesModel.setCreatedAt(DateFormat.getDateTimeInstance().format(new Date()));
                realm.commitTransaction();
                Log.d(TAG, "onClick: saved to DB ");
            }
        });

        flSmartCopy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmQuery<NotesModel> notesModelRealmQuery = realm.where(NotesModel.class);
                RealmResults<NotesModel> query = notesModelRealmQuery.findAll();

                ArrayList<NotesModel> notesList = new ArrayList<NotesModel>();
                notesList.addAll(query);
                for (NotesModel n : notesList){
                    Log.d(TAG, "onClick: " + n.getNote());
                }
            }
        });

        tvJustCopied.setText(copiedText);

        list = thisText.getTextArrayList();
        ClipData thisClip = ClipData.newPlainText(Constants.label, thisText.getText());
        clipboardManager.setPrimaryClip(thisClip);
        copyDataAdapter = new CopyDataAdapter(list, mContext);
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(copyDataAdapter);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_ID")
                .build();
        adView.loadAd(adRequest);
        return view;
    }

    public void clearArrayData() {
        list.clear();
        Serializer.setStringToArrayPrefs(mContext, list);
        Log.d(TAG, "clearArrayData: " + list.toString());
        copyDataAdapter.notifyDataSetChanged();
    }


}
