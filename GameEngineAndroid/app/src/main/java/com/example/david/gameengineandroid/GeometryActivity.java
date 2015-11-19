package com.example.david.gameengineandroid;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.david.gameengineandroid.OpenGLES.MyGLSurfaceView;
import com.example.david.gameengineandroid.Util.AssetUtil;

public class GeometryActivity extends FragmentActivity implements GeometryListFragment.OnImageItemSelectedListener {

    private MyGLSurfaceView mGLView;
    private static AssetUtil assetUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



       // mGLView = new MyGLSurfaceView(this);
        setContentView(R.layout.fragment_container);
        mGLView = (MyGLSurfaceView) findViewById(R.id.glSurfaceViewID);

        if (findViewById(R.id.container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            GeometryListFragment firstFragment = new GeometryListFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.container, firstFragment).commit();
        }
    }

    @Override
    public void onImageItemSelected(int position) {
        GeometryDrawFragment drawFragment = (GeometryDrawFragment) getSupportFragmentManager().
                findFragmentById(R.id.draw_fragment);
        if (findViewById(R.id.container) != null) {
            GeometryDrawFragment newFragment = new GeometryDrawFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            drawFragment.updateGeometry(position);
        }
    }

    public static AssetUtil getAssetUtil() {
        return assetUtil;
    }
}
