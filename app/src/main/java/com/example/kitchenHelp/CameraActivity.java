package com.example.kitchenHelp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback, Detector.Processor {

    private SurfaceView cameraView;
    private SurfaceView transparentView;
    private SurfaceHolder transparentHolder;
    private TextView textView;
    private CameraSource cameraSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

//        cameraView = findViewById(R.id.surface_view);
        textView = findViewById(R.id.textView);

        TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!recognizer.isOperational()) {
            Log.e("CameraActivity", "Detector dependencies are not yet available");
        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), recognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();

            cameraView.getHolder().addCallback(this);
            recognizer.setProcessor(this);
        }
//        transparentView = findViewById(R.id.transparent_view);
        transparentHolder = transparentView.getHolder();
        transparentHolder.setFormat(PixelFormat.TRANSPARENT);
        transparentHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                return;
            }
            cameraSource.start(cameraView.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        cameraSource.stop();
    }

    @Override
    public void release() {

    }

    private void drawRect(float left, float top, float right, float bottom, int color) {
        Canvas canvas = transparentHolder.lockCanvas();
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(3);
        canvas.drawRect(left, top, right, bottom, paint);

        transparentHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void receiveDetections(Detector.Detections detections) {
        SparseArray items = detections.getDetectedItems();
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            TextBlock item = (TextBlock) items.valueAt(i);
            stringBuilder.append(item.getValue());
            RectF rect = new RectF(item.getBoundingBox());
            rect = translateRect(rect);
            drawRect(rect.left, rect.top, rect.right, rect.bottom, Color.BLUE);
        }

        Log.v("strBuilder.toString", stringBuilder.toString());

        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(stringBuilder.toString());
            }
        });
    }

    /**
     * Returns a RectF in which the left and right parameters of the provided Rect are adjusted
     * by translateX, and the top and bottom are adjusted by translateY.
     */
    public RectF translateRect(RectF inputRect) {
        RectF returnRect = new RectF();

        returnRect.left = translateX(inputRect.left);
        returnRect.top = translateY(inputRect.top);
        returnRect.right = translateX(inputRect.right);
        returnRect.bottom = translateY(inputRect.bottom);

        return returnRect;
    }

    /**
     * Adjusts a horizontal value of the supplied value from the preview scale to the view
     * scale.
     */
    public float scaleX(float horizontal) {
        return horizontal * 1;
    }

    /**
     * Adjusts a vertical value of the supplied value from the preview scale to the view scale.
     */
    public float scaleY(float vertical) {
        return vertical * 1;
    }

    /**
     * Adjusts the x coordinate from the preview's coordinate system to the view coordinate
     * system.
     */
    public float translateX(float x) {
        return scaleX(x);
    }

    /**
     * Adjusts the y coordinate from the preview's coordinate system to the view coordinate
     * system.
     */
    public float translateY(float y) {
        return scaleY(y);
    }
}
