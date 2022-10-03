package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class InAppclass extends AppCompatActivity {

    private TextView tvMsg1 ,tvMsg2 ,tvMsg3 ,tvMsg4;
    private ImageView ivGlowing;
    private MediaPlayer mSound = null;
    private ImageView btnInAppclass;
    private int count=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_appclass);

        getSupportActionBar().setTitle("In Application classroom");

        mSound = MediaPlayer.create(InAppclass.this,Const.soundTrack.get("in appclass"));

        mSound.start();

        tvMsg1 = findViewById(R.id.tv_msg_1);
        tvMsg2 = findViewById(R.id.tv_msg_2);
        tvMsg3 = findViewById(R.id.tv_msg_3);
        tvMsg4 = findViewById(R.id.tv_msg_4);
        ivGlowing = findViewById(R.id.iv_glowing);
        btnInAppclass = findViewById(R.id.btn_in_appclass);

        btnInAppclass.setOnClickListener(v ->{
            switch (count) {
                case 0: {
                    tvMsg2.setVisibility(View.VISIBLE);
                    count++;
                }
                break;

                case 1: {
                    tvMsg3.setVisibility(View.VISIBLE);
                    count++;
                }
                break;

                case 2:{
                    ivGlowing.setVisibility(View.VISIBLE);
                    count++;
                }
                break;


                case 3: {
                    tvMsg4.setVisibility(View.VISIBLE);
                    count++;
                }
                break;

                case 4: {
                    mSound.release();
                    Intent it = new Intent(InAppclass.this,GlowingScreen.class);
                    startActivity(it);
                }
                break;
            }
        });

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(mSound != null)
        {
            if(mSound.isPlaying())
                mSound.stop();

            mSound.release();

            mSound =null;
        }
    }
}