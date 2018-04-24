package me.rishabhkhanna.multicopy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.rishabhkhanna.multicopy.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 12/05/17.
 */

public class CopyDataAdapter extends RecyclerView.Adapter<CopyDataAdapter.CopyDataViewHolder> {
    private ArrayList<String> copyDataArrayList;
    private ArrayList<Boolean> listCheckBox;
    private Context context;
    public static final String TAG = "CopyDataAdapter";

    public CopyDataAdapter(ArrayList<String> copyDataArrayList, ArrayList<Boolean> listCheckBox, Context context) {
        this.copyDataArrayList = copyDataArrayList;
        this.context = context;
        this.listCheckBox = listCheckBox;
    }

    @Override
    public CopyDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = li.inflate(R.layout.dailog_tupple_layout, parent, false);
        return new CopyDataViewHolder(root);
    }

    @Override
    public void onBindViewHolder(final CopyDataViewHolder holder, final int position) {
        final String thisText = copyDataArrayList.get(position);
        holder.tvCopyData.setText(thisText);

        final Boolean isThisChecked = listCheckBox.get(position);
        holder.checkBoxSelected.setChecked(isThisChecked);

        Log.d(TAG, "onBindViewHolder: " + position + "is checked : " + isThisChecked);
        holder.llDialogTupple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!listCheckBox.get(position)){
                    holder.checkBoxSelected.setChecked(true);
                    listCheckBox.set(position,true);
                }else if(listCheckBox.get(position)){
                    holder.checkBoxSelected.setChecked(false);
                    listCheckBox.set(position,false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return copyDataArrayList.size();
    }

    public class CopyDataViewHolder extends RecyclerView.ViewHolder {
        TextView tvCopyData;
        View view;
        CheckBox checkBoxSelected;
        LinearLayout llDialogTupple;

        public CopyDataViewHolder(View itemView) {
            super(itemView);
            tvCopyData = (TextView) itemView.findViewById(R.id.tvText);
            checkBoxSelected = (CheckBox) itemView.findViewById(R.id.lvCheckbox);
            llDialogTupple = (LinearLayout) itemView.findViewById(R.id.llDialogTupple);
        }


    }

}
