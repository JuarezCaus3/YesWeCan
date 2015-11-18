package com.example.david.gameengineandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    private int REQUEST_ACTIVITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


    }


    public void onClickBtnGeometry(View view) {
        if (view.getId() == R.id.btnGeometryActivity) {
            Intent i = new Intent(this, GeometryActivity.class);
          // startActivityForResult(i, REQUEST_ACTIVITY);
        }
    }

}
