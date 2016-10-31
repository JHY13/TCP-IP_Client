package com.example.administrator.socketclient.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.ImageView;

import com.example.administrator.socketclient.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016-10-26.
 */

public class UploadActivity extends Activity {

    public static final String imgURL = "http://192.168.0.10:8080/TCPServer/images/cam.jpg";
    private ProgressDialog pro;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);

        imageView = (ImageView) findViewById(R.id.imageView);

        Thread mThread = new Thread() {

            @Override
            public void run() {
                try {
                    URL url = new URL(imgURL);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);

                } catch (IOException e) {

                }
            }
        };
        mThread.start();
        try {
            mThread.join();

            imageView.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
        }
    }
}
