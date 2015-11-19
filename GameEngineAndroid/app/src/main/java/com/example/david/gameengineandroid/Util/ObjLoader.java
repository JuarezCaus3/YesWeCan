package com.example.david.gameengineandroid.Util;



import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by David on 19/11/2015.
 */
public class ObjLoader {

    //private AssetManager assets;

    public float[] vertecisA;
    public short[] indexesA;


    public ArrayList<Float> vertecis = new ArrayList<>();
    public ArrayList<Short> indexes = new ArrayList<>();

    public ObjLoader(String path) throws IOException {

        AssetManager assets = AssetUtil.getContext().getAssets();
        InputStream file = assets.open("caixa.obj");

        BufferedReader read = new BufferedReader(new InputStreamReader(file));

        String currentLine;

        while ((currentLine = read.readLine()) != null) {

            if (currentLine.startsWith("v")) {

                vertecis.add(Float.valueOf(currentLine.split(" ")[1]));
                vertecis.add(Float.valueOf(currentLine.split(" ")[2]));
                vertecis.add(Float.valueOf(currentLine.split(" ")[3]));

            }

            if (currentLine.startsWith("f")) {

                indexes.add(Short.valueOf(currentLine.split(" ")[1]));
                indexes.add(Short.valueOf(currentLine.split(" ")[2]));
                indexes.add(Short.valueOf(currentLine.split(" ")[3]));

            }

            vertecisA = new float[vertecis.size()];
            for (int i = 0; i < vertecis.size(); i++) {

                Float f = vertecis.get(i);
                vertecisA[i] = (f != null ? f : Float.NaN);
            }

        }

        indexesA = new short[indexes.size()];
        for (int i = 0; i < indexes.size(); i++) {

            Short s = indexes.get(i);
            indexesA[i] = (s != null ? s : 1);
        }

    }
}
