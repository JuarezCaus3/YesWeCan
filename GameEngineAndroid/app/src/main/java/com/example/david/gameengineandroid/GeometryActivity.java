package com.example.david.gameengineandroid;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.david.gameengineandroid.OpenGLES.MyGLSurfaceView;

public class GeometryActivity extends FragmentActivity implements GeometryListFragment.OnImageItemSelectedListener {

    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }

    @Override
    public void onImageItemSelected(int position) {

    }
}
