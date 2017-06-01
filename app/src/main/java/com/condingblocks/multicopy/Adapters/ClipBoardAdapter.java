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
 * Created by rishabhkhanna on 20/05/17.
 */

public class ClipBoardAdapter  extends RecyclerView.Adapter<ClipBoardAdapter.ClipboardAdapterViewHolder>{

    private ArrayList<String> clipboardArray;
    private Context context;

    public ClipBoardAdapter(ArrayList<String> clipboardArray, Context context) {
        this.clipboardArray = clipboardArray;
        this.context = context;
    }

    @Override
    public ClipboardAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = li.inflate(R.layout.clipboard_layout,parent,false);

        return new ClipboardAdapterViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ClipboardAdapterViewHolder holder, int position) {
        String thisClip = clipboardArray.get(position);
        holder.tvClipText.setText(thisClip);
    }

    @Override
    public int getItemCount() {
        return clipboardArray.size();
    }

    public class ClipboardAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView tvClipText;

        public ClipboardAdapterViewHolder(View itemView) {
            super(itemView);
            tvClipText = (TextView) itemView.findViewById(R.id.tvClipText);
        }
    }
}
