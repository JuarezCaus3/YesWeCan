package com.example.david.gameengineandroid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class GeometryListFragment extends ListFragment {

    String[] imageItemsArray = {"Triangle", "Square"};

    OnImageItemSelectedListener mCallback;

    public interface OnImageItemSelectedListener {
        public void onImageItemSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layout;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            layout = android.R.layout.simple_list_item_activated_1;

        } else {
            layout = android.R.layout.simple_list_item_1;
        }
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, imageItemsArray));
    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // makes sure parent MainActivity implements
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnImageItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " The MainActivity activity must " +
                    "implement OnImageItemSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        mCallback.onImageItemSelected(position);
    }



}
