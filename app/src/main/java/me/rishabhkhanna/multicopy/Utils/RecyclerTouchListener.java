package me.rishabhkhanna.multicopy.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import me.rishabhkhanna.multicopy.Interfaces.RecyclerClick_Listener;

/**
 * Created by rishabhkhanna on 25/05/17.
 */

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private RecyclerClick_Listener click_listener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RecyclerClick_Listener click_listener) {
        this.click_listener = click_listener;
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View view = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(view != null & click_listener != null){
                    click_listener.onLongClick(view,recyclerView.getChildPosition(view));
                }
                super.onLongPress(e);
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(),e.getY());
        if(child!= null && click_listener != null && gestureDetector.onTouchEvent(e) ){
            click_listener.onClick(child,rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
