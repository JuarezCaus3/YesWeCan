package com.example.david.gameengineandroid.Shapes;

/**
 * Created by David on 19/11/2015.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLES20;
import android.util.Log;

import com.example.david.gameengineandroid.Util.ObjLoader;

public class ObjModel {

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;
    private ObjLoader loader;

    private int shaderProgram;

    public ObjModel() {

        try {
            loader = new ObjLoader("caixa.obj");
        } catch (IOException e) {

            e.printStackTrace();
        }

            vertexBuffer = ByteBuffer.allocateDirect(loader.vertecisA.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            vertexBuffer.put(loader.vertecisA).position(0);

            indexBuffer = ByteBuffer.allocateDirect(loader.indexesA.length * 2).order(ByteOrder.nativeOrder()).asShortBuffer();
            indexBuffer.put(loader.indexesA).position(0);


            int vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
            GLES20.glShaderSource(vertexShader, vertexCode);
            GLES20.glCompileShader(vertexShader);
            int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
            GLES20.glShaderSource(fragmentShader, fragmentCode);
            GLES20.glCompileShader(fragmentShader);

            shaderProgram = GLES20.glCreateProgram();
            GLES20.glAttachShader(shaderProgram, vertexShader);
            GLES20.glAttachShader(shaderProgram, fragmentShader);
            GLES20.glLinkProgram(shaderProgram);

            int[] linked = new int[1];
            GLES20.glGetProgramiv(shaderProgram, GLES20.GL_LINK_STATUS, linked, 0);
            if (linked[0] == 0){
                Log.d("DEBUG", "Shader code error.");
                Log.d("DEBUG", GLES20.glGetProgramInfoLog(shaderProgram));
                GLES20.glDeleteProgram(shaderProgram);
                return;
            }

            GLES20.glDeleteShader(vertexShader);
            GLES20.glDeleteShader(fragmentShader);
    }

    private String vertexCode = "" +
            "attribute vec3 a_position;  \n" +
            "uniform mat4 mvpMatrix;     \n" +
            "void main() {               \n" +
            "   gl_Position = mvpMatrix * vec4(a_position, 1.0f);\n" +
            "}                           \n";

    private String fragmentCode = "" +
            "precision mediump float;                   \n" +
            "void main() {                              \n" +
            "   gl_FragColor = vec4(0.0, 1.0, 0.0, 1.0);\n" +
            "}                                          \n";

    private int attribute_Position;
    private int uniform_mvpMatrix;

    public void draw(float[] mvpMatrix){
        GLES20.glUseProgram(shaderProgram);
        attribute_Position = GLES20.glGetAttribLocation(shaderProgram, "a_position");
        GLES20.glVertexAttribPointer(attribute_Position, 3, GLES20.GL_FLOAT, false, 4 * 4, vertexBuffer);
        GLES20.glEnableVertexAttribArray(attribute_Position);
        uniform_mvpMatrix = GLES20.glGetUniformLocation(shaderProgram, "mvpMatrix");
        GLES20.glUniformMatrix4fv(uniform_mvpMatrix, 1, false, mvpMatrix, 0);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indexBuffer.capacity(), GLES20.GL_UNSIGNED_SHORT, indexBuffer);
        GLES20.glDisableVertexAttribArray(attribute_Position);
    }
}
