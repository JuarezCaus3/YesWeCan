package com.example.david.gameengineandroid;

import android.app.Activity;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.david.gameengineandroid.OpenGLES.MyGLSurfaceView;


public class GeometryDrawFragment extends Fragment {

    int[] array = {};

    private static int mCurrentPosition = -1;
    private GLSurfaceView mGLView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_geometry_draw, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateGeometry(args.getInt("position"));
        } else if (mCurrentPosition != -1){
            updateGeometry(mCurrentPosition);
        }

    }

    private void updateGeometry(int position) {

    }


}
