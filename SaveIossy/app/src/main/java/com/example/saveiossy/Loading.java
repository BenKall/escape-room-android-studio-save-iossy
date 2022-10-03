package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Loading extends AppCompatActivity {

    private Animation Animation;
    private RelativeLayout layout;

    private void MoveImage()
    {
        Animation = AnimationUtils.loadAnimation(Loading.this,R.anim.goup);
        layout.startAnimation(Animation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        getSupportActionBar().setTitle("Library's Computer");

        layout = findViewById(R.id.layoutfade);

        // Wait 2 seconds...
        new Thread(() -> {

            try
            {
                MoveImage();
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            startActivity(new Intent(Loading.this,EndScreen.class));
            finish();
        }).start();
    }
}