package com.condingblocks.multicopy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.condingblocks.multicopy.R;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 12/05/17.
 */

public class CopyDataAdapter extends RecyclerView.Adapter<CopyDataAdapter.CopyDataViewHolder> {
    private ArrayList<String> copyDataArrayList;
    private Context context;

    public CopyDataAdapter(ArrayList<String> copyDataArrayList, Context context) {
        this.copyDataArrayList = copyDataArrayList;
        this.context = context;
    }

    @Override
    public CopyDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = li.inflate(R.layout.dailog_tupple_layout, parent, false);
        return new CopyDataViewHolder(root);
    }

    @Override
    public void onBindViewHolder(CopyDataViewHolder holder, int position) {
        String thisText = copyDataArrayList.get(position);
        holder.tvCopyData.setText(thisText);
    }

    @Override
    public int getItemCount() {
        return copyDataArrayList.size();
    }

    public class CopyDataViewHolder extends RecyclerView.ViewHolder {
        TextView tvCopyData;

        public CopyDataViewHolder(View itemView) {
            super(itemView);
            tvCopyData = (TextView) itemView.findViewById(R.id.tvText);
        }
    }
}
