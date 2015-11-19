package com.example.david.gameengineandroid.OpenGLES;

import android.content.res.AssetManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.example.david.gameengineandroid.Shapes.ObjModel;
import com.example.david.gameengineandroid.Shapes.Square;
import com.example.david.gameengineandroid.Shapes.Triangle;

import java.util.Arrays;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by David on 18/11/2015.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;
    private Square mSquare;
    private ObjModel mModel;
    private int position;
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] rotationMatrix = new float[16];
    private float angle = 0f;
    private AssetManager assets;

    public MyGLRenderer(int position) {
        this.position = position;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LESS);
        // initialize a triangle
        mTriangle = new Triangle();
        // initialize a square
        mSquare = new Square();
        // initialize a obj model
        mModel = new ObjModel();
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);



        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        //Creating rotation matrix
        Matrix.setRotateM(rotationMatrix, 0, angle, 0f, 0f, -1f);

        //rotation x camera = modelView
        float[] duplicateMatrix = Arrays.copyOf(mMVPMatrix, 16);

        Matrix.multiplyMM(mMVPMatrix, 0, duplicateMatrix, 0, rotationMatrix, 0);

        Matrix.setRotateM(rotationMatrix, 0, angle, 0f, -1f, 0f);
        duplicateMatrix = Arrays.copyOf(mMVPMatrix, 16);
        Matrix.multiplyMM(mMVPMatrix, 0, duplicateMatrix, 0, rotationMatrix, 0);

        Matrix.setRotateM(rotationMatrix, 0, angle, -1f, 0f, 0f);
        duplicateMatrix = Arrays.copyOf(mMVPMatrix, 16);
        Matrix.multiplyMM(mMVPMatrix, 0, duplicateMatrix, 0, rotationMatrix, 0);

        if (position == 0) {
            mTriangle.draw(mMVPMatrix);
        }
        if (position == 1) {
            mSquare.draw(mMVPMatrix);
        }
        if (position == 2) {
            mModel.draw(mMVPMatrix);
        }

        angle += 0.7f;
        if (angle > 360f)
            angle = 0f;

    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / (float) height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
